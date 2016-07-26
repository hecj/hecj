package com.hecj.sysconfig.web.controller;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import com.hecj.common.jfinal.controller.BaseController;
import com.hecj.sysconfig.core.model.Qq;
import com.hecj.sysconfig.core.service.QqService;
import com.hecj.sysconfig.web.intercept.TestIntercept;
import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.log.Logger;
import com.jfinal.plugin.spring.Inject.BY_NAME;
/**
 * @author hechaojie
 */
@Before(TestIntercept.class)
public class QqController extends BaseController {
	
	private Logger log = Logger.getLogger(QqController.class);
	
	@BY_NAME
	private QqService<Qq> qqService;
	
	@ClearInterceptor(ClearLayer.UPPER)
	@Before(GET.class)
	public void index(){
		log.info(" welcome QqController ");
		renderHtml("<center>QqController</center>");
	}
	
	public void save(){
		
		log.info(" save Qq ");
		try {
			Qq qq = new Qq();
			Long id = qqService.save(qq);
			log.info(" save Qq , SUCCESS , "+id);
		} catch (Exception e) {
			log.error("ERROR , " + e.getMessage());
			e.printStackTrace();
		}
		redirect("/qq/list");
	}
	
	public void delete(){
		log.info(" delete Qq ");
		try {
			Long id = getParaToLong("id");
			qqService.delete(id);
			log.info(" delete Qq , SUCCESS , "+id);
		} catch (Exception e) {
			log.error("ERROR , " + e.getMessage());
			e.printStackTrace();
		}
		redirect("/qq/list");
	}
	
	public void list(){
		log.info(" list Qq ");
		try {
			List<Qq> dataList = qqService.queryList();
			setAttr("dataList", dataList);
			log.info(" list Qq , SUCCESS , size : " + dataList.size());
		} catch (Exception e) {
			log.error("ERROR , " + e.getMessage());
			e.printStackTrace();
		}
		render("/WEB-INF/view/qq/list.ftl");
	}
	
	public void test01(){
		String sql="'1' or '1'='1'";
		System.out.println(sql);
		//防sql
		String test = StringEscapeUtils.escapeSql(sql);
		System.out.println(test);
		test = StringEscapeUtils.escapeSql(sql);
		System.out.println(test);
		test = StringEscapeUtils.escapeSql(sql);
		System.out.println(test);
		test = StringEscapeUtils.escapeSql(sql);
		System.out.println(test);
		System.out.println(StringEscapeUtils.escapeHtml(test));
		
		
	}
	
}
