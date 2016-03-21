package com.yuanchuang.yohey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 对换积分的界面
 * @author Administrator
 *
 */
public class IntegralStoreActivity extends Activity {
	RelativeLayout include;// 导入的标题
	TextView title;// 标题
	ImageView backImage;// 返回的图片
	TextView myIntegral;// 我的积分
	TextView integralBuyStore;// 积分兑换商品
	TextView buyGift;// 购买的礼品
	TextView receiveGift;// 收到的礼品
	TextView sendGift;// 送出的礼品
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.integral_store_main);
		findView();
	}

	/**
	 * 本页面的控件ID
	 */
	private void findView() {
		// TODO Auto-generated method stub
		include = (RelativeLayout) findViewById(R.id.integral_store_include);
		backImage = (ImageView) include.findViewById(R.id.title_navigation_back_icon);
		title = (TextView) include.findViewById(R.id.title_navigation_text_title);
		myIntegral = (TextView) findViewById(R.id.my_integral);
		integralBuyStore = (TextView) findViewById(R.id.integral_buy_store);
		buyGift = (TextView) findViewById(R.id.integral_store_buy);
		receiveGift = (TextView) findViewById(R.id.integral_store_receive);
		sendGift = (TextView) findViewById(R.id.integral_store_send);
		
		backImage.setVisibility(View.VISIBLE);
		backImage.setImageResource(R.drawable.yo_hey_back_image);
		title.setText("积分商城");
		
		myIntegral.setText("我的积分");
		integralBuyStore.setText("积分兑换的商品");
		buyGift.setText("购买过的商品");
		receiveGift.setText("收到的商品");
		sendGift.setText("送出的商品");
		
		myIntegral.setOnClickListener(clickListener);
		integralBuyStore.setOnClickListener(clickListener);
		buyGift.setOnClickListener(clickListener);
		receiveGift.setOnClickListener(clickListener);
		sendGift.setOnClickListener(clickListener);
        backImage.setOnClickListener(clickListener);
	}

	/**
	 * 控件的点击事件
	 */
	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.my_integral://我的积分点击事件
                intent=new Intent(IntegralStoreActivity.this,MyIntegralActivity.class);
                startActivity(intent);
				break;
			case R.id.integral_buy_store://积分兑换商品点击事件
				intent=new Intent(IntegralStoreActivity.this,IntegralExchangeCommodityActivity.class);
                startActivity(intent);
				break;
			case R.id.integral_store_buy://购买礼品点击事件
				intent=new Intent(IntegralStoreActivity.this,PurchaseCommodityActivity.class);
                startActivity(intent);
				break;
			case R.id.integral_store_receive://收到礼品点击事件
				intent=new Intent(IntegralStoreActivity.this,ReceiveGiftActivity.class);
                startActivity(intent);
				break;
			case R.id.integral_store_send://送出礼品点击事件
				intent=new Intent(IntegralStoreActivity.this,SendGiftActivity.class);
                startActivity(intent);
				break;
			case R.id.title_navigation_back_icon://关掉该页面
				finish();
				break;
			default:
				break;
			}
		}
	};
}
