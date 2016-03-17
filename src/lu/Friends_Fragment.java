package lu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuanchuang.yohey.FriendMaterialActivity;
import com.yuanchuang.yohey.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Friends_Fragment extends Fragment {
	ExpandableListView expand;
	List<String> list;
	Map<String, List<String>> map;
	LayoutInflater inflater;
	MyAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.inflater = inflater;
		list = new ArrayList<String>();
		map = new HashMap<String, List<String>>();
		list.add("约黑好友");
		list.add("游戏好友");
		for (int i = 0; i < 2; i++) {
			ArrayList<String> array = new ArrayList<String>();
			for (int j = 0; j < 10; j++) {
				array.add("约黑" + i + j);
			}
			map.put(list.get(i), array);

		}
		LinearLayout linearlayout = new LinearLayout(getActivity());
		linearlayout.setLayoutParams(new LayoutParams(-1, -1));
		View view = inflater.inflate(R.layout.friends, linearlayout);

		expand = (ExpandableListView) view.findViewById(R.id.expand);
		adapter = new MyAdapter();
		expand.setAdapter(adapter);
		expand.setOnChildClickListener(childClickListener);
		return view;
	}

	/**
	 * ExpandableListView的点击事件
	 */
	OnChildClickListener childClickListener = new OnChildClickListener() {

		@Override
		public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(getActivity(), FriendMaterialActivity.class);
			startActivity(intent);
			return false;
		}
	};

	class MyAdapter extends BaseExpandableListAdapter {

		@Override
		public Object getChild(int arg0, int arg1) {
			// TODO Auto-generated method stub
			String key = list.get(arg0);
			String child = map.get(key).get(arg1);
			return child;
		}

		@Override
		public long getChildId(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return arg1;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getChildView(int group, int child, boolean arg2, View convertview, ViewGroup arg4) {
			// TODO Auto-generated method stub
			Viewholder holder;
			if (convertview == null) {
				convertview = inflater.inflate(R.layout.child, null);
				holder = new Viewholder();
				holder.name = (TextView) convertview.findViewById(R.id.tv_childname);
				holder.sign = (TextView) convertview.findViewById(R.id.tv_sign);
				holder.onlinestatus = (TextView) convertview.findViewById(R.id.tv_status);
				convertview.setTag(holder);
			} else {
				holder = (Viewholder) convertview.getTag();
			}
			String key = list.get(group);
			holder.name.setText(map.get(key).get(child));
			return convertview;
		}

		@Override
		public int getChildrenCount(int arg0) {
			// TODO Auto-generated method stub
			String key = list.get(arg0);
			int size = map.get(key).size();
			return size;
		}

		@Override
		public Object getGroup(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public long getGroupId(int id) {
			// TODO Auto-generated method stub
			return id;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getGroupView(int index, boolean expand, View convertview, ViewGroup arg3) {
			Viewholder holder;
			if (convertview == null) {
				convertview = inflater.inflate(R.layout.group, null);
				holder = new Viewholder();
				holder.icon = (ImageView) convertview.findViewById(R.id.iv_icon);
				holder.groupname = (TextView) convertview.findViewById(R.id.tv_groupname);
				holder.onlinenum = (TextView) convertview.findViewById(R.id.tv_onlinenum);
				holder.onlinetotal = (TextView) convertview.findViewById(R.id.tv_total);
				convertview.setTag(holder);
			} else {

				holder = (Viewholder) convertview.getTag();
			}
			holder.groupname.setText(list.get(index));
			if (expand) {
				holder.icon.setBackgroundResource(R.drawable.yo_hey_up_triangle);
			} else {
				holder.icon.setBackgroundResource(R.drawable.yo_hey_dwon_triangle);
			}
			return convertview;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isChildSelectable(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return true;
		}
	}

	class Viewholder {
		TextView groupname;
		TextView onlinenum;
		TextView onlinetotal;
		TextView name;
		TextView sign;
		TextView onlinestatus;

		ImageView icon;

	}

}
