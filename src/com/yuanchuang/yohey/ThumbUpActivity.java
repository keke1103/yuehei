package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuanchuang.yohey.adapter.ThumbUpAdapter;
import com.yuanchuang.yohey.tools.DensityUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

public class ThumbUpActivity extends Activity {
	LinearLayout toReturn;// 返回
	LinearLayout forward;// 转发
	LinearLayout comment;// 评论
	LinearLayout thumbUp;// 赞
	ListView listView;
	List<Map<String, Object>> list;
	ThumbUpAdapter adapter;
	View headView;// listview头视图
	ImageView head;// 发帖用户头像
	TextView time;// 发帖时间
	TextView name;// 发帖用户
	TextView content;// 贴子文字内容
	LinearLayout image;// 帖子图片
	TextView forwarding;// 转发数量
	TextView comments;// 评论数量
	TextView thumbNumber;// 赞

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_thumb_up_for_details);
		list = new ArrayList<Map<String, Object>>();
		findView();
		fingHeadView();
		getData();
	}

	private void getData() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < 3; i++) {
			map.put("head", R.drawable.meng_mei_head);
			map.put("name", "叶良成");
			list.add(map);
		}

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
		adapter = new ThumbUpAdapter(list, getApplication());
		listView.setAdapter(adapter);
	}

	private void fingHeadView() {

		head = (ImageView) headView.findViewById(R.id.list_head_thumb_up_image_head);
		time = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_time);
		name = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_name);
		content = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_content);
		image = (LinearLayout) headView.findViewById(R.id.list_head_thumb_up_linear_content);
		forwarding = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_forwarding);
		comments = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_comments);
		thumbNumber = (TextView) headView.findViewById(R.id.list_head_thumb_up_text_tuumb_up);
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

				break;
			case R.id.thumb_up_linear_thumb_up:

				break;
			default:
				break;
			}
		}
	};
}
