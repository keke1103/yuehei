package com.yuanchuang.yohey.adapter;

import java.util.List;
import java.util.Map;

import com.yuanchuang.yohey.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ThumbUpAdapter extends BaseAdapter {
	List<Map<String, Object>> list;
	Context context;
	LayoutInflater inflater;

	public ThumbUpAdapter() {
		// TODO Auto-generated constructor stub
	}

	public ThumbUpAdapter(List<Map<String, Object>> list, Context context) {
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	public void getData(List<Map<String, Object>> list) {
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
		ViewHolder holder;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.list_thumb_up_view, null);
			holder.head=(ImageView) convertView.findViewById(R.id.list_thumb_up_head);
			holder.name=(TextView) convertView.findViewById(R.id.list_thumb_up_name);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		
		return convertView;
	}

	class ViewHolder {
		ImageView head;
		TextView name;
	}
}
