package com.hecj.code.jdbc.db.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.hecj.code.jdbc.db.DBDatabase;
import com.hecj.code.jdbc.vo.Field;
import com.hecj.code.jdbc.vo.Table;

public class OracleDBDatabase implements DBDatabase {
	
	private Connection conn ;
	
	public OracleDBDatabase() {
	}
	
	public OracleDBDatabase(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Table> getTables(List<String> gen_tables) {
		return null;
	}

	@Override
	public List<Field> getFieldsByTable(String table_name) {
		return null;
	}

	@Override
	public Map<String, String> getDataType() {
		return null;
	}

}
