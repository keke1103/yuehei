package com.yuanchuang.yohey.view;

 
import com.yuanchuang.yohey.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class RecorderView {
	 float time;
	 String filePathString;
	 View mView;

	private RecorderView(float time, String filePathString) {
	 
		this.time = time;
		this.filePathString = filePathString;
		
		
	}
	public static RecorderView createRecorder(Context context,float time, String filePathString){
		RecorderView v=new RecorderView(time, filePathString);
		v.mView=LayoutInflater.from(context).inflate(R.layout.recorder_layout , null);
		TextView t=(TextView) v.mView.findViewById(R.id.yuyin_time);
		t.setText(time+"\"");	
		return v;	
	}
	
	public View getmView() {
		return mView;
	}
	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public String getFilePathString() {
		return filePathString;
	}

	public void setFilePathString(String filePathString) {
		this.filePathString = filePathString;
	}
	
}
