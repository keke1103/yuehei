package com.yuanchuang.yohey;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * QQ注册界面
 * @author Administrator
 *
 */
public class QQRegisterActivity extends Activity {
	RelativeLayout include;// 导入头文件
	ImageView toReturn;// 返回
	TextView title;// 标题
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qq_register_main);
		findiew();
	}
	/**
	 * 
	 */
	OnClickListener onClickListener=new OnClickListener() {
		
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
	@SuppressWarnings("deprecation")
	private void findiew() {
		// TODO Auto-generated method stub
		include = (RelativeLayout) findViewById(R.id.qq_register_include);
		toReturn =(ImageView) include.findViewById(R.id.title_navigation_back_icon);
		title = (TextView) include.findViewById(R.id.title_navigation_text_title);
		include.setBackgroundColor(getResources().getColor(R.color.black));
		toReturn.setVisibility(View.VISIBLE);
		toReturn.setImageResource(R.drawable.yo_hey_back_image);
		title.setText("QQ注册");
		title.setTextColor(getResources().getColor(R.color.write));
		toReturn.setOnClickListener(onClickListener);
	}

}
