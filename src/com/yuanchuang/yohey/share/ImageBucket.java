package com.yuanchuang.yohey.share;

import java.io.Serializable;
import java.util.List;
/**
 * 一个目录的相册对象
 * 
 * @author Administrator
 * 
 */
public class ImageBucket {
	public int count = 0;
	public String bucketName;
	public List<ImageItem> imageList;
	
	
	/**
	 * 一个图片对象
	 * 
	 * @author Administrator
	 * 
	 */
	@SuppressWarnings("serial")
	public static class ImageItem implements Serializable {
		public String imageId;
		public String thumbnailPath;
		public String imagePath;
		public boolean isSelected = false;
	}
}
