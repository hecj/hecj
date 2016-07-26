package com.hecj.code.jdbc.db;

import java.util.List;
import java.util.Map;

import com.hecj.code.jdbc.vo.Field;
import com.hecj.code.jdbc.vo.Table;

public interface DBDatabase {
	
	/**
	 * get tables
	 */
	public List<Table> getTables(List<String> gen_tables) ;
	
	/**
	 * get fields
	 */
	public List<Field> getFieldsByTable(String table_name);
	
	/**
	 * get dataType
	 */
	public Map<String,String> getDataType();
	
}
