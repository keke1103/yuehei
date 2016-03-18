package com.yuanchuang.yohey.fragment;

import java.util.ArrayList;
import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.adapter.DynamicAdapter;
import com.yuanchuang.yohey.myData.AdapterData;
import com.yuanchuang.yohey.tools.DensityUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

	@SuppressWarnings("deprecation")
	private void findView() {
		listView = (ListView) mView.findViewById(R.id.dynamic_list_view);
		layoutTitle = (RelativeLayout) mView.findViewById(R.id.dynamic_title);
		title = (TextView) layoutTitle.findViewById(R.id.title_navigation_text_title);
		title.setText(R.string.dynamic);
		title.setTextColor(getResources().getColor(R.color.title_yellow));
		list = new ArrayList<AdapterData>();
		getData();
		Log.i("DynamicFragment", "DynamicFragment");
		mAdapter = new DynamicAdapter(getActivity(), list);
		android.widget.AbsListView.LayoutParams params = new android.widget.AbsListView.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT, DensityUtil.dip2px(getActivity(), 133));
		View v = new View(getActivity());
		v.setBackgroundDrawable(getResources().getDrawable(R.drawable.dynamic_banner));
		v.setLayoutParams(params);
		v.setBackgroundResource(R.drawable.main_guang_gao);
		listView.addHeaderView(v);
		listView.setAdapter(mAdapter);

	}

	private void getData() {

		for (int i = 0; i < 5; i++) {
			data = new AdapterData();
			data.setDy_head(R.drawable.ic_launcher + "");
			data.setDy_name("多啦不爱梦");
			data.setDy_nmae("1分钟");
			data.setDy_context("求大神带我飞");
			Bitmap maps[] = new Bitmap[2];
			maps[0] = BitmapFactory.decodeResource(getResources(), R.drawable.small_image_size);
			maps[1] = BitmapFactory.decodeResource(getResources(), R.drawable.small_image_size);
			// maps[2] = BitmapFactory.decodeResource(getResources(),
			// R.drawable.small_image_size);
			// maps[3] = BitmapFactory.decodeResource(getResources(),
			// R.drawable.small_image_size);
			data.setDy_image(maps);
			list.add(data);
			Log.i("getData", list.size() + "");
		}
	}

}
