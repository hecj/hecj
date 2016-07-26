package com.hecj.sysconfig.web.controller;

import com.hecj.common.jfinal.controller.BaseController;
import com.jfinal.log.Logger;

public class IndexController extends BaseController {
	
	private Logger log = Logger.getLogger(IndexController.class);
	
	public void index(){
		log.info(" welcome index.html ");
		renderJsp("/WEB-INF/view/index.jsp");
	}
}
	
