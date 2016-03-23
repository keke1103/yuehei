package com.yuanchuang.yohey;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yuanchuang.yohey.adapter.PersonalPostAdapter;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.Comment;
import com.yuanchuang.yohey.bmob.Post;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;
import com.yuanchuang.yohey.tools.TimeUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
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
	TextView joinCount;//想加入的人数
	TextView comCount;//评论数
	TextView likeCount;//点赞数
	ImageView likeCountImage;//点赞的图标
	int resultCode=0;
	

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
    /**
     * 获取评论的数据
     */
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
				JSONArray ja=mjo.getJSONArray("results");
				list.clear();
				Comment comment;
				for(int i=0;i<ja.length();i++){
					 comment=Comment.comJsonObject(ja.getJSONObject(i));
					 list.add(comment);	
				}	
				myAdapter.setData(list);
			} catch (JSONException e) {
				e.printStackTrace();
				Toast.makeText(getApplication(), ""+result,Toast.LENGTH_SHORT).show();
			}
		}
	};
    /**
     * 显示帖子的内容
     * @param content 帖子的内容
     */
	private void getContext(String content) {
		TextView text = new TextView(this);
		LayoutParams params = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		text.setLayoutParams(params);
		text.setText(content);
		context.addView(text);

	}
    /**
     * 点击事件
     */
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
			case R.id.personal_post_image_zhan:
				likeCount.setText(""+1);
				break;
			default:
				break;
			}

		}
	};
    /**
     * 控件ID
     */
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
		likeCountImage=(ImageView)headView.findViewById(R.id.personal_post_image_zhan);
        
		title = (TextView) findViewById(R.id.title_navigation_text_title);
		toReturn = findViewById(R.id.title_navigation_back_icon);
		send = findViewById(R.id.personal_post_image_send);
        say=(EditText)findViewById(R.id.personal_post_edit_say);
        
		send.setOnClickListener(onClickListener);
		toReturn.setOnClickListener(onClickListener);
		likeCountImage.setOnClickListener(onClickListener);
		title.setText("帖子详情");
		toReturn.setVisibility(View.VISIBLE);
		listView = (ListView) findViewById(R.id.personal_post_list_message);
	}

	/**
	 * 获取帖子内容的
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
	private void sendCom(){
		String content=say.getText().toString();
		if(!TextUtils.isEmpty(content)){
			Comment comment=new Comment();
			comment.setPost(post);
			comment.setUser(BmobUser.getCurrentUser(this, User.class));
			comment.setContent(content);
			comment.save(this, new SaveListener() {
				public void onSuccess() {
					say.setText("");
					resultCode=1;
					comCount.setText("" + (post.getComcount()+1));
					Post p=new Post();
					p.setComcount(post.getComcount()+1);
					p.update(getApplicationContext(),post.getObjectId(),null);
                    
					getData();
				}
			 
				public void onFailure(int arg0, String arg1) {
					
				}
			});
		}		
	}
    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     * @param v
     * @param event
     * @return
     */
	private boolean isShouldHideKeyboard(View v,MotionEvent event){
		if(v!=null&&(v instanceof EditText)){
			int[] l={0,0};
			v.getLocationInWindow(l);
			int left=l[0];
			int top=l[1];
			int bottom=top+v.getHeight();
			int right=left+v.getWidth();
			if(event.getX()>left&&event.getX()<right&&event.getY()>top&&event.getY()<bottom){
				//点击EditText的事件，忽略它
				return false;
			}else{
				return true;
			}
		}
		// 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
		return false;
	}
	/**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
