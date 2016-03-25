package com.yuanchuang.yohey.chat;

import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;

public class ChatMessageHandler extends BmobIMMessageHandler {
	/**
	 * 当接收到服务器发来的消息时，此方法被调用
	 */
	public void onMessageReceive(MessageEvent arg0) {
		super.onMessageReceive(arg0);
	}

	/**
	 * 每次调用connect方法时会查询一次离线消息，如果有，此方法会被调用
	 */
	public void onOfflineReceive(OfflineMessageEvent arg0) {
		super.onOfflineReceive(arg0);
	}
}
