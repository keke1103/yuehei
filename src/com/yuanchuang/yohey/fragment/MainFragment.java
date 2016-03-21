package com.yuanchuang.yohey.fragment;

import java.util.ArrayList;
import java.util.List;

import com.yuanchuang.yohey.OfficialinformationActivity;
import com.yuanchuang.yohey.PersonalPostActivity;
import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.RecommendActivity;
import com.yuanchuang.yohey.Vide0CollectionActivity;
import com.yuanchuang.yohey.adapter.GalleryAdapter;
import com.yuanchuang.yohey.adapter.MainAdapter;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.Game;
import com.yuanchuang.yohey.bmob.Post;
import com.yuanchuang.yohey.myData.AdapterData;

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
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

@SuppressWarnings("deprecation")
public class MainFragment extends Fragment {
	PopupWindow popupWindow;

	String str3[];// 游戏大区的具体数据
	ListView listView1;// 游戏大区的listview
	int listViewPosition;// listview1的第几项
	ListView listView2;// 游戏大区listview
	TextView gameZone;// 游戏大区
	ImageView gameDan;// 游戏段位
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
	LinearLayout more;// 更多
	RelativeLayout new_freads;// 撸友新动态
	RelativeLayout afarid_official;// 官方新动态
	RelativeLayout video_highlights;// 视屏集锦
	RelativeLayout win_points;// 转转赢积分
	/**
	 * 当前锁定区服;默认为null，就是全服
	 */
	String gameregion;

	@SuppressLint({ "InflateParams", "CutPasteId" })
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		LinearLayout lay = new LinearLayout(getActivity());
		lay.setLayoutParams(new LayoutParams(-1, -1));
		list = new ArrayList<Post>();
		reList = new ArrayList<Post>();
		myView = inflater.inflate(R.layout.activity_yue_lu_main, lay);
		findView();// 找到本页面的id

		View view = inflater.inflate(R.layout.list_head_view, null);
		findInflate(view);// 找到导入的头文件的id
		gallery = (Gallery) view.findViewById(R.id.main_list_head_gallery);
		galleryAdapter = new GalleryAdapter(reList, getActivity());
		gallery.setAdapter(galleryAdapter);
		getRemData(gameregion, -1, -1);

		adapter = new MainAdapter(list, getActivity());
		getPostData(gameregion, -1, -1);

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

