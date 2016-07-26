package com.hecj.sysconfig.service.config;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;

/**
 * 配置model
 * @author hechaojie
 */
public class ActiveRecordPluginConfig {

	public static ActiveRecordPlugin getActiveRecordPlugin(DruidPlugin druidPlugin){
		
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
		return activeRecordPlugin;
	}
}
