package com.yuanchuang.yohey;

import java.io.File;
import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.bmob.Share;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.share.Bimp;
import com.yuanchuang.yohey.share.GridAdapter;
import com.yuanchuang.yohey.share.PhotoActivity;
import com.yuanchuang.yohey.share.TestPicActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;

/**
 * 分享页面
 * 
 * @author Administrator
 *
 */
public class ShareItActivity extends Activity {
	View parant;

	RelativeLayout includeTitle;// 标题栏
	EditText editider;// 分享内容
	TextView position;// 所在位置
	TextView who;// 谁可以看
	TextView remind;// 提醒谁看
	TextView title;
	TextView toReturn;
	TextView send;// 发送消息

	RelativeLayout addPhotos;// 添加图片
	LinearLayout linearPosition;// 所在位置的线性
	LinearLayout linearWho;// 谁可以看
	LinearLayout linearRemind;// 提醒谁看

	GridView noScrollgridview;//
	Intent intent;
	Share share;

	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		parant = getLayoutInflater().inflate(R.layout.activity_yue_lu_share_it, null);
		setContentView(parant);
		findView();
		initView();
		toReturn.setOnClickListener(onClickListener);
		share = new Share();
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.title_navigation_back_text:// 返回
				finish();
				break;

			case R.id.title_navigation_text_right_title:// 发送
				send();
				break;
			case R.id.share_it_relation_add:// 添加图片

