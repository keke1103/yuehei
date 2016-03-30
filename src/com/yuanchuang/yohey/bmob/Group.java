package com.yuanchuang.yohey.bmob;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.tools.HttpGet;

/**
 * 把好友按分组排列的工具包
 * 
 * @author kk0927
 *
 */
public class Group {
	String groupName;
	String objectId;
	User friends[];

	public String getGroupName() {
		return groupName;
	}

	public String getObjectId() {
		return objectId;
	}

	public User[] getFriends() {
		
		return friends;
	}
	public void addUser(User user,boolean index){
		User copy[] =friends;
		if(copy!=null){
			if(index){
				friends=new User[copy.length+1];
				friends[0]=user;
				System.arraycopy(copy, 0, friends, 1, copy.length);
			}else{
				friends=new User[copy.length+1];
				System.arraycopy(copy, 0, friends, 0, copy.length);
				friends[copy.length]=user;
			}
		}else {
			friends=new User[1];
			friends[0]=user;
		}
	} 
	/**
	 * 必须在支线程里面初始化
	 * 
	 * @param objectId
	 * @param groupName
	 * @param context
	 */
	public Group(final String objectId, String groupName, final User user) {
		this.objectId = objectId;
		this.groupName = groupName;

		HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "getgroupfriend");
		get.putString("uid", user.getObjectId());
		get.putString("groupId", objectId);
		String results = get.sendInBack();
		try {
			JSONArray ja = new JSONArray(results);
			friends = new User[ja.length()];
			for (int i = 0; i < ja.length(); i++) {
				friends[i] = User.parseJsonObject(ja.getJSONObject(i));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 此工具类必须在支线程解析
	 * 
	 * @param jo
	 * @param context
	 * @return
	 */
	public static Group parseJSONObject(JSONObject jo, final User user) {
		try {
			String objectId = jo.getString("objectId");
			String groupName = jo.getString("groupName");
			return new Group(objectId, groupName, user);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

	}
}
