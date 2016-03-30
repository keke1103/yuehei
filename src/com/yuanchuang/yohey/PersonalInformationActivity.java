package com.yuanchuang.yohey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.FriendGroup;
import com.yuanchuang.yohey.bmob.Friends;
import com.yuanchuang.yohey.bmob.Game;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 个人资料界面
 * 
 * @author Administrator
 *
 */
public class PersonalInformationActivity extends Activity {
	RelativeLayout include;// 导入头文件
	TextView title;// 标题
	ImageView backimage;// 返回键
	ImageView head;// 头像
	TextView name;// 昵称
	TextView area;// 大区
	TextView grade;// 等级
	TextView dan;// 段位
	TextView first;// 首胜可用
	TextView age;// 年龄
	ImageView sex;// 性别
	TextView up;// 点赞数
	EditText signature;// 个性签名
	LinearLayout others_impression;// 他人印象
	TextView mSignature;// 修改个性签名
	LinearLayout record;// 战绩
	LayoutInflater inflater;
	/**
	 * 战绩
	 */
	View mView;// 战绩的视图
	ImageView mHead;// 战绩的英雄
	TextView mMode;// 战绩的模式
	LinearLayout win;// 战绩的胜利
	LinearLayout lose;// 战绩的失败
	TextView mDate;// 战绩的日期
	TextView mTime;// 战绩的时间
	TextView mkills;// 战绩胜利的击杀
	TextView mHeadcount;// 战绩胜利的人头
	TextView mAssists;// 战绩胜利的助攻
	TextView mLosekills;// 战绩失败的击杀
	TextView mLoseHeadcount;// 战绩失败的人头
	TextView mLoseAssists;// 战绩失败的助攻

