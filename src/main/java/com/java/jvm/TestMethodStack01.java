package com.java.jvm;

import java.util.Arrays;

/**
 * 方法栈
 * Exception in thread "main" java.lang.RuntimeException:
 * 	at com.java.jvm.TestMethodStack01.doMethod02(TestMethodStack01.java:12)
 * 	at com.java.jvm.TestMethodStack01.doMethod01(TestMethodStack01.java:16)
 * 	at com.java.jvm.TestMethodStack01.main(TestMethodStack01.java:20)
 * 	公有的是方法区和堆
 * 每个栈里面的元素称为栈帧（stack frame），栈帧中有方法名,变量，方法参数信息，返回值等方法信息，所以称为
 * 方法栈，方法栈又是线程私有的内存空间，线程结束了也就没有了，
 * 所以一般情况下，GC垃圾回收一般回收的是堆中的垃圾
 * @author wangning
 * @create 2020-12-08 21:34
 */
public class TestMethodStack01 {
    static void doMethod02() {
        System.out.println("doMethod02");
//        throw new RuntimeException("");
        Thread thread = Thread.currentThread();
        StackTraceElement[] stackTrace = thread.getStackTrace();
        System.out.println("stackTrace = " + Arrays.toString(stackTrace));
    }
    static void doMethod01() {
        System.out.println("doMethod01");
        doMethod02();
    }

    public static void main(String[] args) {
        doMethod01();
    }
}
