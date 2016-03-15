package com.yuanchuang.yohey;

import java.util.ArrayList;

import com.yuanchuang.yohey.fragment.Friends_Fragment;
import com.yuanchuang.yohey.fragment.Message_Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Friends_Activity extends Fragment {
	View mView;
	LinearLayout linearlayout;
	LayoutInflater inflater;
	ArrayList<String> string;
	ListView listview;
	RadioGroup radiogroup;
	RadioButton msg, friends;
	Message_Fragment message;
	Friends_Fragment friend;
	FragmentManager fm;

	public android.view.View onCreateView(LayoutInflater inflater, android.view.ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout lay = new LinearLayout(getActivity());
		lay.setLayoutParams(new LayoutParams(-1, -1));

		mView = inflater.inflate(R.layout.message, lay);
		linearlayout = (LinearLayout) mView.findViewById(R.id.linearlayout);
		radiogroup = (RadioGroup) mView.findViewById(R.id.radiogroup);
		msg = (RadioButton) mView.findViewById(R.id.radiobn_msg);
		friends = (RadioButton) mView.findViewById(R.id.radiobtn_friends);
		msg.setChecked(true);// 先设置选中，然后在设置change监听。不会发送监听改变的消息
		radiogroup.setOnCheckedChangeListener(listener);
		message = new Message_Fragment();
		friend = new Friends_Fragment();
		fm = getActivity().getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		 ft.add(R.id.linearlayout, message);
		 ft.commit();
		return mView;
	};

	OnCheckedChangeListener listener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			FragmentTransaction ft = fm.beginTransaction();
			switch (checkedId) {
			case R.id.radiobn_msg:
				Log.i("onCheckedChanged", "消息");
				ft.replace(R.id.linearlayout, message);
				ft.commit();
				break;
			case R.id.radiobtn_friends:
				Log.i("onCheckedChanged", "好友");
				ft.replace(R.id.linearlayout, friend);
				ft.commit();
				break;
			}
		}
	};

}
