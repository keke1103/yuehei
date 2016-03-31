package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.adapter.ThumbUpAdapter;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.BmobFindObject;
import com.yuanchuang.yohey.bmob.Comment;
import com.yuanchuang.yohey.bmob.Share;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.tools.DensityUtil;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;
import com.yuanchuang.yohey.tools.TimeUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * 发帖详情
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("deprecation")
public class CommentDynamicActivity extends Activity {
	EditText say;// 说一句
	View send;// 发送
	TextView title;// 标题
	View toReturn;// 返回

	View ait;// 艾特符号
	View smile;// 表情
	View photos;// 图片
	ImageView head;// 发帖用户头像
	TextView time;// 发帖时间
	TextView name;// 发帖用户
	TextView content;// 贴子文字内容
	View headName;// 头像与昵称
	AbsoluteLayout image;// 帖子图片
	TextView forwarding;// 转发数量
	TextView comments;// 评论数量
	CheckBox thumbNumber;// 赞
	ListView listView;
	List<Comment> list = new ArrayList<Comment>();
	ThumbUpAdapter adapter;
	LayoutInflater inflater;
	View headView;

	Share mShare;

	LinearLayout linearComment;// 需要设置背景的
	TextView pingLun;// 评论的字

	TextView joinCount;
	TextView comCount;
	TextView likeCount;
	Intent intent;
	YoheyApplication app;

	User user;
	int resultCode;

