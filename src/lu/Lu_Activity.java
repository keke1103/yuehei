package lu;

import java.util.List;
import java.util.Map;

import com.yuanchuang.yohey.AddFriendsActivity;
import com.yuanchuang.yohey.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class Lu_Activity extends Fragment {
	RadioGroup radiogroup;
	RadioButton msg, friends;
	List<String> list;
	Map<String, List<String>> map;
	LinearLayout linearlayout;
	Mes_Fragment fr_message;
	Friends_Fragment fr_friends;
	FragmentManager fm;
	TextView add;// 添加

	@Override
	public android.view.View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// super.onCreate(savedInstanceState);
		// setContentView(R.layout.lu);
		LinearLayout lay = new LinearLayout(getActivity());
		lay.setLayoutParams(new LayoutParams(-1, -1));
		View view = inflater.inflate(R.layout.lu, lay);
		add = (TextView) view.findViewById(R.id.rb_add);
		linearlayout = (LinearLayout) view.findViewById(R.id.linearlayout);
		radiogroup = (RadioGroup) view.findViewById(R.id.radiogroup);
		msg = (RadioButton) view.findViewById(R.id.rb_msg);
		friends = (RadioButton) view.findViewById(R.id.rb_friends);
		msg.setChecked(true);
		this.radiogroup.setOnCheckedChangeListener(changelistener);
		add.setOnClickListener(clickListener);
		fm = getChildFragmentManager();
		fr_message = new Mes_Fragment();
		fr_friends = new Friends_Fragment();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(R.id.linearlayout, fr_message);
		ft.add(R.id.linearlayout, fr_friends);
		ft.hide(fr_friends);
		ft.commit();
		return view;
	};

	OnClickListener clickListener = new OnClickListener() {
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
			FragmentTransaction ft = fm.beginTransaction();
			switch (id) {
			case R.id.rb_msg:
				ft.show(fr_message);
				ft.hide(fr_friends);
				ft.commit();
				break;

			case R.id.rb_friends:
				ft.show(fr_friends);
				ft.hide(fr_message);
				ft.commit();
				break;
			}
		}
	};
}
