package com.yuanchuang.yohey;

import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.tools.HttpPost;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
	String userID;//游戏ID
    String userPassword;//游戏密码
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
				userID=account.getText().toString();
				userPassword=password.getText().toString();
				loginService(userID,userPassword);
				//intent = new Intent(LoginAndRegistered.this, MainActivity.class);
				//startActivity(intent);
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
	/**
	 * 向服务器传送数据，验证登录是否成功
	 * @param userID 用户登录的ID
	 * @param userPassword 用户登录的密码
	 */
	public void loginService(String userID,String userPassword){
		String httpPost="http://192.168.11.240/index.php/home/api/login";
		try {
			HttpPost post=HttpPost.parseUrl(httpPost);
			post.putString("username",userID);
			post.putString("password",userPassword);
			post.setOnSendListener(listener);//监听事件
			post.send();//发送数据
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	/**
	 * 验证是否上传数据成功
	 */
	OnSendListener listener=new OnSendListener() {
		
		@Override
		public void start() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void end(String result) {
			// TODO Auto-generated method stub
			Log.i(">>>>>>>>>>>>>>>",result);
			try {
				JSONObject jsonObject=new JSONObject(result);//解析result这个json数据
				int status=jsonObject.getInt("status");//获得登录是否成功的数字，1为成功，其他为失败
				Log.i(">>>>>>>>>>>>>>>",""+status);
				if(status==1){
					Intent intent = new Intent(LoginAndRegistered.this, MainActivity.class);
					startActivity(intent);
				}else{
					Toast.makeText(getApplication(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};	
}
