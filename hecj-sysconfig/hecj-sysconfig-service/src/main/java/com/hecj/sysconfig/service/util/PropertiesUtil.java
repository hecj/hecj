package com.hecj.sysconfig.service.util;

import com.jfinal.kit.Prop;

public class PropertiesUtil {
	
	private static String configName = "config.properties";
	
	private static Prop prop = null ;
	
	private synchronized static void initConfig(){
		
		prop = new Prop(configName);
	}
	
	public static String getProperty(String key){
		if (prop == null){
			initConfig();
		}
		return prop.get(key);
	}

}
