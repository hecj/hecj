package test.conn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hecj.code.jdbc.conn.SqlServerConnection;
import com.hecj.common.utils.HttpRequest;

public class BaiduDemo {
	
	public static void main(String[] args) {
		
//		Connection conn = SqlServerConnection.getConnection();
//		
//		/**
//		 * ACC2020
//ACC2021
//ACC2022
//ACC2023
//ACC2024
//ACC2025
//ACC2026
//		 */
//		
//		try {
//			
//			PreparedStatement ps = conn.prepareStatement("select * from T_ACC_007 ");
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				System.out.println(rs.getString(1));
//			}
//			
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		
		/*
		
		q(query)	是	无	上地、天安、中关、shanghai	输入建议关键字（支持拼音）
region	是	无	全国、北京市、131、江苏省等	所属城市/区域名称或代号
location	否	无	40.047857537164,116.31353434477	传入location参数后，返回结果将以距离进行排序
output	否	xml	json、xml	返回数据格式，可选json、xml两种
ak	是	无	E4805d16520de693a3fe707cdc962045	开发者访问密钥，必选。
sn	否	无		用户的权限签名
timestamp	否	无		设置sn后该值必选

 
{
    "status":0,
    "message":"ok",
    "result":[
        {
            "name":"天安门",
            "location":{
                "lat":39.915174,
                "lng":116.403875
            },
            "uid":"65e1ee886c885190f60e77ff",
            "city":"北京市",
            "district":"东城区",
            "business":"",
            "cityid":"131"
        },
        {
            "name":"天安门广场",
            "location":{
                "lat":39.912735,
                "lng":116.404015
            },
            "uid":"c9b5fb91d49345bc5d0d0262",
            "city":"北京市",
            "district":"东城区",
            "business":"",
            "cityid":"131"
        },
        ...
    ]
}
		广东明业药业有限公司
		**/
		
		// key 203.212.4.117 hl9uar2eT6bKfoEpV0ncIamUd5KrLt5c http://api.map.baidu.com/place/v2/suggestion/
		
		// http://api.map.baidu.com/place/v2/suggestion?query=%E4%BA%91%E5%8D%97%E7%9C%81%E4%B9%85%E6%B3%B0%E8%8D%AF%E4%B8%9A%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%E4%B8%B4%E6%B2%A7%E5%88%86%E5%85%AC%E5%8F%B8&region=%E4%BA%91%E5%8D%97%E7%9C%81&output=json&ak=hl9uar2eT6bKfoEpV0ncIamUd5KrLt5c
		//for(int i=0;i<20;i++){
			yiyuan();
		//}
		
		
	}
	static String ak = "hl9uar2eT6bKfoEpV0ncIamUd5KrLt5c";
	static String url = "http://api.map.baidu.com/place/v2/suggestion";
	
	/**
	 {
    "status": 0,
    "message": "ok",
    "result": [
        {
            "name": "代理-商友(耀东国际影城)",
            "location": {
                "lat": 22.832852,
                "lng": 113.278608
            },
            "uid": "29147ed4eebd0d77b6f42f22",
            "city": "佛山市",
            "district": "顺德区",
            "business": "",
            "cityid": "138"
        },
        {
            "name": "代理-商友(广州万达影城)",
            "location": {
                "lat": 23.177997,
                "lng": 113.273467
            },
            "uid": "19ebd2712074a7bbcf06a441",
            "city": "广州市",
            "district": "白云区",
            "business": "",
            "cityid": "257"
        },
        {
            "name": "代理-商友(太平洋宏帆国际)",
            "location": {
                "lat": 23.073532,
                "lng": 113.058483
            },
            "uid": "7be00014c100881075e5b6e8",
            "city": "佛山市",
            "district": "南海区",
            "business": "",
            "cityid": "138"
        },
        {
            "name": "代理-商友(顺德天星)",
            "location": {
                "lat": 22.959095,
                "lng": 113.246644
            },
            "uid": "33a7e8adf1ee10c04f6d46bc",
            "city": "佛山市",
            "district": "顺德区",
            "business": "",
            "cityid": "138"
        },
        {
            "name": "代理-商友(大扬影城太港城店)",
            "location": {
                "lat": 23.035224,
                "lng": 113.200159
            },
            "uid": "16141542b9d8ac73bda2409d",
            "city": "佛山市",
            "district": "南海区",
            "business": "",
            "cityid": "138"
        },
        {
            "name": "番禺市桥文化中心",
            "location": {
                "lat": 22.940577,
                "lng": 113.369907
            },
            "uid": "b292b3aa2e23eb57be484261",
            "city": "广州市",
            "district": "番禺区",
            "business": "",
            "cityid": "257"
        },
        {
            "name": "代理-商友(星美国际影城宏发店)",
            "location": {
                "lat": 22.560929,
                "lng": 113.895945
            },
            "uid": "10da65c16aa90604f32d830a",
            "city": "深圳市",
            "district": "宝安区",
            "business": "",
            "cityid": "340"
        },
        {
            "name": "代理-商友(万达影城海雅广场店)",
            "location": {
                "lat": 22.56528,
                "lng": 113.911401
            },
            "uid": "12467948d1496f442bd7abb0",
            "city": "深圳市",
            "district": "宝安区",
            "business": "",
            "cityid": "340"
        },
        {
            "name": "代理-商友(星烨南岭国际影城)",
            "location": {
                "lat": 22.616088,
                "lng": 114.157281
            },
            "uid": "604bf727e9d4a806364aaeb0",
            "city": "深圳市",
            "district": "龙岗区",
            "business": "",
            "cityid": "340"
        },
        {
            "name": "代理-商友(和平电影院)",
            "location": {
                "lat": 30.676792,
                "lng": 104.081785
            },
            "uid": "7d137afdc307470d9d82ea0e",
            "city": "成都市",
            "district": "青羊区",
            "business": "",
            "cityid": "75"
        }
    ]
}


	 
	 */
	
