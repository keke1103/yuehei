package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.adapter.PostSAdater;
import com.yuanchuang.yohey.bmob.BmobFindObject;
import com.yuanchuang.yohey.bmob.Post;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.myData.AdapterData;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.LayoutParams;
import cn.bmob.v3.BmobUser;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 帖子消息界面
 * 
 * @author Administrator
 *
 */
public class PostedMessagesActivity extends Activity {
	List<Post> list;
	RelativeLayout includeTitle;// 导入头文件
	TextView title;// 标题
	ImageView toReturn;// 返回按钮
	ListView listView;
	PostSAdater postAdapter;// 自定义adapter
	AdapterData data;

	User user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_post_details);
		user=BmobUser.getCurrentUser(getApplicationContext(), User.class);
		
		list = new ArrayList<Post>();

		findView();
		getData();
		postAdapter = new PostSAdater(list, this);

		Log.i("DynamicFragment", "DynamicFragment");
		LayoutParams params = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
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
    /**
     * 获取数据
     */
	private void getData() {
		BmobFindObject object=new BmobFindObject(user.getObjectId(), "Post");
		object.findPostByUserId(mListner);
	}

	OnSendListener mListner=new OnSendListener() {
		
		@Override
		public void start() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void end(String result) {
			Log.i(">>>>>>>>>>>>>>>", result);
			try {
				JSONObject joo = new JSONObject(result);
				JSONArray ja = joo.getJSONArray("results");
				for (int i = 0; i < ja.length(); i++) {	
					Post p = Post.paresJSONObject(ja.getJSONObject(i));
					list.add(p);
				}
				postAdapter.setData(list);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	};
	
	
	private void findView() {
		includeTitle = (RelativeLayout) findViewById(R.id.post_details_include_title);
		listView = (ListView) findViewById(R.id.post_details_list_view);
		toReturn = (ImageView) includeTitle.findViewById(R.id.title_navigation_back_icon);
		title = (TextView) includeTitle.findViewById(R.id.title_navigation_text_title);
		toReturn.setVisibility(View.VISIBLE);
		title.setText(R.string.my_posted_messages);
	}
}
