package com.yuanchuang.yohey.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuanchuang.yohey.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Friends_Fragment extends Fragment {
	ExpandableListView listview;
	List<String> list;// 用于存放分组 group text
	Map<String,List<String>> map;//存放child text，key 为 list中的元素。
	LayoutInflater inflater;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	LinearLayout linearlayout=new LinearLayout(getActivity());
	linearlayout.setLayoutParams(new LayoutParams(-1, -1));
	View view=inflater.inflate(R.layout.friends_fragment, null);
	listview=(ExpandableListView)view.findViewById(R.id.listview_expandable);
	list=new ArrayList<String>();
	map=new HashMap<String, List<String>>();
	list.add("约黑好友");
	list.add("游戏好友");
	/*
	 * for循环用于，添加数据
	 */
	for(int i=0;i<=1;i++)
	{
		List<String> arraylist=new ArrayList<String>();
		for(int j=0;j<=10;j++)
		{
			arraylist.add("约黑"+i);
		}
		
		map.put(list.get(i), arraylist);
		
	}
	this.inflater=inflater;//把当前的填充器给全局变量，以便在适配器使用
	MyAdapter adapter=new MyAdapter();
	listview.setAdapter(adapter);
	return view;

}
//可扩展列表的适配器
class MyAdapter extends BaseExpandableListAdapter
{

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		String key=list.get(groupPosition);
		
		return map.get(key).size();
	}
   @Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
	   
		return list.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		String key=list.get(groupPosition);
		return map.get(key).get(childPosition);
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
   public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}
//group View
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		Viewholder holder;
		String key=list.get(groupPosition);
		if(convertView==null)
		   {
			   holder=new Viewholder();
			   convertView= inflater.inflate(R.layout.group_view, null);
			   holder.image=(ImageView) convertView.findViewById(R.id.iv);
			   holder.text=(TextView) convertView.findViewById(R.id.tv);
			   convertView.setTag(holder);
		   }
		else
		{
			holder=(Viewholder) convertView.getTag();
		}
		
		holder.text.setText(key);
		//当列表是展开的时候用展开的图标
		if(isExpanded==true)
		{
			
			holder.image.setBackgroundResource(R.drawable.rub_course_descending_order_non_sel);
		}
		//当列表是合拢的时候用合拢的图标
		else
		{
			
			holder.image.setBackgroundResource(R.drawable.rub_course_go_to_next_right);
		}
		return convertView;
	}
//child view
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
	   Viewholder holder;
	   String key=list.get(groupPosition);
	   String msg=map.get(key).get(childPosition);
	   if(convertView==null)
	   {
		   holder=new Viewholder();
		   convertView= inflater.inflate(R.layout.child_view, null);
		   
		   holder.text=(TextView) convertView.findViewById(R.id.tv);
		   convertView.setTag(holder);
	   }
	   else
	   {
		   holder=(Viewholder) convertView.getTag();
	   }
	      holder.text.setText(msg);
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}
	

}
class Viewholder
{
TextView text;
ImageView image;
}
}
