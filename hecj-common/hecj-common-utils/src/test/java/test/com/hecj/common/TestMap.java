//package test.com.hecj.common;
//
//import com.hecj.common.map.util.CoordinateConversion;
//import com.hecj.common.utils.HttpRequest;
//
//public class TestMap {
//	
//	public static void main(String[] args) {
//		
//		/**       纬                                 经
//		 谷歌地图：39.9586877228,116.4534272726
//		百度地图：39.9643400000,116.4600290000
//		腾讯高德：39.9586719029,116.4534055698
//		图吧地图：39.9580124229,116.4422013698
//		谷歌地球：39.9573224229,116.4472313698
//		北纬N39°57′26.36″ 东经E116°26′50.03″
//		
//		lon 经度
//	 	lat 纬度
//		 */
//		//System.out.println(CoordinateConversion.google_bd_encrypt(39.9586877228d, 116.4534272726d).toString());
//		
//		String s = HttpRequest.sendPost("http://115.28.242.29:8081/version/index?type=android_eyaoren", "");
//		System.out.println(s);
//	}
//}
