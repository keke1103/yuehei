package com.yuanchuang.yohey;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.yuanchuang.yohey.app.YoheyApplication;
import com.yuanchuang.yohey.bmob.FriendGroup;
import com.yuanchuang.yohey.bmob.Game;
import com.yuanchuang.yohey.bmob.User;
import com.yuanchuang.yohey.tools.HttpGet;
import com.yuanchuang.yohey.tools.HttpPost.OnSendListener;
import com.yuanchuang.yohey.tools.QQBaseUIListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 登录注册页面
 * 
 * @author Administrator
 *
 */
public class LoginAndRegistered extends Activity {
	YoheyApplication application;

	AlertDialog alertDialog;// 自定义的alertDialog
	EditText account;// 账号输入框
	EditText password;// 密码输入框
	ImageView rememberPassword;// 记住密码图
	TextView dorget_password;// 忘记密码
	Button login;// 登录按钮
	Button registered;// 注册按钮
	View qqLogin;// qq登录
	TextView webLogin;// 微博登录

	ImageView headicon;

	/**
	 * 
	 */
	ProgressDialog dialog;
	int zhandouli;// 战斗力
	int level;// 游戏等级

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yue_lu_login_registered);

		application = (YoheyApplication) getApplication();
		application.mTencent = Tencent.createInstance(application.APP_ID, this);
		findView();
		initView();
		dialog = new ProgressDialog(this);

		try {
			loginByQq(getLoginData());
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	private void initView() {
		login.setOnClickListener(onClickListener);
		registered.setOnClickListener(onClickListener);
		qqLogin.setOnClickListener(onClickListener);
		rememberPassword.setOnClickListener(onClickListener);
		dorget_password.setOnClickListener(onClickListener);
	}

	/**
	 * 自定义的Dialog
	 */
	@SuppressLint({ "InflateParams", "NewApi" })
	public void customDialog(final User user) {
		dialog.dismiss();
		LayoutInflater inflater = getLayoutInflater();
		View view = inflater.inflate(R.layout.choose_game_region_main, null);
		final Spinner area = (Spinner) view.findViewById(R.id.choose_game_spinner);
		final EditText name = (EditText) view.findViewById(R.id.choose_game_name);
		final EditText duanwei = (EditText) view.findViewById(R.id.choose_game_duanwei);
		View btn = view.findViewById(R.id.choose_comit);

		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final String a = ((TextView) area.getSelectedView()).getText().toString();
				final String n = name.getText().toString();
				final String d = duanwei.getText().toString();
				HttpGet get;
				try {
					get = new HttpGet("http://API.xunjob.cn/playerinfo.php");// 玩家的基本信息接口
					get.putString("serverName", URLEncoder.encode(a, "UTF-8"));
					get.putString("playerName", URLEncoder.encode(n, "UTF-8"));

					Log.i("insert", a + " -- " + n);
					OnSendListener mListener = new OnSendListener() {
						@Override
						public void start() {
						}

						@Override
						public void end(String result) {

							Log.i("lol result", result);

							JSONObject object;
							try {
								object = new JSONObject(result);
								level = object.getInt("level");
								zhandouli = object.getInt("zhandouli");
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							setGame(user, n, a, level, d, zhandouli);

						}
					};
					get.setOnSendListener(mListener);
					get.send();

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		alertDialog = builder.create();
		alertDialog.setView(view);
		alertDialog.show();
	}

	/**
	 * 点击事件
	 */
	OnClickListener onClickListener = new OnClickListener() {
		boolean remember = true;// 为了完成记住密码图片的改变定义的boolean

		@Override
		public void onClick(View v) {
			Intent intent;
			switch (v.getId()) {
			case R.id.login_registered_button_login:
				String userID = account.getText().toString();
				String userPassword = password.getText().toString();
				loginService(userID, userPassword);
				break;

			case R.id.login_registered_button_registered:
				intent = new Intent(LoginAndRegistered.this, InputMobilePhoneNumber.class);
				startActivity(intent);
				break;
			case R.id.login_register_text_qq_login:
				onClickQQLogin();
				break;
			case R.id.login_registered_relative_remberpassword:
				if (remember) {
					rememberPassword.setImageResource(R.drawable.yo_hey_remember_password1);
					remember = false;
				} else {
					rememberPassword.setImageResource(R.drawable.yo_hey_remberpassword);
					remember = true;
				}
				break;
			case R.id.login_register_text_forget:
				// customDialog();
				break;
			case R.id.choose_comit:
				// 游戏账号绑定监听！
				break;
			default:
				break;
			}
		}
	};

	/**
	 * 查找ID
	 */
	private void findView() {
		rememberPassword = (ImageView) findViewById(R.id.login_registered_relative_remberpassword);
		account = (EditText) findViewById(R.id.login_registered_edit_account);
		password = (EditText) findViewById(R.id.login_registered_edit_password);
		dorget_password = (TextView) findViewById(R.id.login_register_text_forget);
		login = (Button) findViewById(R.id.login_registered_button_login);
		registered = (Button) findViewById(R.id.login_registered_button_registered);
		qqLogin = findViewById(R.id.login_register_text_qq_login);
		webLogin = (TextView) findViewById(R.id.login_register_text_web_login);
		headicon = (ImageView) findViewById(R.id.login_registered_image_head_portrait);
	}

	/**
	 * 向服务器传送数据，验证登录是否成功
	 * 
	 * @param userID
	 *            用户登录的ID
	 * @param userPassword
	 *            用户登录的密码
	 */
	public void loginService(String userID, String userPassword) {

	}

	boolean isClickQQ;

	protected void onClickQQLogin() {
		if (isClickQQ)
			return;
		dialog.show();
		isClickQQ = true;
		application.mTencent.login(this, "all", loginListener);
		application.isServerSideLogin = false;

		Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
	}

	public IUiListener loginListener = new BaseUiListener();

	public class BaseUiListener implements IUiListener {

		@Override
		public void onComplete(Object response) {
			if (null == response) {
				Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
				return;
			}
			JSONObject jsonResponse = (JSONObject) response;
			if (null != jsonResponse && jsonResponse.length() == 0) {
				Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
				return;
			}
			Toast.makeText(getApplicationContext(), "授权成功", Toast.LENGTH_SHORT).show();
			// 显示登陆成功后log日志登陆信息
			String rmsg = response.toString().replace(",", "\n");
			Log.d("Yohey", rmsg);
			doComplete((JSONObject) response);
		}

		public void doComplete(JSONObject values) {
			saveLoginData(values);
			loginByQq(values);

		}

		@Override
		public void onError(UiError e) {
			dialog.dismiss();
			isClickQQ = false;
			Toast.makeText(getApplicationContext(), "onError: " + e.errorDetail, Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onCancel() {
			dialog.dismiss();
			isClickQQ = false;
			Toast.makeText(getApplicationContext(), "onCancel:  ", Toast.LENGTH_SHORT).show();
			if (application.isServerSideLogin) {
				application.isServerSideLogin = false;
			}
		}
	}

	/**
	 * 第三方登陆的方法
	 * 
	 * @param values
	 */
	private void loginByQq(JSONObject values) {
		dialog.show();
		try {
			String token = values.getString(Constants.PARAM_ACCESS_TOKEN);
			String expires = values.getString(Constants.PARAM_EXPIRES_IN);
			String openId = values.getString(Constants.PARAM_OPEN_ID);

			if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires) && !TextUtils.isEmpty(openId)) {

				application.mTencent.setAccessToken(token, expires);
				application.mTencent.setOpenId(openId);
				application.qqInfo = new UserInfo(LoginAndRegistered.this, application.mTencent.getQQToken());
				application.qqInfo.getUserInfo(new QQBaseUIListener(LoginAndRegistered.this) {

					@Override
					protected void doComplete(JSONObject jo) {
						final String username;
						final String icon;
						final String sex;
						Log.i("UserInfo", jo.toString());
						try {
							username = jo.getString("nickname");
							icon = jo.getString("figureurl_qq_2");
							sex = jo.getString("gender");

							final com.yuanchuang.yohey.bmob.User user = new com.yuanchuang.yohey.bmob.User();
							user.setUsername(application.mTencent.getOpenId());
							user.setPassword("123456");
							BmobQuery<com.yuanchuang.yohey.bmob.User> query = new BmobQuery<com.yuanchuang.yohey.bmob.User>();

							query.addWhereEqualTo("username", application.mTencent.getOpenId());
							query.setLimit(1);
							query.findObjects(getApplicationContext(),
									new FindListener<com.yuanchuang.yohey.bmob.User>() {

								@Override
								public void onError(int arg0, String arg1) {

								}

								@Override
								public void onSuccess(List<User> arg0) {
									if (arg0.size() == 0) {

										user.setIcon(icon);
										user.setNickName(username);
										user.setSex(!"男".equals(sex));
										user.signUp(LoginAndRegistered.this, new SaveListener() {
											@Override
											public void onSuccess() {
												addGame(user);
											}

											@Override
											public void onFailure(int arg0, String arg1) {

												Toast.makeText(getApplicationContext(), "error" + arg1,
														Toast.LENGTH_SHORT).show();
											}
										});
									} else {

										user.login(getApplicationContext(), new SaveListener() {

											@Override
											public void onSuccess() {
												BmobQuery<Game> query = new BmobQuery<Game>();
												query.addWhereEqualTo("user", user);
												query.findObjects(getApplicationContext(), new FindListener<Game>() {
													@Override
													public void onSuccess(List<Game> arg0) {
														if (arg0.size() == 0) {
															addGame(user);
														} else {
															Intent intent = new Intent(LoginAndRegistered.this,
																	MainActivity.class);
															startActivity(intent);
															finish();
														}
													}

													@Override
													public void onError(int arg0, String arg1) {
														Toast.makeText(getApplicationContext(), "error" + arg1,
																Toast.LENGTH_SHORT).show();
													}
												});
											}

											@Override
											public void onFailure(int arg0, String arg1) {
												Toast.makeText(getApplicationContext(), "error" + arg1,
														Toast.LENGTH_SHORT).show();
											}
										});
									}
								}
							});
						} catch (JSONException e) {
						}
					}
				});
			}
		} catch (Exception e) {
		}

	}

	/**
	 * 授权数据存到本地
	 * 
	 * @param re
	 */
	public void saveLoginData(JSONObject re) {
		getApplicationContext();
		SharedPreferences storage = getApplicationContext().getSharedPreferences("yohey", Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = storage.edit();
		edit.putString("qqData", re.toString());
		edit.commit();
	}

	/**
	 * 获取本地的授权数据
	 * 
	 * @return
	 * @throws JSONException
	 */
	public JSONObject getLoginData() throws JSONException {
		getApplicationContext();
		SharedPreferences storage = getApplicationContext().getSharedPreferences("yohey", Context.MODE_PRIVATE);
		String js = storage.getString("qqData", "");
		JSONObject jo = new JSONObject(js);
		return jo;
	}

	/**
	 * 绑定用户角色的方法！
	 * 
	 * @param user
	 */
	public void addGame(User user) {

		customDialog(user);

	}

	/**
	 * 获得账号的基本信息,并添加默认分组
	 * 
	 * @param user
	 * @param name
	 *            游戏昵称
	 * @param region
	 *            区服
	 * @param grade
	 *            等级
	 * @param dan
	 *            段位
	 */
	private void setGame(final User user, String name, String region, int grade, String dan, int zhandouli) {
		dialog.show();
		FriendGroup fg = new FriendGroup();
		fg.setGroupName("我的撸友");
		fg.setUser(user);
		fg.save(getApplicationContext());
		final Game game = new Game();
		game.setGamedan(dan);
		game.setGamegrade(grade);
		game.setGameregion(region);
		game.setGamename(name);
		game.setUser(user);
		game.setZhandouli(zhandouli);
		game.save(getApplicationContext(), new SaveListener() {

			@Override
			public void onSuccess() {
				Log.i("onSuccess", "onSuccess");
				User u = new User();
				u.setDefGame(game);
				u.update(getApplicationContext(), user.getObjectId(), null);
				Intent intent = new Intent(LoginAndRegistered.this, MainActivity.class);
				startActivity(intent);
				finish();
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(getApplicationContext(), "error" + arg1, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onDestroy() {
		application.connectChatService();
		User u = BmobUser.getCurrentUser(getApplicationContext(), User.class);
		if (u != null) {
			u.getFriendGroup(application);
		}
		super.onDestroy();
	}

	/**
	 * Activity回调
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Constants.REQUEST_LOGIN || requestCode == Constants.REQUEST_APPBAR) {
			Log.i("LoginActivity", "is doing qq");
			Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
