package com.yuanchuang.yohey;

import java.util.ArrayList;

import com.yuanchuang.yohey.adapter.FriendMessageBaseAdapter;
import com.yuanchuang.yohey.myData.User;

import android.app.Activity;
import android.os.Bundle;
/**
 * 好友聊天界面
 * @author Administrator
 *
 */
import android.widget.ListView;
public class FriendMessageActivity extends Activity {
	ListView messageListView;//聊天的listView
	FriendMessageBaseAdapter friendMessageBaseAdapter;
	ArrayList<User> friendMessageList;//用户的基本信息
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friend_message_frame_main);
		getData();
		findView();
	}
	/**
	 * 获得friendMessageList的数据
	 */
    private void getData() {
		// TODO Auto-generated method stub
		friendMessageList=new ArrayList<User>();
		for(int i=0;i<4;i++){
			User user=new User();
			friendMessageList.add(user);
		}	
	}
	/**
     * 控件ID
     */
	private void findView() {
		// TODO Auto-generated method stub
		messageListView=(ListView)findViewById(R.id.friend_message_frame_listview);
		friendMessageBaseAdapter=new FriendMessageBaseAdapter(friendMessageList,getApplication());
		messageListView.setAdapter(friendMessageBaseAdapter);
	}
    
}
