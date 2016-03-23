package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.adapter.PersonalPostAdapter;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.Comment;
import com.yuanchuang.yohey.bmob.Friends;
import com.yuanchuang.yohey.bmob.Post;
import com.yuanchuang.yohey.bmob.User;
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
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

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
	View addFriend;
	int resultCode = 0;

	List<Comment> list;
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
		list = new ArrayList<Comment>();
		myAdapter = new PersonalPostAdapter(list, getApplication());

		listView.addHeaderView(headView);
		listView.setAdapter(myAdapter);
		setData();
	}

	private void getData() {
		HttpGet get = new HttpGet("http://cloud.bmob.cn/a52fec72f31cc7c8/getpostcom");
		get.putString("pid", post.getObjectId());
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
				JSONArray ja = mjo.getJSONArray("results");
				list.clear();
				Comment comment;
				for (int i = 0; i < ja.length(); i++) {
					comment = Comment.comJsonObject(ja.getJSONObject(i));
					list.add(comment);
				}
				myAdapter.setData(list);
			} catch (JSONException e) {
				e.printStackTrace();
				Toast.makeText(getApplication(), "" + result, Toast.LENGTH_SHORT).show();
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
				setResult(resultCode);
				finish();
				break;
			case R.id.personal_post_image_send:
				sendCom();
				break;
			case R.id.add_friend:
				addFriend();
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
		addFriend = headView.findViewById(R.id.add_friend);
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
		say = (EditText) findViewById(R.id.personal_post_edit_say);

		send.setOnClickListener(onClickListener);
		toReturn.setOnClickListener(onClickListener);
		addFriend.setOnClickListener(onClickListener);
		title.setText("帖子详情");
		toReturn.setVisibility(View.VISIBLE);
		listView = (ListView) findViewById(R.id.personal_post_list_message);
	}

	/**
	 * 好友添加
	 */
	private void addFriend() {
		Friends f = new Friends();
		f.addFriend(PersonalPostActivity.this, post.getUser(), new SaveListener() {
			public void onSuccess() {
				Toast.makeText(getApplicationContext(), "好友添加成功", Toast.LENGTH_SHORT).show();
			}

			public void onFailure(int arg0, String arg1) {
				Toast.makeText(getApplicationContext(), arg1, Toast.LENGTH_SHORT).show();
			}
		});
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

	/**
	 * 向服务器发送评论数据
	 */
	private void sendCom() {
		String content = say.getText().toString();
		if (!TextUtils.isEmpty(content)) {
			Comment comment = new Comment();
			comment.setPost(post);
			comment.setUser(BmobUser.getCurrentUser(this, User.class));
			comment.setContent(content);
			comment.save(this, new SaveListener() {
				public void onSuccess() {
					say.setText("");
					resultCode = 1;
					comCount.setText("" + (post.getComcount() + 1));
					Post p = new Post();
					p.setComcount(post.getComcount() + 1);
					p.update(getApplicationContext(), post.getObjectId(), null);

					getData();
				}

				public void onFailure(int arg0, String arg1) {

				}
			});
		}
	}
}
