package lu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuanchuang.yohey.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class Mes_Fragment extends Fragment {
ListView listview;
ArrayList<Map<String,String>> list;
Map<String,String> map;
LayoutInflater inflater;
MyAdapter adapter;
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	this.inflater=inflater;
	list=new ArrayList<Map<String,String>>();
	for(int i=0;i<15;i++)
	{
		map=new HashMap<String,String>();
		map.put("", "");
		list.add(map);
	}
	LinearLayout linearlayout=new LinearLayout(getActivity());
	linearlayout.setLayoutParams(new LayoutParams(-1, -1));
	View view=inflater.inflate(R.layout.message, null);
	listview=(ListView)view.findViewById(R.id.listview);
	adapter=new MyAdapter(list);
	listview.setAdapter(adapter);
	return view;
}
 class MyAdapter extends BaseAdapter
 {
	 ArrayList<Map<String,String>> arraylist;
	 public MyAdapter(ArrayList<Map<String,String>> arraylist)
	 {
		 this.arraylist=arraylist;
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

	@Override
	public View getView(int arg0, View convertview, ViewGroup arg2) {
		Viewholder holder;
		if(convertview==null)
		{
			convertview=inflater.inflate(R.layout.message_listview, null);
			holder=new Viewholder();
			convertview.setTag(holder);
		}
		else
		{
			holder=(Viewholder) convertview.getTag();
		}
		return convertview;
	}
	}
 class Viewholder
 {
	 
	 
 }
}
