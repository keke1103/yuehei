package com.yuanchuang.yohey.bmob;

import com.yuanchuang.yohey.myData.Picture;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import com.yuanchuang.yohey.bmob.User;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

@SuppressWarnings("serial")
public class Post extends BmobObject {

	private String content;// 帖子内容
	private String title;
	private Picture[] images;
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

	public Picture[] getImages() {
		return images;
	}

	public void setImages(Picture[] images) {
		this.images = images;
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

}
