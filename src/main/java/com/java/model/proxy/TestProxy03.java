package com.java.model.proxy;

/**
 * @author wangning
 * @create 2020-12-01 15:55
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 搜索服务的数据层对象
 * 动态代理的一个条件，你代理的那个目标对象必须有一个接口
 */
interface SearchDao {
    Object search(String key);
}

/**
 * 模拟mybatis中的sqlsession
 */
interface SqlSession {
    <T> T getMapper(Class<T> targetClass);
}


/**
 * 模拟SqlSession对象的实现
 */
class DefaultSqlSession implements SqlSession {
    //创建接口类型的代理对象
    @Override
    public <T> T getMapper(Class<T> targetClass) {
        //此方法中如何产生一个代理对象
        //1.创建代理工厂对象
        DaoProxyFactory factory = new DaoProxyFactory();
        //2.基于工厂创建代理对象
        return factory.newInstance(targetClass, this);
    }
}

/**
 * 产生代理对象的工厂
 */
class DaoProxyFactory {
    //创建代理对象
    @SuppressWarnings("unchecked")
    public <T> T newInstance(Class<T> targetClass, SqlSession session) {

        return (T) Proxy.newProxyInstance(
                targetClass.getClassLoader(),
                new Class[]{targetClass},
                new DaoProxyHandler(targetClass, session)
        );
    }
}

/**
 * 代理对象处理器（负责处理具体业务）
 */
class DaoProxyHandler implements InvocationHandler {

    private Class<?> targetClass;
    private SqlSession session;

    public DaoProxyHandler(Class<?> targetClass, SqlSession session) {
        this.targetClass = targetClass;
        this.session = session;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        SqlSession.selectList()...
        System.out.println("invoke==>" + method.getName());
        String namespace = targetClass.getName();
        String elementId = method.getName();
        String statement = namespace + "." + elementId;
        System.out.println("statement = " + statement);
        //        SqlSession.selectList(statemnrt,args)...
        return statement;
    }
}

public class TestProxy03 {
    public static void main(String[] args) {
        SqlSession session = new DefaultSqlSession();
        SearchDao dao = session.getMapper(SearchDao.class);
        System.out.println(dao.getClass().getName());
        dao.search("tedu");
    }
}
