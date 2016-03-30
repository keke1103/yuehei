package com.yuanchuang.yohey.adapter;

import java.util.List;

import com.yuanchuang.yohey.PersonalInformationActivity;
import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.Post;
import com.yuanchuang.yohey.tools.OnFlushOldData;
import com.yuanchuang.yohey.tools.TimeUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 主页面的adapter
 * 
 * @author Administrator
 *
 */
public class MainAdapter extends BaseAdapter {
	List<Post> list;
	Context context;
	LayoutInflater inflater;
	Post p;

	public MainAdapter() {
		// TODO Auto-generated constructor stub
	}

	public MainAdapter(List<Post> list, Context context) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	public void setData(List<Post> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	public List<Post> getData() {
		return list;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position % list.size();
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		p = list.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_main_post, null);
			holder.add = (TextView) convertView.findViewById(R.id.list_main_text_add);
			holder.area = (TextView) convertView.findViewById(R.id.list_main_text_area);
			holder.context = (LinearLayout) convertView.findViewById(R.id.list_main_linear_context);
			holder.dan = (TextView) convertView.findViewById(R.id.list_main_text_dan);
			holder.head = (ImageView) convertView.findViewById(R.id.list_main_image_head_tu);
			holder.message = (TextView) convertView.findViewById(R.id.list_main_text_message);
			holder.name = (TextView) convertView.findViewById(R.id.list_main_text_nick);
			holder.time = (TextView) convertView.findViewById(R.id.list_main_text_time);
			holder.up = (TextView) convertView.findViewById(R.id.list_main_text_up);
			holder.line = convertView.findViewById(R.id.list_main_line_zhong);
			holder.headName = convertView.findViewById(R.id.list_main_linear_head_image_nick);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.add.setText(p.getJoincount() + "");
		holder.name.setText(p.getUser().getNickName());
		// Log.i("MainAdapter", p.getCreatedAt());
		holder.time.setText(TimeUtil.formateTimeToNow(p.getCreatedAt()));
		holder.context.removeAllViews();
		TextView text = new TextView(context);
		text.setText(p.getTitle());
		text.setPadding(10, 5, 0, 0);
		text.setTextSize(12);
		holder.context.addView(text);
		holder.line.setVisibility(View.GONE);
		holder.area.setText(p.getGame().getGameregion());
		holder.dan.setText(p.getGame().getGamedan());
		p.getUser().binderImageView(holder.head);
		holder.message.setText(p.getComcount() + "");
		holder.up.setText(p.getLikenumber() + "");
		holder.headName.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(context, PersonalInformationActivity.class);
				YoheyApplication app = new YoheyApplication();

				app.data = p.getUser();
				Toast.makeText(context, (CharSequence) p.getUser().getNickName(), Toast.LENGTH_SHORT).show();
				Log.i("mainAdapter", app.data + "");
				context.startActivity(intent);
			}
		});
		if (((list.size() - position) < 2) && mFlush != null) {
			mFlush.flush(this, position);
		}
		return convertView;
	}

	class ViewHolder {
		View line;
		View headName;// 头像和昵称
		ImageView head;
		TextView name;
		TextView time;
		LinearLayout context;
		TextView area;
		TextView dan;
		TextView add;
		TextView message;
		TextView up;
	}

	OnFlushOldData mFlush;

	public void setOnFlushOldData(OnFlushOldData mFlush) {
		this.mFlush = mFlush;
	}
}
