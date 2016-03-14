package com.yuanchuang.yohey;

import java.util.ArrayList;

import com.yuanchuang.yohey.fragment.DynamicFragment;
import com.yuanchuang.yohey.fragment.MEFragment;
import com.yuanchuang.yohey.fragment.MainFragment;
import com.yuanchuang.yohey.fragment.Main_FragmentAdapter;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

public class MainActivity extends FragmentActivity {
	RelativeLayout includeTitle;
	RelativeLayout inLayoutSelect;
	Main_FragmentAdapter pagerAdapter;
	RadioButton[] radioButton;
	ArrayList<Fragment> fragmentList;
	MainFragment mainFragment;
	FragmentManager mfFragmentManager;
	DynamicFragment dynamicFragment;
	MEFragment meFragment;
	RadioGroup mRadio;
	ViewPager mPager;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_text);
		mfFragmentManager = getSupportFragmentManager();
		findView();

	}

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
		//Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont/iconfont.ttf");
		inLayoutSelect = (RelativeLayout) findViewById(R.id.fragment_main_selection_bar);
		mRadio = (RadioGroup) inLayoutSelect.findViewById(R.id.selection_bar_radio_group);
		mRadio.setOnCheckedChangeListener(listener);
		mPager = (ViewPager) findViewById(R.id.layou_main);
		radioButton = new RadioButton[4];
		radioButton[0] = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_main);
		radioButton[1] = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_friends);
		radioButton[2] = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_circle_of_friends);
		radioButton[3] = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_personal);

		mainFragment = new MainFragment();
		dynamicFragment = new DynamicFragment();
		meFragment = new MEFragment();
	    fragmentList = new ArrayList<Fragment>();
		fragmentList.add(mainFragment);
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
