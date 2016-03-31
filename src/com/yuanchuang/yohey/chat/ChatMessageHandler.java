package com.yuanchuang.yohey.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;

public class ChatMessageHandler extends BmobIMMessageHandler {
	Context context;

	public ChatMessageHandler(Context context) {
		this.context = context;
	}

	/**
	 * 当接收到服务器发来的消息时，此方法被调用
	 */
	@Override
	public void onMessageReceive(MessageEvent arg0) {
		// super.onMessageReceive(arg0);
		Log.i("ChatMessageHandler", arg0.getFromUserInfo().getAvatar() + ":" + arg0.getMessage());
		Intent arg1 = new Intent("cn.bmob.im.action.MESSAGE");
		Bundle b = new Bundle();
		b.putSerializable("event", arg0);
		arg1.putExtras(b);
		context.sendBroadcast(arg1);
	}

	/**
	 * 每次调用connect方法时会查询一次离线消息，如果有，此方法会被调用
	 */
	@Override
	public void onOfflineReceive(OfflineMessageEvent arg0) {
		super.onOfflineReceive(arg0);
	}
}
