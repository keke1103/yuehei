package com.yuanchuang.yohey;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ThumbUpActivity extends Activity {
	LinearLayout toReturn;// 返回
	LinearLayout forward;// 转发
	LinearLayout comment;// 评论
	LinearLayout thumbUp;// 赞
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_thumb_up_for_details);
		findView();
	}

	private void findView() {
		toReturn = (LinearLayout) findViewById(R.id.thumb_up_linear_to_return);
		forward = (LinearLayout) findViewById(R.id.thumb_up_linear_forward);
		comment = (LinearLayout) findViewById(R.id.thumb_up_linear_comment);
		thumbUp = (LinearLayout) findViewById(R.id.thumb_up_linear_thumb_up);

		toReturn.setOnClickListener(clickListener);
		forward.setOnClickListener(clickListener);
		comment.setOnClickListener(clickListener);
		thumbUp.setOnClickListener(clickListener);
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
