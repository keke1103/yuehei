package com.yuanchuang.yohey.share;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.ShareItActivity;
import com.yuanchuang.yohey.share.ImageBucket.ImageItem;
import com.yuanchuang.yohey.share.ImageGridAdapter.TextCallback;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class ImageGridActivity extends Activity {

	List<ImageItem> dataList;
	GridView gridView;
	ImageGridAdapter adapter;
	AlbumHelper helper;
	View back;
	TextView title;
	Button bt;

	@SuppressLint("HandlerLeak")
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(ImageGridActivity.this, "最多选择9张图片", Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		}
	};
	private OnClickListener click = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.title_navigation_back_text:
				finish();
				break;

			default:
				break;
			}
		}
	};

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_image_grid);

		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());

		dataList = (List<ImageItem>) getIntent().getSerializableExtra(TestPicActivity.EXTRA_IMAGE_LIST);

		initView();
		bt = (Button) findViewById(R.id.bt);
		bt.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ArrayList<String> list = new ArrayList<String>();
				Collection<String> c = adapter.map.values();
				Iterator<String> it = c.iterator();

				while (it.hasNext()) {
					list.add(it.next());
				}
				if (Bimp.act_bool) {
					Intent intent = new Intent(ImageGridActivity.this, ShareItActivity.class);
					startActivity(intent);
					Bimp.act_bool = false;
				}
				for (int i = 0; i < list.size(); i++) {
					if (Bimp.drr.size() < 9) {
						Bimp.drr.add(list.get(i));
					}
				}
				finish();
			}

		});
	}

	/**
	 *  
	 */
	private void initView() {
		back = findViewById(R.id.title_navigation_back_text);
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(click);
		title = (TextView) findViewById(R.id.title_navigation_text_title);
		title.setText("相册");
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new ImageGridAdapter(ImageGridActivity.this, dataList, mHandler);
		gridView.setAdapter(adapter);
		adapter.setTextCallback(new TextCallback() {
			public void onListen(int count) {
				bt.setText("完成" + "(" + count + ")");
			}
		});

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				adapter.notifyDataSetChanged();
			}

		});

	}
}
