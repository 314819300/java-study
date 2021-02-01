package com.java.test;

import org.junit.Test;
import sun.security.provider.DSAPublicKeyImpl;

import java.sql.SQLOutput;

/**
 * 
 * @author wangning
 * @create 2020-11-30 17:25
 */
public class TestSpeed {
    @Test
    public void run() {
        long t1 = System.nanoTime();
        StringBuffer stringBuffer = new StringBuffer();
        String str1 = "a";
        String str2 = "b";
        for (int i = 0; i < 10000000 ; i++) {
            stringBuffer.append(str1);
            stringBuffer.append(str2);
        }
        long t2 = System.nanoTime();
        System.out.println("t = " + (t2-t1));
    }
    @Test
    public void run1() {
        long t1 = System.nanoTime();
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < 10000000 ; i++) {
            String str1 = "a";
            String str2 = "b";
            stringBuffer.append(str1);
            stringBuffer.append(str2);
        }
        long t2 = System.nanoTime();
        System.out.println("tt = " + (t2-t1));
    }
}
