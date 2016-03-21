package com.yuanchuang.yohey.bmob;

import cn.bmob.v3.BmobObject;

@SuppressWarnings("serial")
public class Game extends BmobObject {
	private String gameregion;
	private String gamename;
	private int gamedan;
	private int gamegrade;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 游戏区服
	 * 
	 * @param gameregion
	 */
	public String getGameregion() {
		return gameregion;
	}

	/**
	 * 游戏区服
	 * 
	 * @param gameregion
	 */
	public void setGameregion(String gameregion) {
		this.gameregion = gameregion;
	}

	/**
	 * 游戏昵称
	 * 
	 * @param gameregion
	 */
	public String getGamename() {
		return gamename;
	}

	/**
	 * 游戏昵称
	 * 
	 * @param gameregion
	 */
	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	/**
	 * 游戏段位
	 * 
	 * @param gameregion
	 */
	public String getGamedan() {

		switch (gamedan) {
		case 0:
			return "";
		case 1:
			return "青铜  Ⅵ";
		case 2:
			return "青铜 Ⅴ";
		case 3:
			return "青铜 Ⅳ";
		case 4:
			return "青铜 Ⅲ";
		case 5:
			return "青铜 Ⅱ";
		case 6:
			return "青铜 Ⅰ";
		case 7:
			return "白银Ⅴ";
		case 8:
			return "白银Ⅳ";
		case 9:
			return "白银Ⅲ";
		case 10:
			return "白银Ⅱ";
		case 11:
			return "白银Ⅰ";
		case 12:
			return "黄金Ⅴ";
		case 13:
			return "黄金Ⅳ";
		case 14:
			return "黄金Ⅲ";
		case 15:
			return "黄金Ⅱ";
		case 16:
			return "黄金Ⅰ";
		case 17:
			return "白金Ⅵ";
		case 18:
			return "白金Ⅴ";
		case 19:
			return "白金Ⅳ";

		case 20:
			return "白金Ⅲ";
		case 21:
			return "白金Ⅱ";
		case 22:
			return "白金Ⅰ";
		case 23:
			return "砖石Ⅶ";
		case 24:
			return "砖石Ⅵ";
		case 25:
			return "砖石Ⅴ";
		case 26:
			return "砖石Ⅳ";
		case 27:
			return "砖石Ⅲ";
		case 28:
			return "砖石Ⅱ";
		case 29:
			return "砖石Ⅰ";
		case 30:

			break;
		case 31:

			break;
		default:
			break;
		}
		return "";
	}

	/**
	 * 游戏段位
	 * 
	 * @param gameregion
	 */
	public void setGamedan(int gamedan) {
		this.gamedan = gamedan;
	}

	/**
	 * 游戏等级
	 * 
	 * @param gameregion
	 */
	public int getGamegrade() {
		return gamegrade;
	}

	/**
	 * 游戏等级
	 * 
	 * @param gameregion
	 */
	public void setGamegrade(int gamegrade) {
		this.gamegrade = gamegrade;
	}

}
