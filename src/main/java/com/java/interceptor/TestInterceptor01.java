package com.java.interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangning
 * @create 2020-12-21 8:59
 * 此测试用来模拟spring中的拦截器
 *
 */
interface Interceptor {
	void preHandle();
	void postHandle();
}

class InterceptorChain {
	public List<Interceptor> interceptorChain = new ArrayList<>();

	public void add(Interceptor interceptor) {
		interceptorChain.add(interceptor);
	}

	public List<Interceptor> getInterceptorChain() {
		return interceptorChain;
	}

	public void executePreHandle() {
		for(int i = 0;i<interceptorChain.size();i++) {
			Interceptor interceptor = interceptorChain.get(i);
			interceptor.preHandle();
		}
	}

	public void executePostHandle() {
		for (int i = interceptorChain.size()-1; i >= 0 ; i--) {
			Interceptor interceptor = interceptorChain.get(i);
			interceptor.postHandle();
		}
	}

}

class SearchController {
	public void search() {
		System.out.println("search ...");
	}
}

class DispatcherServlet {
	//此处为了方便，就直接使用构造方法进行调用，其实spring底层使用反射进行调用的
	private InterceptorChain interceptorChain;
	private SearchController searchController;
	public DispatcherServlet(InterceptorChain interceptorChain,SearchController searchController) {
		this.interceptorChain = interceptorChain;
		this.searchController = searchController;
	}
	public void handle() {
		interceptorChain.executePreHandle();
		searchController.search();
		interceptorChain.executePostHandle();
	}

}

class TimeInterceptor implements Interceptor {

	@Override
	public void preHandle() {
		System.out.println("time pre");
	}

	@Override
	public void postHandle() {
		System.out.println("time post");
	}
}

class LogInterceptor implements Interceptor {
	@Override
	public void preHandle() {
		System.out.println("log pre");
	}

	@Override
	public void postHandle() {
		System.out.println("log post");
	}
}

public class TestInterceptor01 {
	public static void main(String[] args) {
		InterceptorChain interceptorChain = new InterceptorChain();
		SearchController searchController = new SearchController();
		TimeInterceptor timeInterceptor = new TimeInterceptor();
		LogInterceptor logInterceptor = new LogInterceptor();
		interceptorChain.add(timeInterceptor);
		interceptorChain.add(logInterceptor);
		System.out.println("interceptorChain = " + interceptorChain);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(interceptorChain, searchController);
		dispatcherServlet.handle();
	}
}
