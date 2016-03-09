package com.yuanchuang.yohey.tools;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import android.os.AsyncTask;

/**
 * post请求方式封装类，通过 HttpPost(URL url)获得实例！
 * 
 * </p>
 * 或者通过 parseUrl(String url)来获得示例！
 * </p>
 * 通过putString(k,v)或putMap(map)来添加发送数据
 * </p>
 * 通过putFile();添加要文件发送
 * </p>
 * 数据设置结束过后记得调用send()方法,开始网络post传输,在发送之前可设置OnSendListener监听，实现ui的更新。
 * 
 * </p>
 * 亦可主动调用sendInBack(),手动开支线程，完成请求！
 * 
 * @author kk0927
 *
 */
public class HttpPost {
	URL mUrl;
	final String BOUNDARY = "adss--ssad";
	private final StringBuilder mBuilder = new StringBuilder();

	private File sendFile;// 须传输的文件
	private String fileKey;// 传输文件的key
	private String fileName;// 文件名
	private String fileType;// 声明文件的类型

	/**
	 * 通过 url 直接获得HttpPost对象
	 * 
	 * @param url
	 */
	public HttpPost(URL url) {
		mUrl = url;

	};

	/**
	 * 通过 解析url地址获得一个HttpPost对象;
	 * 
	 * @param url
	 * @return HttpPost
	 * @throws MalformedURLException
	 *             url 不规范抛出
	 */
	public static HttpPost parseUrl(String url) throws MalformedURLException {
		URL u = new URL(url);
		return new HttpPost(u);
	}

	/**
	 * POST 方式传输的一组表单数据, key值不能为空，为空put无效！
	 * </p>
	 * 例：
	 * </p>
	 * post.putString("name","jack");
	 * 
	 * @param k
	 * @param v
	 */
	public void putString(String k, String v) {
		if (k == null || k.isEmpty()) {
			return;
		}
		mBuilder.append("--" + BOUNDARY + "\r\n");
		mBuilder.append("Content-Disposition: form-data; name=\"" + k + "\"");
		mBuilder.append("\r\n\r\n");
		mBuilder.append(v + "\r\n");
	}

	/**
	 * POST 方式传输的多组表单数据,key值不能为空，为空的那条传输无效！
	 * </p>
	 * Map<String, String> map=new HashMap();
	 * </p>
	 * map.put("name","jack");
	 * </p>
	 * map.put("password","123456");
	 * </p>
	 * post.putMap(map);
	 * 
	 * @param map
	 */
	public void putMap(Map<String, String> map) {
		if (map != null) {
			for (java.util.Map.Entry<String, String> set : map.entrySet()) {
				putString(set.getKey(), set.getValue());
			}
		}
	}

	/**
	 * 
	 * @param key
	 *            必须不为空，否则无效
	 * @param file
	 *            传输的文件,必须存在且不为空，否则无效
	 * @param newName
	 *            新名字，可为空
	 * @param type
	 *            传输的文件类型声明,注意格式，可为空,默认 image/jpeg;
	 */
	public void putFile(String key, File file, String newName, String type) {
		if (key != null && !key.isEmpty() && file != null && file.exists()) {
			fileKey = key;
			sendFile = file;
			if (newName == null || newName.isEmpty()) {
				fileName = file.getName();
			} else {
				fileName = newName;
			}
			if (type == null || type.isEmpty()) {
				fileType = "image/jpeg";
			} else {
				fileType = type;
			}
		}
	}

	/**
	 * 开始执行post请求,注意：不需开支线程请求！
	 * </p>
	 * 在发送之前可设置OnSendListener监听，在监听的end(String result)方法里实现ui的更新。
	 * 
	 */
	public void send() {
		if (mListener != null)
			mListener.start();
		new MyAyckTast().execute();

	}

	/**
	 * 开始执行发送，须在子线程操作！
	 * 
	 * @return -1》》io异常！-2》》请求异常
	 */
	public String sendInBack() {
		long dataLength = 0;
		try {
			byte end[] = ("--" + BOUNDARY + "--\r\n").getBytes("UTF-8");
			if (sendFile != null) {

				mBuilder.append("--" + BOUNDARY + "\r\n");
				mBuilder.append(
						"Content-Disposition: form-data; name=\"" + fileKey + "\"; filename=\"" + fileName + "\"\r\n");
				mBuilder.append("Content-Type: " + fileType + "\r\n\r\n");
				dataLength += sendFile.length();
				end = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("UTF-8");
			}

			dataLength = mBuilder.toString().length() + end.length + dataLength;

			HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			conn.setRequestProperty("Content-Length", String.valueOf(dataLength));
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.connect();
			OutputStream oStream = conn.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(oStream);
			bos.write(mBuilder.toString().getBytes("UTF-8"));
			bos.flush();
			InputStream in;
			if (sendFile != null) {
				in = new FileInputStream(sendFile);
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) != -1) {
					bos.write(buf, 0, len);
				}
				bos.flush();
				in.close();
			}
			bos.write(end);
			bos.flush();
			bos.close();
			oStream.close();
			StringBuilder result = new StringBuilder();
			if (conn.getResponseCode() == 200) {
				in = conn.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String line = reader.readLine();
				while ((line != null && !line.isEmpty())) {
					result.append(line);
					line = reader.readLine();
				}
				reader.close();
				in.close();
				return result.toString();
			}
			return "-2";
		} catch (IOException e) {
			return "-1";
		}
	}

	private OnSendListener mListener;

	/**
	 * 设置请求监听
	 * </p>
	 * 重写start() end()两个方法 实现ui更新
	 * 
	 * @param OnSendListener
	 */
	public void setOnSendListener(OnSendListener mListener) {
		this.mListener = mListener;
	}

	/**
	 * post网络请求监听
	 * 
	 * @author kk0927
	 *
	 */
	public static interface OnSendListener {
		/**
		 * 服务器请求开始的时候调用
		 */
		void start();

		/**
		 * 服务器请求结束的时候调用
		 * 
		 * @param result
		 *            -1》》io异常！-2》》请求异常
		 */
		void end(String result);
	}

	private class MyAyckTast extends AsyncTask<Void, Integer, String> {

		protected String doInBackground(Void... params) {

			return sendInBack();
		}

		protected void onPostExecute(String result) {
			if (mListener != null)
				mListener.end(result);
		}
	}
}
