package com.yuanchuang.yohey;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
	LinearLayout record;//// 战绩
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_personal_information);
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
		others_impression = (LinearLayout) include.findViewById(R.id.personal_user_linear_other_impression);
		record = (LinearLayout) findViewById(R.id.personal_user_linear_record);

		inflater = getLayoutInflater();
		mView = inflater.inflate(R.layout.view_personal_information, null);
		backimage.setVisibility(View.VISIBLE);
		backimage.setImageResource(R.drawable.yo_hey_back_image);
		title.setText("个人资料");
		backimage.setOnClickListener(clickListener);

		
	
			//record.addView(mView);
		
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.title_navigation_back_icon:
				finish();
				break;

			default:
				break;
			}
		}
	};
}
