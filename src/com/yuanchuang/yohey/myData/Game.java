package com.yuanchuang.yohey.myData;

import org.json.JSONException;
import org.json.JSONObject;

public class Game {
	int id;
	int uid;
	String gameregion;
	String gamename;
	int gamedan;
	int gamegrade;

	public int getId() {
		return id;
	}

	public int getUid() {
		return uid;
	}

	/**
	 * 游戏所在区服
	 * 
	 * @return
	 */
	public String getGameregion() {
		return gameregion;
	}

	/**
	 * 游戏昵称名
	 * 
	 * @return
	 */
	public String getGamename() {
		return gamename;
	}

	/**
	 * 游戏段位
	 * 
	 * @return
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
	 * 游戏等级
	 * 
	 * @return
	 */
	public int getGamegrade() {
		return gamegrade;
	}

	public static Game paresJSONObejct(JSONObject jo) {
		Game g = null;
		try {
			int id = jo.getInt("id");
			g = new Game();
			g.id = id;
		} catch (JSONException e) {
			return null;
		}

		try {
			g.uid = jo.getInt("uid");
		} catch (JSONException e4) {

		}
		try {
			g.gameregion = jo.getString("gameregion");
		} catch (JSONException e3) {

		}
		try {
			g.gamename = jo.getString("gamename");
		} catch (JSONException e2) {

		}
		try {
			g.gamedan = jo.getInt("gamedan");
		} catch (JSONException e1) {

		}
		try {
			g.gamegrade = jo.getInt("gamegrade");
		} catch (JSONException e) {

		}
		return g;
	}
}
