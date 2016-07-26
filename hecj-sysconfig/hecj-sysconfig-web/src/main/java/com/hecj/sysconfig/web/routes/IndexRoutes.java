package com.hecj.sysconfig.web.routes;

import com.hecj.sysconfig.web.controller.Index2Controller;
import com.hecj.sysconfig.web.controller.IndexController;
import com.hecj.sysconfig.web.controller.QqController;
import com.jfinal.config.Routes;

public class IndexRoutes extends Routes {

	@Override
	public void config() {
		
		add("/", IndexController.class);
		add("/index", Index2Controller.class);
		add("/qq", QqController.class);
	}

}
