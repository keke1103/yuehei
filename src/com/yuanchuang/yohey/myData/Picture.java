package com.yuanchuang.yohey.myData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.yuanchuang.yohey.cache.YoheyCache;
import com.yuanchuang.yohey.cache.YoheySqlHelper;
import com.yuanchuang.yohey.view.BmobImageView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import cn.bmob.v3.datatype.BmobFile;

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
	}

	/**
	 * 使用》 new Picture(jo.getString(key));
	 * 
	 * @param url
	 */
	public Picture(String url) {
		thumbnail_pic = url;
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

	/**
	 * 展示BmobFile图片
	 * 
	 * @param bf
	 * @param v
	 * @param w
	 *            缩略图宽
	 * @param h
	 *            缩略图高
	 */
	public static void showBmobImage(final BmobFile bf, final BmobImageView v, int w, int h) {
		SQLiteDatabase db = YoheyCache.getSqlDB(v.getContext());
		Cursor c = db.query(YoheySqlHelper.IMAGE_TABLE, new String[] { "path" }, "url=?",
				new String[] { bf.getUrl() + "_" + w + "*" + h }, null, null, null);
		String path;
		File f;
		if (c.moveToNext()) {
			path = c.getString(c.getColumnIndex("path"));
			f = new File(YoheyCache.getImageFile().getPath() + File.separator + path);
			InputStream is;
			try {
				is = new FileInputStream(f);
				Bitmap bm = BitmapFactory.decodeStream(is);
				v.setImageBitmap(bm);
				is.close();
				Log.i("Picture", "缓存加载成功");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			v.setUrl(bf.getUrl() + "_" + w + "*" + h);
			Log.i("Picture", "正在执行缓存");
			bf.loadImage(v.getContext(), v, w, h);

		}
	}

	/**
	 * 展示原图
	 * 
	 * @param bf
	 * @param v
	 */
	public static void showBmobImage(BmobFile bf, final BmobImageView v) {
		SQLiteDatabase db = YoheyCache.getSqlDB(v.getContext());
		Cursor c = db.query(YoheySqlHelper.IMAGE_TABLE, new String[] { "path" }, "url=?", new String[] { bf.getUrl() },
				null, null, null);
		String path;
		File f;
		if (c.moveToNext()) {
			path = c.getString(c.getColumnIndex("path"));
			f = new File(YoheyCache.getImageFile().getPath() + File.separator + path);
			InputStream is;
			try {
				is = new FileInputStream(f);
				Bitmap bm = BitmapFactory.decodeStream(is);
				v.setImageBitmap(bm);
				is.close();
				Log.i("Picture", "缓存加载成功");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			v.setUrl(bf.getUrl());
			Log.i("Picture", "正在执行缓存");
			bf.loadImage(v.getContext(), v);

		}
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
						InputStream ips = new FileInputStream(f);
						mBitmap = BitmapFactory.decodeStream(ips);
						ips.close();
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
