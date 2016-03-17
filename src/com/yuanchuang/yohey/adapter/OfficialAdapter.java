package com.yuanchuang.yohey.adapter;

import java.util.List;

import com.tencent.connect.avatar.c;
import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.myData.AdapterData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_official_information_view, null);
			holder.head = (ImageView) convertView.findViewById(R.id.official_information_image_head);
			holder.title = (TextView) convertView.findViewById(R.id.official_information_text_title);
			holder.context = (TextView) convertView.findViewById(R.id.official_information_text_context);
			holder.time = (TextView) convertView.findViewById(R.id.official_information_text_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.head.setImageResource(R.drawable.guan_fang_zi_xun_1);
		holder.title.setText(list.get(position).getOfficial_announcement());
		holder.context.setText(R.string.nuokesashi);
		holder.time.setText(list.get(position).getOfficial_time());

		return convertView;
	}

	class ViewHolder {
		ImageView head;
		TextView title;
		TextView context;
		TextView time;
	}
}