	User user;
	boolean isMine = true;
	YoheyApplication app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		app = (YoheyApplication) getApplication();
		setContentView(R.layout.activity_yue_lu_personal_information);
		if (app.data == null) {
			user = BmobUser.getCurrentUser(this, User.class);
		} else {
			try {
				user = (User) app.data;
				app.data = null;
				User u = BmobUser.getCurrentUser(getApplicationContext(), User.class);
				isMine = user.getObjectId().equals(u.getObjectId());
				Log.i("PersonalUser", isMine + " A:" + user.getObjectId() + " B:" + u.getObjectId());
			} catch (ClassCastException e) {
				user = BmobUser.getCurrentUser(this, User.class);
			}
		}
		findView();
		findRecord();
	}

	private void findRecord() {

		mHead = (ImageView) mView.findViewById(R.id.view_personal_informat_text_head);
		mMode = (TextView) mView.findViewById(R.id.view_personal_informat_text_mode);
		win = (LinearLayout) mView.findViewById(R.id.view_personal_informat_text_win);
		lose = (LinearLayout) mView.findViewById(R.id.view_personal_informat_text_lose);
		mDate = (TextView) mView.findViewById(R.id.view_personal_informat_text_date);
		mTime = (TextView) mView.findViewById(R.id.view_personal_informat_text_time);
		mkills = (TextView) mView.findViewById(R.id.view_personal_informat_text_win_kills);
		mHeadcount = (TextView) mView.findViewById(R.id.view_personal_informat_text_win_assists);
		mAssists = (TextView) mView.findViewById(R.id.view_personal_informat_text_win_head_count_inco);
		mLosekills = (TextView) mView.findViewById(R.id.view_personal_informat_text_lose_kills);
		mLoseHeadcount = (TextView) mView.findViewById(R.id.view_personal_informat_text_lose_headcount);
		mLoseAssists = (TextView) mView.findViewById(R.id.view_personal_informat_text_lose_assists);
		setData();

	}

	private void setData() {
		mMode.setText("艾欧尼亚");
		win.setVisibility(View.VISIBLE);
		lose.setVisibility(View.GONE);
		mDate.setText("2月22日");
		mTime.setText("15:33");
		mkills.setText("10");
		mHeadcount.setText("12");
		mAssists.setText("13");
		gainUser();
	}

	@SuppressLint("InflateParams")
	public void findView() {
		include = (RelativeLayout) findViewById(R.id.personal_information_title_bar);
		title = (TextView) include.findViewById(R.id.title_navigation_text_title);
		backimage = (ImageView) include.findViewById(R.id.title_navigation_back_icon);

		head = (ImageView) findViewById(R.id.personal_user_image_portrait);
		name = (TextView) findViewById(R.id.personal_user_text_nickname);
		area = (TextView) findViewById(R.id.personal_information_text_server);
		grade = (TextView) findViewById(R.id.personal_user_text_grade);
		dan = (TextView) findViewById(R.id.personal_user_text_designation);
		age = (TextView) findViewById(R.id.personal_user_text_ages);
		sex = (ImageView) findViewById(R.id.personal_user_text_sex);
		up = (TextView) findViewById(R.id.personal_text_thumbs_up);
		signature = (EditText) findViewById(R.id.personal_user_text_signature);
		mSignature = (TextView) findViewById(R.id.personal_signature_modification);
		others_impression = (LinearLayout) include.findViewById(R.id.personal_user_linear_other_impression);
		record = (LinearLayout) findViewById(R.id.personal_user_linear_record);
		first = (TextView) findViewById(R.id.personal_user_text_first);

		backimage.setVisibility(View.VISIBLE);
		backimage.setImageResource(R.drawable.yo_hey_back_image);
		title.setText("个人资料");
		signature.setText(user.getMood());
		backimage.setOnClickListener(clickListener);
		mSignature.setOnClickListener(clickListener);

		if (!isMine) {
			mSignature.setVisibility(View.INVISIBLE);
			first.setText("加为好友");
			first.setOnClickListener(clickListener);
		}

		inflater = getLayoutInflater();
		mView = inflater.inflate(R.layout.view_personal_information, null);
		record.addView(mView);
	}

	OnClickListener clickListener = new OnClickListener() {
		boolean msignature = true;

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.title_navigation_back_icon:
				finish();
				break;
			case R.id.personal_signature_modification:
				if (msignature) {
					signature.setFocusable(true);
					signature.setFocusableInTouchMode(true);
					signature.requestFocus();
					signature.findFocus();
					signature.setEnabled(true);
					mSignature.setText("确定");
					msignature = false;

				} else {
					signature.setFocusable(false);
					signature.setEnabled(false);
					mSignature.setText("修改");
					msignature = true;
					saveMood();
				}
				break;
			case R.id.personal_user_text_first:
				addFriend();
				break;
			default:
				break;
			}
		}
	};

	private void saveMood() {
		User nu = new User();
		nu.setMood(signature.getText().toString());
		nu.update(getApplicationContext(), user.getObjectId(), new UpdateListener() {

			public void onSuccess() {
				user.setMood(signature.getText().toString());
				app.friendGroup[0].getFriends()[0].setMood(user.getMood());
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(getApplicationContext(), arg1, Toast.LENGTH_SHORT).show();
				signature.setText(user.getMood());
			}
		});

	}

	/**
	 * 获取用户信息
	 */
	public void gainUser() {
		name.setText(user.getNickName());
		user.binderImageView(head);
		HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "getgame");
		get.putString("gid", user.getDefGame().getObjectId());
		get.setOnSendListener(new OnSendListener() {

			public void start() {
			}

			@Override
			public void end(String result) {
				try {
					user.setDefGame(Game.paresJSONObejct(new JSONObject(result)));
					area.setText(user.getDefGame().getGameregion());
					dan.setText(user.getDefGame().getGamedan());
					grade.setText("LV" + user.getDefGame().getGamegrade());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		get.send();
	}

	/**
	 * 更新用户信息
	 */
	public void newUserInformation() {
		BmobUser newUser = new BmobUser();
		BmobUser bmobUser = BmobUser.getCurrentUser(this);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			public void onSuccess() {
				Toast.makeText(getApplication(), "更新用户信息成功", Toast.LENGTH_SHORT).show();
			}

			public void onFailure(int arg0, String arg1) {
				Toast.makeText(getApplication(), "更新用户信息失败", Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * 向默认组,我的撸友分组添加好友
	 * 
	 */
	private void addFriend() {
		HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "getgroup");
		get.putString("uid", BmobUser.getCurrentUser(getApplicationContext()).getObjectId());
		get.setOnSendListener(new OnSendListener() {
			public void start() {
			}

			public void end(String result) {
				try {

					JSONObject jo = new JSONObject(result);
					JSONArray ja = jo.getJSONArray("results");
					for (int i = 0; i < ja.length(); i++) {
						FriendGroup fg = FriendGroup.parseJSONObject(ja.getJSONObject(i));
						if (fg != null && fg.getGroupName().equals("我的撸友")) {
							Log.i("PersonalInform", "add friends to " + fg.getObjectId());
							addFriend(fg);
							break;
						}
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		get.send();
	}

	/**
	 * 向某个组添加好友
	 * 
	 * @param groupId
	 */
	private void addFriend(final FriendGroup group) {
		final Friends f = new Friends();
		f.addFriend(getApplicationContext(), user, group, new OnSendListener() {
			public void start() {
			}

			public void end(String result) {
				try {
					JSONObject jo = new JSONObject(result);
					String time = jo.getString("updatedAt");
					Log.i("AddFriends", time);
					Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
				} catch (JSONException e) {
					Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
