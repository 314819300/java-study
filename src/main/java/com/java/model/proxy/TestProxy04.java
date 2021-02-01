package com.java.model.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 基于CGLIB实现动态代理
 * @author wangning
 * @create 2020-12-08 15:40
 */

/**
 * 目标对象，没有实现任何接口
 */
class User {
    public void save() {
        System.out.println("已经保存了数据...");
    }
}

/**
 *  CGLIB子类代理工厂
 *  对UserDao在内存中动态构建一个子类对象
 */
class ProxyFactory implements MethodInterceptor {

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        //1.使用工具类
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(target.getClass());
        //3.设置回掉函数
        enhancer.setCallback(this);
        //4.创建子类（代理对象）
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务...");
        Object invoke = method.invoke(target, args);
        System.out.println("提交事务...");
        return invoke;
    }

}
public class TestProxy04 {
    public static void main(String[] args) {
        //目标对象
        User target = new User();
        //代理对象
        User user = (User)new ProxyFactory(target).getProxyInstance();
        //执行代理对象的方法
        user.save();
    }
}
