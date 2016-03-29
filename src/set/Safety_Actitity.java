package set;

import com.yuanchuang.yohey.R;

import android.app.Activity;
import android.os.Bundle;
/**
 * 帐号安全界面
 * @author Administrator
 *
 */
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Safety_Actitity extends Activity {
	View toReturn;// 返回
	LinearLayout loginNmae;// 登录名
	LinearLayout emailAddr;// 邮箱地址
	LinearLayout phoneNamber;// 手机号
	TextView nickName;// 昵称
	TextView email;// 显示的邮箱或显示未绑定
	TextView number;// 显示的手机号
	TextView password;// 修改密码
	TextView safetyCenter;// 安全中心

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_account_safety);
		findView();
	}

	private void findView() {
		toReturn = findViewById(R.id.account_safety_image_to_return);
		loginNmae = (LinearLayout) findViewById(R.id.account_safety_linear_login_name);
		emailAddr = (LinearLayout) findViewById(R.id.account_safety_linear_email_addr);
		phoneNamber = (LinearLayout) findViewById(R.id.account_safety_linear_phone_number);
		nickName = (TextView) findViewById(R.id.account_safety_text_login_name);
		email = (TextView) findViewById(R.id.account_safety_text_email_addr);
		number = (TextView) findViewById(R.id.account_safety_text_phone_number);
		password = (TextView) findViewById(R.id.account_safety_text_change_password);
		safetyCenter = (TextView) findViewById(R.id.account_safety_text_safety_center);

		toReturn.setOnClickListener(clickListener);
		loginNmae.setOnClickListener(clickListener);
		emailAddr.setOnClickListener(clickListener);
		phoneNamber.setOnClickListener(clickListener);

		nickName.setText("SKT-1 Caker");
		email.setText("未绑定");
		number.setText("189 8953 4464");
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.account_safety_image_to_return:
				finish();
				break;

			case R.id.account_safety_linear_login_name:

				break;

			case R.id.account_safety_linear_email_addr:

				break;

			case R.id.account_safety_linear_phone_number:

				break;

			default:
				break;
			}

		}
	};
}
