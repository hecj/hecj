package com.hecj.sysconfig.web.controller;
import java.net.URLEncoder;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.hecj.common.utils.StringUtil;
import com.hecj.common.jfinal.controller.BaseController;
import com.hecj.common.weixin.util.OAuth2;
import com.hecj.sysconfig.web.common.Const;
import com.jfinal.aop.Before;
import com.jfinal.log.Logger;
import com.jfinal.plugin.spring.IocInterceptor;

@Before(IocInterceptor.class)
public class WeiXin2Controller extends BaseController {
	
	private Logger log = Logger.getLogger(WeiXin2Controller.class);
	
	/**
	 * 微信接入
	 */
	public void index() throws Exception {

		log.info("-------------微信接入----------------");
		String signature = getPara("signature");
		String timestamp = getPara("timestamp");
		String nonce = getPara("nonce");
		String echostr = getPara("echostr");
		log.info("signature:" + signature + ",timestamp:" + timestamp + ",nonce:" + nonce + ",echostr:" + echostr);
		if (StringUtils.isBlank(echostr)) {
			log.info(" wei xin auth error ");
			renderText(" wei xin auth error ");
			return;
		}
		renderText(echostr);
	}
	
	/**
	 * 微信用户授权
	 */
	public void auth(){
		
		try {
			log.info("---------微信用户授权-----------");
			
			String authURL = OAuth2.urlOauth2Authorize(Const.appID, Const.oauth2Back, "snsapi_base", "hechaojie");
			log.info("authURL:"+authURL);
			redirect(authURL);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 微信用户授权
	 */
	public void auth2(){
		try {
			log.info("---------微信用户授权-----------");
			
			String authURL = OAuth2.urlOauth2Authorize(Const.appID, Const.oauth2Back, "snsapi_userinfo", "hechaojie");
			log.info("authURL:"+authURL);
			redirect(authURL);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 微信用户授权
	 */
	public void login(){
		
		try {
			log.info("---------微信用户授权-----------");
			String authURL="https://open.weixin.qq.com/connect/qrconnect?appid={APP_ID}&redirect_uri={BACK_URL}?&response_type=code&scope=snsapi_login";
			authURL=authURL.replace("{BACK_URL}", URLEncoder.encode(Const.oauth2Back,"UTF-8")).
					replace("{APP_ID}", Const.appID);
//			String authURL = OAuth2.urlOauth2Authorize(Const.appID, Const.oauth2Back, "snsapi_base", "hechaojie");
			log.info("authURL:"+authURL);
			redirect(authURL);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 微信用户授权回调接口
	 */
	public void oauth2Back(){
		
		log.info("--------微信用户授权回调---------------");
		String code = getPara("code");
		String state = getPara("state");
		log.info("code : "+code+" , state : "+state);
		if(StringUtil.isStrEmpty(code)){
			log.info("------用户没有授权");
			renderText("用户没有授权");
		} else{
			
			JSONObject token = OAuth2.getAccessTokenByCode(Const.appID, Const.appsecret, code);
			String access_token = token.getString("access_token");
			String openid = token.getString("openid");
			JSONObject userinfo = OAuth2.getUserinfoByAccessToken(access_token, openid);
			
			log.info("-------授权成功");
//			renderText("授权成功code："+userinfo);
			renderJson(userinfo);
		}
		
	}
	
}
