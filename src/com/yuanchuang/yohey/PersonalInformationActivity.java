package com.yuanchuang.yohey;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 个人资料界面
 * @author Administrator
 *
 */
public class PersonalInformationActivity extends Activity {
	RelativeLayout include;//导入头文件
	TextView title;//标题
	ImageView backimage;//返回键
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_personal_information);
		findView();
	}
	public void findView(){
		include=(RelativeLayout)findViewById(R.id.personal_information_title_bar);
		title=(TextView)include.findViewById(R.id.title_navigation_text_title);
		backimage=(ImageView)include.findViewById(R.id.title_navigation_back_icon);
		backimage.setVisibility(View.VISIBLE);
		backimage.setImageResource(R.drawable.rub_course_back_icon);
		title.setText("个人资料");
		backimage.setOnClickListener(clickListener);
	}
	OnClickListener clickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.title_navigation_back_icon:
				finish();
				break;

			default:
				break;
			}
		}
	};
}
