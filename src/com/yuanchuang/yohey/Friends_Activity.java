package com.yuanchuang.yohey;

import java.util.ArrayList;
import com.yuanchuang.yohey.Message_Fragment.Adapter;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
public class Friends_Activity extends FragmentActivity{
	
	LinearLayout linearlayout;
	LayoutInflater inflater;
	ArrayList<String> string;
	ListView listview;
	RadioGroup radiogroup;
	RadioButton msg,friends;
	Message_Fragment message;
	Friends_Fragment friend;
	FragmentManager fm;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message);
		linearlayout=(LinearLayout)findViewById(R.id.linearlayout);
		radiogroup=(RadioGroup)findViewById(R.id.radiogroup);
		msg=(RadioButton)findViewById(R.id.radiobn_msg);
		friends=(RadioButton)findViewById(R.id.radiobtn_friends);
		msg.setChecked(true);//先设置选中，然后在设置change监听。不会发送监听改变的消息
		radiogroup.setOnCheckedChangeListener(listener);
		message=new Message_Fragment();
		friend=new Friends_Fragment();
		fm=this.getSupportFragmentManager();
		FragmentTransaction ft=fm.beginTransaction();
		ft.add(R.id.linearlayout, message);
		ft.commit();
		}
	OnCheckedChangeListener listener=new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			FragmentTransaction ft=fm.beginTransaction();
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
