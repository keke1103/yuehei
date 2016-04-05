package com.yuanchuang.yohey.myData;

import com.yuanchuang.yohey.bmob.User;

public class AdapterData {

	
	User user;

	String editSortLetters; // 显示数据拼音的首字母

	boolean editbox;

	public String getEditSortLetters() {
		return editSortLetters;
	}

	public void setEditSortLetters(String editSortLetters) {
		this.editSortLetters = editSortLetters;
	}

	public boolean isEditbox() {
		return editbox;
	}

	public void setEditbox(boolean editbox) {
		this.editbox = editbox;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
