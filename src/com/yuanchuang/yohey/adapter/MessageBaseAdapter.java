package com.yuanchuang.yohey.adapter;

import java.util.ArrayList;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.bmob.User;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MessageBaseAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    ArrayList<User> list;
    public MessageBaseAdapter() {
	}
    public MessageBaseAdapter(ArrayList<User> list,Context context) {
    	this.list=list;
    	this.context=context;
    	inflater=LayoutInflater.from(context);
	}
    public void setData(ArrayList<User> list){
    	this.list=list;
    	notifyDataSetChanged();
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Viewholder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.message_listview, null);
			holder = new Viewholder();
			convertView.setTag(holder);
		} else {
			holder = (Viewholder) convertView.getTag();
		}

		return convertView;
	}
	class Viewholder {

	} 
}
