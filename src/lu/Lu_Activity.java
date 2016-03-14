package lu;

import java.util.List;
import java.util.Map;

import com.yuanchuang.yohey.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
public class Lu_Activity extends FragmentActivity {
	RadioGroup radiogroup;
	RadioButton  msg,friends;
	List<String> list;
	Map<String,List<String>> map;
	LinearLayout linearlayout;
	Mes_Fragment fr_message;
	Friends_Fragment fr_friends;
	
	FragmentManager fm;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.lu);
	linearlayout=(LinearLayout)findViewById(R.id.linearlayout);
	radiogroup=(RadioGroup)findViewById(R.id.radiogroup);
	msg=(RadioButton)findViewById(R.id.rb_msg);
	friends=(RadioButton)findViewById(R.id.rb_friends);
	msg.setChecked(true);
	this.radiogroup.setOnCheckedChangeListener(changelistener);
	fm=getSupportFragmentManager();
	fr_message=new Mes_Fragment();
	fr_friends=new Friends_Fragment();
	FragmentTransaction ft=fm.beginTransaction();
	ft.add(R.id.linearlayout, fr_message);
	ft.commit();
}
OnCheckedChangeListener changelistener=new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(RadioGroup arg0, int id) {
		FragmentTransaction ft=fm.beginTransaction();
		switch (id) {
		case R.id.rb_msg:
			ft.replace(R.id.linearlayout, fr_message);
			ft.commit();
			break;

		case R.id.rb_friends:
			ft.replace(R.id.linearlayout, fr_friends);
			ft.commit();
			break;
		}
	}
};
}
