package com.hecj.sysconfig.web.config;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hecj.sysconfig.web.common.Const;
import com.hecj.sysconfig.web.intercept.LoginIntercept;
import com.hecj.sysconfig.web.routes.IndexRoutes;
import com.hecj.sysconfig.web.routes.TestRoutes;
import com.hecj.sysconfig.web.routes.WeiXinRoutes;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.log.Logger;
import com.jfinal.plugin.spring.SpringPlugin;
import com.jfinal.render.ViewType;
/**
* @ClassName: CommonJFinalConfig
* @Description: JFinal基础配置
* @author hecj
 */
public class CommonJFinalConfig extends JFinalConfig {
	
	private Logger log = Logger.getLogger(CommonJFinalConfig.class);
	
	@Override
	public void configConstant(Constants me) {
		log.info("---------系统常量配置----------");
		
		me.setFreeMarkerTemplateUpdateDelay(5);
		
		me.setViewType(ViewType.FREE_MARKER);
		me.setError404View("/WEB-INF/view/common/404.html");
		me.setError500View("/WEB-INF/view/common/500.html");
		me.setBaseViewPath("/WEB-INF/wiew");
		
		loadPropertyFile("const.properties");
		
		me.setDevMode(getPropertyToBoolean("devMode", false));
	}

	@Override
	public void configRoute(Routes me) {
		log.info("---------加载路由----------");
		// 首页路由
		me.add(new IndexRoutes());
		// 微信路由
		me.add(new WeiXinRoutes());
		// 测试路由
		me.add(new TestRoutes());
			
	}

	@Override
	public void configPlugin(Plugins me) {
		log.info("---------加载插件----------");
		// 加载spring插件
		String springConfigDir = "classpath:*applicationContext.xml";
		me.add(new SpringPlugin(new FileSystemXmlApplicationContext(new String[]{springConfigDir})));
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new LoginIntercept());
	}

	@Override
	public void configHandler(Handlers me) {
		 me.add(new ContextPathHandler());
	}

	@Override
	public void afterJFinalStart() {
		super.afterJFinalStart();
		log.info("---------初始化常量----BEGIN------");
		Const.appID = getProperty("appID");
		Const.appsecret = getProperty("appsecret");
		Const.oauth2Back = getProperty("oauth2Back");
		log.info("---------初始化常量----END------");
	}
}