	/**
	 * 设置PopupWindow
	 * 
	 * @param view
	 */
	@SuppressLint("InflateParams")
	private void showPopupWindow(View view, String str[]) {
		View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.game_zone_main, null);
		listView1 = (ListView) contentView.findViewById(R.id.game_zone_listview1);
		listView2 = (ListView) contentView.findViewById(R.id.game_zone_listview2);
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			list.add(str[i]);
		}
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
				list);
		listView1.setAdapter(arrayAdapter);
		popupWindow = new PopupWindow(contentView, 600, LayoutParams.WRAP_CONTENT, true);
		popupWindow.setTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.white));
		popupWindow.showAsDropDown(view);
	}

	/**
	 * 控件ID
	 * 
	 * @param view
	 */
	private void findInflate(View view) {
		main_guang_gao = (ViewFlipper) view.findViewById(R.id.main_image_guang_gao);
		playerHead1 = (ImageView) view.findViewById(R.id.main_image_recommended_user1);
		playerHead2 = (ImageView) view.findViewById(R.id.main_image_recommended_user2);
		playerHead3 = (ImageView) view.findViewById(R.id.main_image_recommended_user3);
		playerHead4 = (ImageView) view.findViewById(R.id.main_image_recommended_user4);
		playerHead5 = (ImageView) view.findViewById(R.id.main_image_recommended_user5);
		more = (LinearLayout) view.findViewById(R.id.main_image_recommended_geng_duo_linear);
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
			switch (parent.getId()) {
			case R.id.game_zone_listview2:
				if (listViewPosition == 2) {
					gameZone.setText(str3[position + 19]);
					Log.i(">>>>>>>>>>>>>>>", str3[position]);
				} else {
					gameZone.setText(str3[position]);
					Log.i(">>>>>>>>>>>>>>>", str3[position]);
				}
				popupWindow.dismiss();// 关闭popupWindow
				break;
			case R.id.main_list_posts:
				intent.setClass(getActivity(), PersonalPostActivity.class);
				Post p = (Post) parent.getAdapter().getItem(position);
				YoheyApplication app = (YoheyApplication) getActivity().getApplication();
				app.data = p;
				startActivity(intent);
				break;
			case R.id.game_zone_listview1:
				listViewPosition = position;
				str3 = getResources().getStringArray(R.array.game_region);
				ArrayList<String> list = new ArrayList<String>();
				switch (position) {
				case 0:
					for (int i = 0; i < str3.length; i++) {
						list.add(str3[i]);
					}
					break;
				case 1:
					for (int i = 0; i < 19; i++) {
						list.add(str3[i]);
					}
					break;
				case 2:
					for (int i = 0; i < 7; i++) {
						list.add(str3[i + 19]);
					}
					break;
				default:
					break;
				}
				listView2.setVisibility(View.VISIBLE);
				ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
						android.R.layout.simple_list_item_1, list);
				listView2.setAdapter(arrayAdapter);
				listView2.setOnItemClickListener(clickListener);
				break;
			default:
				break;
			}

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

			case R.id.main_image_recommended_geng_duo_linear:// 跳转到推荐开黑的列表
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
				intent = getActivity().getIntent();
				intent.setClass(getActivity(), Vide0CollectionActivity.class);
				startActivity(intent);
				break;
			case R.id.main_list_head_liner_win_points:

				break;
			case R.id.title_navigation_game_zone:// 游戏大区的点击事件
				String str[] = { "全部", "电信", "网通" };
				showPopupWindow(v, str);
				listView1.setOnItemClickListener(clickListener);
				break;
			case R.id.title_navigation_text_right_title:// 游戏段位的点击事件
				showPopupWindow(v, getResources().getStringArray(R.array.game_dan));
				break;
			default:
				break;
			}

		}
	};

	private void getRemData(String gameregion, int gamedanmin, int gamedanmax) {
		FindListener<Post> findLister = new FindListener<Post>() {

			public void onSuccess(List<Post> arg0) {
				galleryAdapter.setData(arg0);
			}

			public void onError(int arg0, String arg1) {
				Log.w("MainFragment", "" + arg1);
			}
		};

		BmobQuery<Post> query = new BmobQuery<Post>();
		query.setLimit(6);
		query.include("user");
		query.include("game");
		BmobQuery<Game> gameQuery = null;

		if (gameregion != null) {
			if (gameQuery == null)
				gameQuery = new BmobQuery<Game>();
			gameQuery.addWhereEqualTo("gameregion", gameregion);

		}
		if (gamedanmin > -1) {
			if (gameQuery == null)
				gameQuery = new BmobQuery<Game>();
			gameQuery.addWhereGreaterThanOrEqualTo("gamedan", gamedanmin);
		}
		if (gamedanmax > -1) {
			if (gameQuery == null)
				gameQuery = new BmobQuery<Game>();
			gameQuery.addWhereLessThanOrEqualTo("gamedan", gamedanmax);
		}
		if (gameQuery != null) {
			query.addWhereMatchesQuery("game", "Game", gameQuery);
		}
		query.addWhereGreaterThan("recommend", 0);
		query.findObjects(getActivity(), findLister);
	}

	/**
	 * 首页普通帖子数据的获取
	 * 
	 * @param gameregion
	 *            区服的筛选 (不筛选传null)
	 * @param gamedanmin
	 *            最小段位筛选(不筛选传-1)
	 * @param gamedanmax
	 *            最高段位筛选(不筛选传-1)
	 */
	private void getPostData(String gameregion, int gamedanmin, int gamedanmax) {

		FindListener<Post> findLister = new FindListener<Post>() {

			public void onSuccess(List<Post> arg0) {
				adapter.setData(arg0);
			}

			public void onError(int arg0, String arg1) {
				Log.w("MainFragment", "" + arg1);
			}
		};

		BmobQuery<Post> query = new BmobQuery<Post>();
		query.setLimit(6);
		query.include("user");
		query.include("game");
		BmobQuery<Game> gameQuery = null;

		if (gameregion != null) {
			if (gameQuery == null)
				gameQuery = new BmobQuery<Game>();
			gameQuery.addWhereEqualTo("gameregion", gameregion);
		}
		if (gamedanmin > -1) {
			if (gameQuery == null)
				gameQuery = new BmobQuery<Game>();
			gameQuery.addWhereGreaterThanOrEqualTo("gamedan", gamedanmin);
		}
		if (gamedanmax > -1) {
			if (gameQuery == null)
				gameQuery = new BmobQuery<Game>();
			gameQuery.addWhereLessThanOrEqualTo("gamedan", gamedanmax);
		}
		if (gameQuery != null) {
			query.addWhereMatchesQuery("game", "Game", gameQuery);
		}

		query.findObjects(getActivity(), findLister);
	}

	private void findView() {
		gameDan = (ImageView) myView.findViewById(R.id.title_navigation_text_right_title);
		gameZone = (TextView) myView.findViewById(R.id.title_navigation_game_zone);
		listView = (ListView) myView.findViewById(R.id.main_list_posts);
		gameZone.setOnClickListener(onClickListener);
		gameDan.setOnClickListener(onClickListener);
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

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == 1) {
			getPostData(gameregion, -1, -1);
			getRemData(gameregion, -1, -1);
		}
	}
}
