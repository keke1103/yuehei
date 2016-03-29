package com.yuanchuang.yohey;

import com.yuanchuang.yohey.adapter.GalleryActivityAdapter;
import com.yuanchuang.yohey.app.YoheyApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Gallery;
import cn.bmob.v3.datatype.BmobFile;

@SuppressWarnings("deprecation")
public class GalleryActivity extends Activity {
	Gallery gallery;
	GalleryActivityAdapter myAdapter;
	YoheyApplication app;

	int index;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_hei_gallary);
		app = (YoheyApplication) getApplication();
		BmobFile[] images = (BmobFile[]) app.data;
		app.data = null;
		intent = getIntent();
		index = intent.getExtras().getInt("index");
		gallery = (Gallery) findViewById(R.id.gallary_main);
		getData();
		myAdapter = new GalleryActivityAdapter(images, getBaseContext());
		Log.i("GalleryActivity", "index=" + index);
		gallery.setAdapter(myAdapter);
		gallery.setSelection(index);
	}

	private void getData() {

	}
}
