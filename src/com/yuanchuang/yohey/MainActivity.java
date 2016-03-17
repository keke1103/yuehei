package com.yuanchuang.yohey;

import java.util.ArrayList;

import com.yuanchuang.yohey.fragment.DynamicFragment;
import com.yuanchuang.yohey.fragment.MEFragment;
import com.yuanchuang.yohey.fragment.MainFragment;
import com.yuanchuang.yohey.fragment.Main_FragmentAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import lu.Lu_Activity;

/**
 * 主页面
 * 
 * @author Administrator
 *
 */
public class MainActivity extends FragmentActivity {

	RelativeLayout includeTitle;// 引入头文件
	RelativeLayout inLayoutSelect;// 引入底部菜单栏
	Main_FragmentAdapter pagerAdapter;
	RadioButton[] radioButton;// 互斥按钮数组
	ArrayList<Fragment> fragmentList;// fragment集合
	MainFragment mainFragment;// 主页fragment
	FragmentManager mfFragmentManager;// fragment管理器
	DynamicFragment dynamicFragment;// 动态fragment
	MEFragment meFragment;// 我的fragment

	View postShare;// 发帖与分享
	ImageView postShareImage;
	ImageView post;// 发帖
	ImageView share;// 分享
	Lu_Activity LuFragment;

	RadioGroup mRadio;
	ViewPager mPager;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_text);
		mfFragmentManager = getSupportFragmentManager();
		findView();
	}

	/**
	 * View的点击事件
	 */
	OnClickListener clickListener = new OnClickListener() {
		boolean postshare = true;
		Intent intent;

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.selection_bar_post_share:
				if (postshare) {
					postShare.setVisibility(View.VISIBLE);
					postshare = false;
				} else {
					postShare.setVisibility(View.GONE);
					postshare = true;
				}
				break;
			case R.id.fragment_text_post:
				intent = new Intent(MainActivity.this, PostingInterfaceActivity.class);
				startActivity(intent);
				break;
			case R.id.fragment_text_share:
				intent = new Intent(MainActivity.this, ShareItActivity.class);
				startActivity(intent);
				break;
			default:
				break;
			}
		}
	};

	/**
	 * RadioGroup的点击事件
	 */

	OnCheckedChangeListener listener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			redioButton();
		}
	};

	private void redioButton() {

		for (int i = 0; i < radioButton.length; i++) {
			if (radioButton[i].isChecked()) {
				mPager.setCurrentItem(i);
				postShare.setVisibility(View.GONE);
			} else {
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void findView() {
		post = (ImageView) findViewById(R.id.fragment_text_post);
		share = (ImageView) findViewById(R.id.fragment_text_share);

		inLayoutSelect = (RelativeLayout) findViewById(R.id.fragment_main_selection_bar);
		mRadio = (RadioGroup) inLayoutSelect.findViewById(R.id.selection_bar_radio_group);
		postShare = findViewById(R.id.fragment_text_post_share);
		mRadio.setOnCheckedChangeListener(listener);
		mPager = (ViewPager) findViewById(R.id.layou_main);
		radioButton = new RadioButton[4];
		radioButton[0] = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_main);
		radioButton[1] = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_friends);
		radioButton[2] = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_circle_of_friends);
		radioButton[3] = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_personal);
		postShareImage = (ImageView) inLayoutSelect.findViewById(R.id.selection_bar_post_share);
		postShareImage.setOnClickListener(clickListener);
		post.setOnClickListener(clickListener);
		share.setOnClickListener(clickListener);
		mainFragment = new MainFragment();
		LuFragment = new Lu_Activity();
		dynamicFragment = new DynamicFragment();
		meFragment = new MEFragment();
		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(mainFragment);

		fragmentList.add(LuFragment);
		fragmentList.add(dynamicFragment);
		fragmentList.add(meFragment);
		pagerAdapter = new Main_FragmentAdapter(mfFragmentManager, fragmentList);
		mPager.setAdapter(pagerAdapter);
		mPager.setOnPageChangeListener(pageChange);
	}

	private OnPageChangeListener pageChange = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			radioButton[arg0].setChecked(true);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}
	};
}
