package com.yuanchuang.yohey;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 分享页面
 * 
 * @author Administrator
 *
 */
public class ShareItActivity extends Activity {
	RelativeLayout includeTitle;// 标题栏
	EditText editider;// 分享内容
	TextView position;// 所在位置
	TextView who;// 谁可以看
	TextView remind;// 提醒谁看
	TextView title;
	TextView toReturn;
	TextView send;// 发送消息

	RelativeLayout addPhotos;// 添加图片
	LinearLayout linearPosition;// 所在位置的线性
	LinearLayout linearWho;// 谁可以看
	LinearLayout linearRemind;// 提醒谁看

	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_share_it);
		findView();
		toReturn.setOnClickListener(onClickListener);
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.title_navigation_back_icon:// 返回
				finish();
				break;

			case R.id.title_navigation_text_right_title:// 发送

				break;
			case R.id.share_it_relation_add:// 添加图片

				break;
			case R.id.share_it_linear_position:// 所在位置

				break;
			case R.id.share_it_linear_who:// 谁可以看
				intent = getIntent();
				intent.setClass(ShareItActivity.this, WhoCanSeeActivity.class);
				startActivity(intent);
				break;
			case R.id.share_it_linear_remind:// 提醒谁看

				break;
			default:
				break;
			}

		}
	};

	@SuppressWarnings("deprecation")
	private void findView() {
		includeTitle = (RelativeLayout) findViewById(R.id.share_it_title_bar);
		editider = (EditText) findViewById(R.id.share_it_edit_ider);
		position = (TextView) findViewById(R.id.share_it_text_position);
		who = (TextView) findViewById(R.id.share_it_text_who);
		remind = (TextView) findViewById(R.id.share_it_text_remind);
		send = (TextView) includeTitle.findViewById(R.id.title_navigation_text_right_title);
		title = (TextView) includeTitle.findViewById(R.id.title_navigation_text_title);
		toReturn = (TextView) includeTitle.findViewById(R.id.title_navigation_back_text);
		addPhotos = (RelativeLayout) findViewById(R.id.share_it_relation_add);
		linearPosition = (LinearLayout) findViewById(R.id.share_it_linear_position);
		linearRemind = (LinearLayout) findViewById(R.id.share_it_linear_remind);
		linearWho = (LinearLayout) findViewById(R.id.share_it_linear_who);

		toReturn.setVisibility(View.VISIBLE);
		toReturn.setText(R.string.cancel);
		title.setText(R.string.share);
		send.setText(R.string.send);
		send.setVisibility(View.VISIBLE);
		Resources resources = getResources();

		Drawable drawable = resources.getDrawable(R.drawable.shape_rounded_yellow);
		send.setVisibility(View.VISIBLE);
		send.setBackgroundDrawable(drawable);

		addPhotos.setOnClickListener(onClickListener);
		linearPosition.setOnClickListener(onClickListener);
		linearRemind.setOnClickListener(onClickListener);
		linearWho.setOnClickListener(onClickListener);
	}
}
