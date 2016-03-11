package com.yuanchuang.yohey.adapter;

import java.util.List;
import java.util.Map;

import com.yuanchuang.yohey.R;

import android.content.Context;
import android.location.SettingInjectorService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RecommendAdapter extends BaseAdapter {
	List<Map<String, Object>> list;
	Context context;
	LayoutInflater inflater;

	public RecommendAdapter() {
		// TODO Auto-generated constructor stub
	}

	public RecommendAdapter(List<Map<String, Object>> list, Context context) {
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
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_view_recommend, null);
			holder.head = (ImageView) convertView.findViewById(R.id.recommend_image_user_head);
			holder.name = (TextView) convertView.findViewById(R.id.recommend_text_user_name);
			holder.message = (TextView) convertView.findViewById(R.id.recommend_text_user_message);
			holder.messageIcon = (ImageView) convertView.findViewById(R.id.recommend_image_message_icon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.head.setBackgroundResource(R.drawable.ic_launcher);
		holder.name.setText((CharSequence) list.get(position).get("name"));

		holder.area.setText((CharSequence) list.get(position).get("bojin"));
		holder.message.setText((CharSequence) list.get(position).get("aiouniya"));
		holder.messageIcon.setBackgroundResource(R.drawable.ic_launcher);
		return convertView;
	}

	class ViewHolder {
		ImageView head;
		TextView name;
		TextView area;
		TextView message;
		ImageView messageIcon;
	}
}
