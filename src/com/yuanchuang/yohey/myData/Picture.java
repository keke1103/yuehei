package com.yuanchuang.yohey.myData;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.yuanchuang.yohey.app.YoheyApplication;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

/**
 * 图片类！
 * 
 * @author kk0927
 *
 */
public class Picture {
	public String thumbnail_pic;
	public Bitmap mBitmap;
	public ImageView mView;

	public Picture() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 使用》 new Picture(jo.getString(key));
	 * 
	 * @param url
	 */
	public Picture(String url) {
		if (url.startsWith("http")) {
			thumbnail_pic = url;
		} else {
			thumbnail_pic = YoheyApplication.ServiceIp + url;
		}
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler;

	/**
	 * 通过绑定imageView,来启动image的下载，并在下载完之后让绑定的这个imageView显示；
	 * 
	 * @param v
	 */
	@SuppressLint("HandlerLeak")
	public void binderImageView(ImageView v) {
		this.mView = v;
		mHandler = new Handler() {
			@Override
			public void handleMessage(android.os.Message msg) {
				// if (mBitmap != null)
				mView.setBackgroundColor(0);
				mView.setImageBitmap(mBitmap);
			};
		};
		setBitmap();
	}

	public void setBitmap() {

		new Thread() {
			@Override
			public void run() {
				URL url;
				try {
					if (mBitmap == null) {// 如果为空下载图片
						url = new URL(thumbnail_pic);
						mBitmap = BitmapFactory.decodeStream(url.openStream());
					}
					mHandler.sendEmptyMessage(0);
				} catch (MalformedURLException e) {
					Log.i("Picture", "图片地址格式不正确");
				} catch (IOException e) {
					e.printStackTrace();
					Log.i("Picture", "图片获取失败");
				}

			};

		}.start();
	}

}
