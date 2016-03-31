package set;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.R.color;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 问题反馈界面
 * 
 * @author Administrator
 *
 */
public class Question_back_Activity extends Activity {
	View toReturn;// 返回
	EditText content;// 输入内容
	LinearLayout position;// 位置
	LinearLayout globe;// 公开
	TextView rightTile;

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
		rightTile = (TextView) findViewById(R.id.question_text_to_right);

		rightTile.setVisibility(View.VISIBLE);
		rightTile.setText("完成");
		rightTile.setTextColor(color.black);
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
