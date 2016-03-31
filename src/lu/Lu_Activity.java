package lu;

import com.yuanchuang.yohey.AddFriendsActivity;
import com.yuanchuang.yohey.FriendMaterialActivity;
import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.adapter.FriendsBaseAdapter;
import com.yuanchuang.yohey.adapter.MessageBaseAdapter;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.app.YoheyApplication.OnGroupLoadingEndLitener;
import com.yuanchuang.yohey.app.YoheyNotificationManager;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.cache.YoheyCache;
import com.yuanchuang.yohey.view.MyImageView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import cn.bmob.v3.BmobUser;

/**
 * 好友消息界面
 * 
 * @author Administrator
 *
 */
public class Lu_Activity extends Fragment {
	RadioGroup radiogroup;
	RadioButton msg;// 消息
	RadioButton friends;// 朋友
	ListView msgList;
	ExpandableListView friendList;
	MessageBaseAdapter msgadapter;
	FriendsBaseAdapter friendsadapter;
	TextView add;
	MyImageView headImage;// 头像
	User user;
	YoheyApplication app;

	@Override
	public android.view.View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout lay = new LinearLayout(getActivity());
		app = (YoheyApplication) getActivity().getApplication();
		user = BmobUser.getCurrentUser(getActivity(), User.class);
		lay.setLayoutParams(new LayoutParams(-1, -1));
		View view = inflater.inflate(R.layout.lu, lay);
		radiogroup = (RadioGroup) view.findViewById(R.id.radiogroup);
		msg = (RadioButton) view.findViewById(R.id.rb_msg);
		friends = (RadioButton) view.findViewById(R.id.rb_friends);
		msgList = (ListView) view.findViewById(R.id.listview);
		friendList = (ExpandableListView) view.findViewById(R.id.expand);
		add = (TextView) view.findViewById(R.id.rb_add);
		headImage = (MyImageView) view.findViewById(R.id.lu_head_image);
		this.radiogroup.setOnCheckedChangeListener(changelistener);

		user.binderImageView(headImage);

		msgadapter = new MessageBaseAdapter(app.msgList, app);
		msgList.setAdapter(msgadapter);
		msgList.setOnItemClickListener(clickListener);
		add.setOnClickListener(click);
		YoheyCache.getMssageList(app);
		getMsgListData();
		YoheyNotificationManager.getInstance(getActivity()).addMssageObserb(msgadapter);
		return view;
	};

	@SuppressLint("HandlerLeak")
	private void getMsgListData() {
		app.addGroupLoadingEnd(new OnGroupLoadingEndLitener() {

			public void onLoadingEnd(YoheyApplication app) {
				msgadapter.setAppForShowData(app);
			}
		});
	}

	OnClickListener click = new OnClickListener() {
		Intent intent;

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.rb_add:
				intent = getActivity().getIntent();
				intent.setClass(getActivity(), AddFriendsActivity.class);
				getActivity().startActivity(intent);
				break;

			default:
				break;
			}

		}
	};
	OnCheckedChangeListener changelistener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup arg0, int id) {

			switch (id) {
			case R.id.rb_msg:
				msgList.setVisibility(View.VISIBLE);
				friendList.setVisibility(View.GONE);
				msgadapter = new MessageBaseAdapter(app.msgList, app);
				msgList.setAdapter(msgadapter);
				msgList.setOnItemClickListener(clickListener);
				break;

			case R.id.rb_friends:
				msgList.setVisibility(View.GONE);
				friendList.setVisibility(View.VISIBLE);
				friendsadapter = new FriendsBaseAdapter(app.friendGroup, getActivity());
				friendList.setAdapter(friendsadapter);
				friendList.setOnChildClickListener(childClickListener);
				break;
			}
		}
	};

	/**
	 * listview的点击事件
	 */
	OnItemClickListener clickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// Intent intent = new Intent(getActivity(),
			// FriendMessageActivity.class);
			// startActivity(intent);
		}
	};
	/**
	 * ExpandableListView的点击事件
	 */
	OnChildClickListener childClickListener = new OnChildClickListener() {

		@Override
		public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(getActivity(), FriendMaterialActivity.class);
			app.data = parent.getExpandableListAdapter().getChild(groupPosition, childPosition);
			startActivity(intent);
			return false;
		}
	};
}
