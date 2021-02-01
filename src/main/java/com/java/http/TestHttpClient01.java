package com.java.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HttpClient
 * 本测试类用来测试使用 HTTPClient 发送 post 表单格式的请求
 * @author wangning
 * @create 2020-12-17 9:59
 */
public class TestHttpClient01 {
	public static void main(String[] args) {
		//1.表单提交方式一，使用BasicNameValuePair
		List<BasicNameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("username", "admin"));
		params.add(new BasicNameValuePair("password", "T8sQroZoUOrKUuauPZAt6g=="));
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, StandardCharsets.UTF_8);

		//2.表单提交方式二，自己拼接表单格式的数据
//		Map<String, String> map = new HashMap<>();
//		map.put("username", "admin");
//		map.put("password", "T8sQroZoUOrKUuauPZAt6g==");
//
//		StringBuilder formString = new StringBuilder();
//		if (map.size() > 0) {
//			for (Map.Entry<String, String> entry : map.entrySet()) {
//				formString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
//			}
//			formString.substring(0, formString.length() - 1);
//		}

		String url = "http://localhost:8066/aweb-api/login";
//		JSONObject jsonObject = doFormPost(url, String.valueOf(formString));
		JSONObject jsonObject = doFormPost(url, formEntity);
		System.out.println("main--jsonObject = " + jsonObject);

	}

	/**
	 * 使用post进行表单提交
	 *
	 * @param url        路径
	 *
	 * @return JSONObject
	 */
	public static JSONObject doFormPost(String url, UrlEncodedFormEntity formEntity) {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		CloseableHttpResponse httpResponse = null;
//		System.out.println("jsonString = " + jsonString);
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig =
				RequestConfig.custom().setConnectTimeout(30000)
						.setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
		httpPost.setConfig(requestConfig);
//		addHeader：添加一个新的请求头字段。（一个请求头中允许有重名字段。）
//		setHeader：设置一个请求头字段，有则覆盖，无则添加。
		//这种通过new BasicHeader() 的方式与直接setHeader的区别
//		httpPost.setHeader(new BasicHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8"));
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
		httpPost.setHeader("Accept", "application/json");
//		httpPost.setEntity(new StringEntity(jsonString, StandardCharsets.UTF_8));
		httpPost.setEntity(formEntity);
		try {
			httpResponse = httpClient.execute(httpPost);
			httpResponse.getEntity().getContent();
			StatusLine statusLine = httpResponse.getStatusLine();
			System.out.println("statusLine = " + statusLine);
			//处理返回参数的方式
			//方式二：自己使用流进行处理
			{
//				BufferedReader in = null;
//				in = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
//				StringBuffer stringBuffer = new StringBuffer();
//				String line = "";
			/*
			首先知道System为系统的方法，我们最早和它打交道应该是在System.out.println(); 系统的输入输出流；这里用到的是通过其能获得系统的环境变量。
			line.separator 行分隔符(换行符) 那么其与‘\n’ 有什么区别呢。系统的环境变量，那么系统就有肯能有差别 一般的为Window 下和Unix下其所表示意义就会不同。
			这样写的话屏蔽了 Windows和Linux的区别，则剔除了平台无关性，写一次代码跑通在Linux上和Window上都能够运行。
			这点对编程也是个启示，尽量不去写死某个东西，如果那个东西会发生变化(如这里是操作系统的变化)。
			 */
//				String NL = System.getProperty("line.separator");//行分隔符(换行符)
//				while ((line = in.readLine()) != null) {
//					stringBuffer.append(line + NL);
//				}
//				in.close();
//				System.out.println(stringBuffer.toString());
//				return JSON.parseObject(stringBuffer.toString());
			}

			//方式一：使用EntityUtils
			if (statusLine.getStatusCode() == 200) {
				HttpEntity entity = httpResponse.getEntity();
				String string = EntityUtils.toString(entity, "utf-8");
				System.out.println("string = " + string);
				JSONObject jsonObject = JSON.parseObject(string);
				System.out.println("jsonObject = " + jsonObject);
				System.out.println("jsonObject.getString(\"msg\") = " + jsonObject.getString("msg"));
				return JSON.parseObject(string);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != httpResponse) {
				try {
					httpResponse.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 表单字符串转化成 hashMap
	 * @param orderinfo 表单字符
	 * @return HashMap<String, String>
	 */
	public static HashMap<String, String> form2Map(String orderinfo) {
		String[] listinfo;
		HashMap<String, String> map = new HashMap<>();
		listinfo = orderinfo.split("&");
		for (String s : listinfo) {
			String[] list = s.split("=");
			if (list.length > 1) {
				map.put(list[0], list[1]);
			}
		}
		return map;
	}

	/**
	 * hashMap 转化成表单字符串
	 * serialnum=123456&data=357c0a04f&enable=true&key=b5b806d0dc9
	 *
	 * @param map map
	 * @return null
	 */
	public static String map2Form(HashMap<String, String> map) {
		StringBuilder stringBuilder = new StringBuilder();
		if (map == null) {
			return stringBuilder.toString();
		} else {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
			return stringBuilder.substring(0, stringBuilder.length() - 1);
		}
	}


}
