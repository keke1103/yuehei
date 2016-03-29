package com.yuanchuang.yohey;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.listener.ConversationListener;
import cn.bmob.v3.exception.BmobException;

/**
 * 好友基本信息界面
 * 
 * @author Administrator
 *
 */
public class FriendMaterialActivity extends Activity {
	View backImage;// 返回
	TextView sendMessage;// 发送消息按钮
	ImageView headerImage;
	TextView name;
	TextView mood;// 个性签名

	Intent intent;
	User user;
	YoheyApplication app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friend_material_main);
		intent = getIntent();
		app = (YoheyApplication) getApplication();
		user = (User) app.data;
		app.data = null;
		findView();
		setData();
	}

	/**
	 * 点击事件
	 */
	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.friend_message_frame_back:
				finish();
				break;
			case R.id.friend_material_send_message:
				conectChat();
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
		backImage = findViewById(R.id.friend_message_frame_back);
		sendMessage = (TextView) findViewById(R.id.friend_material_send_message);
		headerImage = (ImageView) findViewById(R.id.friend_message_head_image);
		name = (TextView) findViewById(R.id.friend_message_name);
		mood = (TextView) findViewById(R.id.friend_material_signature);
		backImage.setOnClickListener(clickListener);
		sendMessage.setOnClickListener(clickListener);
	}

	private void setData() {
		user.binderImageView(headerImage);
		name.setText(user.getNickName());
		mood.setText(user.getMood());

	}

	private void conectChat() {
		BmobIMUserInfo userinfo = new BmobIMUserInfo(user.getObjectId(), user.getNickName(), user.getIcon());
		BmobIM.getInstance().startPrivateConversation(userinfo, new ConversationListener() {

			public void done(BmobIMConversation arg0, BmobException e) {
				if (e == null) {
					Bundle b = new Bundle();
					b.putSerializable("c", arg0);
					intent.setClass(FriendMaterialActivity.this, FriendMessageActivity.class);
					intent.putExtras(b);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(getApplicationContext(), e.getMessage() + ":" + e.getErrorCode(), Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

	}
}
