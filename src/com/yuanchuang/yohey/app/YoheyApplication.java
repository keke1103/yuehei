package com.yuanchuang.yohey.app;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.Tencent;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.chat.ChatMessageHandler;

import android.app.Application;
import android.util.Log;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

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
		BmobIM.init(this);
		BmobIM.registerDefaultMessageHandler(new ChatMessageHandler());
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

	/**
	 * 链接聊天服务器
	 */
	public void connectChatService() {
		User user = BmobUser.getCurrentUser(this, User.class);
		if (user == null) {
			Log.w("Application", "当前未有登陆用户");
			return;
		}
		BmobIM.connect(user.getObjectId(), new ConnectListener() {
			public void done(String uid, BmobException e) {
				if (e == null) {
					Log.i("MainActivity", uid + "connect success");
				} else {
					Log.e("MainActivity", uid + "connect success");
				}
			}
		});
	}
}
