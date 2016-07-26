package com.hecj.web.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.junit.Test;

public class Test01 {

	@Test
	public void test01() throws IOException {
//		InputStream in = Test01.class.getResourceAsStream("/weixin.properties");
//		System.out.println(in);
		
		Properties props = new Properties();
//		props.load(Test01.class.getClassLoader().getResourceAsStream("classpath*:weixin.properties"));
		
		//返回读取指定资源的输入流  
        InputStream is=this.getClass().getResourceAsStream("/weixin.properties");   
        BufferedReader br=new BufferedReader(new InputStreamReader(is));  
        String s="";  
        while((s=br.readLine())!=null)  
            System.out.println(s);  
        
        props.load(is);
        System.out.println(props.get("oauth2Authorize"));
        System.out.println(props);
		
	}
	
	@Test
	public void test02() throws ParseException{
		//交通    广发  中信  招行 152310-144000  8310
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(format.parse("2015-10-25").getTime());
	}
	
}
