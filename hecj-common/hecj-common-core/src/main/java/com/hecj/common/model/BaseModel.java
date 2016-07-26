package com.hecj.common.model;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseModel extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * id：主键
	 */
	public final static String id = "id";
	
	/**
	 * create_at：创建时间
	 */
	public final static String createAt = "create_at";
	
	/**
	 * update_at：修改时间
	 */
	public final static String updateAt = "update_at";

	public Object get(String key,Map<String, Class<?>> fieldType) throws Exception {
		if(!fieldType.containsKey(key)){
			throw new Exception(" "+key+" , field not exist");
		}
		return super.get(key);
	}
}
