package com.yuanchuang.yohey.app;

import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.yuanchuang.yohey.MainActivity;
import com.yuanchuang.yohey.myData.User;
import com.yuanchuang.yohey.tools.HttpPost;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;
import com.yuanchuang.yohey.tools.QQBaseUIListener;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class YoheyApplication extends Application {
	/**
	 * 当前登陆用户！
	 */
	public User mUser;
	public String token;
	public Tencent mTencent;
	public UserInfo qqInfo;
	public final String APP_ID = "1105254592";
	public Activity loginActivity;

	boolean isServerSideLogin = false;

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
				Log.d("login>status", "" + status);
				if (status == 1) {
					Intent intent = new Intent(loginActivity, MainActivity.class);
					loginActivity.startActivity(intent);
					JSONObject jo = jsonObject.getJSONObject("result");
					token = jo.getString("token");
					mUser = User.parseJsonObject(jo.getJSONObject("user"));
					loginActivity.finish();
				} else {
					Toast.makeText(loginActivity, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	};

	public void loginByQq(Activity coActivity) {
		if (mTencent == null || mTencent.isSessionValid()) {
			return;
		}
		loginActivity = coActivity;
		mTencent.login(coActivity, "all", loginListener);
		isServerSideLogin = false;
		Log.i("YoheyApplication", "do login");
		Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
	}

	public void loginOutQq() {
		if (mTencent != null && mTencent.isSessionValid()) {
			mTencent.logout(getApplicationContext());
		}
	}

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

					mTencent.setAccessToken(token, expires);
					mTencent.setOpenId(openId);
					qqInfo = new UserInfo(loginActivity, mTencent.getQQToken());
					qqInfo.getUserInfo(new QQBaseUIListener(loginActivity) {

						protected void doComplete(JSONObject jo) {
							String username;
							String icon;
							String sex;
							Log.i("UserInfo", jo.toString());
							try {
								username = jo.getString("nickname");
								icon = jo.getString("figureurl_qq_2");
								sex = jo.getString("gender");
								String url = "http://192.168.11.240/index.php/home/api/otherlogin";
								HttpPost post = HttpPost.parseUrl(url);
								post.putString("qid", mTencent.getOpenId());
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
			if (isServerSideLogin) {
				isServerSideLogin = false;
			}
		}
	}

}
