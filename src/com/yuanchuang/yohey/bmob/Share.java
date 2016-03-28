package com.yuanchuang.yohey.bmob;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

	public static Share parseJSONObject(JSONObject jo) {
		Share s = null;
		try {
			String id = jo.getString("objectId");
			s = new Share();
			s.setObjectId(id);
		} catch (JSONException e) {
			return null;
		}
		// [{"__type":"File","filename":"timg.JPEG","group":"group1","url":"M03/02/43/oYYBAFb4_GmAfDn3AAAmT7tTFrc64.JPEG"}]
		try {
			s.setCreatedAt(jo.getString("createdAt"));
		} catch (JSONException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		try {
			s.setUpdatedAt(jo.getString("updatedAt"));
		} catch (JSONException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			s.setContent(jo.getString("content"));
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			s.setUser(User.parseJsonObject(jo.getJSONObject("user")));
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			JSONArray ja = jo.getJSONArray("images");
			s.images = new ArrayList<BmobFile>();
			JSONObject jmo;
			for (int i = 0; i < ja.length(); i++) {
				jmo = ja.getJSONObject(i);
				String fileName = jmo.getString("filename");
				String group = jmo.getString("group");
				String url = jmo.getString("url");
				BmobFile image = new BmobFile(fileName, group, url);
				s.images.add(image);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return s;
	}

}
