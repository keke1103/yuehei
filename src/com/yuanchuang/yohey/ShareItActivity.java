package com.yuanchuang.yohey;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ShareItActivity extends Activity {
	RelativeLayout includeTitle;//标题栏
	EditText editider;//分享内容
	TextView position;//所在位置
	TextView who;//谁可以看
	TextView remind;//提醒谁看
	TextView title;
	TextView toReturn;
	TextView send;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_share_it);
		findView();
		toReturn.setOnClickListener(onClickListener);
	}
OnClickListener onClickListener=new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_navigation_back_icon:
			finish();
			break;

		default:
			break;
		}
		
	}
};
	private void findView() {
		includeTitle=(RelativeLayout) findViewById(R.id.share_it_title_bar);
		editider=(EditText) findViewById(R.id.share_it_edit_ider);
		position=(TextView) findViewById(R.id.share_it_text_position);
		who=(TextView) findViewById(R.id.share_it_text_who);
		remind=(TextView) findViewById(R.id.share_it_text_remind);
		send=(TextView) includeTitle.findViewById(R.id.title_navigation_text_right_title);
		title=(TextView) includeTitle.findViewById(R.id.title_navigation_text_title);
		toReturn=(TextView) includeTitle.findViewById(R.id.title_navigation_back_icon);
		toReturn.setText(R.string.cancel);
		title.setText(R.string.share);
		send.setText(R.string.send);
	}
}
