package com.yuanchuang.yohey;

import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.Share;
import com.yuanchuang.yohey.bmob.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

public class ForwardingActivity extends Activity {
	TextView toReturn;// 返回
	RelativeLayout send;// 发送
	EditText content;// 输入内容
	CheckBox comment;// 是否评论
	View ait;// 艾特
	View expression;// 表情
	View defoult;// 图片
	Share mShare;
	YoheyApplication app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_forwarding);

		app = (YoheyApplication) getApplication();
		mShare = (Share) app.data;
		app.data = null;
		findView();

	}

	private void sendShare() {
		User user = BmobUser.getCurrentUser(this, User.class);
		Share p = new Share();
		p.setUser(user);

		p.setContent(content.getText().toString());
		if (mShare.getForwarding() != null) {
			p.setForwarding(mShare.getForwarding());
		} else {
			p.setForwarding(mShare);
		}
		p.save(getApplicationContext(), new SaveListener() {

			@Override
			public void onSuccess() {
				Toast.makeText(getApplicationContext(), "帖子发表成功", Toast.LENGTH_SHORT).show();
				setResult(1);
				finish();
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(getApplicationContext(), "帖子发表失败:" + arg1, Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void findView() {
		toReturn = (TextView) findViewById(R.id.forwarding_text_toReturn);
		send = (RelativeLayout) findViewById(R.id.forwarding_text_send);
		content = (EditText) findViewById(R.id.forwarding_edit_reason);
		comment = (CheckBox) findViewById(R.id.forwarding_check_box_comment);
		ait = findViewById(R.id.forwarding_view_ait);
		expression = findViewById(R.id.forwarding_view_expression);
		defoult = findViewById(R.id.forwarding_view_defoult);

		toReturn.setOnClickListener(onClickListener);
		send.setOnClickListener(onClickListener);

	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.forwarding_text_toReturn:
				finish();
				break;
			case R.id.forwarding_text_send:
				sendShare();
				break;
			case R.id.forwarding_view_ait:

				break;
			case R.id.forwarding_view_expression:

				break;
			case R.id.forwarding_view_defoult:

				break;
			default:
				break;
			}

		}
	};
}
