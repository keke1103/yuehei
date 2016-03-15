package com.yuanchuang.yohey.app;

import com.yuanchuang.yohey.myData.User;

import android.app.Application;

public class YoheyApplication extends Application {
	/**
	 * 当前登陆用户！
	 */
	public User mUser;
	public String token;
}
