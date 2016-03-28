package com.yuanchuang.yohey.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.CommentDynamicActivity;
import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.adapter.DynamicAdapter;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.Share;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.myData.AdapterData;
import com.yuanchuang.yohey.tools.DensityUtil;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
	List<Share> list;
	DynamicAdapter mAdapter;
	TextView title;
	AdapterData data;// 数据类
	User user;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		LinearLayout lay = new LinearLayout(getActivity());
		lay.setLayoutParams(new LayoutParams(-1, -1));
		user = User.getCurrentUser(getActivity(), User.class);
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

		list = new ArrayList<Share>();
		getData();
		Log.i("DynamicFragment", "DynamicFragment");
		mAdapter = new DynamicAdapter(getActivity(), list);
		android.widget.AbsListView.LayoutParams params = new android.widget.AbsListView.LayoutParams(
				LayoutParams.FILL_PARENT, DensityUtil.dip2px(getActivity(), 134));
		View v = new View(getActivity());
		v.setBackgroundDrawable(getResources().getDrawable(R.drawable.dynamic_banner));
		v.setLayoutParams(params);
		v.setBackgroundResource(R.drawable.main_guang_gao);
		listView.addHeaderView(v);
		listView.setAdapter(mAdapter);
		listView.setOnItemClickListener(clickListener);

	}

	OnItemClickListener clickListener = new OnItemClickListener() {
		Intent intent;

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			intent = new Intent(getActivity(), CommentDynamicActivity.class);
			startActivity(intent);
		}
	};

	private void getData() {
		HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "getshare");
		get.putString("uid", user.getObjectId());
		OnSendListener mListener = new OnSendListener() {
			public void start() {

			}

			public void end(String result) {
				Log.i("DynamicFragment", result);
				try {
					JSONObject jo = new JSONObject(result);
					JSONArray ja = jo.getJSONArray("results");
					for (int i = 0; i < ja.length(); i++) {
						Share s = Share.parseJSONObject(ja.getJSONObject(i));
						list.add(s);
					}
					mAdapter.setData(list);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		};
		get.setOnSendListener(mListener);
		get.send();
	}

}
