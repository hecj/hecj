package com.hecj.common.weixin.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hecj.common.utils.HttpRequest;
import com.hecj.common.utils.StringUtil;
import com.hecj.common.weixin.props.WeiXinPropertiesUtil;
/**
 * @ClassName: OAuth2
 * @Description: 微信用户授权基础类
 * @author hechaojie
 * @date 2015-7-27 下午2:54:31
 * 
 */
public final class OAuth2 {
	
	private static final Log log = LogFactory.getLog(OAuth2.class);
	
	/**
	 * 生成 用户同意授权 URL，获取code
	 * @param appID 公众号的唯一标识 
	 * @param backURL 回调接口地址
	 * @param scope 作用域【snsapi_base、snsapi_userinfo】
	 * @param state 重定向后会带上state参数
	 * @return 
	 * @throws UnsupportedEncodingException
	 */
	public static String urlOauth2Authorize(String appID, String backURL, String scope, String state) throws UnsupportedEncodingException {
		String request = WeiXinPropertiesUtil.getProperty("oauth2_authorize");
		request = request.replace("{APPID}", appID)
						.replace("{REDIRECT_URI}", URLEncoder.encode(backURL,"UTF-8"))
						.replace("{SCOPE}", scope)
						.replace("{STATE}", state);
		log.info(" oauth2 authorize request :" + request);
		return request;
	}
	
	/**
	 * 通过code换取网页授权access_token
	 * @param appID 公众号的唯一标识 
	 * @param secret 公众号的appsecret 
	 * @param code 用户授权code
	 */
	public static JSONObject getAccessTokenByCode(String appID, String secret, String code){
		log.info("appID : "+appID+", secret : "+secret + " , code : "+ code);
		String request = WeiXinPropertiesUtil.getProperty("oauth2_access_token");
		request = request.replace("{APPID}", appID)
							.replace("{SECRET}", secret)
							.replace("{CODE}", code);
		log.info(" request : " + request);
		String response = "";
		try {
			response = HttpRequest.sendGet(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info(" response : " + response);
		if(StringUtil.isStrEmpty(response)){
			log.error(" request error , response empty ");
			return null ;
		}
		return JSON.parseObject(response);
	}
	
	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 * @param @param accessToken
	 * @param @param openId
	 * @param @return
	 * @return JSONObject
	 * @throws
	 */
	public static JSONObject getUserinfoByAccessToken(String accessToken, String openId){
		log.info("accessToken : "+accessToken+", openId : "+openId);
		String request = WeiXinPropertiesUtil.getProperty("userinfo");
		request = request.replace("{ACCESS_TOKEN}", accessToken)
							.replace("{OPENID}", openId);
		log.info(" request : " + request);
		String response ="";
		try {
			response = HttpRequest.sendGet(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info(" response : " + response);
		if(StringUtil.isStrEmpty(response)){
			log.error(" request error , response empty ");
			return null ;
		}
		return JSON.parseObject(response);
	}
	
}
