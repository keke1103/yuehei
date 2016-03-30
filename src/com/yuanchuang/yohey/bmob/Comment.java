package com.yuanchuang.yohey.bmob;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.BmobObject;

@SuppressWarnings("serial")
public class Comment extends BmobObject {
	private Post post;
	private User user;
	private Share share;
	private String content;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static Comment comJsonObject(JSONObject jo) {
		Comment comment = new Comment();
		try {
			comment.setObjectId(jo.getString("objectId"));
		} catch (JSONException e) {

		}
		try {
			comment.setContent(jo.getString("content"));
		} catch (JSONException e) {

		}
		try {
			comment.setCreatedAt(jo.getString("createdAt"));
		} catch (JSONException e) {

		}
		try {
			comment.setTableName(jo.getString("tablename"));
		} catch (JSONException e1) {

		}
		try {
			comment.setUpdatedAt(jo.getString("updatedAt"));
		} catch (JSONException e1) {

		}
		try {
			comment.post = Post.paresJSONObject(jo.getJSONObject("post"));
		} catch (JSONException e) {

		}
		try {
			comment.share = Share.parseJSONObject(jo.getJSONObject("share"));
		} catch (JSONException e1) {

		}
		try {
			comment.user = User.parseJsonObject(jo.getJSONObject("user"));
		} catch (JSONException e) {

		}
		return comment;
	}

	public Share getShare() {
		return share;
	}

	public void setShare(Share share) {
		this.share = share;
	}
}
