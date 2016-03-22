package com.yuanchuang.yohey.bmob;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.BmobObject;

@SuppressWarnings("serial")
public class Post extends BmobObject {

	private String content;// 帖子内容
	private String title;
 
	private int likenumber;
	private int comcount;

	private int joincount;
	private int recommend;
	private User user;
	private Game game;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLikenumber() {
		return likenumber;
	}

	public void setLikenumber(int likenumber) {
		this.likenumber = likenumber;
	}

	public int getComcount() {
		return comcount;
	}

	public void setComcount(int comcount) {
		this.comcount = comcount;
	}

	public int getJoincount() {
		return joincount;
	}

	public void setJoincount(int joincount) {
		this.joincount = joincount;
	}

	public int getRecommend() {
		return recommend;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public static Post paresJSONObject(JSONObject jo) {
		Post p = null;
		try {
			p = new Post();
			p.setObjectId(jo.getString("objectId"));  
		} catch (JSONException e) {
			return null;
		}
		try {
			p.joincount = jo.getInt("joincount");
		} catch (JSONException e6) {

		}
		try {
			p.setCreatedAt(jo.getString("createAt")); 
		} catch (JSONException e6) {

		}
		try {
			p.setUpdatedAt(jo.getString("updateAt"));
		} catch (JSONException e6) {

		}
		try {
			p.setContent(jo.getString("content"));
		} catch (JSONException e5) {

		}
		try {
			p.setTitle(jo.getString("title"));
		} catch (JSONException e4) {

		}
		try {
			p.setComcount(jo.getInt("comcount"));
		} catch (JSONException e3) {

		}
		try {
			p.setLikenumber(jo.getInt("likenumber"));
		} catch (JSONException e2) {

		}
		try {
			p.user = User.parseJsonObject(jo.getJSONObject("user"));
		} catch (JSONException e1) {

		}
		try {
			p.game = Game.paresJSONObejct(jo.getJSONObject("game"));
		} catch (JSONException e) {

		}
		return p;
	}
}
