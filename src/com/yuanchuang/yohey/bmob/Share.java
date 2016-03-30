package com.yuanchuang.yohey.bmob;

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
	private BmobFile[] images;

	private User[] wholike;// 点赞
	private Share forwarding;// 转发
	private int comCount;// 评论

	public Share getForwarding() {
		return forwarding;
	}

	public void setForwarding(Share forwarding) {
		this.forwarding = forwarding;
	}

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

	public BmobFile[] getImages() {
		return images;
	}

	public int getLikeNumber() {
		if (wholike == null)
			return 0;
		return wholike.length;
	}

	public void addLikeUser(User user) {
		User copy[] = wholike;
		boolean bl = false;
		if (copy != null) {
			for (User u : copy) {
				if (u == null)
					continue;
				if (u.getObjectId().equals(user.getObjectId())) {
					bl = true;
				}
			}
			if (bl)
				return;
			wholike = new User[(getLikeNumber() + 1)];
			System.arraycopy(copy, 0, wholike, 0, copy.length);
			wholike[wholike.length - 1] = user;
		} else {
			wholike = new User[1];
			wholike[0] = user;
		}
	}

	public boolean deleteLikeUser(User user) {
		if (user == null || wholike == null)
			return false;
		User copy[] = wholike;
		boolean bl = false;
		for (User u : copy) {
			if (u == null)
				continue;
			if (u.getObjectId().equals(user.getObjectId())) {
				bl = true;
			}
		}
		if (bl) {
			int i = 0;
			wholike = new User[copy.length - 1];
			for (User u : copy) {
				if (u == null)
					continue;
				if (!u.getObjectId().equals(user.getObjectId())) {
					wholike[i] = u;
					i++;
				}
			}
		} else {
			return false;
		}

		return true;
	}

	public User[] getLikeUser() {
		return wholike;
	}

	public void setImages(List<BmobFile> images) {
		this.images = images.toArray(new BmobFile[images.size()]);
	}

	public void setComCount(int i) {
		comCount = i;
	}

	public int getComCount() {
		return comCount;
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

		}
		try {
			s.setUpdatedAt(jo.getString("updatedAt"));
		} catch (JSONException e3) {

		}
		try {
			s.setContent(jo.getString("content"));
		} catch (JSONException e2) {

		}
		try {
			s.setUser(User.parseJsonObject(jo.getJSONObject("user")));
		} catch (JSONException e1) {
		}
		try {
			JSONArray ja = jo.getJSONArray("images");
			s.images = new BmobFile[ja.length()];
			JSONObject jmo;
			for (int i = 0; i < ja.length(); i++) {
				jmo = ja.getJSONObject(i);
				String url = jmo.getString("url");
				String fileName = jmo.getString("filename");
				String group = jmo.getString("group");
				BmobFile image = new BmobFile(fileName, group, url);
				s.images[i] = image;
			}

		} catch (JSONException e) {
		}
		try {
			JSONArray ja = jo.getJSONObject("wholike").getJSONArray("objects");
			s.wholike = new User[ja.length()];
			for (int i = 0; i < ja.length(); i++) {
				s.wholike[i] = User.parseJsonObject(ja.getJSONObject(i));
			}
		} catch (JSONException e) {

		}
		try {
			s.comCount = jo.getInt("comCount");
		} catch (JSONException e) {

		}
		try {
			s.forwarding = Share.parseJSONObject(jo.getJSONObject("forwarding"));
		} catch (JSONException e) {

		}
		return s;
	}

}
