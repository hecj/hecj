package com.hecj.code.jdbc.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import com.hecj.code.jdbc.db.impl.MySqlDBDatabase;
import com.hecj.code.jdbc.db.impl.OracleDBDatabase;
/**
 * db database factory
 */
public class DBDatabaseFactory {

	public static DBDatabase getDBDatabase(Connection conn){

		try {
			DatabaseMetaData meta = conn.getMetaData();
			String dbType = meta.getDatabaseProductName();
			System.out.println("dbType : " + dbType );
			dbType = dbType.toUpperCase();
			switch (dbType) {
			case "MYSQL":
				return new MySqlDBDatabase(conn);
			case "ORACLE":
				return new OracleDBDatabase(conn);
			default:
				break;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
