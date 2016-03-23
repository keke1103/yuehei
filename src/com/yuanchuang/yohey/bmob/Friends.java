package com.yuanchuang.yohey.bmob;

import android.content.Context;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

@SuppressWarnings("serial")
public class Friends extends BmobObject {

	User user_a;
	User user_b;

	/**
	 * 执行添加好友
	 * 
	 * @param context
	 * @param friend
	 * @param insertListener
	 */
	public void addFriend(Context context, User friend, SaveListener insertListener) {

		user_a = BmobUser.getCurrentUser(context, User.class);
		boolean bl = friend.getObjectId().equals(user_a.getObjectId());
		if (bl) {
			return;
		}
		user_b = friend;
		save(context, insertListener);
	}
}
