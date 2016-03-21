package com.yuanchuang.yohey;

import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.myData.Game;
import com.yuanchuang.yohey.tools.HttpPost;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
	int gid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_posting_interface);
		application = (YoheyApplication) getApplication();
		findView();
		LayoutParams params = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		TextView text = new TextView(this);
		text.setLayoutParams(params);
		text.setTextSize(13);
		text.setText(R.string.cancel);
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
				surePost();
				break;
			case R.id.title_navigation_view:
				finish();
				break;
			default:
				break;
			}
		}
	};

	@SuppressWarnings("deprecation")
	private void showArea() {
		PopupWindow p = new PopupWindow(300, 300);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		TextView text;

		for (Game g : application.mUser.getGame()) {
			text = new TextView(this);
			text.setId(g.getId());
			text.setText(g.getGameregion() + "-" + g.getGamename());
			text.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					areaSet.setText(((TextView) v).getText());
					gid = v.getId();
				}
			});
			layout.addView(text);
		}

		p.setContentView(layout);
		p.setBackgroundDrawable(new BitmapDrawable());
		p.showAsDropDown(areaSet);
	}

	private void surePost() {
		String content = context.getText().toString();
		if (!TextUtils.isEmpty(content) && gid != 0) {
			try {
				HttpPost post = HttpPost.parseUrl(YoheyApplication.ServiceIp + "/index.php/home/api/postcreate");
				post.putString("token", application.token);
				post.putString("uid", application.mUser.getId() + "");
				post.putString("gid", gid + "");
				post.putString("title", content);
				post.setOnSendListener(new OnSendListener() {
					public void start() {
					}

					public void end(String result) {
						try {
							JSONObject jo = new JSONObject(result);
							if (jo.getInt("stauts") == 1) {
								finish();
							} else {
								Toast.makeText(getApplicationContext(), jo.getString("message"), Toast.LENGTH_SHORT)
										.show();
							}
						} catch (JSONException e) {

							e.printStackTrace();
						}

					}
				});
				post.send();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
}
