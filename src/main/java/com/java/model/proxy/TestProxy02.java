package com.java.model.proxy;

/**
 * 动态代理：
 * @author wangning
 * @create 2020-12-01 10:06
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 目标接口，此接口提供了搜索服务的标准
 */
interface  SearchService {
    Object search(String key);
}

/**
 * 提供一种从文件中搜索内容的服务
 */
class FileSearchServiceImpl implements SearchService {

    /**
     * 那么如何基于OCP(开闭原则)对方法进行功能扩展，例如在原有的基础上增加搜索服务的时间，而又不用修改原来的类
     *
     * @param key
     * @return
     */
    @Override
    public Object search(String key) {
        //按照本来的想法，可以直接在此处添加,但是这样却不符合OCP，而且如果需要添加的类比较多，修改起来就会很麻烦
//        System.out.println("start = " + System.nanoTime());
        System.out.println("search " + key);
//        System.out.println("end = " + System.nanoTime());
        String result = "search " + key;
        return result;
    }
}

class DatabaseSearchServiceImpl implements SearchService {

    @Override
    public Object search(String key) {
        System.out.println("search from database");
        String result = "database search result " + key;
        return result;
    }
}

/**
 * 搜索服务工厂，用来创建代理对象
 */
class SearchServiceFactory {
    /**
     * 为目标创建代理对象
     * @param target 目标对象
     * @return 代理对象
     */
    public Object newProxyInstance(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new SearchProxyHandler(target));

    }
}

class SearchProxyHandler implements InvocationHandler {
    private Object target;

    public SearchProxyHandler(Object target) {
        this.target = target;
    }

    /**
     * 为目标创建代理对象
     * @param target
     * @return
     */
    public Object newProxyInstance(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//ClassLoader（为代理对象指定类加载器）
                target.getClass().getInterfaces(),//interfaces 代理对象要实现的接口
                this);// invocationHandler （处理器对象，负责帮代理对象处理业务）
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start == "+ System.nanoTime());
        Object invoke = method.invoke(target, args);
        System.out.println("end == "+ System.nanoTime());
        return invoke;
    }
}

public class TestProxy02 {
    public static void main(String[] args) {
//        doMethod01();
        doMethod02();

    }

    private static void doMethod02() {
        SearchService service = (SearchService) new SearchServiceFactory().newProxyInstance(new DatabaseSearchServiceImpl());
        Object result = service.search("tmooc");
        System.out.println("result = " + result);
    }

    private static void doMethod01() {
        //1.构建一个目标对象
        SearchService target = new FileSearchServiceImpl();
        //2.为目标创建一个代理对象
        //2.1创建代理工厂
        SearchServiceFactory factory = new SearchServiceFactory();
        //2.2创建代理对象
        SearchService proxy = (SearchService)factory.newProxyInstance(target);
        //2.3执行代理对象的方法
        String search = (String) proxy.search("tedu");
        System.out.println("search = " + search);
    }
}
