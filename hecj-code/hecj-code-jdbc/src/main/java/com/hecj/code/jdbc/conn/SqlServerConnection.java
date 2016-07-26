package com.hecj.code.jdbc.conn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.hecj.common.utils.PropertiesUtil;

public class SqlServerConnection {

	private static Properties prop = null;
	private static Connection conn = null;
	
	static{
		try {
			prop = PropertiesUtil.getProperties(DBConnection.class,"db.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection() {

		String jdbcUrl = prop.getProperty("sqlserver.jdbcUrl");
		String user = prop.getProperty("sqlserver.user");
		String password = prop.getProperty("sqlserver.password");

		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
