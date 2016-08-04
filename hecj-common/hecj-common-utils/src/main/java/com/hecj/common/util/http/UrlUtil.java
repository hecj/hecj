package com.hecj.common.util.http;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UrlUtil {

    /**
     * 获取url中的参数
     * @param url
     * @param key
     * @return
     */
    public static String getUrlParam(String url, String key){
        String[] urlP = url.split("[?]");
        if(urlP.length > 1){
            url = url.replaceFirst(urlP[0]+"[?]", "");
            String[] urlList = url.split("[&]");
            for(String str : urlList){
                String[] strList = str.split("[=]");
                if(strList.length > 1&& key.equals(strList[0])){
                    return str.replaceFirst(key+"[=]", "");
                }
            }
        }
        return "";
    }
    
  	/**
  	 * url中&参数转map
  	 * @param url
  	 * @return
  	 */
    public static Map<String,String> url2map(String url)   {
        String  pstr="";
        String[] urlSplit = url.split("\\?");
        if(urlSplit.length>1){
            pstr=urlSplit[1];
        } else{
        	pstr = url;
        }
        String[] params = pstr.split("&");
        Map<String,String> tm=new HashMap<String,String>();
        for (String s:params) {
            String[] sz = s.split("=");
            if(sz.length>1){
                try {
                    tm.put(sz[0], URLDecoder.decode(sz[1], "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return tm;
    }
    
    /**
     * map转url
     */
    public static String map2Url(Map<String,String> map){
    	StringBuffer sb = new StringBuffer();
    	Set<String> keys = map.keySet();
    	for (String key : keys) {
			sb.append(key+"="+map.get(key)+"&");
		}
    	return sb.toString();
    }

}