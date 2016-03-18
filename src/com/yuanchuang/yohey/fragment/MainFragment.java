package com.yuanchuang.yohey.fragment;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.OfficialinformationActivity;
import com.yuanchuang.yohey.PersonalPostActivity;
import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.RecommendActivity;
import com.yuanchuang.yohey.adapter.GalleryAdapter;
import com.yuanchuang.yohey.adapter.MainAdapter;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.myData.AdapterData;
import com.yuanchuang.yohey.myData.Post;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.HttpPost;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;
import android.widget.ViewFlipper;

@SuppressWarnings("deprecation")
public class MainFragment extends Fragment {
	View myView;// 视图
	ListView listView;
	List<Post> reList;
	List<Post> list;
	MainAdapter adapter;
	AdapterData data;
	Gallery gallery;
	GalleryAdapter galleryAdapter;

	Intent intent;
	// 主页面内容
	ViewFlipper main_guang_gao;// 主页面的头视图的广告
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
		list = new ArrayList<Post>();
		reList = new ArrayList<Post>();
		myView = inflater.inflate(R.layout.activity_yue_lu_main, lay);
		findView();// 找到本页面的id
		getData();
		adapter = new MainAdapter(list, getActivity());

		View view = inflater.inflate(R.layout.list_head_view, null);
		findInflate(view);// 找到导入的头文件的id
		gallery = (Gallery) view.findViewById(R.id.main_list_head_gallery);

		getRemData();
		galleryAdapter = new GalleryAdapter(reList, getActivity());
		gallery.setAdapter(galleryAdapter);

		listView.addHeaderView(view);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(clickListener);
		intent = getActivity().getIntent();
		// 测试数据

		MainGuangGao guang = new MainGuangGao(new Drawable[] { getResources().getDrawable(R.drawable.main_guang_gao),
				getResources().getDrawable(R.drawable.ic_launcher) }, null);
		addGuangGao(guang);

		return myView;

	}

	private void findInflate(View view) {

		main_guang_gao = (ViewFlipper) view.findViewById(R.id.main_image_guang_gao);
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

	OnItemClickListener clickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			intent.setClass(getActivity(), PersonalPostActivity.class);
			Post p = (Post) parent.getAdapter().getItem(position);
			YoheyApplication app = (YoheyApplication) getActivity().getApplication();
			app.data = p;
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
	private OnSendListener mListener = new OnSendListener() {

		@Override
		public void start() {
		}

		@Override
		public void end(String result) {
			JSONObject jor;
			try {
				jor = new JSONObject(result);
				if (jor.getInt("stauts") == 1) {
					JSONArray ja = jor.getJSONArray("result");
					list = new ArrayList<Post>();
					for (int i = 0; i < ja.length(); i++) {
						list.add(Post.paresJSONObject(ja.getJSONObject(i)));
					}
					adapter.setData(list);
				} else {
					Toast.makeText(getActivity(), jor.getString("message"), Toast.LENGTH_SHORT).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
				Log.w("HttpPost result", result);
			}
		}
	};

	private void getRemData() {
		HttpGet p = new HttpGet(YoheyApplication.ServiceIp + "/index.php/home/api/getrecommend");
		p.setOnSendListener(new OnSendListener() {

			public void start() {

			}

			public void end(String result) {
				try {
					JSONObject jjo = new JSONObject(result);
					if (jjo.getInt("stauts") == 1) {
						JSONArray ja = jjo.getJSONArray("result");
						for (int i = 0; i < ja.length(); i++) {
							Post p = Post.paresJSONObject(ja.getJSONObject(i));
							Log.i("remind post", i +"<><>"+ p.getId());
							reList.add(p);
						}
						galleryAdapter.setData(reList);
					}

				} catch (JSONException e) {

					e.printStackTrace();
				}
			}
		});
		p.send();
	}

	private void getData() {
		try {
			HttpPost p = HttpPost.parseUrl(YoheyApplication.ServiceIp + "/index.php/home/api/main");
			p.setOnSendListener(mListener);
			p.send();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private void findView() {
		listView = (ListView) myView.findViewById(R.id.main_list_posts);
	}

	/**
	 * 添加
	 * 
	 * @param MainGuangGao
	 *            广告图片drawable 与其相应的点击事件的封装类
	 */
	public void addGuangGao(MainGuangGao guang) {
		View v;
		for (int i = 0; i < guang.getImage().length; i++) {
			v = new View(getActivity());
			v.setBackgroundDrawable(guang.getImage()[i]);
			v.setId(i + 1);
			v.setOnClickListener(guang.listener);
			main_guang_gao.addView(v);
		}
		main_guang_gao.setInAnimation(getActivity(), R.drawable.push_left_in);
		main_guang_gao.setOutAnimation(getActivity(), R.drawable.push_right_out);
		main_guang_gao.setFlipInterval(7000);
		main_guang_gao.startFlipping();

	}

	/**
	 * 发送帖子
	 */
	public void sendPost() {

	}

	/**
	 * 首页广告的封装类,多张图片对应一个监听。 </br>
	 * 监听里面用switch case v.getid();id为1代表是第0张图片,2代表第1张图片;
	 * 
	 * @author kk0927
	 *
	 */
	public static class MainGuangGao {
		Drawable images[];
		OnClickListener listener;

		public MainGuangGao(Drawable[] image, OnClickListener listener) {
			this.images = image;
			this.listener = listener;
		}

		public Drawable[] getImage() {
			return images;
		}

		public OnClickListener getListener() {
			return listener;
		}

	}
}
