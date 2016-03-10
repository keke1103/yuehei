package com.yuanchuang.yohey.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.adapter.DynamicAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DynamicFragment extends Fragment {
	ListView listView;
	RelativeLayout layoutTitle;// 标题布局
	View mView;
	List<Map<String, Object>> list;
	DynamicAdapter mAdapter;
	TextView title;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		LinearLayout lay = new LinearLayout(getActivity());
		lay.setLayoutParams(new LayoutParams(-1, -1));
		mView = inflater.inflate(R.layout.activity_yue_lu_dynamic, lay);
	
		findView();
		return mView;
	}

	private void findView() {
		listView = (ListView) mView.findViewById(R.id.dynamic_list_view);
		layoutTitle = (RelativeLayout) mView.findViewById(R.id.dynamic_title);
		title = (TextView) layoutTitle.findViewById(R.id.title_navigation_text_title);
		title.setText(R.string.dynamic);
		list = new ArrayList<Map<String, Object>>();
		getData();
		Log.i("DynamicFragment", "DynamicFragment");
		mAdapter = new DynamicAdapter(getActivity(), list);
		listView.setAdapter(mAdapter);
	}

	@SuppressWarnings("deprecation")
	private void getData() {

		Map<String, Object> map;
		for (int i = 0; i < 5; i++) {
			map = new HashMap<String, Object>();
			map.put("head", getResources().getDrawable(R.drawable.ic_launcher));
			map.put("nickName", "多啦不爱梦");
			map.put("time", "1分钟");
			map.put("cotext", "求大神带我飞");
			list.add(map);
			Log.i("getData", list.size() + "");
		}
	}

}
