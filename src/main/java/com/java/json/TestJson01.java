package com.java.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * @author wangning
 * @create 2020-12-19 14:39
 *
 * Gson 的使用
 * Gson是目前功能最全的Json解析神器，Gson的应用主要为toJson与fromJson两个转换函数
 * 而在使用这种对象转换之前需先创建好对象的类型以及其成员才能成功的将JSON字符串成功转换成相对应的对象。
 * 类里面只要有get和set方法，Gson完全可以将复杂类型的json到bean或bean到json的转换，是JSON解析的神器。
 * Gson在功能上面无可挑剔，但是性能上面比FastJson有所差距。
 */
class User {
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
public class TestJson01 {
	public static void main(String[] args) {
		//1.对象转json
		Gson gson = new Gson();
		User user = new User();
		user.setId(1);
		user.setName("tomcat");
		String json = gson.toJson(user);
		System.out.println("json = " + json);

		//2.json转对象
		String json1 = "{\"id\":2,\"name\":\"Json技术\"}";
		User user1 = gson.fromJson(json1, User.class);
		System.out.println("user1 = " + user1.toString());
		//3.复杂的
		// 将json转换成复杂类型的bean,需要使用TypeToken
		// Gson gson = new Gson();
		// String json = "[{\"id\":\"1\",\"name\":\"Json技术\"},{\"id\":\"2\",\"name\":\"java技术\"}]";
		// 将json转换成List
		// List list = gson.fromJson(json, new TypeToken<List>() {}.getType());
		// 将json转换成Set
		// Set set = gson.fromJson(json, new TypeToken<Set>() {}.getType());

		//4.通过json对象直接操作json以及一些json的工具
		// GsonBuilder gsonBuilder = new GsonBuilder();
		// ResponseData responseData = gsonBuilder.create().fromJson(result, ResponseData.class);
	}
}
