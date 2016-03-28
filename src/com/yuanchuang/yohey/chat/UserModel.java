package com.yuanchuang.yohey.chat;

import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.event.MessageEvent;

public class UserModel {

	private static UserModel mUser = new UserModel();

	private UserModel() {
	};

	public static UserModel getInstance() {
		return mUser;
	}

	public void updateUserInfo(MessageEvent event, UpdateCacheListener listener) {
		final BmobIMConversation conversation = event.getConversation();
		final BmobIMUserInfo info = event.getFromUserInfo();
		String username = info.getName();
		String title = conversation.getConversationTitle();
		// sdk内部，将新会话的会话标题用objectId表示，因此需要比对用户名和会话标题--单聊，后续会根据会话类型进行判断
		if (!username.equals(title)) {

		}
	}

	public void queryUserInfo(String userId, QueryUserListener queryUserListener) {

	}

	public interface UpdateCacheListener {

	}

	public interface QueryUserListener {
		public void done();
	}
}
