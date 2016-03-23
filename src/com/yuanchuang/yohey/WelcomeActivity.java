package com.yuanchuang.yohey;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;

public class WelcomeActivity extends Activity {
	ViewFlipper mView;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		mView = (ViewFlipper) findViewById(R.id.welcom_view);
		View view1 = new View(getApplication());
		View view2 = new View(getApplication());
		View view3 = new View(getApplication());
		View view4 = new View(getApplication());
		view1.setBackgroundResource(R.drawable.welcome1);
		view2.setBackgroundResource(R.drawable.welcome2);
		view3.setBackgroundResource(R.drawable.welcome3);
		view4.setBackgroundResource(R.drawable.welcome4);
		mView.addView(view1);
		mView.addView(view2);
		mView.addView(view3);
		mView.addView(view4);
		mView.setFlipInterval(2000);
		mView.startFlipping();

		handler.postDelayed(run, 6500);

	}

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			mView.stopFlipping();
			mView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = getIntent();
					intent.setClass(getApplicationContext(), LoginAndRegistered.class);
					startActivity(intent);
					finish();
				}
			});
		}
	};

	Runnable run = new Runnable() {

		public void run() {
			handler.sendEmptyMessage(1);
		}
	};

}