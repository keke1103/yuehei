package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.List;

import com.yuanchuang.yohey.adapter.RecommendAdapter;
import com.yuanchuang.yohey.myData.AdapterData;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 推荐开黑的界面
 * 
 * @author Administrator
 *
 */
public class RecommendActivity extends Activity {
	List<AdapterData> list;// listView的填充
	ListView listView;
	RelativeLayout layout;
	TextView title;
	View toReturn;
	RecommendAdapter recommendAdapter;
	AdapterData data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_recommend);
		list = new ArrayList<AdapterData>();
		findView();
		getData();
		recommendAdapter = new RecommendAdapter(list, this);
		listView.setAdapter(recommendAdapter);
	}

	private void getData() {
		for (int i = 0; i < 5; i++) {
			data = new AdapterData();
			data.setReco_area(R.string.ao_virginia + "");
			data.setReco_dan(R.string.bojin + "");
			data.setReco_head(R.drawable.ic_launcher + "");
			data.setReco_message(R.string.aiouniya + "");
			data.setReco_name(R.string.zhao + "");
			list.add(data);
		}

	}

	OnClickListener clickListener = new OnClickListener() {

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

	private void findView() {
		listView = (ListView) findViewById(R.id.recommened_list);
		layout = (RelativeLayout) findViewById(R.id.recommend_include_title);
		title = (TextView) layout.findViewById(R.id.title_navigation_text_title);
		toReturn = layout.findViewById(R.id.title_navigation_back_icon);
		toReturn.setVisibility(View.VISIBLE);
		toReturn.setOnClickListener(clickListener);
		title.setText(R.string.tuijiankaihei);
	}

}
