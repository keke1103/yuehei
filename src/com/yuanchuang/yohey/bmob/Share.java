package com.yuanchuang.yohey.bmob;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

@SuppressWarnings("serial")
public class Share extends BmobObject {
	private String content;
	private User user;
	private List<BmobFile> images;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<BmobFile> getImages() {
		return images;
	}

	public void setImages(List<BmobFile> images) {
		this.images = images;
	}

 
}
