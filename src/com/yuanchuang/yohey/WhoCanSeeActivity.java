package com.yuanchuang.yohey;

import java.util.HashMap;
import java.util.Map;

import com.yuanchuang.yohey.adapter.WhoExpandableAdpter;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
	Map<String, Object> map;
	ExpandableListView listView;
	private int lastClick = -1;// 上一次点击的group的position

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_who_can_see);
		// mainTable = new ArrayList<Map<String, Object>>();
		// childTeble = new ArrayList<Map<String, Object>>();

		mainTable = new String[] { "公开", "私密", "部分可见", "不给谁看" };
		childTeble = new String[][] { { "小岚", "泡泡" }, { "小岚", "泡泡" }, { "小岚", "泡泡" }, { "小岚", "泡泡" } };
		findView();

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
				Log.i(">>>>>>", parent + "<<<" + v + ".." + groupPosition + "123" + childPosition);
				myAdapter.notifyDataSetChanged();
				return false;
			}
		});
		myAdapter = new WhoExpandableAdpter(mainTable, childTeble, this);
		listView.setAdapter(myAdapter);
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
	}
}
