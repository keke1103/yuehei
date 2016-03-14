package com.yuanchuang.yohey;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PersonalPostActivity extends Activity {
	View head;
	TextView nickName;
	TextView time;
	TextView imageYan;
	TextView rowse;
	LinearLayout context;
	TextView imageAdd;
	TextView imageMessage;
	TextView imageUp;
	TextView imageMore;
	EditText say;
	RelativeLayout include;
	TextView title;
	View toReturn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_yue_lu_personal_post);
		Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont/iconfont.ttf");
		findView();
		TextView text = new TextView(this);
		LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		text.setLayoutParams(params);
		text.setText(R.string.aiouniya);
		context.addView(text);
		imageYan.setTypeface(iconfont);
		imageAdd.setTypeface(iconfont);
		imageMessage.setTypeface(iconfont);
		imageUp.setTypeface(iconfont);
		imageMore.setTypeface(iconfont);
		// imageYan.setTextSize(20);
		imageAdd.setTextSize(17);
		imageMessage.setTextSize(17);
		imageMore.setTextSize(17);
		imageUp.setTextSize(17);
	}

	private void findView() {

		include = (RelativeLayout) findViewById(R.id.personal_post_include_title);
		head = findViewById(R.id.personal_post_image_head);
		nickName = (TextView) findViewById(R.id.personal_post_nick_text_name);
		time = (TextView) findViewById(R.id.personal_post_text_time);
		imageYan = (TextView) findViewById(R.id.personal_post_text_yang);
		rowse = (TextView) findViewById(R.id.personal_post_text_rowse);
		context = (LinearLayout) findViewById(R.id.personal_post_cotext);
		imageAdd = (TextView) findViewById(R.id.personal_post_text_addd);
		imageMessage = (TextView) findViewById(R.id.personal_post_text_message);
		imageMore = (TextView) findViewById(R.id.personal_post_text_more);
		imageUp = (TextView) findViewById(R.id.personal_post_text_appreciate);
		say = (EditText) findViewById(R.id.personal_post_edit_say);
		title = (TextView) include.findViewById(R.id.title_navigation_text_title);
		toReturn = include.findViewById(R.id.title_navigation_back_icon);
		title.setText(R.string.personal_post);
		toReturn.setVisibility(View.VISIBLE);

	}
}
