package com.yuanchuang.yohey;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	RelativeLayout includeTitle;
	View user1;
	View user2;
	View user3;
	TextView clickMore;
	RelativeLayout inLayoutSelect;
	RadioButton main;
	RadioButton friends;
	RadioButton circleOfFriends;
	RadioButton me;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_main);
		findView();
		main.setOnClickListener(clickListener);
		friends.setOnClickListener(clickListener);
		circleOfFriends.setOnClickListener(clickListener);
		me.setOnClickListener(clickListener);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.selection_bar_main:

				break;

			case R.id.selection_bar_friends:

				break;
			case R.id.selection_bar_circle_of_friends:

				break;
			case R.id.selection_bar_personal:

				break;
			default:
				break;
			}
		}
	};

	private void findView() {
		inLayoutSelect = (RelativeLayout) findViewById(R.id.mian_selection_bar);
		main = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_main);
		friends = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_friends);
		circleOfFriends = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_circle_of_friends);
		me = (RadioButton) inLayoutSelect.findViewById(R.id.selection_bar_personal);
	}
}
