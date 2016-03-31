package com.yuanchuang.yohey.adapter;

import java.util.ArrayList;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.myData.MssageListData;
import com.yuanchuang.yohey.tools.TimeUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MessageBaseAdapter extends BaseAdapter {
	LayoutInflater inflater;
	Context context;
	ArrayList<MssageListData> list;

	public MessageBaseAdapter() {
	}

	public MessageBaseAdapter(ArrayList<MssageListData> list, YoheyApplication app) {
		this.list = list;
		this.app = app;
		this.context = app.getApplicationContext();
		inflater = LayoutInflater.from(context);
	}

	public void setData(ArrayList<MssageListData> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	public void addMssage(MssageListData data) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getFriendId().equals(data.getFriendId())) {
				list.remove(i);
				list.add(0, data);
				notifyDataSetChanged();
				return;
			}
		}
		list.add(0, data);
		notifyDataSetChanged();
	}

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
		Viewholder holder;
		MssageListData data = list.get(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.message_listview, null);
			holder = new Viewholder();
			holder.name = (TextView) convertView.findViewById(R.id.tv_childname);
			holder.time = (TextView) convertView.findViewById(R.id.tv_status);
			holder.content = (TextView) convertView.findViewById(R.id.tv_sign);
			holder.count = (TextView) convertView.findViewById(R.id.tv_count);
			holder.head = (ImageView) convertView.findViewById(R.id.tv_image);
			convertView.setTag(holder);
		} else {
			holder = (Viewholder) convertView.getTag();
		}
		holder.setData(data);
		return convertView;
	}

	YoheyApplication app;

	public void setAppForShowData(YoheyApplication app) {
		this.app = app;
		notifyDataSetChanged();
	}

	class Viewholder {
		TextView name;
		TextView time;
		TextView content;
		TextView count;
		ImageView head;
		User f;

		void setData(MssageListData d) {
			content.setText(d.getMsg());
			time.setText(TimeUtil.formateTimeToNow(d.getTime() * 1000L));
			if (app != null && app.getFriendById(d.getFriendId()) != null) {
				f = app.getFriendById(d.getFriendId());
				f.binderImageView(head);
				name.setText(f.getNickName());
			}
		}
	}
}
