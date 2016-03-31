package com.yuanchuang.yohey.app;

import com.yuanchuang.yohey.FriendMessageActivity;
import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.adapter.MessageBaseAdapter;
import com.yuanchuang.yohey.myData.MssageListData;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.widget.BaseAdapter;
import cn.bmob.newim.event.MessageEvent;

public class YoheyNotificationManager {
	public static YoheyNotificationManager manager;

	public int count = 1;
	private Context context;

	FriendMessageActivity obseverActivity;

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

	public void execMessage(MessageEvent event) {
		if (adapterObser != null) {
			MssageListData data = new MssageListData();
			data.setFriendId(event.getMessage().getFromId());
			data.setMsg(event.getMessage().getContent());
			data.setTime(System.currentTimeMillis() / 1000);
			adapterObser.addMssage(data);
		}
		if (obseverActivity != null && obseverActivity.getConversation().getConversationId()
				.equals(event.getConversation().getConversationId())) {
			notifiObsever(event);
		} else {
			notifi(event);
		}
	}

	private void notifiObsever(MessageEvent event) {

		obseverActivity.getData();
	}

	MessageBaseAdapter adapterObser;

	public void addMssageObserb(MessageBaseAdapter adapterObser) {
		this.adapterObser = adapterObser;
	}

	public void deleteMssageObserb() {
		adapterObser = null;
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
