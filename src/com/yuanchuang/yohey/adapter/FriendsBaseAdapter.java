package com.yuanchuang.yohey.adapter;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.bmob.Group;
import com.yuanchuang.yohey.bmob.User;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendsBaseAdapter extends BaseExpandableListAdapter {
	LayoutInflater inflater;
	Context context;
	Group group[];

	public FriendsBaseAdapter() {
	}

	public FriendsBaseAdapter(Group group[], Context context) {
		this.group = group;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	public void setData(Group group[]) {
		this.group = group;
		notifyDataSetChanged();
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return group.length;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return group[groupPosition].getFriends().length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return group[groupPosition];
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return group[groupPosition].getFriends()[childPosition];
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
		return true;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		ClassifyHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.group, null);
			holder = new ClassifyHolder();
			holder.classifyName = (TextView) convertView.findViewById(R.id.tv_groupname);
			holder.icon = (ImageView) convertView.findViewById(R.id.iv_icon);
			holder.onlinenum = (TextView) convertView.findViewById(R.id.tv_onlinenum);
			holder.onlinetotal = (TextView) convertView.findViewById(R.id.tv_total);
			convertView.setTag(holder);
		} else {
			holder = (ClassifyHolder) convertView.getTag();
		}
		holder.classifyName.setText(group[groupPosition].getGroupName());
		holder.onlinenum.setText("" + group[groupPosition].getFriends().length);
		holder.onlinetotal.setText("" + group[groupPosition].getFriends().length);
		if (isExpanded) {
			holder.icon.setBackgroundResource(R.drawable.yo_hey_up_triangle);
		} else {
			holder.icon.setBackgroundResource(R.drawable.yo_hey_dwon_triangle);
		}
		return convertView;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		FriendHolder holder;
		User f = group[groupPosition].getFriends()[childPosition];
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.child, null);
			holder = new FriendHolder();
			holder.friendName = (TextView) convertView.findViewById(R.id.tv_childname);
			holder.headImage = (ImageView) convertView.findViewById(R.id.tv_childHeadImage);
			holder.friendPS = (TextView) convertView.findViewById(R.id.tv_sign);
			holder.friendWhether = (TextView) convertView.findViewById(R.id.tv_status);
			convertView.setTag(holder);
		} else {
			holder = (FriendHolder) convertView.getTag();
		}
		holder.friendName.setText(f.getNickName());
		f.binderImageView(holder.headImage);
		holder.friendPS.setText(f.getMood());
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	class ClassifyHolder {
		TextView classifyName;// 分类的名字
		TextView onlinenum;// 有多少好友在线
		TextView onlinetotal;// 有多少好友
		ImageView icon;// 图标
	}

	class FriendHolder {
		ImageView headImage;// 头像
		TextView friendName;// 好友名字
		TextView friendPS;// 个性签名
		TextView friendWhether;// 是否在线
	}
}
