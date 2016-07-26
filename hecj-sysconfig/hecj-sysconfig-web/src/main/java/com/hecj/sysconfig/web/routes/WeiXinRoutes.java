package com.hecj.sysconfig.web.routes;

import com.hecj.sysconfig.web.controller.PotentialController;
import com.hecj.sysconfig.web.controller.WeiXin2Controller;
import com.hecj.sysconfig.web.controller.WeiXinController;
import com.jfinal.config.Routes;

public class WeiXinRoutes extends Routes {

	@Override
	public void config() {
		
		add("/wx", WeiXinController.class);
		add("/wx/u", PotentialController.class);
		add("/wx2", WeiXin2Controller.class);
	}

}
