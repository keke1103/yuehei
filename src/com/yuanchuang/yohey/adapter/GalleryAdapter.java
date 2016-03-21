package com.yuanchuang.yohey.adapter;

import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.bmob.Post;
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
 * 主页面中间的画廊的BaseAdapter
 * 
 * @author Administrator
 *
 */
public class GalleryAdapter extends BaseAdapter {
	List<Post> list;
	Context context;
	LayoutInflater inflater;

	public GalleryAdapter(List<Post> list, Context context) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	public void setData(List<Post> list) {
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
		Post data = list.get(position);
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
		holder.add.setText("" + data.getJoincount());
		data.getUser().binderImageView(holder.head);
		holder.name.setText(data.getUser().getNickName());
		holder.time.setText(TimeUtil.formateTimeToNow(data.getCreatedAt()));
		holder.area.setText(data.getGame().getGameregion());
		holder.dan.setText(data.getGame().getGamedan());
		holder.context.removeAllViews();
		TextView text = new TextView(context);
		text.setText(data.getTitle());
		text.setPadding(10, 5, 0, 0);
		text.setTextSize(12);
		holder.context.addView(text);
		holder.message.setText(data.getComcount() + "");
		holder.up.setText(data.getLikenumber() + "");
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
