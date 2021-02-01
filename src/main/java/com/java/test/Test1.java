package com.java.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangning
 * @create 2020-12-22 16:30
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
public class Test1 {
	public static void main(String[] args) {
		List<User> list = new ArrayList<>();
		User user = new User();
		for(int i = 0; i< 3;i++) {
			user.setId(1);
			user.setName("hhh");
			list.add(user);
		}
		System.out.println("list = " + list);
	}
}
