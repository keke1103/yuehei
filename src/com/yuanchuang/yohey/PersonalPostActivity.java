package com.yuanchuang.yohey;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
	TextView imageAdd;// 添加图片
	TextView imageMessage;// 留言图片
	TextView imageUp;// 赞图片
	TextView imageMore;// 更多图片
	EditText say;// 说一句
	RelativeLayout include;// 导入头文件
	TextView title;// 标题
	View toReturn;// 返回

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
		imageMore.setOnClickListener(onClickListener);
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.personal_post_text_more:
				showPopupWindow(imageMore);
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

	// 自定义popupwindow做法
	@SuppressLint("InflateParams")
	@SuppressWarnings({ "deprecation" })
	private void showPopupWindow(View view) {
		TextView hide;
		TextView report;
		// 一个自定义的布局，作为显示的内容
		View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_personal, null);
		hide = (TextView) contentView.findViewById(R.id.popupwindow_hide);
		report = (TextView) contentView.findViewById(R.id.popupwindow_report);
		final PopupWindow popupWindow = new PopupWindow(contentView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, true);

		popupWindow.setTouchable(true);

		popupWindow.setTouchInterceptor(new OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (v.getId()) {
				case R.id.popupwindow_hide:
					Toast.makeText(getApplication(), "我是隐藏", Toast.LENGTH_SHORT).show();
					break;

				case R.id.popupwindow_report:
					Toast.makeText(getApplication(), "我是举报", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
				Log.i("mengdd", "onTouch : " + v + "<><><>" + event+"1111"+v.getId());

				return false;
				// 这里如果返回true的话，touch事件将被拦截
				// 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
			}
		});

		// 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
		popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.switch_bg_holo_dark));

		// 设置好参数之后再show
		popupWindow.showAsDropDown(view);

	}
}
