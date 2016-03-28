package com.yuanchuang.yohey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuanchuang.yohey.adapter.FriendMessageBaseAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import android.widget.ListView;
import android.widget.Toast;
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

	EditText msgEdit;
	View msgSend;

	private OnClickListener click = new OnClickListener() {

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.friend_message_frame_back:
				finish();
				break;
			case R.id.msg_send:
				sendMsg();
				break;
			default:
				break;
			}
		}

	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friend_message_frame_main);
		intent = getIntent();
		c = BmobIMConversation.obtain(BmobIMClient.getInstance(),
				(BmobIMConversation) intent.getSerializableExtra("c"));
		getData();
		findView();

	}

	/**
	 * 获得friendMessageList的数据
	 */
	private void getData() {

		c.queryMessages(null, 10, msgListener);
	}

	/**
	 * 发送消息
	 */
	private void sendMsg() {
		String content = msgEdit.getText().toString();

		if (TextUtils.isEmpty(content)) {
			Toast.makeText(getApplicationContext(), "消息不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		BmobIMTextMessage msg = new BmobIMTextMessage();
		msg.setContent(content);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level", "1");
		msg.setExtraMap(map);
		c.sendMessage(msg, new MessageSendListener() {
			public void done(BmobIMMessage arg0, BmobException e) {
				friendMessageBaseAdapter.addMessageData(arg0);
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

		public void done(List<BmobIMMessage> list, BmobException e) {
			if (e == null) {
				if (list != null && !list.isEmpty()) {
					if (friendMessageBaseAdapter == null)
						friendMessageBaseAdapter = new FriendMessageBaseAdapter(list,FriendMessageActivity.this);
					else
						friendMessageBaseAdapter.setMessageData(list);
				}

			} else {
				Toast.makeText(getApplicationContext(), e.getMessage() + "(" + e.getErrorCode() + ")",
						Toast.LENGTH_SHORT).show();
			}
		}
	};

	/**
	 * 控件ID
	 */
	private void findView() {
		messageListView = (ListView) findViewById(R.id.friend_message_frame_listview);
		messageListView.setAdapter(friendMessageBaseAdapter);
		toReturn = findViewById(R.id.friend_message_frame_back);
		toReturn.setOnClickListener(click);
		msgEdit = (EditText) findViewById(R.id.msg_edit);
		msgSend = findViewById(R.id.msg_send);
		msgSend.setOnClickListener(click);
	}

}
