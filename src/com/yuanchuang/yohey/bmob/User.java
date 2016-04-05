package com.yuanchuang.yohey.bmob;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.myData.Picture;
import com.yuanchuang.yohey.tools.HttpGet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;

public class User extends BmobUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean sex;// 性别

	private String nickname;

	private String icon; // 头像地址
	private BmobFile header;// 头像
	private int ntegral;// 签到积分
	private int tasknte;// 任务积分
	private int likente;// 好评积分
	private int bonuspoints;// 消费积分
	private int likecount;// 我的点赞数
	private int likednumber;// 我的被点赞数
	private String mood;// 个性签名
	private int age;// 年龄
	private Game defGame;

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Game getDefGame() {
		return defGame;
	}

	public void setDefGame(Game defGame) {
		this.defGame = defGame;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 绑定用户头像的显示
	 * </p>
	 * 优先显示 header头像，若header为空，显示icon头像，若都为空，显示默认头像
	 * 
	 * @param v
	 */
	@SuppressLint("HandlerLeak")
	public void binderImageView(final ImageView v) {

		if (header == null && icon == null) {
			v.setImageResource(R.drawable.default_icon);
		} else if (header == null) {
			new Picture(icon).binderImageView(v);
		} else

		{
			header.loadImage(v.getContext(), v);
		}

	}

	/**
	 * 获取点赞积分;
	 * 
	 * @return
	 */
	public int getNtegral() {
		return ntegral;
	}

	/**
	 * 获取任务积分;
	 * 
	 * @return
	 */
	public int getTasknte() {
		return tasknte;
	}

	/**
	 * 获取好评积分;
	 * 
	 * @return
	 */
	public int getLikente() {
		return likente;
	}

	/**
	 * 获取消费积分;
	 * 
	 * @return
	 */
	public int getBonuspoints() {
		return bonuspoints;
	}

	public int getLikecount() {
		return likecount;
	}

	/**
	 * 被点赞数
	 * 
	 * @return
	 */
	public int getLikednumber() {
		return likednumber;
	}

	/**
	 * true 为女
	 * 
	 * @return
	 */
	public Boolean getSex() {
		return sex;
	}

	/**
	 * true -女 false 男 <br>
	 * 默认 男
	 * 
	 * @param sex
	 */
	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	/**
	 * 获取用户昵称
	 * 
	 * @return
	 */
	public String getNickName() {
		return nickname;
	}

	/**
	 * 设置用户昵称
	 * 
	 * @param nick
	 */
	public void setNickName(String nick) {
		this.nickname = nick;
	}

	/**
	 * 
	 * @param jo
	 * @return
	 */
	public static User parseJsonObject(JSONObject jo) {
		User u = new User();
		try {
			u.setObjectId(jo.getString("objectId"));
		} catch (JSONException e) {
			return null;
		}
		try {
			u.setUsername(jo.getString("username"));
		} catch (JSONException e9) {

		}
		try {
			u.setNickName(jo.getString("nickname"));
		} catch (JSONException e9) {

		}
		try {
			u.setIcon(jo.getString("icon"));

		} catch (JSONException e8) {

		}
		try {
			u.setCreatedAt(jo.getString("createdAt"));
		} catch (JSONException e7) {

		}
		try {
			u.ntegral = jo.getInt("ntegral");
		} catch (JSONException e6) {

		}
		try {
			u.tasknte = jo.getInt("tasknte");
		} catch (JSONException e5) {

		}
		try {
			u.likente = jo.getInt("likente");
		} catch (JSONException e4) {

		}
		try {
			u.bonuspoints = jo.getInt("bonuspoints");
		} catch (JSONException e3) {

		}
		try {
			u.likecount = jo.getInt("likecount");
		} catch (JSONException e2) {

		}
		try {
			u.likednumber = jo.getInt("likednumber");
		} catch (JSONException e1) {

		}
		try {
			u.mood = jo.getString("mood");
		} catch (JSONException e) {
		}
		try {
			u.defGame = Game.paresJSONObejct(jo.getJSONObject("defGame"));
		} catch (JSONException e) {

		}

		return u;
	}

	@Override
	public void login(Context context, SaveListener listener) {
		super.login(context, listener);
	}

	public void getFriendGroup(final YoheyApplication app) {
		new Thread() {
			public void run() {
				HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "getgroup");
				get.putString("uid", User.this.getObjectId());
				String data = get.sendInBack();
				try {
					JSONObject jo = new JSONObject(data);
					JSONArray ja = jo.getJSONArray("results");
					app.friendGroup.clear();
					for (int i = 0; i < ja.length(); i++) {
						Group g = Group.parseJSONObject(ja.getJSONObject(i), User.this);
						if (g != null)
							app.friendGroup.add(g);
					}
					app.friendGroup.get(0).addUser(User.this, true);
					app.isLoadingEnd = true;
					app.mHandler.sendEmptyMessage(100);

					Log.i("User", "friend group load end length=" + app.friendGroup.size());
				} catch (JSONException e) {
					e.printStackTrace();

				}
			};
		}.start();
	}
}
