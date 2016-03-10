package com.yuanchuang.yohey.adapter;

import java.util.List;

import com.yuanchuang.yohey.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DynamicAdapter extends BaseAdapter {
	List<String> list;
	Context context;
	LayoutInflater inflater;

	public DynamicAdapter() {

	}

	public DynamicAdapter(Context context, List<String> list) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.list_dynamic, null);
			holder.headPortrait=convertView.findViewById(R.id.list_dynamic_image_head_portrait);
			//holder.nickNmae=convertView.findViewById(R.id.list_dynamic_image_leave_a_message)
		}
		return null;
	}

	class ViewHolder {
		View headPortrait;
		TextView nickNmae;
		TextView time;
		LinearLayout line;
		View forwarding;
		View leaveMessage;
		View thumbUp;
	}
}
