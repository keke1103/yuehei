package com.yuanchuang.yohey.bmob;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.BmobObject;

@SuppressWarnings("serial")
public class Game extends BmobObject {
	private String gameregion;
	private String gamename;
	private int gamedan;
	private int gamegrade;
	private int zhandouli;
	private User user;

	public int getZhandouli() {
		return zhandouli;
	}

	public void setZhandouli(int zhandouli) {
		this.zhandouli = zhandouli;
	}

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
			return "铂金Ⅵ";
		case 18:
			return "铂金Ⅴ";
		case 19:
			return "铂金Ⅳ";
		case 20:
			return "铂金Ⅲ";
		case 21:
			return "铂金Ⅱ";
		case 22:
			return "铂金Ⅰ";
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
			return "大师";
		case 31:
			return "王者";
		default:
			break;
		}
		return "";
	}

	public void setGamedan(String dan) {
		if (dan == null) {
			setGamedan(0);
			return;
		}

		if ("青铜Ⅵ".equals(dan)) {
			setGamedan(1);
			return;
		}
		if ("青铜Ⅴ".equals(dan)) {
			setGamedan(2);
			return;
		}
		if ("青铜Ⅳ".equals(dan)) {
			setGamedan(3);
			return;
		}
		if ("青铜 Ⅲ".equals(dan)) {
			setGamedan(4);
			return;
		}
		if ("青铜 Ⅱ".equals(dan)) {
			setGamedan(5);
			return;
		}
		if ("青铜I".equals(dan)) {
			setGamedan(6);
			return;
		}

		if ("白银Ⅴ".equals(dan)) {
			setGamedan(7);
			return;
		}
		if ("白银Ⅳ".equals(dan)) {
			setGamedan(8);
			return;
		}
		if ("白银Ⅲ".equals(dan)) {
			setGamedan(9);
			return;
		}
		if ("白银Ⅱ".equals(dan)) {
			setGamedan(10);
			return;
		}
		if ("白银I".equals(dan)) {
			setGamedan(11);
			return;
		}
		if ("黄金Ⅴ".equals(dan)) {
			setGamedan(12);
			return;
		}
		if ("黄金Ⅳ".equals(dan)) {
			setGamedan(13);
			return;
		}
		if ("黄金Ⅲ".equals(dan)) {
			setGamedan(14);
			return;
		}
		if ("黄金Ⅱ".equals(dan)) {
			setGamedan(15);
			return;
		}
		if ("黄金I".equals(dan)) {
			setGamedan(16);
			return;
		}
		if ("白金Ⅵ".equals(dan)) {
			setGamedan(17);
			return;
		}
		if ("白金Ⅴ".equals(dan)) {
			setGamedan(18);
			return;
		}

		if ("白金Ⅳ".equals(dan)) {
			setGamedan(19);
			return;
		}
		if ("白金Ⅲ".equals(dan)) {
			setGamedan(20);
			return;
		}
		if ("白金Ⅱ".equals(dan)) {
			setGamedan(21);
			return;
		}
		if ("白金I".equals(dan)) {
			setGamedan(22);
			return;
		}
		if ("砖石Ⅶ".equals(dan)) {
			setGamedan(23);
			return;
		}
		if ("砖石Ⅵ".equals(dan)) {
			setGamedan(24);
			return;
		}
		if ("砖石Ⅴ".equals(dan)) {
			setGamedan(25);
			return;
		}
		if ("砖石Ⅳ".equals(dan)) {
			setGamedan(26);
			return;
		}
		if ("砖石Ⅲ".equals(dan)) {
			setGamedan(27);
			return;
		}
		if ("砖石Ⅱ".equals(dan)) {
			setGamedan(28);
			return;
		}
		if ("砖I".equals(dan)) {
			setGamedan(29);
			return;
		}
		if ("大师".equals(dan)) {
			setGamedan(30);
			return;
		}
		if ("王者".equals(dan)) {
			setGamedan(31);
			return;
		}
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

	/**
	 * 
	 * @param jo
	 * @return
	 */
	public static Game paresJSONObejct(JSONObject jo) {
		Game g = null;
		try {
			g = new Game();
			g.setObjectId(jo.getString("objectId"));
		} catch (JSONException e) {
			return null;
		}
		try {
			g.setGameregion(jo.getString("gameregion"));
		} catch (JSONException e3) {

		}
		try {
			g.setGamename(jo.getString("gamename"));
		} catch (JSONException e2) {

		}
		try {
			g.setGamedan(jo.getInt("gamedan"));
		} catch (JSONException e1) {

		}
		try {
			g.setGamegrade(jo.getInt("gamegrade"));
		} catch (JSONException e) {

		}
		return g;
	}
}
