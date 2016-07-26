package com.hecj.code.jdbc.vo;

public class FormatUtil {
	
	/**
	 * 驼峰命名
	 */
	public static String humpName(String name){
		String[] names = name.split("_");
		if(names.length > 0){
			String newName = names[0];
			for(int i=1;i<names.length;i++){
				String ns = names[i];
				if(ns != null && ns.length()>0){
					newName += toFirstUpperCase(ns);
				}
			}
			return newName;
		}else{
			return name;
		}
	}
	
	/**
	 * 单词首字母大写
	 */
	public static String formatName(String name){
		String[] names = name.split("_");
		if(names.length > 0){
			String newName = toFirstUpperCase(names[0]);
			for(int i=1;i<names.length;i++){
				String ns = names[i];
				if(ns != null && ns.length()>0){
					newName += toFirstUpperCase(ns);
				}
			}
			return newName;
		}else{
			return toFirstUpperCase(name);
		}
	}
	
	/**
	 * 首字母大写
	 */
	private static String toFirstUpperCase(String s){
		return s.substring(0,1).toUpperCase()+s.substring(1, s.length());
	}
	
}
