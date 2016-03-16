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
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 主页面的adapter
 * @author Administrator
 *
 */
public class MainAdapter extends BaseAdapter {
	List<AdapterData> list;
	Context context;
	LayoutInflater inflater;

	public MainAdapter() {
		// TODO Auto-generated constructor stub
	}

	public MainAdapter(List<AdapterData> list, Context context) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
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
			convertView = inflater.inflate(R.layout.list_main_post, null);
			holder.add = (TextView) convertView.findViewById(R.id.list_main_text_add);
			holder.area = (TextView) convertView.findViewById(R.id.list_main_text_area);
			holder.context = (LinearLayout) convertView.findViewById(R.id.list_main_linear_context);
			holder.dan = (TextView) convertView.findViewById(R.id.list_main_text_dan);
			holder.head = (ImageView) convertView.findViewById(R.id.list_main_image_head_tu);
			holder.message = (TextView) convertView.findViewById(R.id.list_main_text_message);
			holder.name = (TextView) convertView.findViewById(R.id.list_main_text_nick);
			holder.time = (TextView) convertView.findViewById(R.id.list_main_text_time);
			holder.up = (TextView) convertView.findViewById(R.id.list_main_text_up);
			holder.line = convertView.findViewById(R.id.list_main_line_zhong);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.add.setText(list.get(position).getMain_add() + "");
		holder.name.setText(list.get(position).getMain_name());
		holder.time.setText(list.get(position).getMain_time());
		holder.context.removeAllViews();
		TextView text = new TextView(context);
		text.setText(list.get(position).getMain_context());
		text.setPadding(10, 5, 0, 0);
		text.setTextSize(12);
		holder.context.addView(text);
		holder.line.setVisibility(View.GONE);
		holder.area.setText(list.get(position).getMain_area());
		holder.dan.setText(list.get(position).getMain_dan());
		holder.head.setImageResource(R.drawable.meng_mei_head);
		holder.message.setText(list.get(position).getMain_message() + "");
		holder.up.setText(list.get(position).getMain_up() + "");
		return convertView;
	}

	class ViewHolder {
		View line;
		ImageView head;
		TextView name;
		TextView time;
		LinearLayout context;
		TextView area;
		TextView dan;
		TextView add;
		TextView message;
		TextView up;
	}
}
