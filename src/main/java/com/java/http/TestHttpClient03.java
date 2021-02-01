package com.java.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author wangning
 * @create 2020-12-18 16:48
 */
public class TestHttpClient03 {
	public static void main(String[] args) {
		String url = "http://localhost:8066/aweb-api/user/list";
		doGet(url);
		//如果get方式带参数
		StringBuffer params = new StringBuffer();
		try {
			// 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
			params.append("name=" + URLEncoder.encode("&", "utf-8"));
			params.append("&");
			params.append("age=24");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//然后就可以使用以下作为发送get含参数的通信
		String urlParams = url+ "?" +params;
	}

	public static void doGet(String url) {
		// 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			HttpEntity responseEntity = response.getEntity();
			String string = EntityUtils.toString(responseEntity);
			JSONObject jsonObject = JSON.parseObject(string);
			System.out.println("jsonObject = " + jsonObject);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
