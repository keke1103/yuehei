package com.yuanchuang.yohey.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.yuanchuang.yohey.cache.YoheyCache;
import com.yuanchuang.yohey.cache.YoheySqlHelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class BmobImageView extends ImageView {

	String tag = this.getClass().toString();
	String url;

	public void setUrl(String url) {
		this.url = url;
	}

	public BmobImageView(Context context) {
		super(context);
	}

	public BmobImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BmobImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@SuppressLint("NewApi")
	public BmobImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	public void setImageBitmap(final Bitmap bm) {
		Log.i(tag, "setImageBitmap");

		super.setImageBitmap(bm);
		if (bm == null)
			return;
		new Thread() {
			public void run() {
				saveImage(bm);
			};
		}.start();
	}

	/**
	 * 缓存bitmap
	 * 
	 * @param bm
	 */
	public void saveImage(Bitmap bm) {
		if (url == null)
			return;

		SQLiteDatabase db = YoheyCache.getSqlDB(getContext());
		try {
			int ran = (int) (Math.random() * 20);
			String name = System.currentTimeMillis() + "" + ran + ".png";
			File f = new File(YoheyCache.getImageFile().getPath() + File.separator + name);
			try {
				FileOutputStream fos = new FileOutputStream(f);
				bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
				fos.flush();
				fos.close();
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
				e.printStackTrace();
			}
			ContentValues values = new ContentValues();
			values.put("url", url);
			values.put("path", name);
			db.insert(YoheySqlHelper.IMAGE_TABLE, null, values);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	protected void onDetachedFromWindow() {
		Log.i("BmobImageView", "我的imageView销毁了");
		try {
			BitmapDrawable bd = (BitmapDrawable) getDrawable();
			bd.getBitmap().recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onDetachedFromWindow();
	}
}
