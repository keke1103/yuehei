package com.yuanchuang.yohey.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.Tencent;
import com.yuanchuang.yohey.LoginAndRegistered;
import com.yuanchuang.yohey.bmob.Group;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.chat.ChatMessageHandler;
import com.yuanchuang.yohey.myData.MssageListData;
import com.yuanchuang.yohey.tools.HttpGet;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

public class YoheyApplication extends Application {
	public static final String ServiceIp = "http://cloud.bmob.cn/a52fec72f31cc7c8/";

	public Tencent mTencent;
	public UserInfo qqInfo;
	public final String APP_ID = "1105254592";
	/**
	 * 登陆用户的分组信息
	 */
	public ArrayList<Group> friendGroup = new ArrayList<Group>();

	Map<String, User> mFriend = new HashMap<String, User>();
	/**
	 * 用于好友分组是否获取结束
	 */
	public boolean isLoadingEnd = false;

	public ArrayList<MssageListData> msgList = new ArrayList<MssageListData>();

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
		getApplicationContext();
		SharedPreferences storage = getApplicationContext().getSharedPreferences("yohey", Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = storage.edit();
		edit.putString("qqData", "");
		edit.commit();
	}

	/**
	 * 根据ID获取好友分组里面的指定好友;如若好友里面没有，则到服务器请求获取该用户数据返回;如若还没有，返回null,务必做空指针判断
	 * 
	 * @param objectId
	 * @return
	 */
	public void getFriendById(final String objectId, final Handler mHandler) {
		Runnable mRun = new Runnable() {

			public void run() {
				User f = getFriendById(objectId);
				int count = 0;
				while (f == null && count < 10) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					f = getFriendById(objectId);
					count++;
				}
				if (f == null) {
					HttpGet get = new HttpGet(ServiceIp + "getobject");
					get.putString("table", "_User");
					get.putString("id", objectId);
					String str = get.sendInBack();
					try {
						f = User.parseJsonObject(new JSONObject(str));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

				Message msg = Message.obtain();
				msg.what = 101;
				msg.obj = f;
				mHandler.sendMessage(msg);
			}
		};
		mHandler.post(mRun);
	}

	/**
	 * 如若好友里面没有该好友则返回null,在主线程中。如若好友没加载玩,也会抛出null
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
			@Override
			public void done(String uid, BmobException e) {
				if (e == null) {
					Log.i("MainActivity", uid + "connect success");
				} else {
					Log.e("MainActivity", uid + "connect fail");
				}
			}
		});
	}

	public void addGroupLoadingEnd(OnGroupLoadingEndLitener mGroupLoadingEnd) {
		this.mGroupLoadingEnd.add(mGroupLoadingEnd);
	}

	ArrayList<OnGroupLoadingEndLitener> mGroupLoadingEnd = new ArrayList<YoheyApplication.OnGroupLoadingEndLitener>();

	@SuppressLint("HandlerLeak")
	public Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 100) {
				for (OnGroupLoadingEndLitener end : mGroupLoadingEnd) {
					if (end != null) {
						end.onLoadingEnd(YoheyApplication.this);
					}
				}
			}
		};
	};

	public interface OnGroupLoadingEndLitener {

		void onLoadingEnd(YoheyApplication app);
	}
}
