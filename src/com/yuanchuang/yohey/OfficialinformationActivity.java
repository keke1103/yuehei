package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.adapter.OfficialAdapter;
import com.yuanchuang.yohey.myData.AdapterData;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 官方资讯
 * 
 * @author Administrator
 *
 */
public class OfficialinformationActivity extends Activity {
	ListView listView;
	List<AdapterData> list;
	AdapterData data;// 数据存储类
	OfficialAdapter adapter;//
	TextView title;// 标题
	View toReturn;// 返回按钮
	RelativeLayout include;// 导入文件

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_recommend);
		list = new ArrayList<AdapterData>();
		findView();
		getData();
		adapter = new OfficialAdapter(list, getApplication());
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(itemClickListener);
	}

	private void getData() {
		for (int i = 0; i < 5; i++) {
			data = new AdapterData();
			data.setOfficial_image_brief(R.drawable.guan_fang_zi_xun_1);
			data.setOfficial_announcement("3.11免费英雄更换公告");
			data.setOfficial_time("一天前");
			data.setOfficial_context("");
			list.add(data);
			Log.i("getData", list.size() + "");
		}

	}

	private void findView() {
		listView = (ListView) findViewById(R.id.recommened_list);
		include = (RelativeLayout) findViewById(R.id.recommend_include_title);
		title = (TextView) include.findViewById(R.id.title_navigation_text_title);
		toReturn = include.findViewById(R.id.title_navigation_back_icon);
		title.setText("官方资讯");
		toReturn.setVisibility(View.VISIBLE);
		toReturn.setOnClickListener(clickListener);
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
	OnItemClickListener itemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub

		}
	};
}
