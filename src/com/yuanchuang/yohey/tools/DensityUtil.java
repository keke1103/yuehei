package com.yuanchuang.yohey.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class DensityUtil {
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 绘制九宫格图片
	 * 
	 * @param mContext
	 * @param imageLayout
	 * @param imgs
	 */
	public static void sudoku(Context mContext, AbsoluteLayout imageLayout, Bitmap[] imgs) {
		if (imgs != null) {
			int w = imageLayout.getWidth();
			LayoutParams params;

			int i = 0;
			ImageView v;
			imageLayout.removeAllViews();
			if (imgs.length == 1) {
				params = new LayoutParams(w/2 - 20, w /2- 20, 10, 0);
				v = new ImageView(mContext);
				imageLayout.addView(v, params);
				v.setBackgroundColor(Color.GRAY);
				// imgs[i].binderImageView(v);
				v.setImageBitmap(imgs[i]);

			} else if (imgs.length == 2) {
				params = new LayoutParams(w / 3 - 10, w / 3 - 10, 0, 0);
				v = new ImageView(mContext);
				v.setBackgroundColor(Color.GRAY);
				imageLayout.addView(v, params);
				// imgs[0].binderImageView(v);
				v.setImageBitmap(imgs[i]);
				params = new LayoutParams(w / 2 - 10, w / 2 - 10, w / 2, 0);
				v = new ImageView(mContext);
				v.setBackgroundColor(Color.GRAY);
				// imgs[1].binderImageView(v);
				v.setImageBitmap(imgs[i]);
			} else if (imgs.length == 4) {
				for (Bitmap p : imgs) {
					params = new LayoutParams(w / 2 - 10, w / 2 - 10, (i % 3) * w / 2, (i / 3) * w / 2);
					v = new ImageView(mContext);
					v.setBackgroundColor(Color.GRAY);
					imageLayout.addView(v, params);
					// p.binderImageView(v);
					v.setImageBitmap(p);
					i++;
				}
			} else
				for (Bitmap p : imgs) {
					if (i == 9) {
						break;// 只显示9张图片！
					}
					params = new LayoutParams(w / 3 - 5, w / 3 - 5, (i % 3) * w / 3, (i / 3) * w / 3);
					v = new ImageView(mContext);
					v.setBackgroundColor(Color.GRAY);
					imageLayout.addView(v, params);
					// p.binderImageView(v);
					v.setImageBitmap(p);
					i++;
				}
		}
	}
}
