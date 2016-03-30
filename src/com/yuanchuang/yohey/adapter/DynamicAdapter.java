package com.yuanchuang.yohey.adapter;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.CommentDynamicActivity;
import com.yuanchuang.yohey.ForwardingActivity;
import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.ViewFilperActivity;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.Share;
import com.yuanchuang.yohey.tools.DensityUtil;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;
import com.yuanchuang.yohey.tools.TimeUtil;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
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
	Context context;
	LayoutInflater inflater;
	ImageView image;
	TextView tt;
	YoheyApplication app;

	public DynamicAdapter() {

	}

	public DynamicAdapter(Context context, Application app, List<Share> list) {
		this.context = context;
		this.app = (YoheyApplication) app;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	public void setData(List<Share> list) {
		this.list = list;
		notifyDataSetChanged();
	}

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
		Log.i("position", position + "");
		Share mShare = list.get(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_dynamic, null);
			holder = new ViewHolder(convertView);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.setData(mShare);
		return convertView;
	}

	Intent intent;

	class ViewHolder {
		ImageView headPortrait;// 用户头像
		TextView nickNmae;// 用户昵称
		TextView time;// 发送时间
		TextView line;// 输入内容
		View forwarding;// 转发
		View leaveMessage;// 留言
		CheckBox thumbUp;// 点赞RelativeLayout relative;// 整体布局

		TextView commentCount;// 评论数量
		AbsoluteLayout imageLayout;

		Share mShare;

		public ViewHolder(View convertView) {
			headPortrait = (ImageView) convertView.findViewById(R.id.list_dynamic_image_head_portrait);
			nickNmae = (TextView) convertView.findViewById(R.id.list_dynamic_text_nickname);
			time = (TextView) convertView.findViewById(R.id.list_dynamic_text_time);
			line = (TextView) convertView.findViewById(R.id.list_dynamic_layout_context);
			forwarding = convertView.findViewById(R.id.list_dynamic_image_share_it);
			leaveMessage = convertView.findViewById(R.id.list_dynamic_image_leave_a_message);
			thumbUp = (CheckBox) convertView.findViewById(R.id.list_dynamic_image_like);
			commentCount = (TextView) convertView.findViewById(R.id.dynamic_comment_count);
			imageLayout = (AbsoluteLayout) convertView.findViewById(R.id.list_dynamic_absolute_image);
			forwarding.setOnClickListener(clickListener);
			thumbUp.setOnClickListener(clickListener);
			leaveMessage.setOnClickListener(clickListener);
		}

		void setData(Share share) {
			mShare = share;
			loadData();
			setThumbUp(mShare.getObjectId());
		}

		void loadData() {
			nickNmae.setText(mShare.getUser().getNickName());
			mShare.getUser().binderImageView(headPortrait);
			time.setText(TimeUtil.formateTimeToNow(mShare.getCreatedAt()));
			line.setText(mShare.getContent());
			imageLayout.removeAllViews();
			imageLayout.setTag(mShare);
			thumbUp.setText("2");
			setThumbUp(mShare.getObjectId());
			if (mShare.getImages() != null) {
				DensityUtil.sudoku(context, imageLayout, mShare.getImages(), new OnClickListener() {
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
			}

		}

		void setThumbUp(String sid) {
			HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "islikeshare");
			get.putString("uid", BmobUser.getCurrentUser(context).getObjectId());
			get.putString("sid", sid);
			get.setOnSendListener(new OnSendListener() {
				public void start() {
				}

				public void end(String result) {
					thumbUp.setChecked("like".equals(result));
				}
			});
			get.send();
		}

		OnCheckedChangeListener like = new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {

			}
		};

		void likeShare() {
			HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "likeshare");
			get.putString("uid", BmobUser.getCurrentUser(context).getObjectId());
			get.putString("sid", mShare.getObjectId());
			get.setOnSendListener(new OnSendListener() {
				public void start() {
				}

				public void end(String result) {
					try {
						JSONObject jo = new JSONObject(result);
						jo.get("updatedAt");
					} catch (JSONException e) {
						thumbUp.setChecked(!thumbUp.isChecked());
					}
				}
			});
			get.send();
		}

		OnClickListener clickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.list_dynamic_image_share_it:
					intent = new Intent(context, ForwardingActivity.class);
					context.startActivity(intent);
					break;
				case R.id.list_dynamic_image_leave_a_message:
					intent = new Intent(context, CommentDynamicActivity.class);
					app.data = mShare;
					context.startActivity(intent);
					break;

				case R.id.list_dynamic_image_like:
					likeShare();
					break;
				default:
					break;
				}

			}
		};
	}
}
