package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.List;

import com.yuanchuang.yohey.adapter.PostSAdater;
import com.yuanchuang.yohey.myData.AdapterData;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 我参加的帖子
 * 
 * @author Administrator
 *
 */
public class ParticipationPostsActivity extends Activity {
	List<AdapterData> list;
	RelativeLayout includeTitle;
	TextView title;
	ImageView toReturn;
	ListView listView;
	PostSAdater postAdapter;
	AdapterData data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_post_details);
		list = new ArrayList<AdapterData>();

		findView();
		getData();
		postAdapter = new PostSAdater(list, this);

		Log.i("DynamicFragment", "DynamicFragment");
		LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		TextView text = new TextView(getApplication());
		text.setLayoutParams(params);
		text.setPadding(10, 10, 10, 10);

		text.setGravity(CONTEXT_INCLUDE_CODE);
		text.setText(R.string.more_more);
		text.setBackgroundResource(R.drawable.text_stroke);

		listView.addFooterView(text);
		listView.setAdapter(postAdapter);
		toReturn.setOnClickListener(onClickListener);
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.title_navigation_back_icon:
				finish();
				break;

			default:
				break;
			}

		}
	};

	private void getData() {

		for (int i = 0; i < 5; i++) {
			data = new AdapterData();
			data.setPost_name("多啦不爱梦");
			data.setPost_head("");
			data.setPost_browse(((int) (Math.random() * 10)));
			data.setPost_message(((int) (Math.random() * 10)));
			;
			data.setPost_time("2015.8.12");
			data.setPost_con("求大神带我飞");
			list.add(data);
			Log.i("getData", list.size() + "");
		}
	}

	private void findView() {
		includeTitle = (RelativeLayout) findViewById(R.id.post_details_include_title);
		listView = (ListView) findViewById(R.id.post_details_list_view);
		toReturn = (ImageView) includeTitle.findViewById(R.id.title_navigation_back_icon);
		title = (TextView) includeTitle.findViewById(R.id.title_navigation_text_title);
		toReturn.setVisibility(View.VISIBLE);
		title.setText(R.string.participation_posts);
	}
}
