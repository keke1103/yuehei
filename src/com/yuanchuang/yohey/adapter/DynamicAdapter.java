package com.yuanchuang.yohey.adapter;

import java.util.List;

import com.yuanchuang.yohey.CommentDynamicActivity;
import com.yuanchuang.yohey.ForwardingActivity;
import com.yuanchuang.yohey.GalleryActivity;
import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.ThumbUpActivity;
import com.yuanchuang.yohey.bmob.Share;
import com.yuanchuang.yohey.tools.DensityUtil;
import com.yuanchuang.yohey.tools.TimeUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 动态BaseAdapter
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("deprecation")
public class DynamicAdapter extends BaseAdapter {
	List<Share> list;
	Context context;
	LayoutInflater inflater;
	ImageView image;
	TextView tt;

	public DynamicAdapter() {

	}

	public DynamicAdapter(Context context, List<Share> list) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	public void setData(List<Share> list) {
		this.list = list;
		notifyDataSetChanged();
	}

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

	@SuppressLint({ "InflateParams", "ResourceAsColor" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		Log.i("position", position + "");
		Share mShare = list.get(position);
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
		holder.nickNmae.setText(mShare.getUser().getNickName());
		mShare.getUser().binderImageView(holder.headPortrait);
		holder.time.setText(TimeUtil.formateTimeToNow(mShare.getCreatedAt()));
		holder.line.setText(mShare.getContent());
		if (mShare.getImages() != null) {

			DensityUtil.sudoku(context, holder.imageLayout,
					mShare.getImages().toArray(new BmobFile[mShare.getImages().size()]));
		}
		holder.forwarding.setOnClickListener(clickListener);
		holder.thumbUp.setOnClickListener(clickListener);
		holder.leaveMessage.setOnClickListener(clickListener);
		holder.imageLayout.setOnClickListener(clickListener);
		return convertView;
	}

	OnClickListener clickListener = new OnClickListener() {
		Intent intent;

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.list_dynamic_image_share_it:
				intent = new Intent(context, ForwardingActivity.class);
				context.startActivity(intent);
				break;
			case R.id.list_dynamic_image_leave_a_message:
				intent = new Intent(context, CommentDynamicActivity.class);
				context.startActivity(intent);
				break;
			case R.id.list_dynamic_image_like:
				intent = new Intent(context, ThumbUpActivity.class);
				context.startActivity(intent);
				break;
			case R.id.list_dynamic_absolute_image:
				intent = new Intent(context, GalleryActivity.class);
				context.startActivity(intent);
				break;
			default:
				break;
			}

		}
	};

	class ViewHolder {
		ImageView headPortrait;// 用户头像
		TextView nickNmae;// 用户昵称
		TextView time;// 发送时间
		TextView line;// 输入内容
		View forwarding;// 转发
		View leaveMessage;// 留言
		View thumbUp;// 点赞RelativeLayout relative;// 整体布局

		AbsoluteLayout imageLayout;
	}
}
