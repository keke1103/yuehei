package com.yuanchuang.yohey.cache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class YoheySqlHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "yohey_db";
	public static int VERSION = 1;
	public static final String IMAGE_TABLE = "image_ceche";
	public static final String MSSAGE_TABLE = "mssage";
	public String tableName = "image_ceche";

	public YoheySqlHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	public YoheySqlHelper(Context context, int version) {
		super(context, DB_NAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		if (db != null) {
			String sql = "Create table `" + IMAGE_TABLE
					+ "` (`url` VERCHAR(300) PRIMARY KEY,`path` VERCHAR(300) NOT NULL ); ";
			db.execSQL(sql);
			sql = "Create table `" + MSSAGE_TABLE
					+ "` (`id` INTEGER PRIMARY KEY AUTOINCREMENT,`friendId` VARCHAR(30) NOT NULL ,`top` INTEGER(8),`newmsg` VARCHAR(60),`time` INTEGER(15));";
			db.execSQL(sql);//autoincrement
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (db == null)
			return;
		String sql = " DROP TABLE IF EXISTS " + tableName;// 如果存在此表，则删除！
		db.execSQL(sql); // 删除
		onCreate(db); // 删除之后在创建

	}

}
