package com.yuanchuang.yohey.adapter;

import java.util.ArrayList;
import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.tools.TimeUtil;

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
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.v3.BmobUser;

/**
 * 好友聊天界面的BaseAdapter
 * 
 * @author Administrator
 *
 */
public class FriendMessageBaseAdapter extends BaseAdapter {
	List<BmobIMMessage> list;
	Context context;
	LayoutInflater inflater;
	User mine;
	YoheyApplication app;

	public FriendMessageBaseAdapter(List<BmobIMMessage> list, YoheyApplication app) {
		this.list = list;
		this.context = app.getApplicationContext();
		this.app = app;
		mine = BmobUser.getCurrentUser(context, User.class);
		inflater = LayoutInflater.from(context);
	}

	public void setMessageData(List<BmobIMMessage> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	public void addMessageData(BmobIMMessage msg) {
		if (list == null)
			list = new ArrayList<BmobIMMessage>();
		list.add(msg);
		notifyDataSetChanged();
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

		BmobIMMessage msg = list.get(position);
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.friend_chat_main, null);
			holder = new ViewHolder();
			holder.messageTime = (TextView) convertView.findViewById(R.id.friend_chat_time);
			holder.myChat = (RelativeLayout) convertView.findViewById(R.id.my_chat_relative);
			holder.myChatContent = (TextView) convertView.findViewById(R.id.my_chat_content);
			holder.myChatImage = (ImageView) convertView.findViewById(R.id.my_chat_head_portrait);
			holder.friendChat = (LinearLayout) convertView.findViewById(R.id.friend_chat_linear);
			holder.friendChatImage = (ImageView) convertView.findViewById(R.id.friend_chat_head_portrait);
			holder.friendChatContent = (TextView) convertView.findViewById(R.id.friend_chat_content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		BmobIMUserInfo info = msg.getBmobIMUserInfo();
		holder.messageTime.setText(TimeUtil.formateTime(msg.getCreateTime()));
		if (info != null && info.getUserId().equals(mine.getObjectId())) {
			 
			holder.myChatContent.setText(msg.getContent());
			
			mine.binderImageView(holder.myChatImage);
			holder.myChat.setVisibility(View.VISIBLE);
			holder.friendChat.setVisibility(View.GONE);
		} else {
			app.getFriendById(msg.getFromId()).binderImageView(holder.friendChatImage);
			holder.friendChatContent.setText(msg.getContent());
			holder.myChat.setVisibility(View.GONE);
			holder.friendChat.setVisibility(View.VISIBLE);
		}

		return convertView;
	}

	class ViewHolder {
		TextView messageTime;// 消息时间
		RelativeLayout myChat;// 我的聊天
		TextView myChatContent;// 我的聊天内容
		ImageView myChatImage;// 我的聊天头像
		LinearLayout friendChat;// 朋友的聊天
		ImageView friendChatImage;// 朋友的聊天头像
		TextView friendChatContent;// 朋友的聊天内容
	}

}
