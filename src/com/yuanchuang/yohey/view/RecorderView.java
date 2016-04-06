package com.yuanchuang.yohey.view;

import java.io.File;
import java.io.IOException;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.cache.YoheyCache;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class RecorderView {
	int time;
	String filePathString;
	View mView;

	MediaPlayer player;

	@SuppressLint({ "InflateParams", "HandlerLeak" })
	private RecorderView(String filePathString, final Context context) {
		Log.i(">>>>>>>>>>>++++++", filePathString);
		String ps[] = filePathString.split("&");
		if (ps.length > 1||!ps[0].startsWith("http")) {
			this.filePathString = ps[0];
			File file = new File(this.filePathString);
			player = new MediaPlayer();
			if (file.exists()) {
				Uri uri = Uri.fromFile(file);
				try {
					player.setDataSource(context, uri);
					player.prepare();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			time = player.getDuration() / 1000;
			mView = LayoutInflater.from(context).inflate(R.layout.recorder_layout, null);
			mView.setOnClickListener(mClickListener);
			TextView t = (TextView) mView.findViewById(R.id.yuyin_time);
			t.setText(time + "\"");
		}else{
			Handler handler=new Handler(){
				@Override
				public void handleMessage(Message msg) {
					String path= (String) msg.obj;
					Log.i("RecorderView", "path:"+path);
					RecorderView.this.filePathString=path;
					File file = new File(path);
					player = new MediaPlayer();
					if (file.exists()) {
						Uri uri = Uri.fromFile(file);
						try {
							player.setDataSource(context, uri);
							player.prepare();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					time = player.getDuration() / 1000;
					TextView t = (TextView) mView.findViewById(R.id.yuyin_time);
					t.setText(time + "\"");
					mView.setOnClickListener(mClickListener);
				}
			};
			YoheyCache.getAudio(filePathString, context, handler);
			mView = LayoutInflater.from(context).inflate(R.layout.recorder_layout, null);
		
		}

	}
	
	
	

	OnClickListener mClickListener = new OnClickListener() {

		public void onClick(View v) {
			player.start();
		}
	};

	public static RecorderView createRecorder(Context context, String filePathString) {
		RecorderView v = new RecorderView(filePathString, context);
		return v;
	}

	public View getmView() {
		return mView;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getFilePathString() {
		return filePathString;
	}

	public void setFilePathString(String filePathString) {
		this.filePathString = filePathString;
	}

}
