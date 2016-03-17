package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.List;

import com.yuanchuang.yohey.adapter.PersonalPostAdapter;
import com.yuanchuang.yohey.myData.AdapterData;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 发帖详情
 * 
 * @author Administrator
 *
 */
public class PersonalPostActivity extends Activity {
	View head;// 头像
	TextView nickName;// 昵称
	TextView time;// 时间
	TextView imageYan;// 浏览图标
	TextView rowse;// 浏览量
	LinearLayout context;// 发表内容
	EditText say;// 说一句
	RelativeLayout include;// 导入头文件
	TextView title;// 标题
	View toReturn;// 返回
	List<AdapterData> list;
	PersonalPostAdapter myAdapter;
	ListView listView;
	LayoutInflater inflater;
	View headView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_yue_lu_personal_post);
		list = new ArrayList<AdapterData>();
		findView();

		getData();

		myAdapter = new PersonalPostAdapter(list, getApplication());

		getContext();
		listView.addHeaderView(headView);
		listView.setAdapter(myAdapter);
	}

	private void getData() {
		AdapterData data = new AdapterData();
		for (int i = 0; i < 3; i++) {
			data = new AdapterData();
			data.setPost_details_head(R.drawable.zhu_ge_wo_ai_ni_tu);
			data.setPost_details_name("猪哥我爱你");
			data.setPost_details_time("2012-2-15");
			data.setPost_details_context("R.string.carry_me_fly");
			list.add(data);
			Log.i("getData", list.size() + "");
		}
	}

	private void getContext() {
		TextView text = new TextView(this);
		LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		text.setLayoutParams(params);
		text.setText(R.string.aiouniya);
		context.addView(text);

	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.personal_post_image_head:

				break;

			default:
				break;
			}

		}
	};

	@SuppressLint("InflateParams")
	private void findView() {

		include = (RelativeLayout) findViewById(R.id.personal_post_include_title);
		head = findViewById(R.id.personal_post_image_head);
		nickName = (TextView) findViewById(R.id.personal_post_nick_text_name);
		time = (TextView) findViewById(R.id.personal_post_text_time);

		title = (TextView) include.findViewById(R.id.title_navigation_text_title);
		toReturn = include.findViewById(R.id.title_navigation_back_icon);

		inflater = LayoutInflater.from(getApplication());
		headView = inflater.inflate(R.layout.list_head_personal_post, null);
		context = (LinearLayout) headView.findViewById(R.id.personal_post_cotext);
		title.setText("帖子详情");
		toReturn.setVisibility(View.VISIBLE);
		listView = (ListView) findViewById(R.id.personal_post_list_message);
	}
}
