package com.hecj.sysconfig.service.server;

import com.hecj.sysconfig.service.config.ActiveRecordPluginConfig;
import com.hecj.sysconfig.service.config.DruidPluginConfig;
import com.jfinal.log.Logger;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.spring.SpringPlugin;
/**
 * 服务启动类
 * @author hechaojie
 */
public class Server {
	
	private static Logger log = Logger.getLogger(Server.class);
	
	public static void main(String[] args) {
		
		DruidPlugin druidPlugin = DruidPluginConfig.getDruidPlugin();
		druidPlugin.start();
		
		ActiveRecordPluginConfig.getActiveRecordPlugin(druidPlugin).start();
		
		new SpringPlugin("classpath*:spring/applicationContext.xml").start();
		
		log.info(" Server start , SUCCESS ");
		
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			log.error("ERROR , Server start ");
			e.printStackTrace();
		}

	}

}
