package com.yuanchuang.yohey.edit;

import java.util.Comparator;

import com.yuanchuang.yohey.myData.AdapterData;

/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<AdapterData> {

	public int compare(AdapterData o1, AdapterData o2) {
		if (o1.getEditSortLetters().equals("@") || o2.getEditSortLetters().equals("#")) {
			return -1;
		} else if (o1.getEditSortLetters().equals("#") || o2.getEditSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getEditSortLetters().compareTo(o2.getEditSortLetters());
		}
	}

}
