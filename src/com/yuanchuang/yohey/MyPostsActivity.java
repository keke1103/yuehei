package com.yuanchuang.yohey;

import com.yuanchuang.yohey.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * 我的帖子页面
 * 
 * @author Administrator
 *
 */
public class MyPostsActivity extends Activity {
	View toRetrn;// 我的帖子
	LinearLayout added;// 我加入的帖子
	LinearLayout postedMessages;// 我发的帖子
	TextView title;// 标题

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_my_posts);
		fingVew();
		LayoutParams params = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		TextView text = new TextView(this);
		text.setLayoutParams(params);
		text.setText(R.string.my_post);

		toRetrn.setOnClickListener(clickListener);
		added.setOnClickListener(clickListener);
		postedMessages.setOnClickListener(clickListener);

	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent;
			switch (v.getId()) {
			case R.id.title_navigation_back_icon:
				finish();
				break;

			case R.id.my_posts_linear_added:
				intent = new Intent(MyPostsActivity.this, ParticipationPostsActivity.class);
				startActivity(intent);
				break;

			case R.id.my_posts_linear_message:
				intent = new Intent(MyPostsActivity.this, PostedMessagesActivity.class);
				startActivity(intent);
				break;

			default:
				break;
			}

		}
	};

	private void fingVew() {

		toRetrn = (View) findViewById(R.id.title_navigation_back_icon);
		title = (TextView) findViewById(R.id.title_navigation_text_title);
		added = (LinearLayout) findViewById(R.id.my_posts_linear_added);
		postedMessages = (LinearLayout) findViewById(R.id.my_posts_linear_message);

		toRetrn.setVisibility(View.VISIBLE);
		title.setText("我的帖子");
	}
}
