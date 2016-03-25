package set;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.app.YoheyApplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 帐号管理界面
 * 
 * @author Administrator
 *
 */
public class Manager_Activity extends Activity {
	View toReturn;// 返回
	ImageView head;// 头像
	TextView name;// 名字
	LinearLayout addNumber;// 添加账号
	LinearLayout exitNumber;// 退出账号
	YoheyApplication youhei;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_account_manager);
		findVIew();
		youhei = (YoheyApplication) getApplication();
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
				youhei.loginOutQq(Manager_Activity.this);
				break;
			default:
				break;
			}

		}
	};
}
