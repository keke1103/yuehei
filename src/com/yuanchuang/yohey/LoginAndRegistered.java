package com.yuanchuang.yohey;

import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.myData.User;
import com.yuanchuang.yohey.tools.HttpPost;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;
import com.yuanchuang.yohey.tools.QQBaseUIListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
		String httpPost = YoheyApplication.ServiceIp + "/index.php/home/api/login";
		try {
			HttpPost post = HttpPost.parseUrl(httpPost);
			post.putString("username", userID);
			post.putString("password", userPassword);
			post.setOnSendListener(loginPostListener);// 监听事件
			post.send();// 发送数据
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	protected void onClickQQLogin() {
		if (application.mTencent == null || application.mTencent.isSessionValid()) {
			return;
		}
		application.mTencent.login(this, "all", loginListener);
		application.isServerSideLogin = false;
		Log.i("YoheyApplication", "do login");
		Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
	}

	/**
	 * 登陆回掉监听！
	 */
	public OnSendListener loginPostListener = new OnSendListener() {
		public void start() {

		}
		public void end(String result) {
			try {
				JSONObject jsonObject = new JSONObject(result);// 解析result这个json数据
				int status = jsonObject.getInt("status");// 获得登录是否成功的数字，1为成功，其他为失败
				Log.d("login>r", "" + result);
				if (status == 1) {
					Intent intent = new Intent(LoginAndRegistered.this, MainActivity.class);
					startActivity(intent);
					JSONObject jo = jsonObject.getJSONObject("result");
					application.token = jo.getString("token");
					application.mUser = User.parseJsonObject(jo.getJSONObject("user"));
					finish();
				} else {
					Toast.makeText(LoginAndRegistered.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	};

	public IUiListener loginListener = new BaseUiListener();

	public class BaseUiListener implements IUiListener {

		@Override
		public void onComplete(Object response) {
			if (null == response) {
				Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
				return;
			}
			JSONObject jsonResponse = (JSONObject) response;
			if (null != jsonResponse && jsonResponse.length() == 0) {
				Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
				return;
			}
			Toast.makeText(getApplicationContext(), "授权成功", Toast.LENGTH_SHORT).show();
			// 显示登陆成功后log日志登陆信息
			String rmsg = response.toString().replace(",", "\n");
			Log.d("Yohey", rmsg);

			// 有奖分享处理
			// handlePrizeShare();

			doComplete((JSONObject) response);
		}

		protected void doComplete(JSONObject values) {
			try {
				String token = values.getString(Constants.PARAM_ACCESS_TOKEN);
				String expires = values.getString(Constants.PARAM_EXPIRES_IN);
				String openId = values.getString(Constants.PARAM_OPEN_ID);

				if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires) && !TextUtils.isEmpty(openId)) {

					application.mTencent.setAccessToken(token, expires);
					application.mTencent.setOpenId(openId);
					application.qqInfo = new UserInfo(LoginAndRegistered.this, application.mTencent.getQQToken());
					application.qqInfo.getUserInfo(new QQBaseUIListener(LoginAndRegistered.this) {

						protected void doComplete(JSONObject jo) {
							String username;
							String icon;
							String sex;
							Log.i("UserInfo", jo.toString());
							try {
								username = jo.getString("nickname");
								icon = jo.getString("figureurl_qq_2");
								sex = jo.getString("gender");
								String url = YoheyApplication.ServiceIp + "/index.php/home/api/otherlogin";
								HttpPost post = HttpPost.parseUrl(url);
								post.putString("qid", application.mTencent.getOpenId());
								post.putString("username", username);
								post.putString("icon", icon);
								post.putString("sex", sex);
								post.setOnSendListener(loginPostListener);
								post.send();
							} catch (JSONException e) {
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
			} catch (Exception e) {
			}
		}

		@Override
		public void onError(UiError e) {
			Toast.makeText(getApplicationContext(), "onError: " + e.errorDetail, Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onCancel() {
			Toast.makeText(getApplicationContext(), "onCancel:  ", Toast.LENGTH_SHORT).show();
			if (application.isServerSideLogin) {
				application.isServerSideLogin = false;
			}
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("LoginActivity", "-->onActivityResult " + requestCode + " resultCode=" + resultCode);
		Log.i("LoginActivity", data.toString());

		if (requestCode == Constants.REQUEST_LOGIN || requestCode == Constants.REQUEST_APPBAR) {
			Log.i("LoginActivity", "is doing qq");
			Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
