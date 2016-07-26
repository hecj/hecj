package com.hecj.sysconfig.web.controller;

import java.util.List;

import com.hecj.common.jfinal.controller.BaseController;
import com.hecj.sysconfig.core.model.Potential;
import com.hecj.sysconfig.core.service.PotentialService;
import com.jfinal.aop.Before;
import com.jfinal.log.Logger;
import com.jfinal.plugin.spring.Inject.BY_NAME;
import com.jfinal.plugin.spring.IocInterceptor;
/**
 * @author hechaojie
 */
@Before(IocInterceptor.class)
public class PotentialController extends BaseController {
	
	private Logger log = Logger.getLogger(PotentialController.class);
	
	@BY_NAME
	private PotentialService<Potential> potentialService;
	
	public void index(){
		log.info(" welcome PotentialController ");
		renderHtml("<center>PotentialController</center>");
	}
	
	public void save(){
		log.info(" save Potential ");
		try {
			Potential potential = new Potential();
			Long id = potentialService.save(potential);
			log.info(" save Potential , SUCCESS , "+id);
		} catch (Exception e) {
			log.error("ERROR , " + e.getMessage());
			e.printStackTrace();
		}
		redirect("/wx/u/list");
	}
	
	public void delete(){
		log.info(" delete Potential ");
		try {
			Long id = getParaToLong("id");
			potentialService.delete(id);
			log.info(" delete Potential , SUCCESS , "+id);
		} catch (Exception e) {
			log.error("ERROR , " + e.getMessage());
			e.printStackTrace();
		}
		redirect("/wx/u/list");
	}
	
	public void list(){
		log.info(" list Potential ");
		try {
			List<Potential> dataList = potentialService.queryList();
			setAttr("dataList", dataList);
			log.info(" list Potential , SUCCESS , size : " + dataList.size());
		} catch (Exception e) {
			log.error("ERROR , " + e.getMessage());
			e.printStackTrace();
		}
		render("/WEB-INF/view/potential/list.ftl");
	}
	
}
