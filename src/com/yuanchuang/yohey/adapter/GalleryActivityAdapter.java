package com.yuanchuang.yohey.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GalleryActivityAdapter extends BaseAdapter {
	int[] pic;
	Context context;
	LayoutInflater inflater;
	LinearLayout line = null;

	public GalleryActivityAdapter() {
		// TODO Auto-generated constructor stub
	}

	public GalleryActivityAdapter(int[] pic, Context context) {
		this.context = context;
		this.pic = pic;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pic.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return pic[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (line == null) {
			holder = new ViewHolder();
			line = new LinearLayout(context);
			LayoutParams params = new LayoutParams(
					new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
			ImageView image = new ImageView(context);
			image.setLayoutParams(params);
			line.addView(image);
			holder.image = image;
			line.setTag(holder);
		} else {
			holder = (ViewHolder) line.getTag();
		}
		holder.image.setImageResource(pic[position]);

		return line;
	}

	class ViewHolder {
		ImageView image;
	}
}
