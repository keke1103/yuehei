package com.yuanchuang.yohey;

import java.util.ArrayList;

import com.yuanchuang.yohey.fragment.DynamicFragment;
import com.yuanchuang.yohey.fragment.MEFragment;
import com.yuanchuang.yohey.fragment.MainFragment;
import com.yuanchuang.yohey.fragment.Main_FragmentAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
/**
 * 主页面
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
	Friends_Activity friends_Activity;
	FragmentManager mfFragmentManager;// fragment管理器
	DynamicFragment dynamicFragment;// 动态fragment
	MEFragment meFragment;// 我的fragment
	RadioGroup mRadio;
	ViewPager mPager;
	View add;// select中间的加号

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_text);
		mfFragmentManager = getSupportFragmentManager();
		findView();
		add.setOnClickListener(clickListener);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.selection_bar_image:

				break;

			default:
				break;
			}

		}
	};
	OnCheckedChangeListener listener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {

			redioButton();
		}

		private void redioButton() {

			for (int i = 0; i < radioButton.length; i++) {
				if (radioButton[i].isChecked()) {
					mPager.setCurrentItem(i);
				} else {

				}
			}
		}
	};

	@SuppressWarnings("deprecation")
	private void findView() {
		// Typeface iconfont = Typeface.createFromAsset(getAssets(),
		// "iconfont/iconfont.ttf");
		inLayoutSelect = (RelativeLayout) findViewById(R.id.fragment_main_selection_bar);
		mRadio = (RadioGroup) inLayoutSelect.findViewById(R.id.selection_bar_radio_group);
		mRadio.setOnCheckedChangeListener(listener);
		add = inLayoutSelect.findViewById(R.id.selection_bar_image);
		mPager = (ViewPager) findViewById(R.id.layou_main);
		radioButton = new RadioButton[4];
		radioButton[0] = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_main);
		radioButton[1] = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_friends);
		radioButton[2] = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_circle_of_friends);
		radioButton[3] = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_personal);

		mainFragment = new MainFragment();
		dynamicFragment = new DynamicFragment();
		meFragment = new MEFragment();
		friends_Activity = new Friends_Activity();
		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(mainFragment);
		fragmentList.add(friends_Activity);
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
