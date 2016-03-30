package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.adapter.ThumbUpAdapter;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.Share;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.tools.DensityUtil;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.TimeUtil;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsoluteLayout;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;

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

	AbsoluteLayout image;// 帖子图片
	TextView forwarding;// 转发数量
	TextView comments;// 评论数量
	CheckBox thumbNumber;// 赞
	ListView listView;
	List<Share> list;;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_yue_lu_personal_post);
		user = BmobUser.getCurrentUser(getApplicationContext(), User.class);
		intent = getIntent();
		list = new ArrayList<Share>();
		app = (YoheyApplication) getApplication();
		mShare = (Share) app.data;
		app.data = null;
		findView();
		findHeadView();
		getData();
		listView.setDivider(getResources().getDrawable(R.color.post_line));
		listView.setDividerHeight(1);
		listView.addHeaderView(headView);

		adapter = new ThumbUpAdapter(list, getApplication());
		listView.setAdapter(adapter);
		// setData();
	}

	private void getData() {

	}

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
				Toast.makeText(getApplication(), "为什么要发呢", Toast.LENGTH_SHORT).show();
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

		ait.setOnClickListener(onClickListener);
		smile.setOnClickListener(onClickListener);
		photos.setOnClickListener(onClickListener);
		send.setOnClickListener(onClickListener);
		toReturn.setOnClickListener(onClickListener);
		title.setText("详情");
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

		comments.setTextColor(getResources().getColor(R.color.yellow_zan));
		pingLun.setTextColor(getResources().getColor(R.color.yellow_zan));
		thumbNumber.setTextColor(getResources().getColor(R.color.main_text));
		linearComment.setBackgroundResource(R.drawable.fill_the_gray_background);

		thumbNumber.setChecked(intent.getBooleanExtra("isLike", false));
		name.setText(mShare.getUser().getNickName());
		mShare.getUser().binderImageView(head);
		time.setText(TimeUtil.formateTimeToNow(mShare.getCreatedAt()));
		content.setText(mShare.getContent());
		image.removeAllViews();

		if (mShare.getImages() != null) {
			DensityUtil.sudoku(this, image, mShare.getImages(), new OnClickListener() {
				public void onClick(View v) {
					int index = v.getId() - 1000;
					Share sh = (Share) ((View) v.getParent()).getTag();
					intent.setClass(CommentDynamicActivity.this, ViewFilperActivity.class);
					Log.i("DynamicAdapterImageClick", "id=" + v.getId() + " index=" + index);
					intent.putExtra("index", index);
					app.data = sh.getImages();
					startActivity(intent);
				}
			});
		}
		// forwarding.setText("3");
		comments.setText("3");
		thumbNumber.setText("3");
		thumbNumber.setOnClickListener(onClickListener);

	}

	void likeShare() {
		HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "likeshare");
		get.putString("uid", user.getObjectId());
		get.putString("sid", mShare.getObjectId());
		get.setOnSendListener(new OnSendListener() {
			public void start() {
			}

			public void end(String result) {
				try {
					JSONObject jo = new JSONObject(result);
					jo.get("updatedAt");
				} catch (JSONException e) {
					thumbNumber.setChecked(!thumbNumber.isChecked());
				}
			}
		});
		get.send();
	}
	/**
	 * 设置帖子内容的显示
	 */
	// private void setData() {
	// name.setText(post.getUser().getNickName());
	// post.getUser().binderImageView(head);
	// time.setText(TimeUtil.formateTimeToNow(post.getCreatedAt()));
	// joinCount.setText("" + post.getJoincount()); comCount.setText(""
	// + post.getComcount()); likeCount.setText("" +
	// post.getLikenumber()); if (!TextUtils.isEmpty(post.getTitle())) {
	// getContext(post.getTitle()); } else {
	//
	// } }

}
