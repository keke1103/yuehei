package com.yuanchuang.yohey.myData;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	int id;//用户的ID
	String qid;//帐号
	String username;//帐号
	String icon;//头像地址
	User [] friends;//用户的好友
	long registertime;//注册时间或者是第一次用三方登录的时间
	String [] checkday;//签到时间
	int ntegral;//签到积分
	int tasknte ;//任务积分
	int likente;//好评积分
	int bonuspoints;//消费积分
	int likecount;//我的点赞数
	int likednumber;//我的被点赞数
	int defgid;//默认区服游戏的ID

	public static User parseJsonObject(JSONObject jo){
		User u=new User();
		try {
			u.id=jo.getInt("id");
		} catch (JSONException e) {
			return null;
		}
		try {
			u.qid=jo.getString("qid");
		} catch (JSONException e10) {
			 
		}
		try {
			u.username=jo.getString("username");
		} catch (JSONException e9) {
			 
		}
		try {
			u.icon=jo.getString("icon");
		} catch (JSONException e8) {
			 
		}
		try {
			u.registertime=jo.getLong("registertime");
		} catch (JSONException e7) {
			 
		}
		try {
			u.ntegral=jo.getInt("ntegral");
		} catch (JSONException e6) {
			 
		}
		try {
			u.tasknte=jo.getInt("tasknte");
		} catch (JSONException e5) {
			 
		}
		try {
			u.likente=jo.getInt("likente");
		} catch (JSONException e4) {
			 
		}
		try {
			u.bonuspoints=jo.getInt("bonuspoints");
		} catch (JSONException e3) {
			 
		}
		try {
			u.likecount=jo.getInt("likecount");
		} catch (JSONException e2) {
			 
		}
		try {
			u.likednumber=jo.getInt("likenumber");
		} catch (JSONException e1) {
			 
		}
		try {
			u.defgid=jo.getInt("defgid");
		} catch (JSONException e) {
			 
		}
		return u;
	}
	
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
	public int getDefgid() {
		return defgid;
	}
	public void setDefgid(int defgid) {
		this.defgid = defgid;
	}
}
