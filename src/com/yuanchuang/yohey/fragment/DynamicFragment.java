package com.yuanchuang.yohey.fragment;

import java.util.ArrayList;
import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.adapter.DynamicAdapter;
import com.yuanchuang.yohey.myData.AdapterData;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 动态页面
 * 
 * @author Administrator
 *
 */
public class DynamicFragment extends Fragment {
	ListView listView;
	RelativeLayout layoutTitle;// 标题布局
	View mView;
	List<AdapterData> list;
	DynamicAdapter mAdapter;
	TextView title;
	AdapterData data;// 数据类

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
		list = new ArrayList<AdapterData>();
		getData();
		Log.i("DynamicFragment", "DynamicFragment");
		mAdapter = new DynamicAdapter(getActivity(), list);

		ImageView image = new ImageView(getActivity());

		image.setImageResource(R.drawable.ic_launcher);
		listView.addHeaderView(image);
		listView.setAdapter(mAdapter);

	}

	private void getData() {

		for (int i = 0; i < 5; i++) {
			data = new AdapterData();
			data.setDy_head(R.drawable.ic_launcher + "");
			data.setDy_name("多啦不爱梦");
			data.setDy_nmae("1分钟");
			data.setDy_context("求大神带我飞");
			list.add(data);
			Log.i("getData", list.size() + "");
		}
	}

}
