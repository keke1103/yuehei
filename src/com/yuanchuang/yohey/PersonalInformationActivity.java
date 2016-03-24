package com.yuanchuang.yohey;

import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.Game;
import com.yuanchuang.yohey.bmob.Post;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
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
	TextView signature;// 个性签名
	LinearLayout others_impression;// 他人印象
	TextView mSignature;//修改个性签名
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
	Post post;
	YoheyApplication app;
	
	AlertDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_personal_information);
		findView();
		
		app = (YoheyApplication) getApplication();
		user=BmobUser.getCurrentUser(this, User.class);
		post = (Post) app.data;
		app.data = null;
		
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
		signature = (TextView) findViewById(R.id.personal_user_text_signature);
		mSignature=(TextView)findViewById(R.id.personal_signature_modification);
		others_impression = (LinearLayout) include.findViewById(R.id.personal_user_linear_other_impression);
		record = (LinearLayout) findViewById(R.id.personal_user_linear_record);

		backimage.setVisibility(View.VISIBLE);
		backimage.setImageResource(R.drawable.yo_hey_back_image);
		title.setText("个人资料");
		backimage.setOnClickListener(clickListener);
		mSignature.setOnClickListener(clickListener);
		
		inflater = getLayoutInflater();
		mView = inflater.inflate(R.layout.view_personal_information, null);
		record.addView(mView);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.title_navigation_back_icon:
				finish();
				break;
			case R.id.personal_signature_modification:
				customDialog();
				break;
			default:
				break;
			}
		}
	};
	/**
	 * 自定义Dialog
	 */
	public void customDialog(){
		LinearLayout layout=new LinearLayout(getApplication());
		LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		layout.setLayoutParams(params);
		layout.setOrientation(1);
		
		EditText editText=new EditText(getApplication());
		editText.setLayoutParams(params);
		layout.addView(editText);
		
		Button button=new Button(getApplication());
		button.setLayoutParams(params);
		button.setText("确定");
		layout.addView(button);
		
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		dialog=builder.create();
		dialog.setView(layout);
		dialog.show();
	}
	/**
	 * 获取用户信息
	 */
	public void gainUser(){
		name.setText(user.getNickName());
	    user.binderImageView(head);	
	    HttpGet get=new HttpGet(YoheyApplication.ServiceIp+"getgame");
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
				grade.setText("LV"+user.getDefGame().getGamegrade());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
	    get.send();
	}
	/**
	 * 更新用户信息
	 */
	public void newUserInformation(){
		BmobUser newUser=new BmobUser();
		BmobUser bmobUser=BmobUser.getCurrentUser(this);
		newUser.update(this, bmobUser.getObjectId(),new UpdateListener() {
			public void onSuccess() {
				Toast.makeText(getApplication(),"更新用户信息成功",Toast.LENGTH_SHORT).show();
			}
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(getApplication(),"更新用户信息失败",Toast.LENGTH_SHORT).show();
			}
		});
	}
}
