package com.java.model.proxy;

/**
 * 静态代理：
 * 优点：可以做到在符合开闭原则的情况下对目标对象进行功能扩展。
 * 缺点：我们得为每一个服务都得创建代理类，工作量太大，不易管理。同时接口一旦发生改变，代理类也得相应修改。
 * @author wangning
 * @create 2020-12-01 10:06
 */

/**
 * 创建一个服务类接口
 */
interface Student {
    Object search(String key);
}

/**
 * 实现服务接口
 */
class StudentImpl implements Student {

    @Override
    public Object search(String key) {
        System.out.println("正在查询中...");
        String result = "search" + key;
        return result;
    }
}

/**
 * 创建代理类并实现接口
 * 代理对象（Student_proxy）的目的是帮助核心对象（StudentDao）做非核心的事情。
 * 但是代理对象（Student_proxy）必须和核心对象（StudentDao）实现共同的接口。
 */
class StudentProxy implements Student {

    //定义一个 SearchService 接口 作为属性，目的是就是在处理完代理需要做的事情之后调用 SearchService 需要做的核心业务，
    //但不是代理具体去做这些核心的事情，只是调用它们而已。
    private Student student;

    //创建一个代理的参数为 SearchService 接口的构造函数
    public StudentProxy(Student student) {
        this.student = student;

    }

    @Override
    public Object search(String key) {
        System.out.println("start = " + System.nanoTime());
        System.out.println("扩展业务");
        String result = (String) student.search(key);
        System.out.println("end = " + System.nanoTime());
        return result;
    }
}

/**
 * 编写测试类
 */
public class TestProxy01 {
    public static void main(String[] args) {
        //创建一个代理对象
        Student service = new StudentProxy(new StudentImpl());
        //通过这个代理对象执行相关的方法（代理对象和核心对象有共同接口）
        service.search("study");
    }
}
