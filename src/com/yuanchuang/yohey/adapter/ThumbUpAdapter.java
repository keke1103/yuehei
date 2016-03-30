package com.yuanchuang.yohey.adapter;

import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.bmob.Comment;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ThumbUpAdapter extends BaseAdapter {
	List<Comment> list;
	Context context;
	LayoutInflater inflater;

	public ThumbUpAdapter() {
		 
	}

	public ThumbUpAdapter(List<Comment> list, Context context) {
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	public void setData(List<Comment> list) {
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

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_post_details, null);
			holder.head = (ImageView) convertView.findViewById(R.id.list_post_details_image_head);
			holder.name = (TextView) convertView.findViewById(R.id.list_post_details_text_name);
			holder.cont=(TextView) convertView.findViewById(R.id.list_post_details_text_context);
			holder.time=(TextView) convertView.findViewById(R.id.list_post_details_text_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.setData(list.get(position));		
		return convertView;
	}

	class ViewHolder {
		ImageView head;
		TextView name;
		TextView time;
		TextView cont;
		void setData(Comment com){
			com.getUser().binderImageView(head);
			name.setText(com.getUser().getNickName());
			time.setText(com.getCreatedAt());
			cont.setText(com.getContent());
		}
	}
}
