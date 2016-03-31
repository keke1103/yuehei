package com.yuanchuang.yohey;

import com.yuanchuang.yohey.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
    
	ImageView videoImage1;
	ImageView videoImage2;
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
			videoImage1=(ImageView)view1.findViewById(R.id.myVideo1);
			videoImage2=(ImageView)view1.findViewById(R.id.myVideo2);
			videoImage1.setOnClickListener(clickListener);
			videoImage2.setOnClickListener(clickListener);
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
			case R.id.myVideo1:
				playVideo();
				break;
			case R.id.myVideo2:
				playVideo();
				break;
			default:
				break;
			}
		}
	};
	/**
	 * 用自带的播放器播放视频
	 */
	public void playVideo(){
		Uri uri = Uri.parse("http://video.dispatch.tc.qq.com/31498679/q0167gkowwi.mp4?vkey=41EAEE44CEA6F4DAD4FBB4E47A23F06AEF799B16A0075E2C8C6CF43E6A4B93D4F0235C0FF431694EA73EEDA8246643A8EF7622A50A341945E0D1B8A4B395E8207562CC8E14DF41DD6BB414F9C11B69CF1BC6130992754783&br=62430&platform=1&fmt=mp4&level=0&type=mp4");
		Intent intent = new Intent(Intent.ACTION_VIEW,uri);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.setType("video/*");
		intent.setDataAndType(uri , "video/*");
		startActivity(intent);
	}
}
