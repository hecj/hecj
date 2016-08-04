package com.hecj.common.util.patten;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PattenUtils {
	
	/**
	 * 匹配元素
	 * 只匹配一个元素
	 */
	public static String pattenUniqueContent(String content,String regex){
		if(content==null||content.equals("")){
			return "";
		}
		String group="";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(content);
		while(m.find()){
			group=m.group();
		}
		return group;
	}
	
	/**
	 * 描述：是否邮箱
	 * @author: hecj
	 */
	public static boolean isEmail(String email){
		String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		if(m.find()){
			return true;
		}
		return false;
	}
}