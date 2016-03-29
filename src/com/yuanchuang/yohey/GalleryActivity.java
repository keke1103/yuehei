package com.yuanchuang.yohey;

import com.yuanchuang.yohey.adapter.GalleryActivityAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Gallery;

@SuppressWarnings("deprecation")
public class GalleryActivity extends Activity {
	int[] pic;
	Gallery gallery;
	GalleryActivityAdapter myAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_hei_gallary);
		gallery = (Gallery) findViewById(R.id.gallary_main);
		getData();
		myAdapter = new GalleryActivityAdapter(pic, getBaseContext());

		gallery.setAdapter(myAdapter);
	}

	private void getData() {
		pic = new int[5];
		for (int i = 0; i < pic.length; i++) {
			pic[i] = R.drawable.guan_fang_zi_xun_1;
		}
	}
}
