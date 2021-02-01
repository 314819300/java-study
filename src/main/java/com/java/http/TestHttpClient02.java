package com.java.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.formula.functions.T;
import org.jcp.xml.dsig.internal.dom.DOMDigestMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 此类用来测试使用 HttpClient 发送 post application/json 格式的数据
 * @author wangning
 * @create 2020-12-18 9:08
 */
public class TestHttpClient02 {
	public static void main(String[] args) {
		//1.直接写字符串
		String jsonString1 = "{\"pageNum\":1,\"pageSize\":10,\"query\":{}}";
		String url = "http://localhost:8066/aweb-api/user/list";

		JSONObject jsonObject = JSON.parseObject(jsonString1);
		String jsonString = JSON.toJSONString(jsonObject);
		System.out.println("jsonString = " + jsonString);
		//通过Map对象进行传递参数，转换成表单进行参数传递
		Map<String, Object> itemMap = JSONObject.toJavaObject(jsonObject,Map.class);
		System.out.println("itemMap = " + itemMap);
		Object object = new Object();
		Map<String, Object> map = new HashMap<>();
		map.put("pageNum",1);
		map.put("pageSize",10);
		map.put("query",object);
		JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));
		doJsonPost(url,String.valueOf(itemJSONObj));

	}

	public static void doJsonPost(String url, String jsonString) {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);
		HttpResponse httpResponse = null;
		httpPost.setHeader("Content-type", "application/json");
		httpPost.addHeader("X-CSRF-TOKEN", "cf458d74-909c-4225-8949-fc8eef7710c2");
//		RequestConfig builder = RequestConfig.custom().setConnectTimeout(30000).build();
		httpPost.setHeader("Accept", "application/json");
		StringEntity stringEntity = new StringEntity(jsonString, "UTF-8");
		httpPost.setEntity(stringEntity);
		try {
			httpResponse = httpClient.execute(httpPost);
			StatusLine statusLine = httpResponse.getStatusLine();
			System.out.println("statusLine = " + statusLine);

			HttpEntity entity = httpResponse.getEntity();
			String string = EntityUtils.toString(entity, "utf-8");
			JSONObject jsonObject = JSON.parseObject(string);
			System.out.println("jsonObject = " + jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
