package com.yuanchuang.yohey.view;

import java.io.File;
import java.io.IOException;

import com.yuanchuang.yohey.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class RecorderView {
	int time;
	String filePathString;
	View mView;

	MediaPlayer player;

	@SuppressLint("InflateParams")
	private RecorderView(String filePathString, Context context) {

		this.filePathString = filePathString;
		File file = new File(filePathString);
		player = new MediaPlayer();
		if (file.exists()) {
			Uri uri = Uri.fromFile(file);
			try {
				player.setDataSource(context, uri);
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
		time = player.getCurrentPosition() / 1000;
		mView = LayoutInflater.from(context).inflate(R.layout.recorder_layout, null);
		mView.setOnClickListener(mClickListener);
		TextView t = (TextView) mView.findViewById(R.id.yuyin_time);
		t.setText(time + "\"");

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
