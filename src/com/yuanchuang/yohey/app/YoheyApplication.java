package com.yuanchuang.yohey.app;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.Tencent;

import android.app.Application;
import cn.bmob.v3.Bmob;

public class YoheyApplication extends Application {

	// public static final String ServiceIp = "http://192.168.11.240";

	public static final String ServiceIp = "http://cloud.bmob.cn/a52fec72f31cc7c8/";

	public Tencent mTencent;
	public UserInfo qqInfo;
	public final String APP_ID = "1105254592";

	public boolean isServerSideLogin = false;

	@Override
	public void onCreate() {
		super.onCreate();
		String appId = "032e79773577386c1ae147ff379fb465";
		Bmob.initialize(getApplicationContext(), appId);
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
