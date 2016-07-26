package com.hecj.sysconfig.service.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.hecj.sysconfig.service.util.PropertiesUtil;
import com.jfinal.plugin.druid.DruidPlugin;

/**
 * 数据库配置
 * @author hechaojie
 */
public class DruidPluginConfig{
	
	public static DruidPlugin getDruidPlugin(){
		
		String url = PropertiesUtil.getProperty("msyql.jdbcUrl");
		String user = PropertiesUtil.getProperty("msyql.user");
		String password = PropertiesUtil.getProperty("msyql.password");
		
		DruidPlugin druidPlugin = new DruidPlugin(url,user,password);
		druidPlugin.addFilter(new StatFilter());
		WallFilter wall = new WallFilter();
		wall.setDbType("mysql");
		druidPlugin.addFilter(wall);
		
		return druidPlugin;
	}
	
}
