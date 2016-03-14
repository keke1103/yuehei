package com.yuanchuang.yohey;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 我的积分界面
 * @author Administrator
 *
 */
public class MyIntegralActivity extends Activity {
	RelativeLayout include;//导入头文件
	ImageView backImage;//返回控件
	TextView title;//标题
	TextView myIntegralNumber;//我的积分
	TextView registrationIntegral;//签到积分
	TextView praiseIntegral;//好评积分
	TextView taskIntegral;//任务积分
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_integral_main);
		findView();
		backImage.setVisibility(View.VISIBLE);
		backImage.setImageResource(R.drawable.rub_course_back_icon);
		title.setText("我的积分");
		backImage.setOnClickListener(clickListener);
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
	/**
	 * 控件的ID
	 */
    public void findView(){
    	myIntegralNumber=(TextView)findViewById(R.id.my_integral_number);
    	registrationIntegral=(TextView)findViewById(R.id.my_integral_registration_number);
    	praiseIntegral=(TextView)findViewById(R.id.my_integral_praise_number);
    	taskIntegral=(TextView)findViewById(R.id.my_integral_task_number);
    	include=(RelativeLayout)findViewById(R.id.my_integral_include);
    	backImage=(ImageView)include.findViewById(R.id.title_navigation_back_icon);
    	title=(TextView)include.findViewById(R.id.title_navigation_text_title);
    }
}
