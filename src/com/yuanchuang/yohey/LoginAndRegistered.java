package com.yuanchuang.yohey;

import java.net.MalformedURLException;

import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.tools.HttpPost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 登录注册页面
 * 
 * @author Administrator
 *
 */
public class LoginAndRegistered extends Activity {
	YoheyApplication application;

	EditText account;// 账号输入框
	EditText password;// 密码输入框
	TextView dorget_password;// 忘记密码
	Button login;// 登录按钮
	Button registered;// 注册按钮
	View qqLogin;// qq登录
	TextView webLogin;// 微博登录
	String userID;// 游戏ID
	String userPassword;// 游戏密码
	ImageView headicon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_login_registered);
		application = (YoheyApplication) getApplication();
		application.mTencent = Tencent.createInstance(application.APP_ID, this);
		findView();
		initView();

	}

	private void initView() {
		login.setOnClickListener(onClickListener);
		registered.setOnClickListener(onClickListener);
		qqLogin.setOnClickListener(onClickListener);
	}

	/**
	 * 点击事件
	 */
	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent;
			switch (v.getId()) {
			case R.id.login_registered_button_login:
				userID = account.getText().toString();
				userPassword = password.getText().toString();
				loginService(userID, userPassword);
				break;

			case R.id.login_registered_button_registered:
				intent = new Intent(LoginAndRegistered.this, InputMobilePhoneNumber.class);
				startActivity(intent);
				break;
			case R.id.login_register_text_qq_login:
				onClickQQLogin();
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
		dorget_password = (TextView) findViewById(R.id.login_register_text_forget);
		login = (Button) findViewById(R.id.login_registered_button_login);
		registered = (Button) findViewById(R.id.login_registered_button_registered);
		qqLogin = findViewById(R.id.login_register_text_qq_login);
		webLogin = (TextView) findViewById(R.id.login_register_text_web_login);
		headicon = (ImageView) findViewById(R.id.login_registered_image_head_portrait);
	}

	/**
	 * 向服务器传送数据，验证登录是否成功
	 * 
	 * @param userID
	 *            用户登录的ID
	 * @param userPassword
	 *            用户登录的密码
	 */
	public void loginService(String userID, String userPassword) {
		String httpPost = "http://192.168.11.240/index.php/home/api/login";
		try {
			HttpPost post = HttpPost.parseUrl(httpPost);
			post.putString("username", userID);
			post.putString("password", userPassword);
			post.setOnSendListener(application.loginPostListener);// 监听事件
			post.send();// 发送数据
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	protected void onClickQQLogin() {
		application.loginByQq(this);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("LoginActivity", "-->onActivityResult " + requestCode + " resultCode=" + resultCode);
		Log.i("LoginActivity", data.toString());

		if (requestCode == Constants.REQUEST_LOGIN || requestCode == Constants.REQUEST_APPBAR) {
			Log.i("LoginActivity", "is doing qq");
			Tencent.onActivityResultData(requestCode, resultCode, data, application.loginListener);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
