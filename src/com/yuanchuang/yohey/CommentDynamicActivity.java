package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuanchuang.yohey.adapter.ThumbUpAdapter;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.Post;
import com.yuanchuang.yohey.tools.DensityUtil;
import com.yuanchuang.yohey.tools.TimeUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 发帖详情
 * 
 * @author Administrator
 *
 */
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
	LinearLayout image;// 帖子图片
	TextView forwarding;// 转发数量
	TextView comments;// 评论数量
	TextView thumbNumber;// 赞
	ListView listView;
	List<Map<String, Object>> list;
	ThumbUpAdapter adapter;
	LayoutInflater inflater;
	View headView;
	Intent mIntent;
	Post post;

	LinearLayout linearComment;// 需要设置背景的
	LinearLayout linearUp;// 需要设置背景的
	TextView zan;// 赞这个字
	TextView pingLun;// 评论的字

	TextView joinCount;
	TextView comCount;
	TextView likeCount;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_yue_lu_personal_post);
		mIntent = getIntent();
		list = new ArrayList<Map<String, Object>>();
		YoheyApplication app = (YoheyApplication) getApplication();
		post = (Post) app.data;
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
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < 3; i++) {
			map.put("head", R.drawable.meng_mei_head);
			map.put("name", "叶良成");
			list.add(map);
		}
	}

	/*
	 * private void getContext(String content) { TextView text = new
	 * TextView(this); LayoutParams params = new
	 * LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
	 * android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
	 * text.setLayoutParams(params); text.setText(content); image.addView(text);
	 * 
	 * }
	 */

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.personal_post_image_head:

				break;
			case R.id.title_navigation_back_icon:
				finish();
				break;
			case R.id.personal_post_image_send:
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
			default:
				break;
			}

		}
	};

	private void findView() {
		title = (TextView) findViewById(R.id.title_navigation_text_title);
		toReturn = findViewById(R.id.title_navigation_back_icon);
		send = findViewById(R.id.personal_post_image_send);
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

	@SuppressWarnings("deprecation")
	@SuppressLint("InflateParams")
	private void findHeadView() {
		inflater = getLayoutInflater();
		headView = inflater.inflate(R.layout.list_head_thumb_up_for_details, null);
		head = (ImageView) headView.findViewById(R.id.list_head_thumb_up_image_head);
		time = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_time);
		name = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_name);
		content = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_content);
		image = (LinearLayout) headView.findViewById(R.id.list_head_thumb_up_linear_content);
		forwarding = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_forwarding);
		comments = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_comments);
		thumbNumber = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_tuumb_up);
		linearComment = (LinearLayout) headView.findViewById(R.id.list_head_thumb_up_linear_comments);
		linearUp = (LinearLayout) headView.findViewById(R.id.list_head_thumb_up_linear_tuumb_up);
		zan = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_view_tuumb_up);
		pingLun = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_view_comm);

		comments.setTextColor(getResources().getColor(R.color.yellow_zan));
		pingLun.setTextColor(getResources().getColor(R.color.yellow_zan));
		zan.setTextColor(getResources().getColor(R.color.main_text));
		thumbNumber.setTextColor(getResources().getColor(R.color.main_text));
		linearComment.setBackgroundResource(R.drawable.fill_the_gray_background);
		linearUp.setBackgroundResource(R.color.post_background);
		head.setImageResource(R.drawable.meng_mei_head);
		time.setText("3分钟前");
		name.setText(R.string.zhao);
		content.setText("哈哈哈哈哈哈哈哈哈哈哈");
		ImageView view = new ImageView(this);
		LayoutParams params = new LayoutParams(DensityUtil.dip2px(getApplication(), 50),
				DensityUtil.dip2px(getApplication(), 50));
		view.setLayoutParams(params);
		view.setImageResource(R.drawable.big_picture_size);
		image.addView(view);
		forwarding.setText("3");
		comments.setText("3");
		thumbNumber.setText("3");

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
