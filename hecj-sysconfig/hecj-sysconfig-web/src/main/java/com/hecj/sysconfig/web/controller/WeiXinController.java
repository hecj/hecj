package com.hecj.sysconfig.web.controller;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.hecj.common.utils.StringUtil;
import com.hecj.common.jfinal.controller.BaseController;
import com.hecj.common.weixin.util.OAuth2;
import com.hecj.sysconfig.core.model.Potential;
import com.hecj.sysconfig.core.service.PotentialService;
import com.hecj.sysconfig.web.common.Const;
import com.jfinal.aop.Before;
import com.jfinal.log.Logger;
import com.jfinal.plugin.spring.IocInterceptor;
import com.jfinal.plugin.spring.Inject.BY_NAME;

@Before(IocInterceptor.class)
public class WeiXinController extends BaseController {
	
	private Logger log = Logger.getLogger(WeiXinController.class);
	
	@BY_NAME
	private PotentialService<Potential> potentialService;
	
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
			
			Potential p = new Potential();
			/**
			 * 使用基类实现
			 */
			p.put(Potential.openid, userinfo.getString("openid"));
			p.put(Potential.city, userinfo.getString("city"));
			p.put(Potential.country, userinfo.getString("country"));
			p.put(Potential.headimgurl, userinfo.getString("headimgurl"));
			p.put(Potential.isDelete, 0);
			p.put(Potential.loginAt, System.currentTimeMillis());
			p.put(Potential.nickname, userinfo.getString("nickname"));
			p.put(Potential.privilege, userinfo.getString("privilege"));
			p.put(Potential.sex, userinfo.getIntValue("sex"));
			p.put(Potential.privilege, userinfo.getString("privilege"));
			log.info("-p-"+p);
			try {
				log.info("begin");
				potentialService.save(p);
			} catch (Exception e) {
				log.error("errror"+e.getMessage());
				e.printStackTrace();
			}
			
			log.info("-------授权成功");
			renderText("授权成功code："+code);
		}
		
	}
	
}
