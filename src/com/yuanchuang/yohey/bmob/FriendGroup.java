package com.yuanchuang.yohey.bmob;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.BmobObject;

@SuppressWarnings("serial")
public class FriendGroup extends BmobObject {
	private String groupName;
	private User user;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Friends[] getFriends() {
		return friends;
	}

	public void setFriends(Friends[] friends) {
		this.friends = friends;
	}

	private Friends[] friends;

	public static FriendGroup parseJSONObject(JSONObject jo) {
		FriendGroup fg = null;
		try {
			String id = jo.getString("objectId");
			fg = new FriendGroup();
			fg.setObjectId(id);
		} catch (JSONException e) {
			return null;
		}
		try {
			fg.setCreatedAt(jo.getString("createdAt"));
		} catch (JSONException e4) {

		}
		try {
			fg.setUpdatedAt(jo.getString("updatedAt"));
		} catch (JSONException e3) {

		}
		try {
			fg.groupName = jo.getString("groupName");
		} catch (JSONException e2) {

		}
		try {
			fg.user = User.parseJsonObject(jo.getJSONObject("user"));
		} catch (JSONException e1) {

		}
		JSONArray ja;
		try {
			ja = jo.getJSONArray("friends");
			fg.friends = new Friends[ja.length()];
			for (int i = 0; i < ja.length(); i++) {
				fg.friends[i] = Friends.parseJSONObject(ja.getJSONObject(i));
			}
		} catch (JSONException e) {
		}
		return fg;
	}
}
