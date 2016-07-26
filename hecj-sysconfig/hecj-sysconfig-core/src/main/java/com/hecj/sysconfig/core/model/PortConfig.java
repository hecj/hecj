package com.hecj.sysconfig.core.model;

import java.util.HashMap;
import java.util.Map;

import com.hecj.common.model.BaseModel;

/**
 * @table:
 */
public class PortConfig extends BaseModel{

	private static final long serialVersionUID = 1L;

	public final static String tableName = "port_config";

	public final static String port = "port";
	
	public final static String name = "name";
	
	public final static String dir = "dir";
	
	public final static String status = "status";
	
	public final static String isDelete = "isDelete";

	public final static Map<String, Class<?>> getFieldType() {
		
		Map<String, Class<?>> fields = new HashMap<String, Class<?>>();
		
		fields.put(id, Long.class);
		fields.put(port, Integer.class);
		fields.put(name, String.class);
		fields.put(dir, String.class);
		fields.put(status, Integer.class);
		fields.put(isDelete, Integer.class);
		fields.put(createAt, Long.class);
		fields.put(updateAt, Long.class);
		
		return fields;
	}
	
	public Object get(String key) throws Exception {
		return super.get(key,getFieldType());
	}

}
