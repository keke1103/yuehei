package com.yuanchuang.yohey.chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import cn.bmob.newim.event.MessageEvent;

public class MessageReceiver extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
		if (null != intent) {
			final MessageEvent event = (MessageEvent) intent.getSerializableExtra("event");
			String str = event.getMessage().getExtra();
			Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
		}
	}

}
