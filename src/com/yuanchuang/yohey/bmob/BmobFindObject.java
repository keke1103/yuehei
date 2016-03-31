package com.yuanchuang.yohey.bmob;

import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.HttpPost;

public class BmobFindObject {
	String id;
	String table;

	public BmobFindObject(String objectId, String table) {
		this.id = objectId;
		this.table = table;
	}

	HttpGet get;

	public void start(HttpPost.OnSendListener mListner) {
		get = new HttpGet(YoheyApplication.ServiceIp + "getobject");
		get.putString("id", id);
		get.putString("table", table);
		get.setOnSendListener(mListner);
		get.send();
	}

	/**
	 * 通过表里面user这个字段的id查询这个对象
	 * 
	 * @param mListner
	 */
	public void findObjectByUserId(HttpPost.OnSendListener mListner) {
		get = new HttpGet(YoheyApplication.ServiceIp + "getobjects");
		get.putString("table", table);
		get.putString("where", "user in(select * from _User where objectId='" + id + "'");
		get.setOnSendListener(mListner);
		get.send();
	}

	/**
	 * 通过表里面user这个字段的id查询这个对象,你可以include相应的表名; 如Post这张表，你可以include> new
	 * String[]{"user","game"};
	 * 
	 * @param mListner
	 */
	public void findObjectByUserId(String include[], HttpPost.OnSendListener mListner) {
		get = new HttpGet(YoheyApplication.ServiceIp + "getobjects");
		get.putString("table", table);
		get.putString("where", "user in(select * from _User where objectId='" + id + "'");
		if (include != null) {
			StringBuilder sb = new StringBuilder();
			for (String clu : include) {
				sb.append("include " + clu + ",");
			}
			get.putString("include", sb.toString());
		}
		get.setOnSendListener(mListner);

		get.send();
	}

	/**
	 * 根据userId查询Post表;
	 * </p>
	 * 此时你的表名无效
	 * </p>
	 * new BmobFindObject(userId,null);也就是表名可以传null,自动锁定Post表
	 * 
	 * @param mListner
	 */
	public void findPostByUserId(HttpPost.OnSendListener mListner) {
		this.table = "Post";
		findObjectByUserId(new String[] { "user", "game" }, mListner);
	}

	/**
	 * 这是个万能的查询接口，查询你所需要的
	 * 
	 * @param include
	 * @param where
	 * @param mListner
	 */
	public void findObjectByWhere(String include[], String where, HttpPost.OnSendListener mListner) {
		get = new HttpGet(YoheyApplication.ServiceIp + "getobjects");
		get.putString("table", table);
		get.putString("where", where);
		if (include != null) {
			StringBuilder sb = new StringBuilder();
			for (String clu : include) {
				sb.append("include " + clu + ",");
			}
			get.putString("include", sb.toString());
		}
		get.setOnSendListener(mListner);

		get.send();
	}
}
