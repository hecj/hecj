package com.hecj.common.util.http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpUrlUtil {
	
	/**
	 * 根据url获取网站host
	 */
	public static String getHost(String url){
		Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
		Matcher m = p.matcher(url);
		if(m.find()){
		      return m.group();
		}
		return url;
	}
	
	/**
	 * 根据url获取网址
	 */
	public static String getRealmName(String url){
		Pattern p = Pattern.compile("^([A-Za-z]+)\\://.((\\w)+\\.)+\\w+");
		Matcher m = p.matcher(url);
		if(m.find()){
		      return m.group();
		}
		return url;
	}
	
	 /** 
     *正则匹配 
     * @param s 
     * @param pattern 
     * @return 
     */  
    public boolean matcher(String s, String pattern) {  
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE);  
        Matcher matcher = p.matcher(s);  
        if (matcher.find()) {  
            return true;  
        } else {  
            return false;  
        }  
    }
    
    /** 
     * 网页编码
     */  
    public static String getCharSet(String contentType){  
        String regex = ".*[c|C][h|H][a|A][r|R][s|S][e|E][t|T]=([^;]*).*";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(contentType);  
        if(matcher.find()) {
            return matcher.group(1);  
        } else{
        	return null;
        }  
    }
    
    /**
     * 通过jsoup获取编码格式
     */
//    public static String getCharSetByHtml(String html){
//    	return Jsoup.parse(html).charset().toString();
//    }
	
	public static void main(String[] args) {
//		System.out.println("http://hchaojie.com?fdsf".split("//?"));
//		System.out.println(getRealmName("http://hchaojie.com?fdsf"));
//		System.out.println(getHost("http://hchaojie.com?fdsf"));
		
		String s = "http://121sd.www.topic.csdn.net/dd?1= http://www.topic.csdn.nes";
		Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
		Matcher m = p.matcher(s);
		if(m.find()){
		      System.out.println(m.group());
		}
		
		Pattern p2 = Pattern.compile("^([A-Za-z]+)\\://.((\\w)+\\.)+\\w+");
		Matcher m2 = p2.matcher(s);
		if(m2.find()){
		      System.out.println(m2.group());
		}
	}
}
