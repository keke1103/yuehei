package com.yuanchuang.yohey;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PostingInterfaceActivity extends Activity {
	RelativeLayout include;// 导入头文件
	TextView title;// 标题
	LinearLayout toReturn;// 取消
	TextView right;// 发表
	EditText context;// 帖子内容
	LinearLayout settings;// 权限设置

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_posting_interface);
		findView();
		LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		TextView text = new TextView(this);
		text.setLayoutParams(params);
		text.setTextSize(13);
		text.setText(R.string.cancel);
		toReturn.addView(text);
	}

	@SuppressWarnings("deprecation")
	private void findView() {
		include = (RelativeLayout) findViewById(R.id.posting_include_itle);
		context = (EditText) findViewById(R.id.posting_include_edit_context);
		settings = (LinearLayout) findViewById(R.id.posting_include_linear_settings);
		title = (TextView) include.findViewById(R.id.title_navigation_text_title);
		toReturn = (LinearLayout) include.findViewById(R.id.title_navigation_view);
		right = (TextView) include.findViewById(R.id.title_navigation_text_right_title);
		title.setText(R.string.osts);
		toReturn.setVisibility(View.VISIBLE);
		right.setVisibility(View.VISIBLE);
		right.setText(R.string.publication);
		right.setTextColor(getResources().getColor(R.color.light_gray));
	}
}
