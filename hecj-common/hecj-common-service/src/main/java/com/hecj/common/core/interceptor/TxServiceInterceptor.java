package com.hecj.common.core.interceptor;
import java.lang.reflect.Method;
import java.sql.SQLException;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.hecj.common.core.tx.TxService;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;

public class TxServiceInterceptor implements MethodInterceptor {
     
    private final Logger log = Logger.getLogger(TxServiceInterceptor.class);
 
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        
    	Object target = invocation.getThis();
        Method targetMethod = invocation.getMethod();
        Class<?>[] targetMethodParamTypes = targetMethod.getParameterTypes();
        Method method = target.getClass().getMethod(targetMethod.getName(),targetMethodParamTypes);
        if (method.isAnnotationPresent(TxService.class)) { // 如果存在事务的话，需要使用到jfinal提供的事务支持。
        	log.info(" -- 已开启事务拦截，位置： "+target.getClass().getName()+"."+targetMethod.getName()+"() --");
            TxInvoke invoke = new TxInvoke(invocation);
            Db.tx(invoke);
            return invoke.getResult();
        }
        return invocation.proceed(); // 如果没有事务的话，直接调用方法
    }
 
    private class TxInvoke implements IAtom {
        
    	private MethodInvocation invocation;
        
    	private Object result = null;
        
        public TxInvoke(MethodInvocation invocation) {
            this.invocation = invocation;
        }
        
        @Override
        public boolean run() throws SQLException {
            try {
                result = invocation.proceed();
                log.info(" -- 事务提交成功 --");
                return true; // 说明调用成功
            } catch (Throwable e) {
                log.error(" -- 事务处理失败，已回滚 --",e);
            } 
            return false;
        }
        
        public Object getResult() {
            return result;
        }
    }
}