package com.hecj.pro.sysconfig.web.controller;

import java.util.List;

import com.hecj.pro.common.jfinal.controller.BaseController;
import ${basePackage}.model.Potential;
import ${basePackage}.service.PotentialService;
import com.jfinal.aop.Before;
import com.jfinal.log.Logger;
import com.jfinal.plugin.spring.Inject.BY_NAME;
import com.jfinal.plugin.spring.IocInterceptor;
/**
 * @author hechaojie
 */
@Before(IocInterceptor.class)
public class ${table.formatName}Controller extends BaseController {
	
	private Logger log = Logger.getLogger(${table.formatName}Controller.class);
	
	@BY_NAME
	private ${table.formatName}Service<${table.formatName}> ${table.formatName?uncap_first}Service;
	
	public void index(){
		log.info(" welcome ${table.formatName}Controller ");
		renderHtml("<center>${table.formatName}Controller</center>");
	}
	
	public void save(){
		log.info(" save ${table.formatName} ");
		try {
			${table.formatName} ${table.formatName?uncap_first} = new ${table.formatName}();
			Long id = ${table.formatName?uncap_first}Service.save(${table.formatName?uncap_first});
			log.info(" save ${table.formatName} , SUCCESS , "+id);
		} catch (Exception e) {
			log.error("ERROR , " + e.getMessage());
			e.printStackTrace();
		}
		redirect("/${table.formatName?uncap_first}/list");
	}
	
	public void delete(){
		log.info(" delete ${table.formatName} ");
		try {
			Long id = getParaToLong("id");
			${table.formatName?uncap_first}Service.delete(id);
			log.info(" delete ${table.formatName} , SUCCESS , "+id);
		} catch (Exception e) {
			log.error("ERROR , " + e.getMessage());
			e.printStackTrace();
		}
		redirect("/${table.formatName?uncap_first}/list");
	}
	
	public void list(){
		log.info(" list ${table.formatName} ");
		try {
			List<${table.formatName}> dataList = ${table.formatName?uncap_first}Service.queryList();
			setAttr("dataList", dataList);
			log.info(" list ${table.formatName} , SUCCESS , size : " + dataList.size());
		} catch (Exception e) {
			log.error("ERROR , " + e.getMessage());
			e.printStackTrace();
		}
		render("/WEB-INF/view/${table.formatName?uncap_first}/list.ftl");
	}
	
}
