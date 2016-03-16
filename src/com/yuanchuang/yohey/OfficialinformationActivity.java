package com.yuanchuang.yohey;

import java.util.List;

import com.yuanchuang.yohey.myData.AdapterData;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class OfficialinformationActivity extends Activity {
ListView listView;
List<AdapterData> list;
AdapterData data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_recommend);
		
	}

}
