package com.hecj.sysconfig.web.controller;


import com.hecj.common.jfinal.controller.BaseController;
import com.jfinal.aop.Before;
import com.jfinal.log.Logger;
import com.jfinal.plugin.spring.IocInterceptor;

@Before(IocInterceptor.class)
public class TestController extends BaseController {
	
	private Logger log = Logger.getLogger(TestController.class);
	static int n = 0 ;
	public void index(){
		System.out.println("n:"+(n++));
		log.info(" welcome index.html ");
		renderHtml("<html><head><title>index</title></head><body><center><br><a href=\"/port\">index:"+n+"</a></center></body></html>");
	}
	public void test(){
		renderHtml("<html><head><title>test</title></head><body><center><br><a href=\"/port\">test</a></center></body></html>");
	}
	
}
