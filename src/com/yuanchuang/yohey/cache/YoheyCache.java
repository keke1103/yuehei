package com.yuanchuang.yohey.cache;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class YoheyCache {

	/**
	 * 获取程序数据缓存区目录
	 * 
	 * @return
	 */
	public static File getYoheyFile() {
		File file;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "yohey");
		} else {
			file = new File(Environment.getDataDirectory().getPath() + File.separator + "yohey");
		}
		file.mkdir();
		return file;
	}

	/**
	 * 获取图片缓存目录
	 * 
	 * @return
	 */
	public static File getImageFile() {
		File f = new File(getYoheyFile().getPath() + File.separator + "images");
		f.mkdir();
		return f;
	}

	public static SQLiteDatabase getSqlDB(Context context) {
		YoheySqlHelper helper = new YoheySqlHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		return db;
	}
}
