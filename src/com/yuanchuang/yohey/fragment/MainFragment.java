package com.yuanchuang.yohey.fragment;

import java.util.ArrayList;
import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.adapter.GalleryAdapter;
import com.yuanchuang.yohey.adapter.MainAdapter;
import com.yuanchuang.yohey.myData.AdapterData;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ViewFlipper;

@SuppressWarnings("deprecation")
public class MainFragment extends Fragment {
	View myView;// 视图
	ListView listView;
	List<AdapterData> list1;
	List<AdapterData> list;
	MainAdapter adapter;
	AdapterData data;
	Gallery gallery;
	GalleryAdapter galleryAdapter;
	ViewFlipper guanggao;

	@SuppressLint({ "InflateParams", "CutPasteId" })
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		LinearLayout lay = new LinearLayout(getActivity());
		lay.setLayoutParams(new LayoutParams(-1, -1));
		list = new ArrayList<AdapterData>();
		list1 = new ArrayList<AdapterData>();
		myView = inflater.inflate(R.layout.activity_yue_lu_main, lay);
		findView();
		getData();
		adapter = new MainAdapter(list, getActivity());

		View view = inflater.inflate(R.layout.list_head_view, null);
		gallery = (Gallery) view.findViewById(R.id.main_list_head_gallery);
		View v = view.findViewById(R.id.main_list_head_gallery);
		getData1();
		galleryAdapter = new GalleryAdapter(list1, getActivity());
		gallery.setAdapter(galleryAdapter);

		listView.addHeaderView(view);
		listView.setAdapter(adapter);

		return myView;

	}

	private void getData1() {

		for (int i = 0; i < 5; i++) {
			data = new AdapterData();
			data.setDy_head(R.drawable.ic_launcher + "");
			data.setDy_name("多啦不爱梦");
			data.setDy_nmae("1分钟");
			data.setDy_context("求大神带我飞");
			list1.add(data);
			Log.i("getData", list1.size() + "");
		}
	}

	private void getData() {
		for (int i = 0; i < 5; i++) {
			data = new AdapterData();
			data.setMain_area("诺克萨斯");
			data.setMain_context("开黑快来，坑比别来");
			data.setMain_dan("尊贵铂金3");
			data.setMain_message(2);
			data.setMain_add(5);
			data.setMain_up(6);
			data.setMain_time("5分钟前");
			data.setMain_name("猪哥我爱你");
			list.add(data);
			Log.i("getData", list.size() + "");
		}

	}

	private void findView() {

		listView = (ListView) myView.findViewById(R.id.main_list_posts);
		guanggao = (ViewFlipper) myView.findViewById(R.id.main_image_guang_gao);
	}

	private void addGuangGao() {

	}

	/**
	 * 发送帖子
	 */
	public void sendPost() {
	}

	public static class MainGuangGao {

		public MainGuangGao() {

		}
	}
}
