package com.yuanchuang.yohey.tools;

import cn.bmob.newim.event.MessageEvent;

/**
 * 消息事件的观察者,接收消息事件,且拦截通知栏的发放
 * 
 * 
 * @author kk0927
 *
 */
public interface MessageObserver {
	/**
	 * 
	 * @param event
	 * @return 是否发送通知栏消息.
	 */
	boolean execMessage(MessageEvent event);
}
