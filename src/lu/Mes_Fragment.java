package lu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.yuanchuang.yohey.FriendMessageActivity;
import com.yuanchuang.yohey.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class Mes_Fragment extends Fragment {
	ListView listview;
	ArrayList<Map<String, String>> list;
	Map<String, String> map;
	LayoutInflater inflater;
	MyAdapter adapter;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.inflater = inflater;
		list = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 15; i++) {
			map = new HashMap<String, String>();
			map.put("", "");
			list.add(map);
		}
		LinearLayout linearlayout = new LinearLayout(getActivity());
		linearlayout.setLayoutParams(new LayoutParams(-1, -1));
		view = inflater.inflate(R.layout.messages, linearlayout);
		listview = (ListView) view.findViewById(R.id.listview);
		adapter = new MyAdapter(list);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(clickListener);
		return view;
	}

	/**
	 * listview的点击事件
	 */
	OnItemClickListener clickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(getActivity(), FriendMessageActivity.class);
			startActivity(intent);
		}
	};

	class MyAdapter extends BaseAdapter {
		ArrayList<Map<String, String>> arraylist;
		Context context;

		public MyAdapter() {
		}

		public MyAdapter(ArrayList<Map<String, String>> arraylist) {
			this.arraylist = arraylist;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return arraylist.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arraylist.get(arg0);
		}

		@Override
		public long getItemId(int id) {
			// TODO Auto-generated method stub
			return id;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(int arg0, View convertview, ViewGroup arg2) {
			Viewholder holder;
			if (convertview == null) {
				convertview = inflater.inflate(R.layout.message_listview, null);
				holder = new Viewholder();
				convertview.setTag(holder);
			} else {
				holder = (Viewholder) convertview.getTag();
			}
			return convertview;
		}
	}

	class Viewholder {

	}
}
