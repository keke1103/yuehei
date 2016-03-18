package com.yuanchuang.yohey.adapter;

import java.util.ArrayList;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.myData.User;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 好友聊天界面的BaseAdapter
 * 
 * @author Administrator
 *
 */
public class FriendMessageBaseAdapter extends BaseAdapter {
	ArrayList<User> list;
	Context context;
	LayoutInflater inflater;

	public FriendMessageBaseAdapter() {
		// TODO Auto-generated constructor stub
	}

	public FriendMessageBaseAdapter(ArrayList<User> list, Context context) {
		// TODO Auto-generated constructor stub
		this.list=list;
		this.context=context;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView==null){
			convertView=inflater.inflate(R.layout.friend_chat_main,null);
			holder=new ViewHolder();
			holder.messageTime=(TextView)convertView.findViewById(R.id.friend_chat_time);
			holder.myChat=(RelativeLayout)convertView.findViewById(R.id.my_chat_relative);
			holder.myChatContent=(TextView)convertView.findViewById(R.id.my_chat_content);
			holder.myChatImage=(ImageView)convertView.findViewById(R.id.my_chat_head_portrait);
			holder.friendChat=(LinearLayout)convertView.findViewById(R.id.friend_chat_linear);
			holder.friendChatImage=(ImageView)convertView.findViewById(R.id.friend_chat_head_portrait);
			holder.friendChatContent=(TextView)convertView.findViewById(R.id.friend_chat_content);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		
		return convertView;
	}
	class ViewHolder{
		TextView messageTime;//消息时间
		RelativeLayout myChat;//我的聊天
		TextView myChatContent;//我的聊天内容
		ImageView myChatImage;//我的聊天头像
		LinearLayout friendChat;//朋友的聊天
		ImageView friendChatImage;//朋友的聊天头像
		TextView friendChatContent;//朋友的聊天内容
	}

}
