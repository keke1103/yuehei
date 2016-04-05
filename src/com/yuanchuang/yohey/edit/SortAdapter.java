package com.yuanchuang.yohey.edit;

import java.util.List;

import com.yuanchuang.yohey.R;
import com.yuanchuang.yohey.myData.AdapterData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class SortAdapter extends BaseAdapter implements SectionIndexer {
	private List<AdapterData> list = null;
	private Context mContext;

	public SortAdapter(Context mContext, List<AdapterData> list) {
		this.mContext = mContext;
		this.list = list;
	}

	/**
	 * 当ListView数据发生变化时,调用此方法来更新ListView
	 * 
	 * @param list
	 */
	public void updateListView(List<AdapterData> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return this.list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	@SuppressLint("InflateParams")
	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		 AdapterData mContent = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.item_who_reminder, null);
			viewHolder.head = (ImageView) view.findViewById(R.id.item_reminder_image_head);
			viewHolder.letter = (TextView) view.findViewById(R.id.catalog);
			viewHolder.name = (TextView) view.findViewById(R.id.item_reminder_text_view_name);
			viewHolder.myBox = (CheckBox) view.findViewById(R.id.item_reminder_chack_box);
			viewHolder.myBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					AdapterData mDa= (AdapterData) buttonView.getTag();
					mDa.setEditbox(isChecked);
				}
			});
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		// 根据position获取分类的首字母的Char ascii值
		int section = getSectionForPosition(position);

		// 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
		if (position == getPositionForSection(section)) {
			viewHolder.letter.setVisibility(View.VISIBLE);
			viewHolder.letter.setText(mContent.getEditSortLetters());
		} else {
			viewHolder.letter.setVisibility(View.GONE);
		}

		mContent.getUser().binderImageView(viewHolder.head);
		viewHolder.name.setText(mContent.getUser().getNickName());
		viewHolder.myBox.setChecked(mContent.isEditbox());
		viewHolder.myBox.setTag(mContent);
		viewHolder.myBox.setChecked(mContent.isEditbox());

		return view;

	}

	final static class ViewHolder {
		TextView letter;// 字母
		TextView name;// 昵称
		ImageView head;// 头像
		CheckBox myBox;
	}

	/**
	 * 根据ListView的当前位置获取分类的首字母的Char ascii值
	 */
	@Override
	public int getSectionForPosition(int position) {
		return list.get(position).getEditSortLetters().charAt(0);
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	@Override
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getEditSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * 提取英文的首字母，非英文字母用#代替。
	 * 
	 * @param str
	 * @return
	 */
	private String getAlpha(String str) {
		String sortStr = str.trim().substring(0, 1).toUpperCase();
		// 正则表达式，判断首字母是否是英文字母
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}