	ViewHolder holder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_yue_lu_personal_post);
		try {
			user = BmobUser.getCurrentUser(getApplicationContext(), User.class);
			intent = getIntent();

			app = (YoheyApplication) getApplication();
			mShare = (Share) app.data;
			app.data = null;
			findView();
			findHeadView();
			adapter = new ThumbUpAdapter(list, getApplication(), CommentDynamicActivity.this);
			getData();
			listView.setDivider(getResources().getDrawable(R.color.post_line));
			listView.setDividerHeight(1);
			listView.addHeaderView(headView);

			listView.setAdapter(adapter);

			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					intent.setClass(getApplicationContext(), PersonalInformationActivity.class);
					app.data = mShare.getComCount();
					startActivity(intent);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
			finish();
		}
	}

	private void getData() {
		HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "getsharecom");
		get.putString("sid", mShare.getObjectId());
		get.setOnSendListener(mListener);
		get.send();
	}

	private OnSendListener mListener = new OnSendListener() {

		@Override
		public void start() {
		}

		@Override
		public void end(String result) {
			try {
				JSONObject mjo = new JSONObject(result);
				JSONArray ja = mjo.getJSONArray("results");
				list.clear();
				Comment comment;
				for (int i = 0; i < ja.length(); i++) {
					comment = Comment.comJsonObject(ja.getJSONObject(i));
					list.add(comment);
				}
				adapter.setData(list);
			} catch (JSONException e) {
				e.printStackTrace();
				Toast.makeText(getApplication(), "" + result, Toast.LENGTH_SHORT).show();
			}
		}
	};

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.personal_post_image_head:

				break;
			case R.id.title_navigation_back_icon:
				finish();
				break;
			case R.id.personal_post_text_send:
				sendCom();
				break;
			case R.id.personal_post_image_ait:
				Toast.makeText(getApplication(), "你在像谁", Toast.LENGTH_SHORT).show();
				break;
			case R.id.personal_post_image_smile:
				Toast.makeText(getApplication(), "不能发表情", Toast.LENGTH_SHORT).show();
				break;
			case R.id.personal_post_image_photos:
				Toast.makeText(getApplication(), "你没有图片", Toast.LENGTH_SHORT).show();
				break;
			case R.id.list_head_thumb_up_linear_tuumb_up:
				likeShare();
				break;
			case R.id.list_head_thumb_up_linear_head:
				intent.setClass(CommentDynamicActivity.this, PersonalInformationActivity.class);
				app.data = mShare.getUser();
				startActivity(intent);
				break;
			default:
				break;
			}

		}
	};

	private void findView() {
		title = (TextView) findViewById(R.id.title_navigation_text_title);
		toReturn = findViewById(R.id.title_navigation_back_icon);
		send = findViewById(R.id.personal_post_text_send);
		ait = findViewById(R.id.personal_post_image_ait);
		smile = findViewById(R.id.personal_post_image_smile);
		photos = findViewById(R.id.personal_post_image_photos);
		say = (EditText) findViewById(R.id.personal_post_edit_say);

		ait.setOnClickListener(onClickListener);
		smile.setOnClickListener(onClickListener);
		photos.setOnClickListener(onClickListener);
		send.setOnClickListener(onClickListener);
		toReturn.setOnClickListener(onClickListener);
		title.setText("留言");
		toReturn.setVisibility(View.VISIBLE);
		listView = (ListView) findViewById(R.id.personal_post_list_message);
	}

	@SuppressLint("InflateParams")
	private void findHeadView() {
		inflater = getLayoutInflater();
		headView = inflater.inflate(R.layout.list_head_thumb_up_for_details, null);
		head = (ImageView) headView.findViewById(R.id.list_head_thumb_up_image_head);
		time = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_time);
		name = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_name);
		content = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_content);
		image = (AbsoluteLayout) headView.findViewById(R.id.list_head_thumb_up_abso_content);
		forwarding = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_forwarding);
		comments = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_comments);
		thumbNumber = (CheckBox) headView.findViewById(R.id.list_head_thumb_up_linear_tuumb_up);
		linearComment = (LinearLayout) headView.findViewById(R.id.list_head_thumb_up_linear_comments);
		pingLun = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_view_comm);
		headName = headView.findViewById(R.id.list_head_thumb_up_linear_head);
		headName.setOnClickListener(onClickListener);
		comments.setTextColor(getResources().getColor(R.color.yellow_zan));
		pingLun.setTextColor(getResources().getColor(R.color.yellow_zan));
		thumbNumber.setTextColor(getResources().getColor(R.color.main_text));
		linearComment.setBackgroundResource(R.drawable.fill_the_gray_background);
		setData();

	}

	@SuppressLint("InflateParams")
	private void setData() {
		thumbNumber.setChecked(intent.getBooleanExtra("isLike", false));
		name.setText(mShare.getUser().getNickName());
		mShare.getUser().binderImageView(head);
		time.setText(TimeUtil.formateTimeToNow(mShare.getCreatedAt()));
		content.setText(mShare.getContent());
		image.removeAllViews();
		comments.setText("" + mShare.getComCount());
		thumbNumber.setText("" + mShare.getLikeNumber());
		thumbNumber.setOnClickListener(onClickListener);
		if (mShare.getForwarding() != null) {
			ViewHolder child = new ViewHolder(getLayoutInflater().inflate(R.layout.list_dynamic, null));
			image.addView(child.mView);
			child.setData(mShare.getForwarding());
		} else if (mShare.getImages() != null && mShare.getImages().length > 0) {
			DensityUtil.sudoku(CommentDynamicActivity.this, image, mShare.getImages(), new OnClickListener() {
				@Override
				public void onClick(View v) {
					int index = v.getId() - 1000;
					intent.setClass(CommentDynamicActivity.this, ViewFilperActivity.class);
					intent.putExtra("index", index);
					app.data = mShare.getImages();
					startActivity(intent);
				}
			});
		}
	};

	class ViewHolder {
		View mView;
		ImageView headPortrait;// 用户头像
		TextView nickNmae;// 用户昵称
		TextView time;// 发送时间
		TextView line;// 输入内容
		View forwarding;// 转发
		View leaveMessage;// 留言
		CheckBox thumbUp;// 点赞RelativeLayout relative;// 整体布局
		View headName;// 头像与昵称
		TextView commentCount;// 评论数量
		AbsoluteLayout imageLayout;

		Share mShare;

		ViewHolder(View child) {
			mView = child;
			headPortrait = (ImageView) child.findViewById(R.id.list_dynamic_image_head_portrait);
			nickNmae = (TextView) child.findViewById(R.id.list_dynamic_text_nickname);
			time = (TextView) child.findViewById(R.id.list_dynamic_text_time);
			line = (TextView) child.findViewById(R.id.list_dynamic_layout_context);
			forwarding = child.findViewById(R.id.list_dynamic_image_share_it);
			leaveMessage = child.findViewById(R.id.list_dynamic_image_leave_a_message);
			thumbUp = (CheckBox) child.findViewById(R.id.list_dynamic_image_like);
			commentCount = (TextView) child.findViewById(R.id.dynamic_comment_count);
			imageLayout = (AbsoluteLayout) child.findViewById(R.id.list_dynamic_absolute_image);
			headName = child.findViewById(R.id.list_dynamic_layout_head_portrait);
			headName.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});
		};

		@SuppressLint("InflateParams")
		private void setData(Share share) {
			mShare = share;
			mView.findViewById(R.id.list_dynamic_relative_linear).setVisibility(View.GONE);
			mView.findViewById(R.id.dynamic_gray_line).setVisibility(View.GONE);
			mView.setBackgroundColor(Color.LTGRAY);
			headPortrait.setVisibility(View.GONE);
			nickNmae.setText(mShare.getUser().getNickName() + ":");
			nickNmae.setText(nickNmae.getText() + ":");
			nickNmae.setTextColor(R.drawable.select_text_color_change);
			nickNmae.setPadding(DensityUtil.dip2px(getApplicationContext(), 5),
					DensityUtil.dip2px(getApplicationContext(), 5), DensityUtil.dip2px(getApplicationContext(), 5),
					DensityUtil.dip2px(getApplicationContext(), 5));
			line.setText(mShare.getContent());
			imageLayout.setTag(mShare);
			time.setVisibility(View.GONE);
			nickNmae.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					intent.setClass(getApplicationContext(), PersonalInformationActivity.class);
					app.data = mShare.getUser();
					startActivity(intent);
				}
			});
			if (mShare.getImages() != null) {
				DensityUtil.sudoku(CommentDynamicActivity.this, imageLayout, mShare.getImages(), new OnClickListener() {
					@Override
					public void onClick(View v) {
						int index = v.getId() - 1000;
						Share sh = (Share) ((View) v.getParent()).getTag();
						intent.setClass(CommentDynamicActivity.this, ViewFilperActivity.class);
						intent.putExtra("index", index);
						app.data = sh.getImages();
						startActivity(intent);
					}
				});
			}
		}
	}

	private void likeShare() {
		HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "likeshare");
		get.putString("uid", user.getObjectId());
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
					if (thumbNumber.isChecked()) {
						mShare.addLikeUser(user);
					} else {
						mShare.deleteLikeUser(user);
					}
					resultCode = 1;
				} catch (JSONException e) {
					thumbNumber.setChecked(!thumbNumber.isChecked());
				}
				thumbNumber.setText("" + mShare.getLikeNumber());
			}

		});
		get.send();

	}

	private void sendCom() {
		String content = say.getText().toString();
		if (TextUtils.isEmpty(content)) {
			Toast.makeText(getApplication(), "空的,为什么要发呢", Toast.LENGTH_SHORT).show();
			return;
		}
		Comment com = new Comment();
		com.setContent(content);
		com.setUser(user);
		com.setShare(mShare);
		com.save(this, new SaveListener() {

			@Override
			public void onSuccess() {
				Share s = new Share();
				s.increment("comCount");
				s.update(getApplicationContext(), mShare.getObjectId(), null);
				BmobFindObject finder = new BmobFindObject(mShare.getObjectId(), "Share");
				finder.start(new OnSendListener() {
					@Override
					public void start() {
					}

					@Override
					public void end(String result) {
						try {
							JSONObject jo = new JSONObject(result);
							int i = jo.getInt("comCount");
							comments.setText("" + i);
							mShare.setComCount(i);
						} catch (JSONException e) {

						}
					}
				});
				getData();
				resultCode = 1;
				say.setText("");
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(getApplication(), "莫怪我，你评论失败", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
