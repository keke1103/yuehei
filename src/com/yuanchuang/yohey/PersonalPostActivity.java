package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.adapter.PersonalPostAdapter;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.Post;
import com.yuanchuang.yohey.myData.PostComment;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;
import com.yuanchuang.yohey.tools.TimeUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 发帖详情
 * 
 * @author Administrator
 *
 */
public class PersonalPostActivity extends Activity {
	ImageView head;// 头像
	TextView nickName;// 昵称
	TextView time;// 时间
	TextView imageYan;// 浏览图标
	TextView rowse;// 浏览量
	LinearLayout context;// 发表内容
	EditText say;// 说一句
	View send;// 发送
	TextView title;// 标题
	View toReturn;// 返回
	TextView joinCount;
	TextView comCount;
	TextView likeCount;
	View ait;// 艾特符号
	View smile;// 表情
	View photos;// 图片

	List<PostComment> list;
	PersonalPostAdapter myAdapter;
	ListView listView;
	LayoutInflater inflater;
	View headView;
	Intent mIntent;
	Post post;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_yue_lu_personal_post);
		mIntent = getIntent();

		YoheyApplication app = (YoheyApplication) getApplication();
		post = (Post) app.data;
		app.data = null;
		findView();

		getData();
		list = new ArrayList<PostComment>();
		myAdapter = new PersonalPostAdapter(list, getApplication());

		listView.addHeaderView(headView);
		listView.setAdapter(myAdapter);
		setData();
	}

	private void getData() {
		HttpGet get = new HttpGet(YoheyApplication.ServiceIp + "/index.php/home/api/comment");
		// get.putString("pid", "" + post.getId());
		get.setOnSendListener(mListener);
		get.send();
	}

	private OnSendListener mListener = new OnSendListener() {

		@Override
		public void start() {
		}

		@Override
		public void end(String result) {
			try {
				JSONObject mjo = new JSONObject(result);
				if (mjo.getInt("stauts") == 1) {
					JSONArray ja = mjo.getJSONArray("result");
					for (int i = 0; i < ja.length(); i++) {
						PostComment pc = PostComment.paresJSONObject(ja.getJSONObject(i));
						list.add(pc);
					}
					myAdapter.setData(list);
				}
			} catch (JSONException e) {

				e.printStackTrace();
			}
		}
	};

	private void getContext(String content) {
		TextView text = new TextView(this);
		LayoutParams params = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		text.setLayoutParams(params);
		text.setText(content);
		context.addView(text);

	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.personal_post_image_head:

				break;
			case R.id.title_navigation_back_icon:
				finish();
				break;
			case R.id.personal_post_image_send:
				Toast.makeText(getApplication(), "发送不出去", Toast.LENGTH_SHORT).show();
				break;
			case R.id.personal_post_image_ait:
				Toast.makeText(getApplication(), "你想@谁", Toast.LENGTH_SHORT).show();
				break;
			case R.id.personal_post_image_smile:
				Toast.makeText(getApplication(), "不能发表情", Toast.LENGTH_SHORT).show();
				break;
			case R.id.personal_post_image_photos:
				Toast.makeText(getApplication(), "你没有图片", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}

		}
	};

	@SuppressLint("InflateParams")
	private void findView() {

		inflater = getLayoutInflater();
		headView = inflater.inflate(R.layout.list_head_personal_post, null);
		head = (ImageView) headView.findViewById(R.id.personal_post_image_head);
		nickName = (TextView) headView.findViewById(R.id.personal_post_nick_text_name);
		time = (TextView) headView.findViewById(R.id.personal_post_text_time);
		context = (LinearLayout) headView.findViewById(R.id.personal_post_cotext);
		joinCount = (TextView) headView.findViewById(R.id.personal_post_text_addd);
		comCount = (TextView) headView.findViewById(R.id.personal_post_text_message);
		likeCount = (TextView) headView.findViewById(R.id.personal_post_text_zhan);

		title = (TextView) findViewById(R.id.title_navigation_text_title);
		toReturn = findViewById(R.id.title_navigation_back_icon);
		send = findViewById(R.id.personal_post_image_send);
		ait = findViewById(R.id.personal_post_image_ait);
		smile = findViewById(R.id.personal_post_image_smile);
		photos = findViewById(R.id.personal_post_image_photos);

		ait.setOnClickListener(onClickListener);
		smile.setOnClickListener(onClickListener);
		photos.setOnClickListener(onClickListener);
		send.setOnClickListener(onClickListener);
		toReturn.setOnClickListener(onClickListener);
		title.setText("详情");
		toReturn.setVisibility(View.VISIBLE);
		listView = (ListView) findViewById(R.id.personal_post_list_message);
	}

	/**
	 * 设置帖子内容的显示
	 */
	private void setData() {
		nickName.setText(post.getUser().getNickName());
		post.getUser().binderImageView(head);
		time.setText(TimeUtil.formateTimeToNow(post.getCreatedAt()));
		joinCount.setText("" + post.getJoincount());
		comCount.setText("" + post.getComcount());
		likeCount.setText("" + post.getLikenumber());
		if (!TextUtils.isEmpty(post.getTitle())) {
			getContext(post.getTitle());
		} else {

		}
	}
}
