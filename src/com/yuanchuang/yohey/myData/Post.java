package com.yuanchuang.yohey.myData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Post {

	int id;// 帖子id
	String content;// 帖子内容
	String title;
	Picture[] images;
	int likenumber;
	int comcount;
	int createtime;
	int updatetime;
	int joincount;
	int recommend;
	int uid;

	public Game getGame() {
		return game;
	}

	User user;
	int gid;
	Game game;

	/**
	 * 获得帖子id;
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 获得帖子内容
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 获得帖子标题
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 获得帖子图片
	 * 
	 * @return
	 */
	public Picture[] getImages() {
		return images;
	}

	/**
	 * 获得帖子点赞数
	 * 
	 * @return
	 */
	public int getLikenumber() {
		return likenumber;
	}

	/**
	 * 获得帖子评论数
	 * 
	 * @return
	 */
	public int getComcount() {
		return comcount;
	}

	/**
	 * 获得帖子创建时间撮
	 * 
	 * @return
	 */
	public int getCreatetime() {
		return createtime;
	}

	/**
	 * 获得帖子修改时间戳
	 * 
	 * @return
	 */
	public int getUpdatetime() {
		return updatetime;
	}

	/**
	 * 获得推荐数，0表示未推荐
	 * 
	 * @return
	 */
	public int getRecommend() {
		return recommend;
	}

	/**
	 * 获得贴主
	 * 
	 * @return
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * 加入人数
	 * 
	 * @return
	 */
	public int getJoincount() {
		return joincount;
	}

	/**
	 * 获得贴主游戏id
	 * 
	 * @return
	 */
	public int getGid() {
		return gid;
	}

	public User getUser() {
		return user;
	}

	public String toJSONString() {

		return null;
	}

	public static Post paresJSONObject(JSONObject jo) {
		Post p = null;
		try {
			int id = jo.getInt("id");
			p = new Post();
			p.id = id;
		} catch (JSONException e) {
			return null;
		}
		try {
			p.uid = jo.getInt("uid");
		} catch (JSONException e7) {

		}
		try {
			p.gid = jo.getInt("gid");
		} catch (JSONException e6) {

		}
		try {
			p.joincount = jo.getInt("joincount");
		} catch (JSONException e6) {

		}
		try {
			p.createtime = jo.getInt("createtime");
		} catch (JSONException e6) {

		}
		try {
			p.updatetime = jo.getInt("updatetime");
		} catch (JSONException e6) {

		}
		JSONArray ja;
		try {
			ja = jo.getJSONArray("images");
			p.images = new Picture[ja.length()];
			for (int i = 0; i < p.images.length; i++) {
				Picture pic = new Picture(ja.getString(i));
				p.images[i] = pic;
			}
		} catch (JSONException e) {

		}

		try {
			p.content = jo.getString("content");
		} catch (JSONException e5) {

		}
		try {
			p.title = jo.getString("title");
		} catch (JSONException e4) {

		}
		try {
			p.comcount = jo.getInt("comcount");
		} catch (JSONException e3) {

		}
		try {
			p.likenumber = jo.getInt("likenumber");
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
