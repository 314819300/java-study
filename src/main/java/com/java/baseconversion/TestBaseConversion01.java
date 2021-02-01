package com.java.baseconversion;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 进制的转换: 字节转换成16进制
 * 使用jdk自带的MessageDigest(消息摘要+MD5)进行加密
 * @author wangning
 * @create 2020-11-30 15:58
 */
public class TestBaseConversion01 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String s = "123456";
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(s.getBytes());
        for (int i = 0; i < bytes.length; i++) {
            String hexstr = Integer.toHexString(0xFF&bytes[i]);
            if(hexstr.length() == 1) {
                hexstr = "0" + hexstr;
            }
            System.out.print(hexstr);
        }
    }

}
