package com.yuanchuang.yohey;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
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
		mView.setFlipInterval(5000);
		mView.startFlipping();

	}

	class MyHalder extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
		}
	}
}
