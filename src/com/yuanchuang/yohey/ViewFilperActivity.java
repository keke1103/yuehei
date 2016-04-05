package com.yuanchuang.yohey;

import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.myData.Picture;
import com.yuanchuang.yohey.tools.DensityUtil;
import com.yuanchuang.yohey.view.BmobImageView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import cn.bmob.v3.datatype.BmobFile;

public class ViewFilperActivity extends Activity {
	GestureDetector gestureDetector;
	// ViewFlipper viewFlipper;
	BmobFile images[];
	YoheyApplication app;
	Intent intent;
	ImageView back;
	int index;

	@Override
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		app = (YoheyApplication) getApplication();
		images = (BmobFile[]) app.data;
		app.data = null;

		intent = getIntent();
		index = intent.getExtras().getInt("index");
		RelativeLayout line = new RelativeLayout(this);
		line.setBackgroundColor(getResources().getColor(R.color.black));
		LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(-2, -2);
		back = new ImageView(this);
		back.setPadding(DensityUtil.dip2px(this, 15), DensityUtil.dip2px(this, 15), DensityUtil.dip2px(this, 15),
				DensityUtil.dip2px(this, 15));
		back.setImageResource(R.drawable.yo_hey_back_image);

		back.setLayoutParams(layout);
		ViewPager mView = new ViewPager(this);
		android.view.ViewGroup.LayoutParams params = new LayoutParams(-1, -1);
		mView.setLayoutParams(new LayoutParams(-1, -1));
		line.addView(mView);
		line.addView(back);

		setContentView(line, params);
		Log.i("ViewFilperActivity", "back" + back.isClickable());
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("ViewFilperActivity", "finish");
				finish();
			}
		});

		mView.setAdapter(new PagerAdapter());

		mView.setCurrentItem(index);

	}

	class PagerAdapter extends android.support.v4.view.PagerAdapter {

		public PagerAdapter() {

		}

		@Override
		public int getCount() {

			return images.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {

			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BmobImageView v = new BmobImageView(ViewFilperActivity.this);
			v.setId(1000 + position);
			// images[position].loadImage(ViewFilperActivity.this, v);
			Picture.showBmobImage(images[position], v);
			container.addView(v, -1, -1);
			return v;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {

			container.removeView(container.findViewById(position + 1000));
		}
	}
}
