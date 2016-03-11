package com.yuanchuang.yohey.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.R.drawable;
import com.yuanchuang.yohey.R.id;
import com.yuanchuang.yohey.R.layout;
import com.yuanchuang.yohey.fragment.Message_Fragment.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Message_Fragment extends Fragment {
	Map<String,String> map;
	List<Map<String,String>> list;
	ListView listview;
	String[] str={"赵日天","黑色玫瑰妹纸","12：08","3"};
	LayoutInflater inflater;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	LinearLayout linearlayout=new LinearLayout(getActivity());
	linearlayout.setLayoutParams(new LayoutParams(-1, -1));
	View view=inflater.inflate(R.layout.message_fragment, null);
	listview=(ListView)view.findViewById(R.id.listview);
	list=new ArrayList<Map<String,String>>();
	this.inflater=inflater;
	for(int i=0;i<50;i++)
	{
		map=new HashMap<String, String>();
		map.put("lefttop", str[0]);
		map.put("leftbot", str[1]);
		map.put("righttop", str[2]);
		map.put("rightbot", str[3]);
		list.add(map);
	}
	Adapter adapter=new Adapter(list);
	listview.setAdapter(adapter);
	 return view;
}
class Adapter extends BaseAdapter  //构造传进来的arraylist
{
	List<Map<String,String>> arraylist;
	
public Adapter(List<Map<String,String>> list)
{
	this.arraylist=list;
	
}

public int getCount() {
		// TODO Auto-generated method stub
		return arraylist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arraylist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Viewholder holder;
		if(convertView==null)
		{
			convertView=inflater.inflate(R.layout.message_listview, null);
			holder=new Viewholder();
			holder.image=(ImageView)convertView.findViewById(R.id.image);
			holder.lefttop=(TextView)convertView.findViewById(R.id.left_top);
			holder.leftbottom=(TextView)convertView.findViewById(R.id.left_bot);
			holder.righttop=(TextView)convertView.findViewById(R.id.right_top);
			holder.rightbottom=(TextView)convertView.findViewById(R.id.right_bot);
			convertView.setTag(holder);
		}
		else
		{
			holder=(Viewholder) convertView.getTag();
		}
		holder.image.setBackgroundResource(R.drawable.mm);
		holder.lefttop.setText(arraylist.get(position).get("lefttop"));
		holder.leftbottom.setText(arraylist.get(position).get("leftbot"));
		holder.righttop.setText(arraylist.get(position).get("righttop"));
		holder.rightbottom.setText(arraylist.get(position).get("rightbot"));
		return convertView;
	}
	}
class Viewholder
{
	ImageView image;
	TextView lefttop;
	TextView leftbottom;
	TextView righttop;
	TextView rightbottom;
	
}

}
