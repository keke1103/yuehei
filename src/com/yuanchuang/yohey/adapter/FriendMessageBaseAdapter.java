package com.yuanchuang.yohey.adapter;

import java.util.ArrayList;
import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.tools.TimeUtil;
import com.yuanchuang.yohey.view.RecorderView;

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
	User f;

	public FriendMessageBaseAdapter(List<BmobIMMessage> list, Context context) {
		this.list = list;
		this.context = context;
		mine = BmobUser.getCurrentUser(context, User.class);
		inflater = LayoutInflater.from(context);
	}

	public void setFriend(User f) {
		this.f = f;
		notifyDataSetChanged();
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

		return list.size();
	}

	public Object getItem(int position) {

		return list.get(position);
	}

	public long getItemId(int position) {

		return position;
	}

	@SuppressLint("InflateParams")
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
			holder.myChatLayout = (LinearLayout) convertView.findViewById(R.id.my_chat_layout);
			holder.friendChatLayout = (LinearLayout) convertView.findViewById(R.id.friend_chat_layout);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.messageTime.setText(TimeUtil.formateTime(msg.getCreateTime()));
		if (msg.getFromId().equals(mine.getObjectId())) {
			holder.myChatLayout.removeAllViews();
			if (msg.getMsgType().equals("sound")) {
				String path = msg.getContent();
				String ps[] = path.split("&");
				RecorderView rec = RecorderView.createRecorder(context, ps[0]);
				holder.myChatLayout.addView(rec.getmView());
			} else {
				holder.myChatContent.setText(msg.getContent());
			}

			mine.binderImageView(holder.myChatImage);
			holder.myChat.setVisibility(View.VISIBLE);
			holder.friendChat.setVisibility(View.GONE);

		} else {
			holder.friendChatLayout.removeAllViews();
			if (f != null)
				f.binderImageView(holder.friendChatImage);
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
		LinearLayout myChatLayout;
		LinearLayout friendChatLayout;
	}

}
