package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.R.color;
import com.yuanchuang.yohey.edit.KanjiConversion;
import com.yuanchuang.yohey.edit.MyEditText;
import com.yuanchuang.yohey.edit.PinyinComparator;
import com.yuanchuang.yohey.edit.SortAdapter;
import com.yuanchuang.yohey.myData.AdapterData;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("DefaultLocale")
public class WhoReminderActivity extends Activity {
	private ListView sortListView;
	private SortAdapter adapter;
	private MyEditText myEditText;
	View toReturn;
	TextView title;
	TextView righeTitle;
	/**
	 * 汉字转换成拼音的类
	 */
	private KanjiConversion kanjiConversion;
	private List<AdapterData> SourceDateList;

	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_who_reminder);
		initViews();
	}

	@SuppressWarnings("deprecation")
	private void initViews() {
		// 实例化汉字转拼音类
		kanjiConversion = KanjiConversion.getInstance();

		pinyinComparator = new PinyinComparator();

		toReturn = findViewById(R.id.title_navigation_back_icon);
		title = (TextView) findViewById(R.id.title_navigation_text_title);
		righeTitle = (TextView) findViewById(R.id.title_navigation_text_right_title);

		toReturn.setVisibility(View.VISIBLE);
		toReturn.setOnClickListener(clickListener);
		title.setText("提醒谁看");
		righeTitle.setText("确定");
		Resources resources = getResources();
		Drawable drawable = resources.getDrawable(R.drawable.shape_rounded_yellow);
		righeTitle.setVisibility(View.VISIBLE);
		righeTitle.setBackgroundDrawable(drawable);
		righeTitle.setTextColor(color.black);
		sortListView = (ListView) findViewById(R.id.country_lvcountry);
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// 这里要利用adapter.getItem(position)来获取当前position所对应的对象
				Toast.makeText(getApplication(), ((AdapterData) adapter.getItem(position)).getEditName(),
						Toast.LENGTH_SHORT).show();
			}
		});

		SourceDateList = filledData(getResources().getStringArray(R.array.date));

		// 根据a-z进行排序源数据
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortAdapter(this, SourceDateList);
		sortListView.setAdapter(adapter);

		myEditText = (MyEditText) findViewById(R.id.filter_edit);

		// 根据输入框输入值的改变来过滤搜索
		myEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
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

	/**
	 * 为ListView填充数据
	 * 
	 * @param date
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	private List<AdapterData> filledData(String[] date) {
		List<AdapterData> myList = new ArrayList<AdapterData>();

		for (int i = 0; i < date.length; i++) {
			AdapterData adapter = new AdapterData();
			adapter.setEditName(date[i]);
			// 汉字转换成拼音
			String pinyin = kanjiConversion.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if (sortString.matches("[A-Z]")) {
				adapter.setEditSortLetters(sortString.toUpperCase());
			} else {
				adapter.setEditSortLetters("#");
			}

			myList.add(adapter);
		}
		return myList;

	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<AdapterData> filterDateList = new ArrayList<AdapterData>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = SourceDateList;
		} else {
			filterDateList.clear();
			for (AdapterData sortModel : SourceDateList) {
				String name = sortModel.getEditName();
				if (name.indexOf(filterStr.toString()) != -1
						|| kanjiConversion.getSelling(name).startsWith(filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}

}
