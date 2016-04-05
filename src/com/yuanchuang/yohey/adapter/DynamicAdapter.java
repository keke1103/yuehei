package com.yuanchuang.yohey.adapter;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.CommentDynamicActivity;
import com.yuanchuang.yohey.ForwardingActivity;
import com.yuanchuang.yohey.PersonalInformationActivity;
import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.ViewFilperActivity;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.Post;
import com.yuanchuang.yohey.bmob.Share;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.tools.DensityUtil;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.OnFlushOldData;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;
import com.yuanchuang.yohey.tools.TimeUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bmob.v3.BmobUser;

/**
 * 动态BaseAdapter
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("deprecation")
public class DynamicAdapter extends BaseAdapter {
	List<Share> list;
	Activity context;
	LayoutInflater inflater;
	ImageView image;
	TextView tt;
	YoheyApplication app;

	public DynamicAdapter() {

	}

	public DynamicAdapter(Activity context, Application app, List<Share> list) {
		this.context = context;
		this.app = (YoheyApplication) app;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	public void setData(List<Share> list) {
		this.list = list;
		notifyDataSetChanged();
	}
	public List<Share> getData() {
		return list;
	}
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint({ "InflateParams", "ResourceAsColor" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		Share mShare = list.get(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_dynamic, null);
			holder = new ViewHolder(convertView);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.setData(mShare);
		if (((list.size() - position) < 2) && mFlush != null) {
			mFlush.flush(this, position);
		}
		return convertView;
	}
	
	
	OnFlushOldData mFlush;

	public void setOnFlushOldData(OnFlushOldData mFlush) {
		this.mFlush = mFlush;
	}

	Intent intent = new Intent();

	class ViewHolder {
		View mView;
		ImageView headPortrait;// 用户头像
		TextView nickNmae;// 用户昵称
		TextView time;// 发送时间
		TextView line;// 输入内容
		View forwarding;// 转发
		View leaveMessage;// 留言
		CheckBox thumbUp;// 点赞RelativeLayout relative;// 整体布局
		View headName;// 头像昵称的包裹
		TextView commentCount;// 评论数量
		AbsoluteLayout imageLayout;

		Share mShare;

		public ViewHolder(View convertView) {
			mView = convertView;
			headPortrait = (ImageView) convertView.findViewById(R.id.list_dynamic_image_head_portrait);
			nickNmae = (TextView) convertView.findViewById(R.id.list_dynamic_text_nickname);
			headName = convertView.findViewById(R.id.list_dynamic_layout_head_portrait);
			time = (TextView) convertView.findViewById(R.id.list_dynamic_text_time);
			line = (TextView) convertView.findViewById(R.id.list_dynamic_layout_context);
			forwarding = convertView.findViewById(R.id.list_dynamic_image_share_it);
			leaveMessage = convertView.findViewById(R.id.list_dynamic_image_leave_a_message);
			thumbUp = (CheckBox) convertView.findViewById(R.id.list_dynamic_image_like);
			commentCount = (TextView) convertView.findViewById(R.id.dynamic_comment_count);
			imageLayout = (AbsoluteLayout) convertView.findViewById(R.id.list_dynamic_absolute_image);

			headName.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					intent.setClass(context, PersonalInformationActivity.class);
					app.data = mShare.getUser();
					context.startActivity(intent);
				}
			});
			forwarding.setOnClickListener(clickListener);
			thumbUp.setOnClickListener(clickListener);
			leaveMessage.setOnClickListener(clickListener);
			mView.setOnClickListener(clickListener);
		}

		void setData(Share share) {
			mShare = share;
			loadData();
			setThumbUp(mShare.getObjectId());
		}

		@SuppressLint("InflateParams")
		void loadData() {
			nickNmae.setText(mShare.getUser().getNickName());
			mShare.getUser().binderImageView(headPortrait);
			time.setText(TimeUtil.formateTimeToNow(mShare.getCreatedAt()));
			line.setText(mShare.getContent());
			commentCount.setText("" + mShare.getComCount());
			imageLayout.removeAllViews();
			imageLayout.setTag(mShare);
			thumbUp.setText("" + mShare.getLikeNumber());
			setThumbUp(mShare.getObjectId());
			if (mShare.getImages() != null) {
				DensityUtil.sudoku(context, imageLayout, mShare.getImages(), new OnClickListener() {
					@Override
					public void onClick(View v) {
						int index = v.getId() - 1000;
						Share sh = (Share) ((View) v.getParent()).getTag();
						intent = new Intent(context, ViewFilperActivity.class);
						Log.i("DynamicAdapterImageClick", "id=" + v.getId() + " index=" + index);
						intent.putExtra("index", index);
						app.data = sh.getImages();
						context.startActivity(intent);
					}
				});
			} else if (mShare.getForwarding() != null) {
				View child = inflater.inflate(R.layout.list_dynamic, null);
				child.findViewById(R.id.list_dynamic_relative_linear).setVisibility(View.GONE);
				child.findViewById(R.id.dynamic_gray_line).setVisibility(View.GONE);
				child.setBackgroundColor(Color.LTGRAY);
				ViewHolder childHold = new ViewHolder(child);
				childHold.setData(mShare.getForwarding());
				childHold.headPortrait.setVisibility(View.GONE);
				childHold.nickNmae.setText(childHold.nickNmae.getText() + ":");
				childHold.nickNmae.setTextColor(R.drawable.select_text_color_change);
				childHold.nickNmae.setPadding(DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5),
						DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5));
				childHold.time.setVisibility(View.GONE);
				imageLayout.addView(child, -1, -1);
				childHold.nickNmae.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						intent.setClass(context, PersonalInformationActivity.class);
						app.data = mShare.getForwarding().getUser();
						context.startActivity(intent);
					}
				});

			}
		}

		void setThumbUp(String sid) {
			HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "islikeshare");
			get.putString("uid", BmobUser.getCurrentUser(context).getObjectId());
			get.putString("sid", sid);
			get.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {
				}

				@Override
				public void end(String result) {
					thumbUp.setChecked("like".equals(result));
				}
			});
			get.send();
		}

		OnCheckedChangeListener like = new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {

			}
		};

		void likeShare() {
			HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "likeshare");
			get.putString("uid", BmobUser.getCurrentUser(context).getObjectId());
			get.putString("sid", mShare.getObjectId());
			get.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {
				}

				@Override
				public void end(String result) {
					try {
						JSONObject jo = new JSONObject(result);
						jo.get("updatedAt");
						if (thumbUp.isChecked()) {
							mShare.addLikeUser(BmobUser.getCurrentUser(context, User.class));
						} else {
							mShare.deleteLikeUser(BmobUser.getCurrentUser(context, User.class));

						}
					} catch (JSONException e) {
						thumbUp.setChecked(!thumbUp.isChecked());
					}
					thumbUp.setText("" + mShare.getLikeNumber());
				}
			});
			get.send();
		}

		OnClickListener clickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.list_dynamic_image_share_it:
					intent.setClass(context, ForwardingActivity.class);
					app.data = mShare;
					context.startActivity(intent);
					break;
				case R.id.list_dynamic_image_like:
					likeShare();
					break;
				case R.id.list_dynamic_image_leave_a_message:

				default:
					intent.setClass(context, CommentDynamicActivity.class);
					app.data = mShare;
					intent.putExtra("isLike", thumbUp.isChecked());
					context.startActivityForResult(intent, 103);
					break;

				}

			}
		};
	}
}
