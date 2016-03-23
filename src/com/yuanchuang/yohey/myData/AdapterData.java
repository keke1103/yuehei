package com.yuanchuang.yohey.myData;

import android.graphics.Bitmap;

public class AdapterData {
	// 动态数据
	String dy_head;
	String dy_name;
	String dy_time;
	String dy_context;
	Bitmap[] dy_image;
	// 帖子数据
	String post_head;
	String post_name;
	int post_browse;
	int post_message;
	String post_time;
	String post_con;
	// 推荐开黑数据
	String reco_head;
	String reco_name;
	String reco_region;
	String reco_dan;
	String reco_message;
	String reco_messageIcon;
	String reco_area;
	// 主页数据
	String main_head;
	String main_name;
	String main_time;
	String main_context;
	String main_area;
	String main_dan;
	int main_add;
	int main_message;
	int main_up;// 点赞
	// 帖子详情
	int post_details_head;
	String post_details_name;
	String post_details_time;
	String post_details_context;
	// 官方资讯
	int official_image_brief;// 官方资讯图片
	String official_announcement;// 官方资讯标题
	String official_context;// 官方资讯内容
	String official_time;// 官方资讯已发布多长时间时间

	// 谁看过我
	String whoContent;
	String whoName;
	boolean whoBox;

	public String getWhoContent() {
		return whoContent;
	}

	public void setWhoContent(String whoContent) {
		this.whoContent = whoContent;
	}

	public String getWhoName() {
		return whoName;
	}

	public void setWhoName(String whoName) {
		this.whoName = whoName;
	}

	public boolean isWhoBox() {
		return whoBox;
	}

	public void setWhoBox(boolean whoBox) {
		this.whoBox = whoBox;
	}

	public Bitmap[] getDy_image() {
		return dy_image;
	}

	public void setDy_image(Bitmap[] dy_image) {
		this.dy_image = dy_image;
	}

	public int getOfficial_image_brief() {
		return official_image_brief;
	}

	public void setOfficial_image_brief(int official_image_brief) {
		this.official_image_brief = official_image_brief;
	}

	public String getOfficial_announcement() {
		return official_announcement;
	}

	public void setOfficial_announcement(String official_announcement) {
		this.official_announcement = official_announcement;
	}

	public String getOfficial_context() {
		return official_context;
	}

	public void setOfficial_context(String official_context) {
		this.official_context = official_context;
	}

	public String getOfficial_time() {
		return official_time;
	}

	public void setOfficial_time(String official_time) {
		this.official_time = official_time;
	}

	public int getPost_details_head() {
		return post_details_head;
	}

	public void setPost_details_head(int post_details_head) {
		this.post_details_head = post_details_head;
	}

	public String getPost_details_name() {
		return post_details_name;
	}

	public void setPost_details_name(String post_details_name) {
		this.post_details_name = post_details_name;
	}

	public String getPost_details_time() {
		return post_details_time;
	}

	public void setPost_details_time(String post_details_time) {
		this.post_details_time = post_details_time;
	}

	public String getPost_details_context() {
		return post_details_context;
	}

	public void setPost_details_context(String post_details_context) {
		this.post_details_context = post_details_context;
	}

	public String getMain_head() {
		return main_head;
	}

	public void setMain_head(String main_head) {
		this.main_head = main_head;
	}

	public String getMain_name() {
		return main_name;
	}

	public void setMain_name(String main_name) {
		this.main_name = main_name;
	}

	public String getMain_time() {
		return main_time;
	}

	public void setMain_time(String main_time) {
		this.main_time = main_time;
	}

	public String getMain_context() {
		return main_context;
	}

	public void setMain_context(String main_context) {
		this.main_context = main_context;
	}

	public String getMain_area() {
		return main_area;
	}

	public void setMain_area(String main_area) {
		this.main_area = main_area;
	}

	public String getMain_dan() {
		return main_dan;
	}

	public void setMain_dan(String main_dan) {
		this.main_dan = main_dan;
	}

	public int getMain_add() {
		return main_add;
	}

	public void setMain_add(int main_add) {
		this.main_add = main_add;
	}

	public int getMain_message() {
		return main_message;
	}

	public void setMain_message(int main_message) {
		this.main_message = main_message;
	}

	public int getMain_up() {
		return main_up;
	}

	public void setMain_up(int main_up) {
		this.main_up = main_up;
	}

	public String getDy_head() {
		return dy_head;
	}

	public void setDy_head(String dy_head) {
		this.dy_head = dy_head;
	}

	public String getDy_name() {
		return dy_name;
	}

	public void setDy_name(String dy_name) {
		this.dy_name = dy_name;
	}

	public String getReco_area() {
		return reco_area;
	}

	public void setReco_area(String reco_area) {
		this.reco_area = reco_area;
	}

	public void setDy_time(String dy_time) {
		this.dy_time = dy_time;
	}

	public String getDy_time() {
		return dy_time;
	}

	public void setDy_nmae(String dy_time) {
		this.dy_time = dy_time;
	}

	public String getDy_context() {
		return dy_context;
	}

	public void setDy_context(String dy_context) {
		this.dy_context = dy_context;
	}

	public String getPost_head() {
		return post_head;
	}

	public void setPost_head(String post_head) {
		this.post_head = post_head;
	}

	public String getPost_name() {
		return post_name;
	}

	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}

	public int getPost_browse() {
		return post_browse;
	}

	public void setPost_browse(int post_browse) {
		this.post_browse = post_browse;
	}

	public int getPost_message() {
		return post_message;
	}

	public void setPost_message(int post_message) {
		this.post_message = post_message;
	}

	public String getPost_time() {
		return post_time;
	}

	public void setPost_time(String post_time) {
		this.post_time = post_time;
	}

	public String getPost_con() {
		return post_con;
	}

	public void setPost_con(String post_con) {
		this.post_con = post_con;
	}

	public String getReco_head() {
		return reco_head;
	}

	public void setReco_head(String reco_head) {
		this.reco_head = reco_head;
	}

	public String getReco_name() {
		return reco_name;
	}

	public void setReco_name(String reco_name) {
		this.reco_name = reco_name;
	}

	public String getReco_region() {
		return reco_region;
	}

	public void setReco_region(String reco_region) {
		this.reco_region = reco_region;
	}

	public String getReco_dan() {
		return reco_dan;
	}

	public void setReco_dan(String reco_dan) {
		this.reco_dan = reco_dan;
	}

	public String getReco_message() {
		return reco_message;
	}

	public void setReco_message(String reco_message) {
		this.reco_message = reco_message;
	}

	public String getReco_messageIcon() {
		return reco_messageIcon;
	}

	public void setReco_messageIcon(String reco_messageIcon) {
		this.reco_messageIcon = reco_messageIcon;
	}

}
