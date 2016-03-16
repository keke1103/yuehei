package com.yuanchuang.yohey.adapter;

import java.util.List;

import com.yuanchuang.yohey.myData.AdapterData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 官方资讯
 * 
 * @author Administrator
 *
 */
public class OfficialAdapter extends BaseAdapter {
	List<AdapterData> list;
	Context context;
	LayoutInflater inflater;

	public OfficialAdapter() {
		// TODO Auto-generated constructor stub
	}

	public OfficialAdapter(List<AdapterData> list, Context context) {
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	public void setData(List<AdapterData> list) {
		this.list = list;
		this.notifyDataSetChanged();
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
