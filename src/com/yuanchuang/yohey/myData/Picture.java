package com.yuanchuang.yohey.myData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.cache.YoheyCache;
import com.yuanchuang.yohey.cache.YoheySqlHelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

		if (url != null && url.startsWith("http")) {
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

						SQLiteDatabase db = YoheyCache.getSqlDB(mView.getContext());
						Cursor c = db.query(YoheySqlHelper.IMAGE_TABLE, new String[] { "path" }, "url=?",
								new String[] { thumbnail_pic }, null, null, null);
						File f = null;
						String path;
						if (!c.moveToNext()) {
							path = null;
						} else {
							path = c.getString(c.getColumnIndex("path"));
							f = new File(YoheyCache.getImageFile().getPath() + File.separator + path);
						}
						c.close();
						if (f == null || !f.exists()) {
							c.close();
							url = new URL(thumbnail_pic);
							InputStream in = url.openStream();
							if (f == null) {
								int rand = (int) (Math.random() * 10);
								path = System.currentTimeMillis() + rand + ".png";
								f = new File(YoheyCache.getImageFile().getPath() + File.separator + path);
								ContentValues cv = new ContentValues();
								cv.put("path", path);
								cv.put("url", thumbnail_pic);
								db.insert(YoheySqlHelper.IMAGE_TABLE, null, cv);
							}
							FileOutputStream fos = new FileOutputStream(f);
							byte[] buf = new byte[1024];
							int len;
							while ((len = in.read(buf)) != -1) {
								fos.write(buf, 0, len);
							}
							fos.flush();
							fos.close();
							in.close();

						}
						db.close();
						Log.i("Picture", f.getPath());
						mBitmap = BitmapFactory.decodeStream(new FileInputStream(f));
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
