package com.yuanchuang.yohey.tools;

import cn.bmob.newim.bean.BmobIMMessage;

/**
 * 消息接受者,用于收到消息时候的处理;
 * 
 * @author kk0927
 *
 */
public interface MessageReciverListener {

	/**
	 * 收到的消息
	 * 
	 * @param msg
	 */
	void recive(BmobIMMessage msg);
}
