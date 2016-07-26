package com.hecj.code.jdbc.conn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.hecj.common.utils.PropertiesUtil;

public class DBConnection {

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
		
		String jdbcUrl = prop.getProperty("msyql.jdbcUrl");
		String user = prop.getProperty("msyql.user");
		String password = prop.getProperty("msyql.password");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
