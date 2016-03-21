package com.yuanchuang.yohey;

import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.Game;
import com.yuanchuang.yohey.bmob.Post;
import com.yuanchuang.yohey.bmob.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * 发表帖子页面
 * 
 * @author Administrator
 *
 */
public class PostingInterfaceActivity extends Activity {
	RelativeLayout include;// 导入头文件
	TextView title;// 标题
	LinearLayout toReturn;// 取消
	TextView right;// 发表
	EditText context;// 帖子内容
	CheckBox areaSet;// 权限设置
	YoheyApplication application;
	Game game;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_posting_interface);
		application = (YoheyApplication) getApplication();
		findView();
		LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		TextView text = new TextView(this);
		text.setLayoutParams(params);
		text.setTextSize(13);
		text.setText(R.string.cancel);
		game = BmobUser.getCurrentUser(getApplicationContext(), User.class).getDefGame();
		toReturn.addView(text);
	}

	@SuppressWarnings("deprecation")
	private void findView() {
		include = (RelativeLayout) findViewById(R.id.posting_include_itle);
		areaSet = (CheckBox) findViewById(R.id.area_check);
		context = (EditText) findViewById(R.id.posting_include_edit_context);
		title = (TextView) include.findViewById(R.id.title_navigation_text_title);
		toReturn = (LinearLayout) include.findViewById(R.id.title_navigation_view);
		right = (TextView) include.findViewById(R.id.title_navigation_text_right_title);
		title.setText(R.string.osts);
		toReturn.setVisibility(View.VISIBLE);
		right.setVisibility(View.VISIBLE);
		areaSet.setOnClickListener(l);
		right.setText(R.string.publication);
		right.setTextColor(getResources().getColor(R.color.light_gray));
		toReturn.setOnClickListener(l);
		right.setOnClickListener(l);
	}

	private OnClickListener l = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.area_check:
				showArea();
				break;
			case R.id.title_navigation_text_right_title:
				submit();
				break;
			case R.id.title_navigation_view:
				finish();
				break;
			default:
				break;
			}
		}
	};

	private void showArea() {
		/*
		 * PopupWindow p = new PopupWindow(300, 300); LinearLayout layout = new
		 * LinearLayout(this); layout.setOrientation(LinearLayout.VERTICAL);
		 * TextView text;
		 * 
		 * for (Game g : application.mUser.getGame()) { text = new
		 * TextView(this); text.setId(g.getId()); text.setText(g.getGameregion()
		 * + "-" + g.getGamename()); text.setOnClickListener(new
		 * OnClickListener() { public void onClick(View v) {
		 * areaSet.setText(((TextView) v).getText()); gid = v.getId(); } });
		 * layout.addView(text); }
		 * 
		 * p.setContentView(layout); p.setBackgroundDrawable(new
		 * BitmapDrawable()); p.showAsDropDown(areaSet);
		 */
	}

	private void submit() {
		User user = BmobUser.getCurrentUser(this, User.class);
		Post p = new Post();
		p.setUser(user);
		p.setGame(user.getDefGame());
		p.setTitle(context.getText().toString());
		p.save(getApplicationContext(), new SaveListener() {

			public void onSuccess() {
				Toast.makeText(getApplicationContext(), "帖子发表成功", Toast.LENGTH_SHORT).show();
				setResult(1);
				finish();
			}

			public void onFailure(int arg0, String arg1) {
				Toast.makeText(getApplicationContext(), "帖子发表失败:" + arg1, Toast.LENGTH_SHORT).show();
			}
		});

	}

}
