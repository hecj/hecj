package com.hecj.code.gen.config;

import java.io.IOException;
import java.util.Properties;

import com.hecj.common.utils.PropertiesUtil;

public class GenConfig {
	
	public static String baseDirJava = "";

	public static String baseDirResources = "";
	
	public static String basePackage = "";
	
	static{
		try {
			Properties prop = PropertiesUtil.getProperties(GenConfig.class, "config.properties");
			baseDirJava = prop.getProperty("baseDirJava");
			baseDirResources = prop.getProperty("baseDirResources");
			basePackage = prop.getProperty("basePackage");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
