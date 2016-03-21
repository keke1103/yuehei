package set;

import com.yuanchuang.yohey.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 系统设置界面
 * 
 * @author Administrator
 *
 */
public class Set_Activity extends Activity {
	LinearLayout account_manager;// 界面管理界面
	LinearLayout account_safety;// 帐号安全界面
	LinearLayout usual_set;// 通用设置
	LinearLayout clear_cache;// 清理缓存
	LinearLayout private_set;// 隐私设置界面
	LinearLayout question_back;// 问题反馈界面
	LinearLayout about_us;// 关于我们
	LinearLayout exit_account;// 退出账号
	TextView clear;// 缓存
	View toReturn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_system_settings);
		findView();

	}

	private void findView() {
		account_manager = (LinearLayout) findViewById(R.id.system_settings_linear_manager);
		account_safety = (LinearLayout) findViewById(R.id.system_settings_linear_safety);
		usual_set = (LinearLayout) findViewById(R.id.system_settings_linear_usual_set);
		clear_cache = (LinearLayout) findViewById(R.id.system_settings_linear_clear_cache);
		private_set = (LinearLayout) findViewById(R.id.system_settings_linear_private_set);
		question_back = (LinearLayout) findViewById(R.id.system_settings_linear_question_back);
		about_us = (LinearLayout) findViewById(R.id.system_settings_linear_about_us);
		exit_account = (LinearLayout) findViewById(R.id.system_settings_linear_exit_assount);
		clear = (TextView) findViewById(R.id.system_settings_text_clear);
		toReturn = findViewById(R.id.system_settings_image_to_return);

		clear.setText("500M");

		toReturn.setOnClickListener(listener);
		account_manager.setOnClickListener(listener);
		account_safety.setOnClickListener(listener);
		usual_set.setOnClickListener(listener);
		clear_cache.setOnClickListener(listener);
		private_set.setOnClickListener(listener);
		question_back.setOnClickListener(listener);
		about_us.setOnClickListener(listener);
		exit_account.setOnClickListener(listener);

	}

	OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.system_settings_linear_manager:// 跳转到界面管理界面
				Intent intent_manager = new Intent(Set_Activity.this, Manager_Activity.class);
				startActivity(intent_manager);
				break;
			case R.id.system_settings_linear_safety:// 跳转到帐号安全界面
				Intent intent_safety = new Intent(Set_Activity.this, Safety_Actitity.class);
				startActivity(intent_safety);
				break;
			case R.id.system_settings_linear_usual_set:// 跳到通用设置
				Intent intent_usual = new Intent(Set_Activity.this, Usual_set_Activity.class);
				startActivity(intent_usual);
				break;
			case R.id.system_settings_linear_clear_cache:// 清理缓存

				break;
			case R.id.system_settings_linear_private_set:// 跳到隐私设置界面
				Intent intent_private = new Intent(Set_Activity.this, Private_set_Activity.class);
				startActivity(intent_private);
				break;
			case R.id.system_settings_linear_question_back:// 跳到问题反馈界面
				Intent intent_back = new Intent(Set_Activity.this, Question_back_Activity.class);
				startActivity(intent_back);
				break;
			case R.id.system_settings_linear_about_us:// 跳到关于我们界面
				Intent intent_about = new Intent(Set_Activity.this, About_us_Activity.class);
				startActivity(intent_about);
				break;
			case R.id.system_settings_linear_exit_assount:// 条到登录界面

				break;
			case R.id.system_settings_image_to_return:// 条到登录界面
				finish();
				break;
			default:
				break;
			}
		}
	};

}
