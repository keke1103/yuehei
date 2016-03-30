package lu;

import java.util.ArrayList;

import com.yuanchuang.yohey.AddFriendsActivity;
import com.yuanchuang.yohey.FriendMaterialActivity;
import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.adapter.FriendsBaseAdapter;
import com.yuanchuang.yohey.adapter.MessageBaseAdapter;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.User;

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
	ArrayList<User> msglist;
	TextView add;
	User user;
	YoheyApplication app;

	@Override
	public android.view.View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout lay = new LinearLayout(getActivity());
		app = (YoheyApplication) getActivity().getApplication();
		user = User.getCurrentUser(getActivity(), User.class);
		lay.setLayoutParams(new LayoutParams(-1, -1));
		View view = inflater.inflate(R.layout.lu, lay);
		msglist = new ArrayList<User>();
		radiogroup = (RadioGroup) view.findViewById(R.id.radiogroup);
		msg = (RadioButton) view.findViewById(R.id.rb_msg);
		friends = (RadioButton) view.findViewById(R.id.rb_friends);
		msgList = (ListView) view.findViewById(R.id.listview);
		friendList = (ExpandableListView) view.findViewById(R.id.expand);
		add = (TextView) view.findViewById(R.id.rb_add);
		this.radiogroup.setOnCheckedChangeListener(changelistener);

		msgadapter = new MessageBaseAdapter(msglist, getActivity());
		msgList.setAdapter(msgadapter);
		msgList.setOnItemClickListener(clickListener);
		add.setOnClickListener(click);
		return view;
	};

	OnClickListener click = new OnClickListener() {
		Intent intent;

		@Override
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
				msgadapter = new MessageBaseAdapter(msglist, getActivity());
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

	// @SuppressLint("UseSparseArrays")
	// private void getData() {
	// // TODO Auto-generated method stub
	//
	// for (int i = 0; i < 15; i++) {
	// User user = new User();
	// msglist.add(user);
	// }
	// friendslist = new ArrayList<String>();
	// friendslist.add("约黑好友");
	// friendslist.add("游戏好友");
	// for (int i = 0; i < 2; i++) {
	// ArrayList<User> array = new ArrayList<User>();
	// for (int j = 0; j < 10; j++) {
	// User user = new User();
	// user.setUsername("开黑" + i);
	// array.add(user);
	// }
	// }
	// }

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
