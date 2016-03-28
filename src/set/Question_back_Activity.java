package set;

import com.yuanchuang.yohey.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
/**
 * 问题反馈界面
 * @author Administrator
 *
 */
import android.widget.LinearLayout;

public class Question_back_Activity extends Activity {
	View toReturn;// 返回
	EditText content;// 输入内容
	LinearLayout position;// 位置
	LinearLayout globe;// 公开

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_question_back);
		findView();
	}

	private void findView() {
		toReturn = findViewById(R.id.question_image_to_return);
		content = (EditText) findViewById(R.id.question_image_edit_content);
		position = (LinearLayout) findViewById(R.id.question_image_linear_display_position);
		globe = (LinearLayout) findViewById(R.id.question_image_linear_globe);

		toReturn.setOnClickListener(clickListener);
		globe.setOnClickListener(clickListener);
		position.setOnClickListener(clickListener);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.question_image_to_return:
				finish();
				break;
			case R.id.question_image_linear_display_position:

				break;
			case R.id.question_image_linear_globe:

				break;
			default:
				break;
			}

		}
	};
}
