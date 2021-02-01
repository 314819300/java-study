package com.java.util;

import java.util.UUID;

/**
 * @author wangning
 * @create 2021-01-11 15:44
 * 随机生成字符串，用于设定表的主键id
 */
public class IdGenerator01 {
	public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
			"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z"};

	public static void main(String[] args) {
		//生成唯一的UUID，可以作为主键
		System.out.println("UUID.randomUUID().toString() = " + UUID.randomUUID().toString().replace("-", "").length());
		System.out.println("UUID.randomUUID().toString() = " + UUID.randomUUID().toString().replace("-", ""));
//		System.out.println("UUID.randomUUID().toString() = " + UUID.randomUUID().toString().replace("", ""));
		//根据UUID转化为短8位UUID
		String shortUUID = getShortUUID();
		System.out.println("shortUUID = " + shortUUID);
	}

	/**
	 * 获取随机字符串
	 *
	 * @return 格式为 xxxx 的字符串
	 */
	public static String getShortUUID() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
	}
}
