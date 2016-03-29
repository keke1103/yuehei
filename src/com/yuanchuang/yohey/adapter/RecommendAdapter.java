package com.yuanchuang.yohey.adapter;

import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.myData.AdapterData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 推荐开黑
 * 
 * @author Administrator
 *
 */
public class RecommendAdapter extends BaseAdapter {
	List<AdapterData> list;
	Context context;
	LayoutInflater inflater;
	AdapterData data;

	public RecommendAdapter() {
		// TODO Auto-generated constructor stub
	}

	public RecommendAdapter(List<AdapterData> list, Context context) {
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

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_view_recommend, null);
			holder.head = (ImageView) convertView.findViewById(R.id.recommend_image_user_head);
			holder.name = (TextView) convertView.findViewById(R.id.recommend_text_user_name);
			holder.area = (TextView) convertView.findViewById(R.id.recommend_text_user_area);
			holder.dan = (TextView) convertView.findViewById(R.id.recommend_text_user_dan);
			holder.message = (TextView) convertView.findViewById(R.id.recommend_text_user_message);
			holder.messageIcon = (ImageView) convertView.findViewById(R.id.recommend_image_message_icon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.head.setBackgroundResource(R.drawable.yp_hey_background_image);
		holder.name.setText(list.get(position).getReco_name());
		holder.dan.setText(list.get(position).getReco_dan());
		holder.area.setText(list.get(position).getReco_area());
		holder.message.setText(list.get(position).getReco_message());

		return convertView;
	}

	class ViewHolder {
		ImageView head;// 头像
		TextView name;// 昵称
		TextView area;// 大区
		TextView dan;// 段位
		TextView message;// 玩家发帖
		ImageView messageIcon;//
	}
}
