package com.hecj.common.sql.util;

import java.util.List;

/**
 * SQL 处理工具类
 */
public class SqlUtil {
	
	/**
	 * 集合拼接
	 */
	public static String withSplit(List<Long> ids, String sp) {
		String str = "";
		for (Object id : ids) {
			str += "'" + id + "'" + sp + "";
		}
		if (ids != null && ids.size() > 0) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
	
	/**
	 * 将list参数转换为预编译PreparedStatement占位符。<br>
	 * 防止动态查询时SQL注入。<br>
	 */
	public static Object[] toObjects(List<Object> params){
		if(params == null){
			return new Object[]{};
		}else{
			Object[] objs = new Object[params.size()];
			for (int i = 0; i < params.size(); i++) {
				objs[i] = params.get(i);
			}
			return objs;
		}
	}
}