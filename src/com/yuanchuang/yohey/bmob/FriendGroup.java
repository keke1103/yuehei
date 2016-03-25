package com.yuanchuang.yohey.bmob;

import cn.bmob.v3.BmobObject;

@SuppressWarnings("serial")
public class FriendGroup extends BmobObject{
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
	private Friends [] friends;
}
