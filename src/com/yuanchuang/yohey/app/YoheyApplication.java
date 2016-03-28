package com.yuanchuang.yohey.app;

import java.util.HashMap;
import java.util.Map;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.Tencent;
import com.yuanchuang.yohey.LoginAndRegistered;
import com.yuanchuang.yohey.bmob.Group;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.chat.ChatMessageHandler;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

import android.util.Log;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.v3.exception.BmobException;

public class YoheyApplication extends Application {

	// public static final String ServiceIp = "http://192.168.11.240";

	public static final String ServiceIp = "http://cloud.bmob.cn/a52fec72f31cc7c8/";

	public Tencent mTencent;
	public UserInfo qqInfo;
	public final String APP_ID = "1105254592";
	/**
	 * 登陆用户的分组信息
	 */
	public Group[] friendGroup;

	Map<String, User> mFriend = new HashMap<String, User>();

	public boolean isServerSideLogin = false;

	@Override
	public void onCreate() {
		super.onCreate();
		String appId = "032e79773577386c1ae147ff379fb465";
		Bmob.initialize(getApplicationContext(), appId);
		BmobIM.init(this);
		BmobIM.registerDefaultMessageHandler(new ChatMessageHandler(getApplicationContext()));
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

	/**
	 * 根据ID获取好友分组里面的指定好友
	 * 
	 * @param objectId
	 * @return
	 */
	public User getFriendById(String objectId) {
		User u = mFriend.get(objectId);
		if (u == null) {
			for (Group g : friendGroup) {
				for (User us : g.getFriends()) {
					if (us.getObjectId().equals(objectId)) {
						mFriend.put(objectId, us);
						return us;
					}
				}
			}
			return null;
		} else {
			return u;
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
					Log.e("MainActivity", uid + "connect fail");
				}
			}
		});
	}
}
