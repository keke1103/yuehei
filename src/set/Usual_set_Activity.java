package set;

import com.yuanchuang.yohey.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;

/**
 * 通用设置界面
 * 
 * @author Administrator
 *
 */
public class Usual_set_Activity extends Activity {
	View toReturn;// 返回
	CheckBox remark;// 备注
	CheckBox quick_drag;// 快速拖动
	CheckBox switch_the_screen_anyway;// 横竖屏切换
	LinearLayout textSet;// 设置字体
	LinearLayout setRemark;// 设置备注
	LinearLayout setQuick_drag;// 设置拖动
	LinearLayout setSwitch_screen;// 设置横竖屏
	LinearLayout settingSound;// 设置声音

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_usual_set);
		findView();
	}

	private void findView() {
		toReturn = findViewById(R.id.set_usual_image_to_return);
		remark = (CheckBox) findViewById(R.id.set_usual_check_box_remark);
		quick_drag = (CheckBox) findViewById(R.id.set_usual_check_box_portrait);
		switch_the_screen_anyway = (CheckBox) findViewById(R.id.set_usual_check_box_start_fast);
		textSet = (LinearLayout) findViewById(R.id.set_usual_linear_textsize_set);
		setRemark = (LinearLayout) findViewById(R.id.set_usual_linear_remark);
		setQuick_drag = (LinearLayout) findViewById(R.id.set_usual_linear_portrait);
		setSwitch_screen = (LinearLayout) findViewById(R.id.set_usual_linear_start_fast);
		settingSound = (LinearLayout) findViewById(R.id.set_usual_linear_sound_vibrate);

		toReturn.setOnClickListener(clickListener);

	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.set_usual_image_to_return:
				finish();
				break;

			default:
				break;
			}

		}
	};
}
