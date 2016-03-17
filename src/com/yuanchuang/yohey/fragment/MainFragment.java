package com.yuanchuang.yohey.fragment;

import java.util.ArrayList;
import java.util.List;

import com.yuanchuang.yohey.OfficialinformationActivity;
import com.yuanchuang.yohey.PersonalPostActivity;
import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.RecommendActivity;
import com.yuanchuang.yohey.adapter.GalleryAdapter;
import com.yuanchuang.yohey.adapter.MainAdapter;
import com.yuanchuang.yohey.myData.AdapterData;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

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
	Intent intent;
	// 主页面内容
	View main_guang_gao;// 主页面的头视图的广告
	ImageView playerHead1;// 玩家1的头像
	ImageView playerHead2;// 玩家2的头像
	ImageView playerHead3;// 玩家3的头像
	ImageView playerHead4;// 玩家4的头像
	ImageView playerHead5;// 玩家5的头像
	View more;// 更多
	RelativeLayout new_freads;// 撸友新动态
	RelativeLayout afarid_official;// 官方新动态
	RelativeLayout video_highlights;// 视屏集锦
	RelativeLayout win_points;// 转转赢积分

	@SuppressLint({ "InflateParams", "CutPasteId" })
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		LinearLayout lay = new LinearLayout(getActivity());
		lay.setLayoutParams(new LayoutParams(-1, -1));
		list = new ArrayList<AdapterData>();
		list1 = new ArrayList<AdapterData>();
		myView = inflater.inflate(R.layout.activity_yue_lu_main, lay);
		findView();// 找到本页面的id
		getData();
		adapter = new MainAdapter(list, getActivity());

		View view = inflater.inflate(R.layout.list_head_view, null);
		findInflate(view);// 找到导入的头文件的id
		gallery = (Gallery) view.findViewById(R.id.main_list_head_gallery);
		View v = view.findViewById(R.id.main_list_head_gallery);
		getData1();

		galleryAdapter = new GalleryAdapter(list1, getActivity());
		gallery.setAdapter(galleryAdapter);
		listView.addHeaderView(view);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(clickListener);
		intent = getActivity().getIntent();
		return myView;

	}

	private void findInflate(View view) {
		main_guang_gao = view.findViewById(R.id.main_image_guang_gao);
		playerHead1 = (ImageView) view.findViewById(R.id.main_image_recommended_user1);
		playerHead2 = (ImageView) view.findViewById(R.id.main_image_recommended_user2);
		playerHead3 = (ImageView) view.findViewById(R.id.main_image_recommended_user3);
		playerHead4 = (ImageView) view.findViewById(R.id.main_image_recommended_user4);
		playerHead5 = (ImageView) view.findViewById(R.id.main_image_recommended_user5);
		more = view.findViewById(R.id.main_image_recommended_gen_duo);
		new_freads = (RelativeLayout) view.findViewById(R.id.main_list_head_liner_new_freads);
		afarid_official = (RelativeLayout) view.findViewById(R.id.main_list_head_liner_not_afarid_official);
		video_highlights = (RelativeLayout) view.findViewById(R.id.main_list_head_liner_video_highlights);
		win_points = (RelativeLayout) view.findViewById(R.id.main_list_head_liner_win_points);
		playerHead1.setOnClickListener(onClickListener);
		playerHead2.setOnClickListener(onClickListener);
		playerHead3.setOnClickListener(onClickListener);
		playerHead4.setOnClickListener(onClickListener);
		playerHead5.setOnClickListener(onClickListener);
		afarid_official.setOnClickListener(onClickListener);
		new_freads.setOnClickListener(onClickListener);
		video_highlights.setOnClickListener(onClickListener);
		win_points.setOnClickListener(onClickListener);
		more.setOnClickListener(onClickListener);
	}
OnItemClickListener clickListener=new OnItemClickListener() {

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		intent.setClass(getActivity(), PersonalPostActivity.class);
		startActivity(intent);
		
	}
};
	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.main_image_guang_gao:
				// intent=getActivity().getIntent(MainFragment.this,);
				break;

			case R.id.main_image_recommended_user1:

				break;

			case R.id.main_image_recommended_user2:

				break;

			case R.id.main_image_recommended_user3:

				break;

			case R.id.main_image_recommended_user4:

				break;

			case R.id.main_image_recommended_user5:

				break;

			case R.id.main_image_recommended_gen_duo:
				
				intent.setClass(getActivity(), RecommendActivity.class);
				startActivity(intent);
				break;
			case R.id.main_list_head_liner_new_freads:

				break;
			case R.id.main_list_head_liner_not_afarid_official:
				intent = getActivity().getIntent();
				intent.setClass(getActivity(), OfficialinformationActivity.class);
				startActivity(intent);
				break;
			case R.id.main_list_head_liner_video_highlights:

				break;
			case R.id.main_list_head_liner_win_points:

				break;
			default:
				break;
			}

		}
	};

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

	}

	/**
	 * 发送帖子
	 */
	public void sendPost() {
	}
}
