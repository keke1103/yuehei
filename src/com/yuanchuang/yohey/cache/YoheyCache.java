package com.yuanchuang.yohey.cache;

import java.io.File;

import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.myData.MssageListData;
import com.yuanchuang.yohey.tools.FileUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import cn.bmob.newim.BmobIM;

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

	/**
	 * 获取缓存大小
	 */
	public static long getCacheLength() {
		long len = 0;
		File dir = getYoheyFile();
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return 0;

		for (File file : dir.listFiles()) {
			if (file.isFile())
				len += file.length(); //
			else if (file.isDirectory()) {
				for (File f : file.listFiles()) {
					if (file.isFile()) {
						len += f.length();
					}
				}
			}
		}
		return len;
	}

	/**
	 * 删除图片缓存
	 */
	public static void deleteImageCache() {
		File dir = getImageFile();
		FileUtils.deleteDir(dir);
	}

	/**
	 * 删除所有缓存
	 */
	public static void deleteCache() {
		File dir = getYoheyFile();
		FileUtils.deleteDir(dir);
	}

	/**
	 * 保存聊天记录表
	 * 
	 * @param context
	 * @param friendId
	 * @param msg
	 */
	public static void saveMssageList(Context context, String friendId, String msg) {
		SQLiteDatabase db = getSqlDB(context);
		Cursor c = db.query(YoheySqlHelper.MSSAGE_TABLE, new String[] { "id" }, "friendId=?", new String[] { friendId },
				null, null, null);
		int id = 0;
		if (c.moveToNext()) {
			ContentValues cv = new ContentValues();
			cv.put("time", System.currentTimeMillis() / 1000);
			cv.put("newmsg", msg);
			id = c.getInt(c.getColumnIndex("id"));
			db.update(YoheySqlHelper.MSSAGE_TABLE, cv, "id=" + id, null);

		} else {
			ContentValues cv = new ContentValues();
			cv.put("friendId", friendId);
			cv.put("time", System.currentTimeMillis() / 1000);
			cv.put("newmsg", msg);
			db.insert(YoheySqlHelper.MSSAGE_TABLE, null, cv);
		}
		c.close();
		db.close();
	}

	/**
	 * 获取最近消息聊天记录表
	 * 
	 * @param app
	 */
	public static void getMssageList(YoheyApplication app) {
		SQLiteDatabase db = getSqlDB(app.getApplicationContext());
		Cursor c = db.query(YoheySqlHelper.MSSAGE_TABLE, null, null, null, null, null, "time desc");
		app.msgList.clear();
		while (c.moveToNext()) {
			MssageListData data = new MssageListData();
			data.setFriendId(c.getString(c.getColumnIndex("friendId")));
			data.setMsg(c.getString(c.getColumnIndex("newmsg")));
			app.msgList.add(data);
		}
		c.close();
		db.close();
	}

	/**
	 * 清空数据库缓存,包括聊天列表和聊天消息表
	 * 
	 * @param context
	 */
	public static void deleteDbData(Context context) {
		SQLiteDatabase db = getSqlDB(context);
		db.delete(YoheySqlHelper.MSSAGE_TABLE, null, null);
		BmobIM.getInstance().clearAllConversation();
	}
}
