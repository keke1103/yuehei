package com.yuanchuang.yohey.adapter;

import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.bmob.Post;
import com.yuanchuang.yohey.tools.TimeUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 发表帖子与参与帖子
 * 
 * @author Administrator
 *
 */
public class PostSAdater extends BaseAdapter {
	List<Post> list;
	Context context;
	LayoutInflater inflater;

	public PostSAdater() {
		// TODO Auto-generated constructor stub
	}

	public PostSAdater(List<Post> list, Context context) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}
    
	public void setData(List<Post> list){
		this.list=list;
		notifyDataSetChanged();
	}
	
	@Override
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
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_post_had_sent_and_add_view, null);
			holder.head = (ImageView) convertView.findViewById(R.id.list_post_details_image_head_portrait);
			holder.name = (TextView) convertView.findViewById(R.id.list_post_details_text_user_name);
			holder.browse = (TextView) convertView.findViewById(R.id.list_post_details_text_yan);
			holder.message = (TextView) convertView.findViewById(R.id.list_post_details_text_message);
			holder.time = (TextView) convertView.findViewById(R.id.list_post_details_text_time);
			holder.con = (TextView) convertView.findViewById(R.id.list_post_details_text_context);

			convertView.setTag(holder);
		}
		{
			holder = (ViewHolder) convertView.getTag();
		}
        Post p=list.get(position);
        p.getUser().binderImageView(holder.head);
        holder.name.setText(p.getUser().getNickName());
        holder.time.setText(TimeUtil.formateTimeToNow(p.getCreatedAt()));
        holder.con.setText(p.getTitle());
        holder.browse.setText(p.getComcount()+"");
        holder.message.setText(p.getComcount()+"");
		return convertView;
	}

	class ViewHolder {
		ImageView head;// 头像
		TextView name;// 昵称
		TextView browse;// 浏览
		TextView message;// 留言
		TextView time;// 时间
		TextView con;// 内容
	}
}
