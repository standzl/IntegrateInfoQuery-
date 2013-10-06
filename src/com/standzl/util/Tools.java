package com.standzl.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Tools {

	private static Tools tools = null;

	private Tools() {
	}

	public static Tools getInstance() {
		if (tools == null) {
			tools = new Tools();
		}
		return tools;
	}

	/**
	 * 向指定URL发送POST请求并读取响应文本
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	public String sendRequestAsPost(URL url, Map<String, String> param) {
		String resultText = null;
		PrintWriter out = null;
		try {
			URLConnection con = url.openConnection();
			con.setConnectTimeout(1000 * 50);
			setConnectionAttr(con);
			con.setDoInput(true);
			con.setDoOutput(true);
			// 发送POST参数
			StringBuffer paramStr = new StringBuffer();
			for (Map.Entry<String, String> entry : param.entrySet()) {
				paramStr.append(entry.getKey() + "=" + entry.getValue() + "&");
			}
			if (paramStr.length() > 1) {
				paramStr.deleteCharAt(paramStr.length() - 1);
			}
			out = new PrintWriter(new OutputStreamWriter(con.getOutputStream()));
			out.write(paramStr.toString());
			out.flush();
			resultText = readTextFromStream(con.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			out = null;
		}
		return resultText;
	}

	/**
	 * 向指定URL发送get请求，并返回响应文本
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	public String sendRequestAsGet(URL url) {
		String resultText = null;
		try {
			URLConnection con = url.openConnection();
			con.setConnectTimeout(1000 * 10);
			setConnectionAttr(con);
			con.setDoInput(true);
			resultText = readTextFromStream(con.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultText;
	}

	private String readTextFromStream(InputStream ins) {
		StringBuffer sb = new StringBuffer();
		String tempStr;
		try {
			BufferedReader bufferReader = new BufferedReader(
					new InputStreamReader(ins));
			while ((tempStr = bufferReader.readLine()) != null) {
				sb.append(tempStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ins = null;
			}
		}
		return sb.toString();
	}

	private void setConnectionAttr(URLConnection con) {
		con.setRequestProperty("Accept", Constant.HTTP_HEAD_ACCEPT);
		con.setRequestProperty("Accept-Encoding",
				Constant.HTTP_HEAD_ACCEPT_ENCODING);
		con.setRequestProperty("Accept-Language",
				Constant.HTTP_HEAD_ACCEPT_LANGUAGE);
		con.setRequestProperty("Connection", Constant.HTTP_HEAD_CONNECTION);
		con.setRequestProperty("Host", Constant.HTTP_HEAD_HOSTADDR);
		con.setRequestProperty("Referer", Constant.HTTP_HEAD_REFERER);
		con.setRequestProperty("User-Agent", Constant.HTTP_HEAD_USER_AGENT);
	}

	public boolean isEmpty(String text) {
		if (text == null)
			return true;
		if ("".equals(text))
			return true;
		return false;
	}

	public String filterStr(String str) {
		if(str==null||"".equals(str))
				return "";
		if (str.contains("&nbsp;")) {
			str = str.replaceAll("&nbsp;", "");
		}
		Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = pattern.matcher(str);
		str = m.replaceAll("");
		return str;
	}
	
	/**
	 * 检查网络状态,如果有网络，返回true，否则返回false
	 * @param context
	 * @return
	 */
	public boolean checkNetworkStatus(Context context){
			ConnectivityManager connectManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo[] nets=connectManager.getAllNetworkInfo();
			if(nets==null || nets.length==0)
				return false;
			for(NetworkInfo info:nets){
					if(info.isConnected()){
						return true;
					}
			}
			return false;
	}
}
