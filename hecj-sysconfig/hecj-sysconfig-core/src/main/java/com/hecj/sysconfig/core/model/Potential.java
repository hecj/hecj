package com.hecj.sysconfig.core.model;

import java.util.HashMap;
import java.util.Map;

import com.hecj.common.model.BaseModel;

/**
 * @table:
 */
public class Potential extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	public final static String tableName = "potential";

	public final static String openid = "openid";
	
	public final static String nickname = "nick_name";
	
	public final static String sex = "sex";
	
	public final static String province = "province";
	
	public final static String city = "city";
	
	public final static String country = "country";
	
	public final static String headimgurl = "head_img_url";
	
	public final static String privilege = "privilege";
	
	public final static String unionid = "unionid";
	
	public final static String isDelete = "is_delete";
	
	public final static String loginAt = "login_at";
	
	public final static Map<String, Class<?>> getFieldType() {
		
		Map<String, Class<?>> fields = new HashMap<String, Class<?>>();
		
		fields.put(id, Long.class);
		fields.put(openid, String.class);
		fields.put(nickname, String.class);
		fields.put(sex, Integer.class);
		fields.put(province, String.class);
		fields.put(city, String.class);
		fields.put(country, String.class);
		fields.put(city, String.class);
		fields.put(headimgurl, String.class);
		fields.put(privilege, String.class);
		fields.put(unionid, String.class);
		fields.put(isDelete, Integer.class);
		fields.put(loginAt, Long.class);
		fields.put(createAt, Long.class);
		fields.put(updateAt, Long.class);
		
		return fields;
	}
	
	public Object get(String key) throws Exception {
		return super.get(key,getFieldType());
	}

}
