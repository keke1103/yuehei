package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.adapter.LocateAdapter;
import com.yuanchuang.yohey.myData.AdapterData;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class LocateActivity extends Activity {
	View toReturn;
	TextView title;
	View rightTltle;
	ListView listView;
	View headView;
	List<AdapterData> list;
	LocateAdapter myAdapter;
	AdapterData data;
	LayoutInflater inflater;
	TextView name;// 定位的地点名
	CheckBox locate;// 是否显示位置

	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_dynamic);
		list = new ArrayList<AdapterData>();
		findView();
		inflater = getLayoutInflater();
		headView = inflater.inflate(R.layout.list_locate_head, null);
		findInView();
		getData();

		myAdapter = new LocateAdapter(list, getApplication());

		listView.setDivider(null);
		listView.addHeaderView(headView);
		listView.setAdapter(myAdapter);

	}

	private void findInView() {
		name = (TextView) headView.findViewById(R.id.locate_head_text_name);
		locate = (CheckBox) headView.findViewById(R.id.locate_head_image);
		name.setText("重庆");

	}

	private void getData() {
		for (int i = 0; i < 10; i++) {
			data = new AdapterData();
			data.setLocatLocation("太平洋广场");
			data.setLocatContent("重庆渝中区上清寺路2号");
			list.add(data);
		}

	}

	private void findView() {
		toReturn = findViewById(R.id.title_navigation_back_icon);
		title = (TextView) findViewById(R.id.title_navigation_text_title);
		rightTltle = findViewById(R.id.title_navigation_image_right_title);
		listView = (ListView) findViewById(R.id.dynamic_list_view);

		toReturn.setVisibility(View.VISIBLE);
		title.setText("所在位置");
		rightTltle.setVisibility(View.VISIBLE);
		toReturn.setOnClickListener(clickListener);
		rightTltle.setOnClickListener(clickListener);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.title_navigation_back_icon:
				finish();
				break;
			case R.id.title_navigation_image_right_title:

				break;
			default:
				break;
			}

		}
	};
}
