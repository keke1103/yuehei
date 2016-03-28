package com.yuanchuang.yohey;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 添加朋友页面
 * 
 * @author Administrator
 *
 */

public class AddFriendsActivity extends Activity {
	RelativeLayout includeTitle;// 导入

	TextView tltle;// 标题

	TextView fNumber;// 朋友数量
	ImageView xin;// 新浪图标
	ImageView qq;// qq图标

	LinearLayout toReturn;// 返回
	LinearLayout QQ;// qq号
	LinearLayout xinLang;// 新浪
	LinearLayout QQWeb;// qq微博
	LinearLayout newFrends;// 新的朋友
	LinearLayout favorite;// 喜爱最多

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_add);
		findView();
		LayoutParams params = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		TextView text = new TextView(this);
		text.setLayoutParams(params);
		text.setText(R.string.msg);
		text.setTextColor(getResources().getColor(R.color.write));
		text.setTextSize(12);
		Drawable drawable = getResources().getDrawable(R.drawable.yo_hey_back_image);

		/// 这一步必须要做,否则不会显示.

		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		text.setCompoundDrawablePadding(15);
		text.setCompoundDrawables(drawable, null, null, null);

		toReturn.addView(text);
		toReturn.setOnClickListener(clickListener);
		fNumber.setText("10");
	}

	private void findView() {
		includeTitle = (RelativeLayout) findViewById(R.id.add_include_title);

		QQ = (LinearLayout) findViewById(R.id.add_line_qq);
		newFrends = (LinearLayout) findViewById(R.id.add_line_new_freads);
		favorite = (LinearLayout) findViewById(R.id.add_line_favorite);
		xinLang = (LinearLayout) findViewById(R.id.add_line_xinlang);
		toReturn = (LinearLayout) includeTitle.findViewById(R.id.title_navigation_view);

		fNumber = (TextView) findViewById(R.id.add_text_number);
		xin = (ImageView) findViewById(R.id.add_image_xinliang);
		qq = (ImageView) findViewById(R.id.add_image_qq_web);
		tltle = (TextView) includeTitle.findViewById(R.id.title_navigation_text_title);
		tltle.setText(R.string.add);

		toReturn.setVisibility(View.VISIBLE);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.add_line_qq:

				break;
			case R.id.add_line_new_freads:

				break;
			case R.id.add_line_favorite:

				break;
			case R.id.add_line_xinlang:

				break;
			case R.id.title_navigation_view:
				finish();
				break;

			default:
				break;
			}

		}
	};
}
