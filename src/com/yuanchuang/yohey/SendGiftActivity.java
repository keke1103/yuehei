package com.yuanchuang.yohey;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 送出的礼品
 * 
 * @author 冯灿
 *
 */
public class SendGiftActivity extends Activity {
	LinearLayout integralCommodity;// 积分兑换商品界面的LinearLayout
	ImageView commodityImage1;// 商品图片
	ImageView commodityImage2;// 商品图片
	ImageView backImage;// 返回图标
	TextView title;// 标题
	RelativeLayout include;// 导入头文件
	LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.integral_exchange_commodity);
		findView();
		backImage.setVisibility(View.VISIBLE);
		backImage.setImageResource(R.drawable.rub_course_back_icon);
		title.setText("送出的礼品");
		backImage.setOnClickListener(clickListener);
		addImage(6);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.title_navigation_back_icon:
				finish();
				break;

			default:
				break;
			}
		}
	};

	/**
	 * 控件的ID
	 */
	private void findView() {
		// TODO Auto-generated method stub
		integralCommodity = (LinearLayout) findViewById(R.id.integral_exchange_commodity_linear1);
		include = (RelativeLayout) findViewById(R.id.integral_exchange_commodity_include);
		backImage = (ImageView) include.findViewById(R.id.title_navigation_back_icon);
		title = (TextView) include.findViewById(R.id.title_navigation_text_title);
		inflater = LayoutInflater.from(getApplication());
	}

	/**
	 * 显示的商品方法
	 * 
	 * @param i
	 *            商品的个数
	 */
	@SuppressLint("InflateParams")
	public void addImage(int i) {
		for (int j = 0; j < i / 2; j++) {
			View view = inflater.inflate(R.layout.commodity_image_main, null);
			commodityImage1 = (ImageView) view.findViewById(R.id.integral_exchange_commodity_image1);
			commodityImage2 = (ImageView) view.findViewById(R.id.integral_exchange_commodity_image2);
			integralCommodity.addView(view);
			commodityImage1.setImageResource(R.drawable.ic_launcher);
			commodityImage2.setImageResource(R.drawable.ic_launcher);
		}
		if (i % 2 == 1) {
			View view = inflater.inflate(R.layout.commodity_image_main, null);
			commodityImage1 = (ImageView) view.findViewById(R.id.integral_exchange_commodity_image1);
			commodityImage2 = (ImageView) view.findViewById(R.id.integral_exchange_commodity_image2);
			integralCommodity.addView(view);
			commodityImage1.setImageResource(R.drawable.ic_launcher);
		}
	}

}
