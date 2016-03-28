package set;

import com.yuanchuang.yohey.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 关于我们界面
 * 
 * @author Administrator
 *
 */
public class About_us_Activity extends Activity {
	TextView versionNumber;// 版本号
	TextView score;// 评分
	TextView features;// 功能介绍
	TextView new_version;// 新版本
	View toReturn;//返回
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_about_us);
		findView();
	}

	private void findView() {
		versionNumber = (TextView) findViewById(R.id.set_about_us_version_number);
		score = (TextView) findViewById(R.id.set_about_us_score);
		features = (TextView) findViewById(R.id.set_about_us_features);
		new_version = (TextView) findViewById(R.id.set_about_us_new_version);
		toReturn=findViewById(R.id.set_about_us_to_return);
		
		versionNumber.setOnClickListener(clickListener);
		score.setOnClickListener(clickListener);
		features.setOnClickListener(clickListener);
		new_version.setOnClickListener(clickListener);
		toReturn.setOnClickListener(clickListener);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.set_about_us_version_number://版本号
				
				break;
			case R.id.set_about_us_score://评分

				break;
			case R.id.set_about_us_features://功能

				break;
			case R.id.set_about_us_new_version://新版本

				break;
			case R.id.set_about_us_to_return://返回
				finish();
				break;
			default:
				break;
			}

		}
	};
}
