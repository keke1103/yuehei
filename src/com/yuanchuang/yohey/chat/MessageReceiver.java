package com.yuanchuang.yohey.chat;

import com.yuanchuang.yohey.FriendMessageActivity;
import com.yuanchuang.yohey.app.YoheyNotificationManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import cn.bmob.newim.event.MessageEvent;

public class MessageReceiver extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
		if (null != intent) {
			final MessageEvent event = (MessageEvent) intent.getSerializableExtra("event");
			String str = event.getMessage().getExtra();
			Bundle b = new Bundle();
			b.putSerializable("c", event.getConversation());
			intent.setClass(context, FriendMessageActivity.class);
			intent.putExtras(b);
			Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
			YoheyNotificationManager.getInstance(context).execMessage(event);
		}
	}

}
