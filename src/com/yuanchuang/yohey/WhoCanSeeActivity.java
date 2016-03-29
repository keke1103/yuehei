package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.adapter.WhoExpandableAdpter;
import com.yuanchuang.yohey.myData.AdapterData;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;

/**
 * 谁可以看页面
 * 
 * @author Administrator
 *
 */
public class WhoCanSeeActivity extends Activity {
	// List<Map<String, Object>> mainTable;
	// List<Map<String, Object>> childTeble;
	String[] mainTable;
	String[][] childTeble;
	WhoExpandableAdpter myAdapter;
	View toReturn;
	TextView title;
	TextView rightTitle;

	ExpandableListView listView;
	private int lastClick = -1;// 上一次点击的group的position
	boolean bool;

	Map<Integer, ArrayList<AdapterData>> list;
	private OnClickListener clike = new OnClickListener() {

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

	@SuppressLint("UseSparseArrays")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_who_can_see);
		// mainTable = new ArrayList<Map<String, Object>>();
		// childTeble = new ArrayList<Map<String, Object>>();
		list = new HashMap<Integer, ArrayList<AdapterData>>();

		mainTable = new String[] { "公开", "私密", "部分可见", "不给谁看" };
		childTeble = new String[][] { { "所有人都可以看" }, { "只有自己能看" }, { "选中的可以看" }, { "选中的不能看" } };
		findView();
		getData();
		listView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				if (lastClick == -1) {
					listView.expandGroup(groupPosition);
				}

				if (lastClick != -1 && lastClick != groupPosition) {
					listView.collapseGroup(lastClick);
					listView.expandGroup(groupPosition);
				} else if (lastClick == groupPosition) {
					if (listView.isGroupExpanded(groupPosition))
						listView.collapseGroup(groupPosition);
					else if (!listView.isGroupExpanded(groupPosition))
						listView.expandGroup(groupPosition);
				}

				lastClick = groupPosition;
				myAdapter.notifyDataSetChanged();
				Log.i("<<<<<<", parent + "<>" + id);
				return true;
			}
		});
		listView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition,
					long id) {

				myAdapter.notifyDataSetChanged();
				return false;
			}
		});
		myAdapter = new WhoExpandableAdpter(mainTable, childTeble, this, list);
		listView.setAdapter(myAdapter);
	}

	private void getData() {
		ArrayList<AdapterData> array;
		AdapterData data;
		for (int i = 0; i < 4; i++) {

			array = new ArrayList<AdapterData>();
			if (i < 2) {

			} else {
				for (int j = 0; j < 4; j++) {
					data = new AdapterData();
					data.setWhoName("小岚");
					data.setWhoContent("泡泡");
					array.add(data);
				}
			}
			list.put(i, array);
			Log.i("data", array.size() + "sss" + list.get(i).size());
		}
		for (int i = 0; i < 4; i++) {

		}
	}

	@Override
	protected void onRestart() {
		myAdapter.notifyDataSetChanged();
		for (int i = 0; i < myAdapter.getGroupCount(); i++) {
			myAdapter.expandGroup(i);
		}
		super.onRestart();
	}

	@SuppressWarnings("deprecation")
	private void findView() {
		listView = (ExpandableListView) findViewById(R.id.who_can_see_expand_list);
		toReturn = findViewById(R.id.title_navigation_back_icon);
		title = (TextView) findViewById(R.id.title_navigation_text_title);
		rightTitle = (TextView) findViewById(R.id.title_navigation_text_right_title);
		title.setText("谁可以看");
		Resources resources = getResources();
		Drawable drawable = resources.getDrawable(R.drawable.shape_rounded_yellow);
		rightTitle.setVisibility(View.VISIBLE);
		rightTitle.setBackgroundDrawable(drawable);
		rightTitle.setText("完成");
		toReturn.setVisibility(View.VISIBLE);
		toReturn.setOnClickListener(clike);
	}
}
