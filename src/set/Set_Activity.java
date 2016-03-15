package set;

import com.yuanchuang.yohey.LoginAndRegistered;
import com.yuanchuang.yohey.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
/**
 * 系统设置界面
 * @author Administrator
 *
 */
public class Set_Activity extends Activity {
	TextView account_manager, account_safety, usual_set, clear_cache;
	TextView private_set, question_back, about_us, exit_account;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set);
		account_manager = (TextView) findViewById(R.id.tv_account_manager);
		account_safety = (TextView) findViewById(R.id.tv_account_safety);
		usual_set = (TextView) findViewById(R.id.tv_usual_set);
		clear_cache = (TextView) findViewById(R.id.tv_clear_cache);
		private_set = (TextView) findViewById(R.id.tv_private_set);
		question_back = (TextView) findViewById(R.id.tv_question_back);
		about_us = (TextView) findViewById(R.id.tv_about_us);
		exit_account = (TextView) findViewById(R.id.exit_account);
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
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tv_account_manager://跳转到界面管理界面
				Intent intent_manager = new Intent(Set_Activity.this, Manager_Activity.class);
				startActivity(intent_manager);
				break;
			case R.id.tv_account_safety://跳转到帐号安全界面
				Intent intent_safety = new Intent(Set_Activity.this, Safety_Actitity.class);
				startActivity(intent_safety);
				break;
			case R.id.tv_usual_set://跳到通用设置
				Intent intent_usual = new Intent(Set_Activity.this, Usual_set_Activity.class);
				startActivity(intent_usual);
				break;
			case R.id.tv_clear_cache://

				break;
			case R.id.tv_private_set://跳到隐私设置界面
				Intent intent_private = new Intent(Set_Activity.this, Private_set_Activity.class);
				startActivity(intent_private);
				break;
			case R.id.tv_question_back://跳到问题反馈界面
				Intent intent_back = new Intent(Set_Activity.this, Question_back_Activity.class);
				startActivity(intent_back);
				break;
			case R.id.tv_about_us://跳到关于我们界面
				Intent intent_about = new Intent(Set_Activity.this, About_us_Activity.class);
				startActivity(intent_about);
				break;
			case R.id.exit_account://条到登录界面
				Intent intent_exit = new Intent(Set_Activity.this,LoginAndRegistered.class);
				startActivity(intent_exit);
				break;
			}
		}
	};
}
