package com.yuanchuang.yohey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SecurityVerification extends Activity {
	RelativeLayout include;//导入头文件
	View verify;//验证图片
	TextView another;//换一张 点击事件
	EditText code;//输入验证码
	Button submit;//提交按钮
	View toReturn;//返回按钮
	TextView title;//标题
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_safety_verification);
		findView();
		toReturn.setOnClickListener(onClickListener);
		title.setText(R.string.safety_verification);
		verify.setOnClickListener(onClickListener);
		another.setOnClickListener(onClickListener);
		submit.setOnClickListener(onClickListener);
	}
	OnClickListener onClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent;
			switch (v.getId()) {
			case R.id.title_navigation_back_icon:
				finish();
				break;
			case R.id.safety_verification_image_another_one:
				
				break;
				
			case R.id.safety_verificatio_text_another_one:
				
				break;
			case R.id.safety_verification_button_submit:
				
				break;
			default:
				break;
			}
		}
	};
	/**
	 * 查找ID
	 */
	private void findView() {
		include=(RelativeLayout) findViewById(R.id.sasafety_verification_title_bar);
		verify=findViewById(R.id.safety_verification_image_another_one);
		another=(TextView) findViewById(R.id.safety_verificatio_text_another_one);
		code=(EditText) findViewById(R.id.safety_verification_edit_cofirmation_code);
		submit=(Button) findViewById(R.id.safety_verification_button_submit);
		toReturn=include.findViewById(R.id.title_navigation_back_icon);
		title=(TextView) include.findViewById(R.id.title_navigation_text_title);
	}
}
