package com.yuanchuang.yohey.adapter;

import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.myData.AdapterData;
import com.yuanchuang.yohey.tools.DensityUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * 动态BaseAdapter
 * 
 * @author Administrator
 *
 */
public class DynamicAdapter extends BaseAdapter {
	List<AdapterData> list;
	Context context;
	LayoutInflater inflater;
	ImageView image;
	TextView tt;

	public DynamicAdapter() {

	}

	public DynamicAdapter(Context context, List<AdapterData> list) {
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

	@SuppressWarnings("deprecation")
	@SuppressLint({ "InflateParams", "ResourceAsColor" })
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
			holder.line = (TextView) convertView.findViewById(R.id.list_dynamic_layout_context);
			holder.forwarding = convertView.findViewById(R.id.list_dynamic_image_share_it);
			holder.leaveMessage = convertView.findViewById(R.id.list_dynamic_image_leave_a_message);
			holder.thumbUp = convertView.findViewById(R.id.list_dynamic_image_like);
			holder.imageLayout = (AbsoluteLayout) convertView.findViewById(R.id.list_dynamic_absolute_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Log.i("position", position + "");

		holder.headPortrait.setImageResource(R.drawable.tui_jian_kai_hei_1);
		holder.nickNmae.setText((CharSequence) list.get(position).getDy_name());
		holder.time.setText((CharSequence) list.get(position).getDy_time());
		holder.line.setText(list.get(position).getDy_context());
		DensityUtil.sudoku(context, holder.imageLayout, list.get(position).getDy_image());

		return convertView;
	}

	class ViewHolder {
		ImageView headPortrait;// 用户头像
		TextView nickNmae;// 用户昵称
		TextView time;// 发送时间
		TextView line;// 输入内容
		View forwarding;// 转发
		View leaveMessage;// 留言
		View thumbUp;// 点赞RelativeLayout relative;// 整体布局

		@SuppressWarnings("deprecation")
		AbsoluteLayout imageLayout;
	}
}
