package com.hecj.code.gen;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.hecj.code.gen.util.GenUtil;
import com.hecj.code.jdbc.conn.DBConnection;
import com.hecj.code.jdbc.db.DBDatabase;
import com.hecj.code.jdbc.db.DBDatabaseFactory;
import com.hecj.code.jdbc.vo.Field;
import com.hecj.code.jdbc.vo.Table;

/**
 * 代码生成类
 * 
 * @author hechaojie
 */
public class CodeGenByDBDemo {
	
	public static void main(String[] args) throws SQLException, IOException {
		Connection conn = DBConnection.getConnection();
		DBDatabase db = DBDatabaseFactory.getDBDatabase(conn);
		List<Table> tables = db.getTables(genTables());
		Map<String,String> dataType = db.getDataType();
		for(Table table : tables){
			List<Field> fields = db.getFieldsByTable(table.getTableName());
			GenUtil.genModel(table, fields,dataType);
			GenUtil.genService(table);
			GenUtil.genServiceImpl(table);
			GenUtil.genController(table, fields);
			GenUtil.genViewList(table, fields);
		}
		GenUtil.genApplicationContextService(tables);
		GenUtil.genDubboProvider(tables);
		GenUtil.genDubboConsumer(tables);
		conn.close();
	}
	
	private static List<String> genTables() throws IOException{
		return FileUtils.readLines(new File("src/main/resources/gen_tables.txt"));
	}
	

}
