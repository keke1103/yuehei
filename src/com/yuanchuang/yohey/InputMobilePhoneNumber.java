package com.yuanchuang.yohey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 输入手机号页面
 * 
 * @author Administrator
 *
 */
public class InputMobilePhoneNumber extends Activity {
	RelativeLayout include;// 导入头文件
	EditText phongNumber;// 输入手机号
	Button next;// 下一步事件
	CheckBox read;// 隐私政策是否勾选
	View toReturn;// 返回
	TextView title;// 标题

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_registered_mobile_number);
		findView();
		toReturn.setOnClickListener(onClickListener);
		next.setOnClickListener(onClickListener);
		title.setText(R.string.enter_your_phone_number);
	}

	OnClickListener onClickListener = new OnClickListener() {
		Intent intent;

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.mobile_number_button_next:
				intent = new Intent(InputMobilePhoneNumber.this, SecurityVerification.class);
				startActivity(intent);
				break;
			case R.id.title_navigation_back_icon:
				finish();
			default:
				break;
			}
		}
	};

	private void findView() {
		include = (RelativeLayout) findViewById(R.id.mobile_number_title_bar);
		phongNumber = (EditText) findViewById(R.id.mobile_number_edit_phone_number);
		next = (Button) findViewById(R.id.mobile_number_button_next);
		read = (CheckBox) findViewById(R.id.mobile_number_chack);
		toReturn = include.findViewById(R.id.title_navigation_back_icon);
		title = (TextView) include.findViewById(R.id.title_navigation_text_title);

	}
}
