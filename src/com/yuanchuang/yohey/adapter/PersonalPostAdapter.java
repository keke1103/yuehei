package com.yuanchuang.yohey.adapter;

import java.util.List;

import com.yuanchuang.yohey.PersonalInformationActivity;
import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.Comment;
import com.yuanchuang.yohey.tools.TimeUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
	List<Comment> list;
	Context context;
	LayoutInflater inflater;
	YoheyApplication app;
	Activity activity;

	public PersonalPostAdapter() {
		// TODO Auto-generated constructor stub
	}

	public PersonalPostAdapter(List<Comment> list, Context context, Activity activity) {
		this.list = list;
		this.context = context;
		this.activity = activity;
		app = (YoheyApplication) activity.getApplication();
		inflater = LayoutInflater.from(context);
	}

	public void setData(List<Comment> list) {
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
		Comment pc = list.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_post_details, null);
			holder.head = (ImageView) convertView.findViewById(R.id.list_post_details_image_head);
			holder.content = (TextView) convertView.findViewById(R.id.list_post_details_text_context);
			holder.name = (TextView) convertView.findViewById(R.id.list_post_details_text_name);
			holder.time = (TextView) convertView.findViewById(R.id.list_post_details_text_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		pc.getUser().binderImageView(holder.head);
		holder.name.setText(pc.getUser().getNickName());
		holder.time.setText(TimeUtil.formateTimeToNow(pc.getCreatedAt()));
		holder.content.setText(pc.getContent());
		holder.setHeadJump(pc);
		return convertView;
	}

	class ViewHolder {
		ImageView head;// 头像
		TextView name;// 姓名
		TextView time;// 时间
		TextView content;// 内容
		LinearLayout line;// 可能回复
		Comment com;

		void setHeadJump(Comment com) {
			this.com = com;
			head.setOnClickListener(onClickListener);

		}

		OnClickListener onClickListener = new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(activity, PersonalInformationActivity.class);
				app.data = com.getUser();
				activity.startActivity(intent);
			}
		};
	}

}
