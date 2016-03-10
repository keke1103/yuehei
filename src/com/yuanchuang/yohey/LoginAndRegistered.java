package com.yuanchuang.yohey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 登录页面
 * 
 * @author Administrator
 *
 */
public class LoginAndRegistered extends Activity {
	EditText account;// 账号输入框
	EditText password;// 密码输入框
	CheckBox remeberPassword;// 记住密码勾选框
	TextView dorget_password;// 忘记密码
	Button login;// 登录按钮
	Button registered;// 注册按钮
	TextView qqLogin;// qq登录
	TextView webLogin;// 微博登录

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_login_registered);
		findView();
		login.setOnClickListener(onClickListener);
		registered.setOnClickListener(onClickListener);
	}

	/**
	 * 点击事件
	 */
	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent;
			switch (v.getId()) {
			case R.id.login_registered_button_login:
				intent = new Intent(LoginAndRegistered.this, MainActivity.class);
				startActivity(intent);
				break;

			case R.id.login_registered_button_registered:
				intent = new Intent(LoginAndRegistered.this, InputMobilePhoneNumber.class);
				startActivity(intent);
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
		account = (EditText) findViewById(R.id.login_registered_edit_account);
		password = (EditText) findViewById(R.id.login_registered_edit_password);
		remeberPassword = (CheckBox) findViewById(R.id.login_register_check_password);
		dorget_password = (TextView) findViewById(R.id.login_register_text_forget);
		login = (Button) findViewById(R.id.login_registered_button_login);
		registered = (Button) findViewById(R.id.login_registered_button_registered);
		qqLogin = (TextView) findViewById(R.id.login_register_text_qq_login);
		webLogin = (TextView) findViewById(R.id.login_register_text_web_login);

	}
}
