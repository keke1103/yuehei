package com.yuanchuang.yohey.adapter;

import java.util.List;
import java.util.Map;

import com.yuanchuang.yohey.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DynamicAdapter extends BaseAdapter {
	List<Map<String, Object>> list;
	Context context;
	LayoutInflater inflater;

	public DynamicAdapter() {

	}

	public DynamicAdapter(Context context, List<Map<String, Object>> list) {
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
		Log.i("position", position + "");
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_dynamic, null);
			holder.headPortrait = (ImageView) convertView.findViewById(R.id.list_dynamic_image_head_portrait);
			holder.nickNmae = (TextView) convertView.findViewById(R.id.list_dynamic_text_nickname);
			holder.time = (TextView) convertView.findViewById(R.id.list_dynamic_text_time);
			holder.line = (LinearLayout) convertView.findViewById(R.id.list_dynamic_layout_context);
			holder.forwarding = convertView.findViewById(R.id.list_dynamic_image_share_it);
			holder.leaveMessage = convertView.findViewById(R.id.list_dynamic_image_leave_a_message);
			holder.thumbUp = convertView.findViewById(R.id.list_dynamic_image_like);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Log.i("position", position + "");

			holder.headPortrait.setImageResource(R.drawable.ic_launcher);
			holder.nickNmae.setText((CharSequence) list.get(position).get("nickName"));
			holder.time.setText((CharSequence) list.get(position).get("time"));
			holder.line.removeAllViews();
			TextView tv = new TextView(context);
			tv.setText((CharSequence) list.get(position).get("cotext"));
			holder.line.addView(tv);
		return convertView;
	}

	class ViewHolder {
		ImageView headPortrait;// 用户头像
		TextView nickNmae;// 用户昵称
		TextView time;// 发送时间
		LinearLayout line;// 输入内容
		View forwarding;// 转发
		View leaveMessage;// 留言
		View thumbUp;// 点赞RelativeLayout relative;// 整体布局
	}
}
