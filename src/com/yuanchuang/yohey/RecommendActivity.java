package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuanchuang.yohey.adapter.RecommendAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RecommendActivity extends Activity {
	List<Map<String, Object>> list;// listView的填充
	ListView listView;
	RelativeLayout layout;
	TextView title;
	View toReturn;
	RecommendAdapter recommendAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_recommend);
		list = new ArrayList<Map<String, Object>>();
		findView();
		getData();
		recommendAdapter = new RecommendAdapter(list, this);
		listView.setAdapter(recommendAdapter);
	}

	private void getData() {
		for (int i = 0; i < 5; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("head", R.drawable.ic_launcher);
			map.put("name", R.string.zhao);
			map.put("bojin", "艾欧尼亚" + R.string.bojin);
			map.put("aiouniya", R.string.aiouniya);
			list.add(map);
		}

	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.title_navigation_back_icon:
				finish();
				break;

			default:
				break;
			}

		}
	};

	private void findView() {
		listView = (ListView) findViewById(R.id.recommened_list);
		layout = (RelativeLayout) findViewById(R.id.recommend_include_title);
		title = (TextView) layout.findViewById(R.id.title_navigation_text_title);
		toReturn = layout.findViewById(R.id.title_navigation_back_icon);
		toReturn.setVisibility(View.VISIBLE);
		toReturn.setOnClickListener(clickListener);
		title.setText(R.string.tuijiankaihei);
	}

}
