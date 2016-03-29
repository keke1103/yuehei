package com.yuanchuang.yohey;

import com.yuanchuang.yohey.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ForwardingActivity extends Activity {
	TextView toReturn;// 返回
	RelativeLayout send;// 发送
	EditText content;// 输入内容
	CheckBox comment;// 是否评论
	View ait;// 艾特
	View expression;// 表情
	View defoult;// 图片

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_forwarding);
		findView();
	}

	private void findView() {
		toReturn = (TextView) findViewById(R.id.forwarding_text_toReturn);
		send = (RelativeLayout) findViewById(R.id.forwarding_text_send);
		content = (EditText) findViewById(R.id.forwarding_edit_reason);
		comment = (CheckBox) findViewById(R.id.forwarding_check_box_comment);
		ait = findViewById(R.id.forwarding_view_ait);
		expression = findViewById(R.id.forwarding_view_expression);
		defoult = findViewById(R.id.forwarding_view_defoult);

		toReturn.setOnClickListener(onClickListener);
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.forwarding_text_toReturn:
				finish();
				break;
			case R.id.forwarding_text_send:

				break;
			case R.id.forwarding_view_ait:

				break;
			case R.id.forwarding_view_expression:

				break;
			case R.id.forwarding_view_defoult:

				break;
			default:
				break;
			}

		}
	};
}
