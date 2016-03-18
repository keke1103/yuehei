package com.yuanchuang.yohey.adapter;

import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.myData.PostComment;
import com.yuanchuang.yohey.tools.TimeUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 帖子详情
 * 
 * @author Administrator
 *
 */
public class PersonalPostAdapter extends BaseAdapter {
	List<PostComment> list;
	Context context;
	LayoutInflater inflater;

	public PersonalPostAdapter() {
		// TODO Auto-generated constructor stub
	}

	public PersonalPostAdapter(List<PostComment> list, Context context) {
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	public void setData(List<PostComment> list) {
		this.list = list;
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
		ViewHolder holder;
		PostComment pc = list.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_post_details, null);
			holder.head = (ImageView) convertView.findViewById(R.id.list_post_details_image_head);
			holder.context = (TextView) convertView.findViewById(R.id.list_post_details_text_context);
			holder.name = (TextView) convertView.findViewById(R.id.list_post_details_text_name);
			holder.time = (TextView) convertView.findViewById(R.id.list_post_details_text_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		pc.getUser().bindHeaderView(holder.head);
		holder.name.setText(pc.getUser().getUsername());
		holder.time.setText(TimeUtil.formateTimeToNow(pc.getTime() * 1000L));
		holder.context.setText(pc.getContent());
		return convertView;
	}

	class ViewHolder {
		ImageView head;// 头像
		TextView name;// 姓名
		TextView time;// 时间
		TextView context;// 内容
		LinearLayout line;// 可能回复
	}

}
