package com.hecj.common.util;

import java.util.Date;
import java.util.Random;

import com.hecj.common.util.date.DateFormatUtil;
/**
 * 描述：Id生成规则
 * @author: hecj
 */
public class GenerateUtil {

	/**
	 * 描述：根据时间戳生成Id
	 * @author: hecj
	 */
	public static String generateId(){
		
		return generateId(3);
	}
	public static String generateId(int num){
		
		return generateId(new Date(),num);
	}
	
	public static String generateId(Date date,int num){
		return DateFormatUtil.format(date, "yyyyMMddHHmmssSSS")+randomNumber(num);
	}
	
	public static String randomNumber(int num){
		StringBuffer str = new StringBuffer();
		Random ran = new Random();
		for (int i = 0; i < num; i++) {
			str.append(String.valueOf(ran.nextInt(10)));
		}
		return str.toString();
	}
	
}
