package com.hecj.code.jdbc.db.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.hecj.code.jdbc.db.DBDatabase;
import com.hecj.code.jdbc.vo.Field;
import com.hecj.code.jdbc.vo.FormatUtil;
import com.hecj.code.jdbc.vo.Table;
import com.hecj.common.utils.PropertiesUtil;

public class MySqlDBDatabase implements DBDatabase {
	
	private Connection conn ;
	
	public MySqlDBDatabase() {
	}
	
	public MySqlDBDatabase(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public List<Table> getTables(List<String> gen_tables) {
		
		String gen_tb = "";
		for(int i=0;i<gen_tables.size();i++){
			if(i == gen_tables.size()-1){
				gen_tb += "'"+gen_tables.get(i).trim()+"'";
			} else{
				gen_tb += "'"+gen_tables.get(i).trim()+"',";
			}
		}
		System.out.println("gen_tb:"+gen_tb);
		List<Table> tables = new ArrayList<Table>();
		try {
			// get db name
			String dbName = conn.getCatalog();
			// get tables
			String sql = "SELECT table_schema,table_name,table_type,table_comment,engine " +
					"FROM information_schema.tables " +
					"WHERE table_type = 'BASE TABLE' and table_schema = ? and table_name in ("+gen_tb+")";
			
			PreparedStatement prest = conn.prepareStatement(sql);
			prest.setString(1,dbName);
			ResultSet result = prest.executeQuery();
			
			while(result.next()){
				Table table = new Table();
				table.setDatabaseName(result.getString("table_schema"));
				table.setTableName(result.getString("table_name"));
				table.setTableType(result.getString("table_type"));
				table.setTableComment(result.getString("table_comment"));
				table.setTableEngine(result.getString("engine"));
				table.setFormatName(FormatUtil.formatName(table.getTableName()));
				tables.add(table);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tables;
	}
	
	@Override
	public List<Field> getFieldsByTable(String table_name) {
		
		List<Field> fields = new ArrayList<Field>();
		try {
			// get db name
			String dbName = conn.getCatalog();
			// get tables
			String sql = "SELECT table_schema,table_name,column_name,ordinal_position,is_nullable,data_type," +
						  " column_type,column_key,extra,column_comment " +
						  " FROM information_schema.columns"+ 
						  " where table_schema = ? " +
						  " and table_name = ? order by ordinal_position asc ";
										
			PreparedStatement prest = conn.prepareStatement(sql);
			prest.setString(1,dbName);
			prest.setString(2,table_name);
			ResultSet result = prest.executeQuery();
			
			while(result.next()){
				Field field = new Field();
				field.setDatabaseName(result.getString("table_schema"));
				field.setTableName(result.getString("table_name"));
				field.setColumnName(result.getString("column_name"));
				field.setColPosition(result.getInt("ordinal_position"));
				field.setNullable(result.getString("is_nullable").equals("YES")?true:false);
				field.setDataType(result.getString("data_type"));
				field.setColumnType(result.getString("column_type"));
				field.setColumnKey(result.getString("column_key"));
				field.setExtra(result.getString("extra"));
				field.setColumnComment(result.getString("column_comment"));
				field.setFormatName(FormatUtil.humpName(field.getColumnName()));
				fields.add(field);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fields;
	}

	@Override
	public Map<String, String> getDataType() {
		Map<String, String> datatype = new HashMap<String, String>();
		try {
			Properties prop = PropertiesUtil.getProperties(getClass(), "data_type/mysql.properties");
			Set<Object> keys =  prop.keySet();
			for(Object key : keys){
				datatype.put((String)key, (String) prop.get(key));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return datatype;
	}

}
