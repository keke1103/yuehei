package com.yuanchuang.yohey.myData;

import org.json.JSONException;
import org.json.JSONObject;

public class PostComment {

	int id;
	int pid;
	int uid;
	String content;
	int time;
	User user;

	/**
	 * 发帖人的用户
	 * 
	 * @return
	 */
	public User getUser() {
		return user;
	}

	public int getId() {
		return id;
	}

	public int getPid() {
		return pid;
	}

	public int getUid() {
		return uid;
	}

	/**
	 * 获取帖子内容
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 获取发帖时间
	 * 
	 * @return
	 */
	public int getTime() {
		return time;
	}

	public static PostComment paresJSONObject(JSONObject jo) {
		PostComment p = null;
		try {
			int id = jo.getInt("id");
			p = new PostComment();
			p.id = id;
		} catch (JSONException e) {
			return null;
		}
		try {
			p.pid = jo.getInt("pid");
		} catch (JSONException e) {

		}
		try {
			p.uid = jo.getInt("uid");
		} catch (JSONException e) {

		}
		try {
			p.time = jo.getInt("time");
		} catch (JSONException e) {

		}
		try {
			p.content = jo.getString("content");
		} catch (JSONException e) {

		}
		try {
			p.user = User.parseJsonObject(jo.getJSONObject("user"));
		} catch (JSONException e) {

		}

		return p;

	}
}
