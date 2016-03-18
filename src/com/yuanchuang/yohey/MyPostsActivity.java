package com.yuanchuang.yohey;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
/**
 * 我的帖子页面
 * @author Administrator
 *
 */
public class MyPostsActivity extends Activity {
	RelativeLayout include;// 导入标题栏
	LinearLayout toRetrn;// 我的帖子
	LinearLayout added;// 我加入的帖子
	LinearLayout postedMessages;// 我发的帖子

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

		@SuppressWarnings("deprecation")
		Drawable drawable = getResources().getDrawable(R.drawable.rub_course_back_icon);
		/// 这一步必须要做,否则不会显示.
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		text.setCompoundDrawablePadding(15);
		text.setCompoundDrawables(drawable, null, null, null);
		toRetrn.addView(text);
		toRetrn.setOnClickListener(clickListener);
		added.setOnClickListener(clickListener);
		postedMessages.setOnClickListener(clickListener);
		
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent;
			switch (v.getId()) {
			case R.id.title_navigation_view:
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
		include = (RelativeLayout) findViewById(R.id.my_posts_include_title);
		toRetrn = (LinearLayout) include.findViewById(R.id.title_navigation_view);
		added = (LinearLayout) findViewById(R.id.my_posts_linear_added);
		postedMessages = (LinearLayout) findViewById(R.id.my_posts_linear_message);
		toRetrn.setVisibility(View.VISIBLE);
		
	}
}
