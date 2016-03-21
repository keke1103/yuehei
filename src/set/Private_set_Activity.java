package set;

import com.yuanchuang.yohey.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;

/**
 * 隐私设置界面
 * 
 * @author Administrator
 *
 */
public class Private_set_Activity extends Activity {
	View toReturn;// 返回
	CheckBox verification;// 加我时验证
	CheckBox qqFriend;// 向我推荐扣扣好友
	CheckBox phoneNumber;// 通过手机号
	LinearLayout blacklist;// 黑名单
	LinearLayout not_allowed_to_see;// 不允许（他）看我

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_private_set);
		findView();
	}

	private void findView() {
		toReturn = findViewById(R.id.private_set_image_to_return);
		verification = (CheckBox) findViewById(R.id.private_set_check_box_add_need);
		qqFriend = (CheckBox) findViewById(R.id.private_set_check_box_qq_friend);
		phoneNumber = (CheckBox) findViewById(R.id.private_set_check_box_search);
		blacklist = (LinearLayout) findViewById(R.id.private_set_linear_biack_list);
		not_allowed_to_see = (LinearLayout) findViewById(R.id.private_set_linear_not_see_mi);

		toReturn.setOnClickListener(clickListener);
		blacklist.setOnClickListener(clickListener);
		not_allowed_to_see.setOnClickListener(clickListener);

		verification.setOnCheckedChangeListener(checkedChangeListener);
		qqFriend.setOnCheckedChangeListener(checkedChangeListener);
		phoneNumber.setOnCheckedChangeListener(checkedChangeListener);
	}

	OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			switch (buttonView.getId()) {
			case R.id.private_set_check_box_add_need:

				break;
			case R.id.private_set_check_box_qq_friend:

				break;
			case R.id.private_set_check_box_search:

				break;

			default:
				break;
			}
		}
	};
	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.private_set_image_to_return:
				finish();
				break;
			case R.id.private_set_linear_biack_list:

				break;
			case R.id.private_set_linear_not_see_mi:

				break;
			default:
				break;
			}

		}
	};
}
