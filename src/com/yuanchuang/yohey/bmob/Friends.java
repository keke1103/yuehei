package com.yuanchuang.yohey.bmob;

import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;

import android.content.Context;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

@SuppressWarnings("serial")
public class Friends extends BmobObject {

	User user_a;
	User user_b;

	/**
	 * 添加好友！
	 * </p>
	 * 添加成功返回:{"updatedAt":"2016-03-27 17:39:47"}
	 * </p>
	 * 失败返回:"你们已经是好友"或"not your group";
	 * 
	 * @param context
	 * @param friend
	 * @param group
	 * @param sendListener
	 *            网络回掉监听
	 */
	public void addFriend(Context context, User friend, FriendGroup group, OnSendListener sendListener) {

		user_a = BmobUser.getCurrentUser(context, User.class);
		HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "addFriends");
		get.putString("uid", user_a.getObjectId());
		get.putString("fid", friend.getObjectId());
		get.putString("groupId", group.getObjectId());
		get.setOnSendListener(sendListener);
		get.send();
	}

	public static Friends parseJSONObject(JSONObject jo) {
		Friends fs = null;
		try {
			String id = jo.getString("objectId");
			fs = new Friends();
			fs.setObjectId(id);
		} catch (JSONException e) {
			return null;
		}
		try {
			fs.setCreatedAt(jo.getString("createAt"));
		} catch (JSONException e3) {

		}
		try {
			fs.setUpdatedAt(jo.getString("updateAt"));
		} catch (JSONException e2) {

		}
		try {
			fs.user_a = User.parseJsonObject(jo.getJSONObject("user_a"));
		} catch (JSONException e1) {

		}
		try {
			fs.user_b = User.parseJsonObject(jo.getJSONObject("user_b"));
		} catch (JSONException e) {

		}
		return fs;
	}
}
