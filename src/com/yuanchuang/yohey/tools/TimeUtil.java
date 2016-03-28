package com.yuanchuang.yohey.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

@SuppressLint("SimpleDateFormat")
public class TimeUtil {

	private TimeUtil() {
	};

	/**
	 * 根据当前时间来格式化时间 </br>
	 * </p>
	 * 例:刚刚，昨天，几天前；
	 * 
	 * @param t
	 * @return 昨天,几天前,2015-12-05
	 */

	public static String formateTimeToNow(long t) {
		Date d = new Date(t);
		return formateTimeToNow(d);
	}

	/**
	 * 根据当前时间来格式化时间
	 * </p>
	 * 例:刚刚，昨天，几天前；
	 * 
	 * @param d
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String formateTimeToNow(Date d) {
		SimpleDateFormat format;

		Date n = new Date();
		if (d.getYear() < n.getYear()) {
			format = new SimpleDateFormat("yyyy-MM-dd");
			return format.format(d);
		} else if (d.getMonth() < n.getMonth()) {
			format = new SimpleDateFormat("MM-dd");
			return format.format(d);
		} else if ((n.getDate() - d.getDate()) > 7) {
			format = new SimpleDateFormat("MM-dd");
			return format.format(d);
		} else if ((n.getDate() - d.getDate()) == 1) {
			return "昨天";
		} else if ((n.getDate() - d.getDate()) <= 7 && (n.getDate() - d.getDate()) > 1) {
			return (n.getDate() - d.getDate()) + "天前";
		} else if ((n.getHours() - d.getHours()) < 1 && (n.getMinutes() - d.getMinutes()) < 3) {
			return "刚刚";
		} else {
			format = new SimpleDateFormat("HH:mm");
			return format.format(d);
		}
	}

	/**
	 * 只能解析格式为：2016-03-21 17:26:37;的时间
	 * 
	 * @param time
	 * @return 例:刚刚，昨天，几天前；
	 */
	public static String formateTimeToNow(String time) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = f.parse(time);
			return formateTimeToNow(date);
		} catch (Exception e) {

			e.printStackTrace();
			return "异常";
		}
	}

	/**
	 * 根据现在时间返回字符串
	 * </p>
	 * 返回非刚刚，几天前类型的时间
	 * 
	 * @param time
	 * @return
	 */
	public static String formateTime(long time) {
		Date d = new Date(time);
		return formateTime(d);
	}

	@SuppressWarnings("deprecation")
	public static String formateTime(Date d) {
		SimpleDateFormat format;
		Date n = new Date();
		if (d.getYear() < n.getYear()) {
			format = new SimpleDateFormat("yyyy-MM-dd");
			return format.format(d);
		} else {
			if (n.getMonth() == d.getMonth() && n.getDate() == d.getDate()) {
				format = new SimpleDateFormat("HH:mm:ss");
				return format.format(d);
			}
			format = new SimpleDateFormat("MM-dd hh:mm");
			return format.format(d);
		}
	}
}
