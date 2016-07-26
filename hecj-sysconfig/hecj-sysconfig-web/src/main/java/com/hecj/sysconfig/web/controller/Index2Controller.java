package com.hecj.sysconfig.web.controller;

import java.util.ArrayList;
import java.util.List;

import com.hecj.common.exception.ServiceException;
import com.hecj.common.jfinal.controller.BaseController;
import com.hecj.sysconfig.core.model.PortConfig;
import com.hecj.sysconfig.core.service.PortConfigService;
import com.jfinal.aop.Before;
import com.jfinal.log.Logger;
import com.jfinal.plugin.spring.Inject.BY_NAME;
import com.jfinal.plugin.spring.IocInterceptor;

@Before(IocInterceptor.class)
public class Index2Controller extends BaseController {
	
	private Logger log = Logger.getLogger(Index2Controller.class);
	
	@BY_NAME
	private PortConfigService<PortConfig> portConfigService;
	public void index(){
		log.info(" welcome index.html ");
		renderHtml("<html><head><title>系统配置</title></head><body><center><br><a href=\"/port\">端口分配中心</a></center></body></html>");
	}
	
	public void port(){
		
		log.info("query portConfig index");
		try {
			List<PortConfig> pList = portConfigService.queryList();
			setAttr("pList", pList);
			log.info("query portConfig , SUCCESS");
		} catch (Exception e) {
			log.error("ERROR , " + e.getMessage());
			e.printStackTrace();
		}
		render("/WEB-INF/view/index/port.ftl");
	}
	
	public void save(){
		try {
			
			PortConfig portConfig  = (PortConfig) getBaseModel(PortConfig.class);
			portConfig.put(PortConfig.name, "我是一个端口哇");
			portConfigService.save(portConfig);
			
		} catch (Exception e) {
			log.error("ERROR , " + e.getMessage());
			e.printStackTrace();
		}
		redirect("/port");
	}
	
	public void save2(){
		try {
			
			PortConfig portConfig  = (PortConfig) getBaseModel(PortConfig.class,"p");
			portConfig.put(PortConfig.name, "我是新的拉的 ");	
			portConfigService.save(portConfig);
			
		} catch (Exception e) {
			log.error("ERROR , " + e.getMessage());
			e.printStackTrace();
		}
		redirect("/port");
	}
	
	public void save3(){
		try {
			
			PortConfig model = new PortConfig();
			model.put(PortConfig.name, "我的一个住");
			portConfigService.save(model);
			
		} catch (Exception e) {
			log.error("ERROR , " + e.getMessage());
			e.printStackTrace();
		}
		redirect("/port");
	}
	
	public void delete(){
		
		try {
			portConfigService.delete(getParaToLong("id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		redirect("/port");
	}
	
	public void delete2(){
		
		try {
			List<Long> ids = new ArrayList();
			ids.add(30l);
			ids.add(29l);
			portConfigService.deleteList(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		redirect("/port");
	}
	
}
