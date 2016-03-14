package com.yuanchuang.yohey.fragment;

import com.yuanchuang.yohey.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainFragment extends Fragment {
	View myView;// 视图
	View user1;// 用户头像1
	View user2;// 用户头像2
	View user3;// 用户头像3
	TextView clickMore;// 点击更多
	RelativeLayout layout;// 标题布局
	TextView title;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		LinearLayout lay = new LinearLayout(getActivity());
		lay.setLayoutParams(new LayoutParams(-1, -1));
		myView = inflater.inflate(R.layout.activity_yue_lu_main, lay);
		findView();
		return myView;

	}

	private void findView() {
		layout = (RelativeLayout) myView.findViewById(R.id.main_title);
		user1 = myView.findViewById(R.id.main_image_recommended_user1);
		user2 = myView.findViewById(R.id.main_image_recommended_user2);
		user3 = myView.findViewById(R.id.main_image_recommended_user3);
		clickMore = (TextView) myView.findViewById(R.id.main_text_click_for_more);
		title = (TextView) myView.findViewById(R.id.title_navigation_text_title);
		title.setText(R.string.homepage);
	}
	/**
	 * 发送帖子
	 */
	public void sendPost(){}
}
