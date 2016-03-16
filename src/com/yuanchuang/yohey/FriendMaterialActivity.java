package com.yuanchuang.yohey;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * 好友基本信息界面
 * @author Administrator
 *
 */
import android.widget.ImageView;
public class FriendMaterialActivity extends Activity {
	ImageView backImage;//返回
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friend_material_main);
		findView();
	}
	/**
	 * 点击事件
	 */
	OnClickListener clickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.friend_message_frame_back:
				finish();
				break;

			default:
				break;
			}
		}
	};
	/**
	 * 控件ID
	 */
	private void findView() {
		// TODO Auto-generated method stub
		backImage=(ImageView)findViewById(R.id.friend_message_frame_back);
		
		backImage.setOnClickListener(clickListener);
	}

}
