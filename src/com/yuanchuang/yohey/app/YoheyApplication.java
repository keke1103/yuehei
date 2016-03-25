package com.yuanchuang.yohey.app;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.Tencent;
import com.yuanchuang.yohey.LoginAndRegistered;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

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

	public void loginOutQq(Activity activity) {
		if (mTencent != null && mTencent.isSessionValid()) {
			mTencent.logout(getApplicationContext());
		}
		BmobUser.logOut(getApplicationContext());
		Intent intent = new Intent(activity, LoginAndRegistered.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		activity.startActivity(intent);
		@SuppressWarnings("static-access")
		SharedPreferences storage = getApplicationContext().getSharedPreferences("yohey",
				getApplicationContext().MODE_PRIVATE);
		SharedPreferences.Editor edit = storage.edit();
		edit.putString("qqData", "");
		edit.commit();
	}

}
