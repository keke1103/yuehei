package com.yuanchuang.yohey;

import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
/**
 * 添加消息界面
 * 
 * @author Administrator
 *
 */
public class AddMessagesAvtivity extends Activity {
	RelativeLayout includeTitle;// 导入
	TextView tltle;// 标题
	LinearLayout toReturn;// 返回
	LinearLayout QQ;// qq号
	TextView QQID;// 搜索图标
	LinearLayout newFrends;// 新的朋友
	LinearLayout favorite;// 喜爱最多
	TextView fNumber;// 朋友数量
	LinearLayout xinLang;// 新浪
	LinearLayout QQWeb;// qq微博
	TextView xin;// 新浪图标
	TextView qq;// qq图标
	View friendsNext;// 朋友下一步图标
	View favoriteNext;// 喜爱最多下一步

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_add);
		findView();
		LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		TextView text = new TextView(this);
		text.setLayoutParams(params);
		text.setText(R.string.msg);

		@SuppressWarnings("deprecation")
		Drawable drawable = getResources().getDrawable(R.drawable.rub_course_back_icon);

		/// 这一步必须要做,否则不会显示.

		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		text.setCompoundDrawablePadding(15);
		text.setCompoundDrawables(drawable, null, null, null);

		toReturn.addView(text);

		fNumber.setText("10");
	}

	private void findView() {
		Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont/iconfont.ttf");
		includeTitle = (RelativeLayout) findViewById(R.id.add_include_title);
		QQ = (LinearLayout) findViewById(R.id.add_line_qq);
		QQID = (TextView) findViewById(R.id.add_text_qq);
		newFrends = (LinearLayout) findViewById(R.id.add_line_new_freads);
		favorite = (LinearLayout) findViewById(R.id.add_line_favorite);
		fNumber = (TextView) findViewById(R.id.add_text_number);
		xinLang = (LinearLayout) findViewById(R.id.add_line_xinlang);
		friendsNext = findViewById(R.id.add_image_frieds);
		favoriteNext = findViewById(R.id.add_image_favorite);
		xin = (TextView) findViewById(R.id.add_text_xinliang);
		qq = (TextView) findViewById(R.id.add_text_qq_web);
		tltle = (TextView) includeTitle.findViewById(R.id.title_navigation_text_title);
		toReturn = (LinearLayout) includeTitle.findViewById(R.id.title_navigation_view);
		tltle.setText(R.string.add);
		QQID.setTypeface(iconfont);
		xin.setTypeface(iconfont);
		qq.setTypeface(iconfont);
		toReturn.setVisibility(View.VISIBLE);
		friendsNext.setVisibility(View.VISIBLE);
		favoriteNext.setVisibility(View.VISIBLE);
	}
}
