package com.yuanchuang.yohey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 输入手机接受到的验证码
 * @author Administrator
 *
 */
public class VerificationCodeActivity extends Activity {
	RelativeLayout include;// 导入头文件
	ImageView toReturn;// 返回
	TextView title;// 标题
	Button nextbtn;//下一步
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_verification_code);
			findView();
	}
	/**
	 * 点击事件
	 */
	OnClickListener clickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent;
			switch (v.getId()) {
			case R.id.title_navigation_back_icon:
				finish();
				break;
			case R.id.verification_code_text_next:
				intent=new Intent(VerificationCodeActivity.this,QQRegisterActivity.class);
				startActivity(intent);
				break;
			default:
				break;
			}
		}
	};
    /**
     * 控件的ID
     */
	@SuppressWarnings("deprecation")
	private void findView() {
		// TODO Auto-generated method stub
		nextbtn=(Button)findViewById(R.id.verification_code_text_next);
		include = (RelativeLayout) findViewById(R.id.mobile_number_title_bar);
		toReturn =(ImageView) include.findViewById(R.id.title_navigation_back_icon);
		title = (TextView) include.findViewById(R.id.title_navigation_text_title);
		include.setBackgroundColor(getResources().getColor(R.color.black));
		toReturn.setVisibility(View.VISIBLE);
		toReturn.setImageResource(R.drawable.yo_hey_back_image);
		title.setText("填写验证码");
		title.setTextColor(getResources().getColor(R.color.write));
		toReturn.setOnClickListener(clickListener);
		nextbtn.setOnClickListener(clickListener);
	}
}