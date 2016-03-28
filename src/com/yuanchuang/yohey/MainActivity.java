package com.yuanchuang.yohey;

import java.util.ArrayList;

import com.yuanchuang.yohey.fragment.DynamicFragment;
import com.yuanchuang.yohey.fragment.MEFragment;
import com.yuanchuang.yohey.fragment.MainFragment;
import com.yuanchuang.yohey.fragment.Main_FragmentAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
	LinearLayout postShare;// 发帖与分享

	ImageView postShareImage;
	Lu_Activity LuFragment;

	RadioGroup mRadio;
	ViewPager mPager;

	@Override
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
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.selection_bar_post_share:
				new PopupWindows(MainActivity.this, mPager);
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
			} else {
			}
		}
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	private void findView() {
		inLayoutSelect = (RelativeLayout) findViewById(R.id.fragment_main_selection_bar);
		mRadio = (RadioGroup) inLayoutSelect.findViewById(R.id.selection_bar_radio_group);

		mRadio.setOnCheckedChangeListener(listener);
		mPager = (ViewPager) findViewById(R.id.layou_main);
		radioButton = new RadioButton[4];
		radioButton[0] = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_main);
		radioButton[1] = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_friends);
		radioButton[2] = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_circle_of_friends);
		radioButton[3] = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_personal);
		postShareImage = (ImageView) inLayoutSelect.findViewById(R.id.selection_bar_post_share);
		postShareImage.setOnClickListener(clickListener);
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


		public void onPageSelected(int arg0) {
			radioButton[arg0].setChecked(true);
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageScrollStateChanged(int arg0) {

		}
	};

	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		Log.i("onActivityResult", "req=" + arg0 + " res=" + arg1);

		mainFragment.onActivityResult(arg0, arg1, arg2);

	};

	/**
	 * 自定义的PopupWindow
	 * 
	 * @author Administrator
	 *
	 */
	public class PopupWindows extends PopupWindow {
		Intent intent;
		private OnClickListener click = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case 12345:
					dismiss();
					break;
				case R.id.post_popupwindoe:
					intent = new Intent(MainActivity.this, PostingInterfaceActivity.class);
					startActivityForResult(intent, 100);
					dismiss();
					break;
				case R.id.share_popupwindoe:
					intent = new Intent(MainActivity.this, ShareItActivity.class);
					startActivity(intent);
					dismiss();
					break;
				default:
					break;
				}

			}
		};

		@SuppressWarnings("deprecation")
		public PopupWindows(Context mContext, View parent) {

			View view = View.inflate(mContext, R.layout.post_share_main, null);
			view.setId(12345);
			view.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_ins));
			View ll_popup = view.findViewById(R.id.share_layout);
			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_bottom_in_2));
			view.setOnClickListener(click);
			View post = view.findViewById(R.id.post_popupwindoe);
			View share = view.findViewById(R.id.share_popupwindoe);
			post.setOnClickListener(click);
			share.setOnClickListener(click);
			setWidth(LayoutParams.MATCH_PARENT);
			setHeight(LayoutParams.MATCH_PARENT);
			setBackgroundDrawable(new BitmapDrawable());
			setFocusable(true);
			setOutsideTouchable(true);
			setContentView(view);
			showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		}

	}
}
