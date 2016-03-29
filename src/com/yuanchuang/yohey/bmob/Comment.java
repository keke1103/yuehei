package com.yuanchuang.yohey.bmob;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.BmobObject;

@SuppressWarnings("serial")
public class Comment extends BmobObject {
	private Post post;
	private User user;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			comment.setContent(jo.getString("content"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			comment.setCreatedAt(jo.getString("createdAt"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			comment.setTableName(jo.getString("tablename"));
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			comment.setUpdatedAt(jo.getString("updatedAt"));
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			comment.post = Post.paresJSONObject(jo.getJSONObject("post"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			comment.user = User.parseJsonObject(jo.getJSONObject("user"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comment;
	}
}
