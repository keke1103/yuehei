package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.List;

import com.yuanchuang.yohey.adapter.ThumbUpAdapter;
import com.yuanchuang.yohey.bmob.Comment;
import com.yuanchuang.yohey.tools.DensityUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsoluteLayout;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 点赞页面
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("deprecation")
public class ThumbUpActivity extends Activity {
	LinearLayout toReturn;// 返回
	LinearLayout forward;// 转发
	LinearLayout comment;// 评论
	LinearLayout thumbUp;// 赞
	ListView listView;
	List<Comment> list = new ArrayList<Comment>();
	ThumbUpAdapter adapter;
	View headView;// listview头视图
	ImageView head;// 发帖用户头像
	TextView time;// 发帖时间
	TextView name;// 发帖用户
	TextView content;// 贴子文字内容

	AbsoluteLayout image;// 帖子图片
	TextView forwarding;// 转发数量
	TextView comments;// 评论数量
	CheckBox thumbNumber;// 赞
	Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_thumb_up_for_details);
		mIntent = getIntent();
		findView();
		fingHeadView();
		getData();
	}

	private void getData() {

	}

	@SuppressLint("InflateParams")
	private void findView() {
		toReturn = (LinearLayout) findViewById(R.id.thumb_up_linear_to_return);
		forward = (LinearLayout) findViewById(R.id.thumb_up_linear_forward);
		comment = (LinearLayout) findViewById(R.id.thumb_up_linear_comment);
		thumbUp = (LinearLayout) findViewById(R.id.thumb_up_linear_thumb_up);
		listView = (ListView) findViewById(R.id.thumb_up_list_view);

		toReturn.setOnClickListener(clickListener);
		forward.setOnClickListener(clickListener);
		comment.setOnClickListener(clickListener);
		thumbUp.setOnClickListener(clickListener);
		LayoutInflater inflater = getLayoutInflater();
		headView = inflater.inflate(R.layout.list_head_thumb_up_for_details, null);

		listView.addHeaderView(headView);
		adapter = new ThumbUpAdapter(list, getApplication(), ThumbUpActivity.this);
		listView.setAdapter(adapter);
	}

	private void fingHeadView() {

		head = (ImageView) headView.findViewById(R.id.list_head_thumb_up_image_head);
		time = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_time);
		name = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_name);
		content = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_content);
		image = (AbsoluteLayout) headView.findViewById(R.id.list_head_thumb_up_abso_content);
		forwarding = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_forwarding);
		comments = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_comments);
		thumbNumber = (CheckBox) headView.findViewById(R.id.list_head_thumb_up_linear_tuumb_up);
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

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.thumb_up_linear_to_return:
				finish();
				break;

			case R.id.thumb_up_linear_forward:

				break;
			case R.id.thumb_up_linear_comment:
				mIntent.setClass(ThumbUpActivity.this, CommentDynamicActivity.class);
				startActivity(mIntent);
				break;
			case R.id.thumb_up_linear_thumb_up:
				Toast.makeText(getApplicationContext(), "就在点赞页面", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	};
}
