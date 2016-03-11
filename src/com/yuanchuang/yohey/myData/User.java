package com.yuanchuang.yohey.myData;

public class User {
	int id;//用户的ID
	String qid;//帐号
	String username;//帐号
	String icon;//头像地址
	User [] friends;//用户的好友
	long registertime;//注册时间或者是第一次用三方登录的时间
	int ntegral;//我的积分
	int likecount;//我的点赞数
	int likednumber;//我的被点赞数
	String defgid;//默认区服游戏的ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public User[] getFriends() {
		return friends;
	}
	public void setFriends(User[] friends) {
		this.friends = friends;
	}
	public long getRegistertime() {
		return registertime;
	}
	public void setRegistertime(long registertime) {
		this.registertime = registertime;
	}
	public int getNtegral() {
		return ntegral;
	}
	public void setNtegral(int ntegral) {
		this.ntegral = ntegral;
	}
	public int getLikecount() {
		return likecount;
	}
	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}
	public int getLikednumber() {
		return likednumber;
	}
	public void setLikednumber(int likednumber) {
		this.likednumber = likednumber;
	}
	public String getDefgid() {
		return defgid;
	}
	public void setDefgid(String defgid) {
		this.defgid = defgid;
	}
}
