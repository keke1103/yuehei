package com.yuanchuang.yohey.app;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.Tencent;
import com.yuanchuang.yohey.myData.User;

import android.app.Application;

public class YoheyApplication extends Application {

	public static final String ServiceIp = "http://cloud.bmob.cn/a52fec72f31cc7c8/";
	
	/**
	 * 当前登陆用户！
	 */
	public User mUser;
	public String token;
	public Tencent mTencent;
	public UserInfo qqInfo;
	public final String APP_ID = "1105254592";

	public boolean isServerSideLogin = false;

	@Override
	public void onCreate() {
		super.onCreate();
		
	}

	/**
	 * 用于页面间的数据交换
	 */
	public Object data;

	public void loginOutQq() {
		if (mTencent != null && mTencent.isSessionValid()) {
			mTencent.logout(getApplicationContext());
		}
	}
}
