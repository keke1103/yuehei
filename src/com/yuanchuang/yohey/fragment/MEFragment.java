package com.yuanchuang.yohey.fragment;

import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.IntegralStoreActivity;
import com.yuanchuang.yohey.MyPostsActivity;
import com.yuanchuang.yohey.PersonalInformationActivity;
import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.Game;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobUser;
import set.Set_Activity;

public class MEFragment extends Fragment {
	RelativeLayout layouttitle;// 标题布局
	TextView title;// 标题
	TextView signIn;// 签到
	ImageView headImage;
	TextView name;
	TextView gameArea;
	LinearLayout myProfile;// 我的资料
	LinearLayout myPosts;// 我的帖子
	LinearLayout integral;// 积分商城
	LinearLayout settings;// 系统设置
	View mView;

	User user;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		LinearLayout lay = new LinearLayout(getActivity());
		lay.setLayoutParams(new LayoutParams(-1, -1));
		mView = inflater.inflate(R.layout.activity_yuye_lu_homepage, lay);
		findView();

		user = BmobUser.getCurrentUser(getActivity(), User.class);
		getData();

		integral.setOnClickListener(onClickListener);
		myPosts.setOnClickListener(onClickListener);

		myProfile.setOnClickListener(onClickListener);
		settings.setOnClickListener(onClickListener);

		return mView;
	}

	@SuppressWarnings("deprecation")
	private void findView() {

		layouttitle = (RelativeLayout) mView.findViewById(R.id.homepage_title_bar);
		title = (TextView) layouttitle.findViewById(R.id.title_navigation_text_title);
		signIn = (TextView) layouttitle.findViewById(R.id.title_navigation_text_right_title);
		name = (TextView) mView.findViewById(R.id.homepage_name);
		headImage = (ImageView) mView.findViewById(R.id.homepage_head_image);
		gameArea = (TextView) mView.findViewById(R.id.homepage_area);
		myProfile = (LinearLayout) mView.findViewById(R.id.homepage_text_my_msg);
		myPosts = (LinearLayout) mView.findViewById(R.id.homepage_text_my_post);
		integral = (LinearLayout) mView.findViewById(R.id.homepage_text_score);
		settings = (LinearLayout) mView.findViewById(R.id.homepage_text_system_set);
		title.setText(R.string.me);
		Resources resources = getContext().getResources();
		Drawable drawable = resources.getDrawable(R.drawable.shape_rounded_yellow);
		signIn.setVisibility(View.VISIBLE);
		signIn.setTextColor(getResources().getColor(R.color.black));
		signIn.setBackgroundDrawable(drawable);
		signIn.setText(R.string.sign_in);
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent;
			switch (v.getId()) {
			case R.id.homepage_text_my_msg:// 跳转到我的资料
				intent = new Intent(getActivity(), PersonalInformationActivity.class);
				startActivity(intent);
				break;
			case R.id.homepage_text_my_post:// 跳转到我的帖子
				intent = new Intent(getActivity(), MyPostsActivity.class);
				startActivity(intent);
				break;
			case R.id.homepage_text_score:// 点击事件，跳转到我的积分界面
				intent = new Intent(getActivity(), IntegralStoreActivity.class);
				startActivity(intent);
				break;
			case R.id.homepage_text_system_set:// 跳到系统设置
				intent = new Intent(getActivity(), Set_Activity.class);
				startActivity(intent);
				break;
			default:
				break;
			}
		}
	};

	/**
	 * 获得用户信息
	 */
	public void getData() {
		name.setText(user.getNickName());
		user.binderImageView(headImage);
		HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "getgame");
		get.putString("gid", user.getDefGame().getObjectId());
		get.setOnSendListener(new OnSendListener() {

			@Override
			public void start() {

			}

			@Override
			public void end(String result) {
				try {
					user.setDefGame(Game.paresJSONObejct(new JSONObject(result)));
					gameArea.setText(user.getDefGame().getGameregion());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		get.send();
	}
}
