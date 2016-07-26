package test.conn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hecj.code.jdbc.conn.DBConnection;

public class Test {

	public static void main(String[] args) {
		
		Connection conn = DBConnection.getConnection();

//		String sql = "select * from USR_USER_INFO LIMIT 0,100";
		
//		String sql = "SELECT * FROM ( SELECT CODE, NAME, REGISTERED_ADD,LON, LAT,"+
//						" ROUND(2*ASIN(SQRT(POWER(SIN((39.963884*PI()/180.0-LAT*PI()/180.0) / 2), 2) +  COS(39.963884*PI()/180.0) "+
//						" * COS(LAT*PI()/180.0) * POWER(SIN((lon=116.45673*PI()/180.0-LON*PI()/180.0) / 2), 2)))*6371.004*10000,3)/10000 "+
//						" AS DISTANCE FROM ORG_ORGANIZE WHERE PRD_CATEGORY='医院' AND  PROVINCE_NAME='北京' "+
//						" AND LAT IS NOT NULL ORDER BY DISTANCE ASC) AS ORG WHERE DISTANCE<=2 ";
		
		String sql = "select CODE,PRD_CATEGORY,PROVINCE_NAME, NAME, REGISTERED_ADD,LON, LAT, (ROUND(2*ASIN(SQRT(POWER(SIN((39.963884*PI()/180.0-LAT*PI()/180.0) / 2), 2) +  COS(39.963884*PI()/180.0) "+
				 "* COS(LAT*PI()/180.0) * POWER(SIN((lon=116.45673*PI()/180.0-LON*PI()/180.0) / 2), 2)))*6371.004*10000,3)/10000) AS DISTANCE "
				 + "from ORG_ORGANIZE WHERE 1=1 AND PRD_CATEGORY ='医院' "
				 + " AND LAT IS NOT NULL HAVING DISTANCE<=2 limit 0,10";
		
		System.out.println("sql:"+sql);
		PreparedStatement prest = null;
		try {
			prest = conn.prepareStatement(sql);
//			prest.setString(1,dbName);
			ResultSet result = prest.executeQuery();
			int i = 0;
			while(result.next()){
				System.out.println("----result----"+(++i));
				String code = result.getString("CODE");
				System.out.println(code+" , "+result.getString("DISTANCE"));
			}
			result.close();
			System.out.println(conn);
			prest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		
	}

}
