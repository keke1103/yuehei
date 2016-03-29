package com.yuanchuang.yohey;

import com.yuanchuang.yohey.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 视频集锦界面
 * 
 * @author Administrator
 *
 */
@SuppressLint("InflateParams")
public class Vide0CollectionActivity extends Activity {
	LinearLayout videoCollection;
	LinearLayout classifyVideo;
	LayoutInflater inflater;
	ImageView backImage;// 返回图标
	TextView title;// 标题

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_collection_main);
		findView();
		addView();
	}

	/**
	 * 添加视图
	 */
	private void addView() {
		// TODO Auto-generated method stub
		inflater = getLayoutInflater();
		for (int i = 0; i < 3; i++) {
			View view = inflater.inflate(R.layout.classify_video_main, null);
			View view1 = inflater.inflate(R.layout.video_player_main, null);
			classifyVideo = (LinearLayout) view.findViewById(R.id.classify_video_linear);
			videoCollection.addView(view);
			classifyVideo.addView(view1);
		}
	}

	/**
	 * 控件ID
	 */
	private void findView() {
		// TODO Auto-generated method stub
		videoCollection = (LinearLayout) findViewById(R.id.video_collection_linear);
		backImage = (ImageView) findViewById(R.id.title_navigation_back_icon);
		title = (TextView) findViewById(R.id.title_navigation_text_title);

		backImage.setVisibility(View.VISIBLE);
		backImage.setImageResource(R.drawable.yo_hey_back_image);
		title.setText("视频");

		backImage.setOnClickListener(clickListener);
	}

	/**
	 * 点击事件
	 */
	OnClickListener clickListener = new OnClickListener() {

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
