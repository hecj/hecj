package com.hecj.sysconfig.web.routes;

import com.hecj.sysconfig.web.controller.TestController;
import com.jfinal.config.Routes;

public class TestRoutes extends Routes {

	@Override
	public void config() {
		
		add("/test", TestController.class);
	}

}
