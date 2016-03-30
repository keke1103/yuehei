package com.yuanchuang.yohey.bmob;

import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.HttpPost;

public class BmobFindById {
	String id;
	String table;
	public BmobFindById(String objectId,String table) {
		 this.id=objectId;
		 this.table=table;
	}
	
	public void start(HttpPost.OnSendListener mListner){
		HttpGet get=new HttpGet(YoheyApplication.ServiceIp+"getobject");
		get.putString("id", id);
		get.putString("table", table);
		get.setOnSendListener(mListner);
		get.send();
	}
}
