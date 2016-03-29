package com.yuanchuang.yohey.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.bmob.v3.datatype.BmobFile;

public class GalleryActivityAdapter extends BaseAdapter {
	BmobFile images[];
	Context context;
	LayoutInflater inflater;

	public GalleryActivityAdapter() {
		// TODO Auto-generated constructor stub
	}

	public GalleryActivityAdapter(BmobFile images[], Context context) {
		this.context = context;
		this.images = images;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return images[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();
			LinearLayout line = new LinearLayout(context);

			ImageView image = new ImageView(context);
			line.addView(image, -1, -1);
			image.setBackgroundColor(Color.LTGRAY);
			holder.image = image;
			line.setTag(holder);
			convertView = line;
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		images[position].loadImage(context, holder.image);
		return convertView;
	}

	class ViewHolder {
		ImageView image;
	}
}
