package set;

import com.yuanchuang.yohey.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
/**
 * 帐号管理界面
 * @author Administrator
 *
 */
import android.widget.LinearLayout;
import android.widget.TextView;

public class Manager_Activity extends Activity {
	View toReturn;// 返回
	ImageView head;// 头像
	TextView name;// 名字
	LinearLayout addNumber;// 添加账号
	LinearLayout exitNumber;// 退出账号

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_account_manager);
		findVIew();
	}

	private void findVIew() {
		toReturn = findViewById(R.id.set_account_to_return);
		head = (ImageView) findViewById(R.id.set_account_head);
		name = (TextView) findViewById(R.id.set_account_name);
		addNumber = (LinearLayout) findViewById(R.id.set_account_add_linear_account_number);
		exitNumber = (LinearLayout) findViewById(R.id.set_account_exit_account_linear_account_number);

		toReturn.setOnClickListener(clickListener);
		addNumber.setOnClickListener(clickListener);
		exitNumber.setOnClickListener(clickListener);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.set_account_to_return:
				finish();
				break;
			case R.id.set_account_add_linear_account_number:

				break;
			case R.id.set_account_exit_account_linear_account_number:
				
				break;
			default:
				break;
			}

		}
	};
}
