package com.hecj.sysconfig.web.intercept;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
/**
 * 登录拦截器
 * @author HECJ
 */
public class TestIntercept implements Interceptor {

	@Override
	public void intercept(ActionInvocation ai) {
		System.out.println("我是TestIntercept=============");
		ai.invoke();
	}

}
