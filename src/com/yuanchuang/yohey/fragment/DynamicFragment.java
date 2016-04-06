package com.yuanchuang.yohey.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.adapter.DynamicAdapter;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.Share;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.myData.AdapterData;
import com.yuanchuang.yohey.tools.DensityUtil;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;
import com.yuanchuang.yohey.tools.OnFlushOldData;
import com.yuanchuang.yohey.view.MyListView;
import com.yuanchuang.yohey.view.MyListView.OnRefreshListener;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;

/**
 * 动态页面
 * 
 * @author Administrator
 *
 */
public class DynamicFragment extends Fragment {
	MyListView listView;
	RelativeLayout layoutTitle;// 标题布局
	View mView;
	View moreView;// listview加载更多的数据
	List<Share> list;
	DynamicAdapter mAdapter;
	TextView title;
	AdapterData data;// 数据类
	User user;
	int pager;// 加载的数据的页数

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		LinearLayout lay = new LinearLayout(getActivity());
		lay.setLayoutParams(new LayoutParams(-1, -1));
		user = BmobUser.getCurrentUser(getActivity(), User.class);
		mView = inflater.inflate(R.layout.activity_yue_lu_dynamic, lay);
		moreView = inflater.inflate(R.layout.more_view_main, null);

		findView();

		return mView;
	}

	@SuppressWarnings("deprecation")
	private void findView() {
		listView = (MyListView) mView.findViewById(R.id.dynamic_list_view);
		layoutTitle = (RelativeLayout) mView.findViewById(R.id.dynamic_title);
		title = (TextView) layoutTitle.findViewById(R.id.title_navigation_text_title);
		title.setText(R.string.dynamic);

		list = new ArrayList<Share>();
		getData();
		Log.i("DynamicFragment", "DynamicFragment");
		mAdapter = new DynamicAdapter(getActivity(), getActivity().getApplication(), list);
		android.widget.AbsListView.LayoutParams params = new android.widget.AbsListView.LayoutParams(
				LayoutParams.FILL_PARENT, DensityUtil.dip2px(getActivity(), 134));
		View v = new View(getActivity());
		v.setBackgroundDrawable(getResources().getDrawable(R.drawable.dynamic_banner));
		v.setLayoutParams(params);
		v.setBackgroundResource(R.drawable.main_guang_gao);

		listView.addHeaderView(v);
		//listView.addFooterView(moreView);
		
		listView.addFooterView(moreView, null, false);

		listView.setAdapter(mAdapter);
		listView.setonRefreshListener(refreshListener);
		mAdapter.setOnFlushOldData(new OnFlushOldData() {

			@Override
			public void flush(BaseAdapter adapter, int position) {
				pager++;
				frushOldShare();
			}
		});
	}

	private OnRefreshListener refreshListener = new OnRefreshListener() {

		@Override
		public void onRefresh() {
			pager = 0;
			getData();
		}
	};

	private void getData() {
		HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "getshare");
		get.putString("uid", user.getObjectId());
		get.putString("pager", "" + pager);
		OnSendListener mListener = new OnSendListener() {
			@Override
			public void start() {

			}

			@Override
			public void end(String result) {
				Log.i("DynamicFragment", result);
				try {
					JSONObject jo = new JSONObject(result);
					JSONArray ja = jo.getJSONArray("results");
					if (ja.length() < 1) {
						listView.removeFooterView(moreView);
						Toast.makeText(getActivity(), "没有数据了", Toast.LENGTH_SHORT).show();
						return;
					}
					for (int i = 0; i < ja.length(); i++) {
						Share s = Share.parseJSONObject(ja.getJSONObject(i));
						list.add(s);
					}
					mAdapter.setData(list);
					listView.onRefreshComplete();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		};
		get.setOnSendListener(mListener);
		get.send();
	}

	private void frushOldShare() {
		HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "getshare");
		get.putString("uid", user.getObjectId());
		get.putString("pager", "" + pager);
		OnSendListener mListener = new OnSendListener() {
			@Override
			public void start() {

			}

			@Override
			public void end(String result) {
				Log.i("DynamicFragment", result);
				try {
					JSONObject jo = new JSONObject(result);
					JSONArray ja = jo.getJSONArray("results");
					if (ja.length() < 1) {
						listView.removeFooterView(moreView);
						//Toast.makeText(getActivity(), "没有数据了", Toast.LENGTH_SHORT).show();
						return;
					}
					for (int i = 0; i < ja.length(); i++) {
						Share s = Share.parseJSONObject(ja.getJSONObject(i));
						mAdapter.getData().add(s);
						mAdapter.notifyDataSetChanged();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		};
		get.setOnSendListener(mListener);
		get.send();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case 1:
			mAdapter.notifyDataSetChanged();
			break;
		case 2:
			YoheyApplication app = (YoheyApplication) (getActivity().getApplication());
			// mAdapter.addData((Share) app.data);
			app.data = null;
			break;
		default:
			break;
		}

	}

	public void onDestroy() {

		super.onDestroy();
	}

}
