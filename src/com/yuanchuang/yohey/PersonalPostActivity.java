package com.yuanchuang.yohey;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 个人发帖界面
 * 
 * @author Administrator
 *
 */
public class PersonalPostActivity extends Activity {
	View head;// 头像
	TextView nickName;// 昵称
	TextView time;// 时间
	TextView imageYan;// 浏览图标
	TextView rowse;// 浏览量
	LinearLayout context;// 发表内容
	EditText say;// 说一句
	RelativeLayout include;// 导入头文件
	TextView title;// 标题
	View toReturn;// 返回

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_yue_lu_personal_post);

		findView();
		TextView text = new TextView(this);
		LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		text.setLayoutParams(params);
		text.setText(R.string.aiouniya);
		context.addView(text);

	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.personal_post_image_head:

				break;

			default:
				break;
			}

		}
	};

	private void findView() {
		include = (RelativeLayout) findViewById(R.id.personal_post_include_title);
		head = findViewById(R.id.personal_post_image_head);
		nickName = (TextView) findViewById(R.id.personal_post_nick_text_name);
		time = (TextView) findViewById(R.id.personal_post_text_time);
		context = (LinearLayout) findViewById(R.id.personal_post_cotext);
		title = (TextView) include.findViewById(R.id.title_navigation_text_title);
		toReturn = include.findViewById(R.id.title_navigation_back_icon);
		title.setText(R.string.personal_post);
		toReturn.setVisibility(View.VISIBLE);

	}
}
