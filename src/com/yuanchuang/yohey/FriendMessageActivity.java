package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuanchuang.yohey.adapter.FriendMessageBaseAdapter;
import com.yuanchuang.yohey.adapter.MediaManager;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.app.YoheyNotificationManager;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.tools.AudioRecordButton;
import com.yuanchuang.yohey.tools.AudioRecordButton.AudioFinishRecorderListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.newim.bean.BmobIMAudioMessage;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMTextMessage;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.newim.listener.MessagesQueryListener;
import cn.bmob.v3.exception.BmobException;

/**
 * 好友聊天界面
 * 
 * @author Administrator
 *
 */
public class FriendMessageActivity extends Activity {
	ListView messageListView;// 聊天的listView
	FriendMessageBaseAdapter friendMessageBaseAdapter;
	View toReturn;
	BmobIMConversation c;
	Intent intent;

	TextView title;
	EditText msgEdit;
	View msgSend;
	YoheyApplication app;

	ImageView yuyinImage;
	AudioRecordButton recordBtn;
	View viewanim;
	boolean yuying = true;
	User friend;

	private OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.friend_message_frame_back:
				finish();
				break;
			case R.id.msg_send:
				sendMsg();
				break;
			case R.id.message_yuyin:
				if (yuying) {
					msgEdit.setVisibility(View.GONE);
					recordBtn.setVisibility(View.VISIBLE);
					yuyinImage.setImageResource(R.drawable.yo_hey_iconfont_jianpan);
					yuying = false;
				} else {
					msgEdit.setVisibility(View.VISIBLE);
					recordBtn.setVisibility(View.GONE);
					yuyinImage.setImageResource(R.drawable.yo_hey_iconfont_huatong);
					yuying = true;
				}
				break;
			default:
				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friend_message_frame_main);
		/**
		 * 初始化通知的消息;
		 */
		YoheyNotificationManager.getInstance(getApplicationContext()).initNotivication();
		app = (YoheyApplication) getApplication();
		/**
		 * 给通知绑定一个监听者,若监听者存在,且对应,通知栏将不发送通知
		 */
		YoheyNotificationManager.getInstance(getApplicationContext()).addObserver(this);
		intent = getIntent();
		c = BmobIMConversation.obtain(BmobIMClient.getInstance(),
				(BmobIMConversation) intent.getSerializableExtra("c"));
		findView();
		initView();
		getData();
	}

	/**
	 * 获得friendMessageList的数据
	 */
	public void getData() {
		c.queryMessages(null, 10, msgListener);
	}

	/**
	 * 发送消息
	 */
	private void sendMsg() {
		String content = msgEdit.getText().toString();

		if (TextUtils.isEmpty(content)) {
			return;
		}
		BmobIMTextMessage msg = new BmobIMTextMessage();
		msg.setContent(content);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level", "1");
		msg.setExtraMap(map);
		c.sendMessage(msg, new MessageSendListener() {
			public void done(BmobIMMessage arg0, BmobException e) {
				addMessage(arg0);
				msgEdit.setText("");
				if (e != null) {
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	/**
	 * 获取聊天记录的监听
	 */
	MessagesQueryListener msgListener = new MessagesQueryListener() {

		@Override
		public void done(List<BmobIMMessage> list, BmobException e) {
			if (e == null) {

				setAdapter(list);

				initTitle();
			} else {
				Toast.makeText(getApplicationContext(), e.getMessage() + "(" + e.getErrorCode() + ")",
						Toast.LENGTH_SHORT).show();
			}
		}
	};

	/**
	 * 设置adapter
	 * 
	 * @param list
	 */
	private void setAdapter(List<BmobIMMessage> list) {
		if (friendMessageBaseAdapter == null) {
			if (list == null)
				list = new ArrayList<BmobIMMessage>();
			friendMessageBaseAdapter = new FriendMessageBaseAdapter(list, this);
			messageListView.setAdapter(friendMessageBaseAdapter);
		} else
			friendMessageBaseAdapter.setMessageData(list);
	}

	/**
	 * 添加消息
	 * 
	 * @param msg
	 */
	public void addMessage(BmobIMMessage msg) {
		if (friendMessageBaseAdapter == null) {
			List<BmobIMMessage> list = new ArrayList<BmobIMMessage>();
			list.add(msg);
			friendMessageBaseAdapter = new FriendMessageBaseAdapter(list, this);
			messageListView.setAdapter(friendMessageBaseAdapter);
		} else {
			friendMessageBaseAdapter.addMessageData(msg);
		}
	}

	/**
	 * 控件ID
	 */
	private void findView() {
		messageListView = (ListView) findViewById(R.id.friend_message_frame_listview);
		title = (TextView) findViewById(R.id.friend_message_name);
		toReturn = findViewById(R.id.friend_message_frame_back);
		toReturn.setOnClickListener(click);
		msgEdit = (EditText) findViewById(R.id.msg_edit);
		msgSend = findViewById(R.id.msg_send);
		yuyinImage = (ImageView) findViewById(R.id.message_yuyin);
		recordBtn = (AudioRecordButton) findViewById(R.id.recordButton);

	}

	private void initView() {
		recordBtn.setAudioFinishRecorderListener(new AudioFinishRecorderListener() {
			public void onFinished(float seconds, String filePath) {
				BmobIMAudioMessage audio = new BmobIMAudioMessage(filePath);
				MessageSendListener arg1 = new MessageSendListener() {

					public void done(BmobIMMessage arg0, BmobException arg1) {

					}

					@Override
					public void onProgress(int arg0) {
						super.onProgress(arg0);
					}

					@Override
					public void onStart(BmobIMMessage msg) {
						friendMessageBaseAdapter.addMessageData(msg);
						Log.i("FriendMessageActivity", msg.getMsgType());
					}
				};
				c.sendMessage(audio, arg1);
			}
		});
		yuyinImage.setOnClickListener(click);
		msgSend.setOnClickListener(click);
	}

	/**
	 * 工具聊天Id初始化聊天窗口的标题
	 */
	private void initTitle() {
		title.setText("");
		app.getFriendById(c.getConversationId(), mHandler);
	}

	public BmobIMConversation getConversation() {
		return c;
	}

	@SuppressLint("HandlerLeak")
	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			friend = ((User) (msg.obj));
			if (friend == null)
				finish();
			title.setText(friend.getNickName());
			friendMessageBaseAdapter.setFriend(friend);
		};
	};

	protected void onDestroy() {
		YoheyNotificationManager.getInstance(getApplicationContext()).deleteObserver();
		MediaManager.release();
		super.onDestroy();
	}

	protected void onPause() {
		super.onPause();
		MediaManager.pause();
	}

	protected void onResume() {
		super.onResume();
		MediaManager.resume();
	}

}
