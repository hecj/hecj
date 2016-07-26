package com.hecj.sysconfig.core.model;

import java.util.HashMap;
import java.util.Map;

import com.hecj.common.model.BaseModel;

/**
 * @table:qq
 * @author:hecj
 */
public class Qq extends BaseModel{
 
	private static final long serialVersionUID = 1L;

	public final static String tableName = "qq";

	/**
	 * qq:qq号码
	 */
	public final static String qq = "qq";
	
	/**
	 * pwd:qq密码
	 */
	public final static String pwd = "pwd";
	
	/**
	 * xiaoxue:小学
	 */
	public final static String xiaoxue = "xiaoxue";
	
	/**
	 * chuzhong:初中
	 */
	public final static String chuzhong = "chuzhong";
	
	/**
	 * gaozhong:高中
	 */
	public final static String gaozhong = "gaozhong";
	
	/**
	 * oldmibao:老密保
	 */
	public final static String oldmibao = "oldmibao";
	

	public final static Map<String, Class<?>> getFieldType() {
		
		Map<String, Class<?>> fields = new HashMap<String, Class<?>>();
		
		fields.put(id,java.lang.Long.class);
		fields.put(qq,java.lang.String.class);
		fields.put(pwd,java.lang.String.class);
		fields.put(xiaoxue,java.lang.String.class);
		fields.put(chuzhong,java.lang.String.class);
		fields.put(gaozhong,java.lang.String.class);
		fields.put(oldmibao,java.lang.String.class);
		fields.put(createAt,java.lang.Long.class);
		fields.put(updateAt,java.lang.Long.class);
		
		return fields;
	}
	
	public Object get(String key) throws Exception {
		
		return super.get(key,getFieldType());
	}

}
