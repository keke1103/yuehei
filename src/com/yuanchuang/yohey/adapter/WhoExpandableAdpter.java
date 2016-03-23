package com.yuanchuang.yohey.adapter;

import com.yuanchuang.yohey.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 谁可以看&&谁不能看
 * 
 * @author Administrator
 *
 */
public class WhoExpandableAdpter extends BaseExpandableListAdapter {
	String[] mainTable;
	String[][] childTeble;
	Context context;
	LayoutInflater inflater;

	public WhoExpandableAdpter() {
		// TODO Auto-generated constructor stub
	}

	public WhoExpandableAdpter(String[] mainTable, String[][] childTeble, Context context) {
		this.mainTable = mainTable;
		this.childTeble = childTeble;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return mainTable.length;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub

		return childTeble[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return mainTable[groupPosition];
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub

		return childTeble[groupPosition][childPosition];
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub

		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_view_who_see_mi_head_view, null);
			holder.mainTitle = (TextView) convertView.findViewById(R.id.list_who_can_see_text_public);
			holder.mainContent = (TextView) convertView.findViewById(R.id.list_who_can_see_text_frisnds);
			holder.mainBox = (ImageView) convertView.findViewById(R.id.list_who_can_see_check_box);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (isExpanded) {
			holder.mainBox.setBackgroundResource(R.drawable.rbi_circular);
		} else {
			holder.mainBox.setBackgroundResource(R.drawable.unselected_round);
		}
		holder.mainContent.setText(mainTable[groupPosition].toString());
		holder.mainTitle.setText(mainTable[groupPosition].toString());
		return convertView;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		boolean bool = false;
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_view_who_child_view, null);
			holder.childName = (TextView) convertView.findViewById(R.id.list_child_who_can_see_text_public);
			holder.childContent = (TextView) convertView.findViewById(R.id.list_child_who_can_see_text_frisnds);
			holder.childBox = (ImageView) convertView.findViewById(R.id.list_child_who_can_see_check_box);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (bool) {
			holder.childBox.setBackgroundResource(R.drawable.tick_round);
		} else {
			holder.childBox.setBackgroundResource(R.drawable.unselected_round);
		}

		holder.childContent.setText(childTeble[groupPosition][childPosition]);
		holder.childName.setText(childTeble[groupPosition][childPosition]);
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

	class ViewHolder {
		// 一级导航
		TextView mainTitle;// 标题
		TextView mainContent;// 内容
		ImageView mainBox;
		// 二级导航
		TextView childName;// 标题
		TextView childContent;// 内容
		ImageView childBox;
	}

	public void expandGroup(int i) {
		// TODO Auto-generated method stub

	}
}
