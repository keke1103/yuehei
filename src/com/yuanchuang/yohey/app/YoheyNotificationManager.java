package com.yuanchuang.yohey.app;

import java.util.ArrayList;
import java.util.List;

import com.yuanchuang.yohey.FriendMessageActivity;
import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.cache.YoheyCache;
import com.yuanchuang.yohey.tools.MessageObserver;
import com.yuanchuang.yohey.tools.MessageReciverListener;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import cn.bmob.newim.event.MessageEvent;

public class YoheyNotificationManager {
	public static YoheyNotificationManager manager;

	public int count = 1;
	private Context context;

	FriendMessageActivity obseverActivity;
	MessageObserver observer;

	/**
	 * 设置消息事件的观察者,记得在页面退出后执行deleteMessageObserver()
	 * 
	 * @param observer
	 */
	public void setMessageObserver(MessageObserver observer) {
		this.observer = observer;
	}

	public void deleteMessageObserver() {
		observer = null;
	}

	private YoheyNotificationManager(Context co) {
		this.context = co;
	};

	public static YoheyNotificationManager getInstance(Context context) {
		if (manager == null) {
			manager = new YoheyNotificationManager(context);
		}
		return manager;
	}

	public void addObserver(FriendMessageActivity obseverActivity) {
		this.obseverActivity = obseverActivity;
	}

	public void deleteObserver() {
		this.obseverActivity = null;
	}

	/**
	 * 处理消息，里面执行消息列表的本地保存，以及，消息的分发
	 * 
	 * @param event
	 */
	public void execMessage(MessageEvent event) {

		for (MessageReciverListener r : recivers) {
			if (r == null)
				continue;
			r.recive(event.getMessage());
		}
		boolean isRead = false;
		if (obseverActivity != null && obseverActivity.getConversation().getConversationId()
				.equals(event.getConversation().getConversationId())) {
			notifiObsever(event);
			isRead = true;
		} else if (observer != null) {
			if (observer.execMessage(event)) {
				notifi(event);
			}
		} else {
			notifi(event);
		}
		YoheyCache.saveMssageList(context, event.getMessage().getFromId(), event.getMessage(), isRead);
	}

	private void notifiObsever(MessageEvent event) {
		obseverActivity.getData();
	}

	List<MessageReciverListener> recivers = new ArrayList<MessageReciverListener>();

	/**
	 * 添加消息接受者
	 * 
	 * @param reciver
	 */
	public void addMssageReciver(MessageReciverListener reciver) {
		this.recivers.add(reciver);
	}

	/**
	 * 删除指定消息接收者
	 * 
	 * @param reciver
	 */
	public void deleteMssageReciver(MessageReciverListener reciver) {
		this.recivers.remove(reciver);
	}

	@SuppressLint("NewApi")
	private void notifi(MessageEvent event) {
		Intent tent = new Intent(context, FriendMessageActivity.class);
		Bundle b = new Bundle();
		b.putSerializable("c", event.getConversation());
		tent.putExtras(b);
		Notification.Builder builder = new Notification.Builder(context);
		builder.setSmallIcon(R.drawable.default_icon);
		builder.setTicker(event.getFromUserInfo().getName());
		builder.setContentTitle(event.getFromUserInfo().getName());
		builder.setContentText(event.getMessage().getContent());
		builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
		builder.setNumber(count);
		PendingIntent intent = PendingIntent.getActivity(context, 0, tent, 0);
		builder.setContentIntent(intent);
		Notification notification = builder.build();
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		NotificationManager mger = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mger.notify(1010, notification);

		count++;
	}

	public void initNotivication() {
		count = 1;
	}
}