	public static void yiyuan(){
		
		Connection conn = SqlServerConnection.getConnection();
		// select T.ACCOUNTID,ACCOUNTNAME,T.PROVINCENAME,T.CITYNAME,T.LAT,t.LON,t.DISTANCE from T_ACC_007 t;
		try {
			
			PreparedStatement ps = conn.prepareStatement("select TOP 2000 ACCOUNTID,ACCOUNTNAME,PROVINCENAME from T_ACC_007 where (DISTANCE IS NULL) or (DISTANCE ='0') ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println();
				System.out.println("============================================");
				String ACCOUNTID = rs.getString("ACCOUNTID");
				String ACCOUNTNAME = rs.getString("ACCOUNTNAME");
				String PROVINCENAME = rs.getString("PROVINCENAME");
				
				String params = "query="+ACCOUNTNAME+"&region="+PROVINCENAME+"&output=json&ak="+ak;
				System.out.println("ACCOUNTNAME:"+ACCOUNTNAME);
				try{
					
					String content = HttpRequest.sendGet(url, params);
					System.out.println("content:"+content);
					JSONObject obj = JSON.parseObject(content);
					if("ok".equals(obj.getString("message"))){
						JSONArray arrays = obj.getJSONArray("result");
//						for(int i=0;i<arrays.size();i++){
//							
//							JSONObject jobj = arrays.getJSONObject(i);
//							if(ACCOUNTNAME.equals(jobj.getString("name"))){
//								
//								JSONObject location = jobj.getJSONObject("location");
//								double lat = location.getDouble("lat");
//								double lng = location.getDouble("lng");
//								String distance = new Geohash().encode(lat, lng);
//								// 截取前5位
//								//distance = distance.substring(0,5);
//								
//								PreparedStatement psmt = conn.prepareStatement("update T_ACC_007 set LAT=?,LON=?,DISTANCE=? where ACCOUNTID=?");
//								psmt.setString(1, lat+"");
//								psmt.setString(2, lng+"");
//								psmt.setString(3, distance+"");
//								psmt.setString(4, ACCOUNTID+"");
//								boolean brs = psmt.execute();
//								System.out.println("执行结果："+brs);
//								psmt.close();
//								
//								continue;
//							}
//							
//						}
						
						
						if(arrays.size() > 0){
							JSONObject jobj = arrays.getJSONObject(0);
							//if(ACCOUNTNAME.equals(jobj.getString("name"))){
								
								JSONObject location = jobj.getJSONObject("location");
								double lat = location.getDouble("lat");
								double lng = location.getDouble("lng");
								String distance = new Geohash().encode(lat, lng);
								// 截取前5位
								//distance = distance.substring(0,5);
								
								PreparedStatement psmt = conn.prepareStatement("update T_ACC_007 set LAT=?,LON=?,DISTANCE=? where ACCOUNTID=?");
								psmt.setString(1, lat+"");
								psmt.setString(2, lng+"");
								psmt.setString(3, distance+"");
								psmt.setString(4, ACCOUNTID+"");
								boolean brs = psmt.execute();
								System.out.println("执行结果："+brs);
								psmt.close();
								
								continue;
							//}
						}
					}
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
				System.out.println();
				System.out.println();
			}
			//conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
}
