package com.yuanchuang.yohey.bmob;

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

	
}