				break;
			case R.id.share_it_linear_position:// 所在位置
				intent = getIntent();
				intent.setClass(ShareItActivity.this, LocateActivity.class);
				startActivity(intent);
				break;
			case R.id.share_it_linear_who:// 谁可以看
				intent = getIntent();
				intent.setClass(ShareItActivity.this, WhoCanSeeActivity.class);
				startActivity(intent);
				break;
			case R.id.share_it_linear_remind:// 提醒谁看
				intent = getIntent();
				intent.setClass(ShareItActivity.this, WhoReminderActivity.class);
				startActivity(getIntent());
				break;
			default:
				break;
			}

		}
	};

	private GridAdapter adapter;

	private void addImageView() {
		new PopupWindows(this, parant);
	}

	private void send() {
		String content = editider.getText().toString();
		if (Bimp.drr.isEmpty() && TextUtils.isEmpty(content)) {
			Toast.makeText(getApplicationContext(), "你想表达什么？", Toast.LENGTH_SHORT).show();
			return;
		}
		share.setContent(content);
		share.setUser(BmobUser.getCurrentUser(getApplicationContext(), User.class));
		if (Bimp.drr.isEmpty()) {
			share.save(getApplicationContext(), new SaveListener() {

				@Override
				public void onSuccess() {
					finish();
				}

				@Override
				public void onFailure(int arg0, String arg1) {
					Toast.makeText(getApplicationContext(), arg1, Toast.LENGTH_SHORT).show();
				}
			});
			return;
		}

		Bmob.uploadBatch(this, Bimp.drr.toArray(new String[Bimp.drr.size()]), new UploadBatchListener() {

			@Override
			public void onSuccess(List<BmobFile> arg0, List<String> arg1) {
				if (Bimp.drr.size() != arg0.size()) {
					// 有且只有在最后一张图上传成功过后发起说说
					return;
				}
				share.setImages(arg0);
				share.save(getApplicationContext(), new SaveListener() {
					@Override
					public void onSuccess() {
						finish();
					}

					@Override
					public void onFailure(int arg0, String arg1) {
						Toast.makeText(getApplicationContext(), arg1, Toast.LENGTH_SHORT).show();
					}
				});
			}

			@Override
			public void onProgress(int arg0, int arg1, int arg2, int arg3) {
				Log.i("ShareItActivity", "上传文件:" + arg0 + " 当前进度:" + arg1 + " 已上传:" + arg2 + " 总进度:" + arg3);
			}

			@Override
			public void onError(int arg0, String arg1) {
				Log.w("ShareItActivity", arg0 + " >" + arg1);
				Toast.makeText(getApplicationContext(), arg1, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@SuppressWarnings("deprecation")
	private void findView() {
		includeTitle = (RelativeLayout) findViewById(R.id.share_it_title_bar);
		editider = (EditText) findViewById(R.id.share_it_edit_ider);
		position = (TextView) findViewById(R.id.share_it_text_position);
		who = (TextView) findViewById(R.id.share_it_text_who);
		remind = (TextView) findViewById(R.id.share_it_text_remind);
		send = (TextView) includeTitle.findViewById(R.id.title_navigation_text_right_title);
		title = (TextView) includeTitle.findViewById(R.id.title_navigation_text_title);
		toReturn = (TextView) includeTitle.findViewById(R.id.title_navigation_back_text);
		addPhotos = (RelativeLayout) findViewById(R.id.share_it_relation_add);
		linearPosition = (LinearLayout) findViewById(R.id.share_it_linear_position);
		linearRemind = (LinearLayout) findViewById(R.id.share_it_linear_remind);
		linearWho = (LinearLayout) findViewById(R.id.share_it_linear_who);
		noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
		noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));// 设置item点击事件无背景效果
		toReturn.setVisibility(View.VISIBLE);
		toReturn.setText(R.string.cancel);
		title.setText(R.string.share);
		send.setText(R.string.send);

		Resources resources = getResources();

		Drawable drawable = resources.getDrawable(R.drawable.shape_rounded_yellow);
		send.setVisibility(View.VISIBLE);
		send.setTextColor(getResources().getColor(R.color.black));
		send.setBackgroundDrawable(drawable);
		send.setOnClickListener(onClickListener);

		addPhotos.setOnClickListener(onClickListener);
		linearPosition.setOnClickListener(onClickListener);
		linearRemind.setOnClickListener(onClickListener);
		linearWho.setOnClickListener(onClickListener);
	}

	private void initView() {
		adapter = new GridAdapter(this);
		adapter.update();
		noScrollgridview.setAdapter(adapter);
		OnItemClickListener listener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (position == Bimp.bmp.size()) {
					addImageView();
				} else {
					Intent intent = new Intent(ShareItActivity.this, PhotoActivity.class);
					intent.putExtra("ID", position);
					startActivity(intent);
				}
			}
		};
		noScrollgridview.setOnItemClickListener(listener);

	}

	/**
	 * 回到这个页面的时候刷新adapter数据
	 */
	@Override
	protected void onRestart() {
		adapter.update();

		super.onRestart();
	}

	/**
	 * 清空static的缓存数据
	 */
	@Override
	protected void onDestroy() {
		Bimp.max = 0;
		Bimp.act_bool = true;
		Bimp.bmp.clear();
		Bimp.drr.clear();
		super.onDestroy();
	}

	public class PopupWindows extends PopupWindow {

		@SuppressWarnings("deprecation")
		public PopupWindows(Context mContext, View parent) {

			View view = View.inflate(mContext, R.layout.item_popupwindows, null);
			view.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_ins));
			LinearLayout ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_bottom_in_2));

			setWidth(LayoutParams.MATCH_PARENT);
			setHeight(LayoutParams.MATCH_PARENT);
			setBackgroundDrawable(new BitmapDrawable());
			setFocusable(true);
			setOutsideTouchable(true);
			setContentView(view);
			showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			update();
			Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
			Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
			Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
			bt1.setOnClickListener(click);
			bt2.setOnClickListener(click);
			bt3.setOnClickListener(click);

		}

		OnClickListener click = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.item_popupwindows_camera:
					photo();
					dismiss();
					break;
				case R.id.item_popupwindows_Photo:
					Intent intent = new Intent(ShareItActivity.this, TestPicActivity.class);
					startActivity(intent);
					dismiss();
					break;
				case R.id.item_popupwindows_cancel:
					dismiss();
					break;

				}

			}
		};
	}

	String path;
	private final int TAKE_PICTURE = 0x000000;

	public void photo() {
		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File file = new File(Environment.getExternalStorageDirectory() + "/myimage/",
				String.valueOf(System.currentTimeMillis()) + ".jpg");
		path = file.getPath();
		Uri imageUri = Uri.fromFile(file);
		openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(openCameraIntent, TAKE_PICTURE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case TAKE_PICTURE:
			if (Bimp.drr.size() < 9 && resultCode == -1) {
				Bimp.drr.add(path);
			}
			break;
		}
	}

}
