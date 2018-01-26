package com.sunny.renthouse.module.mobile.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class ParameterUtil {

	public static final String RENTHOUSE_VERIFICATION_SERVICE = "RENTHOUSE_VERIFICATION_SERVICE";
	
	public static final String RENTHOUSE_SEND_VERIFICATION_CODE = "RENTHOUSE_SEND_VERIFICATION_CODE";
	
	public static final String RENTHOUSE_LOGIN_MOBILE = "RENTHOUSE_LOGIN_MOBILE";
	
	public static final String RENTHOUSE_USERINFO_MOBILE = "RENTHOUSE_USERINFO_MOBILE";
	
	
	//返回成功
	public static final String RETURN_SUCCESS_CODE = "200";
	
	//验证失败,参数不对
	public static final String RETURN_CHECK_PARAMS_INVALID = "201";
	
	//未登录
	public static final String RETURN_NOT_LOGIN = "202";
	
	//验证失败,参数为空
	public static final String RETURN_CHECK_PARAMS_FALSE = "203";
	
	//系统解析出错
	public static final String RETURN_SYSTEM_ERROR = "204";
	
	//系统业务处理失败
	public static final String RETURN_SYSTEM_DW_ERROR = "205";
		
	//业务处理-返回结果是失败
	public static final String RETURN_SYSTEM_DW_BUSINESS_FALSE = "206";
	
	//返回token失效
	public static final String RETURN_TOKEN_INVALID = "209";
	
	//工具对象
    private static ParameterUtil parameter = null;
    
    //bean的MAP集合
    private Map<String, String> beanMap = null;

  //初始化接口service的bean
  	private ParameterUtil() {
  		if(beanMap == null) {
  			beanMap = new HashMap<String, String>();
  		}
  		//校验手机号是否存在
  		beanMap.put(RENTHOUSE_VERIFICATION_SERVICE, "verificationService");
  		//发送验证码
  		beanMap.put(RENTHOUSE_SEND_VERIFICATION_CODE, "sendVerificationCode");
  		//登录
  		beanMap.put(RENTHOUSE_LOGIN_MOBILE, "userloginservice");
  		//用户信息
  		beanMap.put(RENTHOUSE_USERINFO_MOBILE, "userInfoService");

  	}
  	
	/**
	 * 获取参数处理工具类
	 * @return
	 * return ParameterUtil
	 */
	private static ParameterUtil getInstance() {
		 if (parameter == null) {  
	            synchronized (ParameterUtil.class) {  
	                if (parameter == null) {  
	                	parameter = new ParameterUtil();  
	                }  
	            }  
	        } 
		return parameter;
	}
	
	/**
	 * 获取bean名称
	 * @param key
	 * @return
	 */
	public static String getBeanName(String key) {
		return getInstance().beanMap.get(key);
	}
	
	/**
	 * 统一转换接口参数类型
	 * @param obj
	 * @param clz
	 * @return
	 */
	public static <T> List<T> changeToManyBusiness(Object obj, Class<T> clz) {
		List<T> list = new ArrayList<T>();
		String json = JSON.toJSONString(obj);
		//判断该类是否为ARRAYLIST子类或者数组
		if(obj.getClass().isAssignableFrom(ArrayList.class) || obj.getClass().isArray()) {
			list = JSON.parseArray(json,clz);
		} else {
			T o = JSON.parseObject(json, clz);
			list.add(o);
		}
		return list;
	}
	
	
	public static void initESBHeader(HttpServletRequest request, HttpServletResponse response,
			 String sysType, String requestId) {
		//版本号
		response.addHeader(ESBHeaderConstant.VERSION, request.getHeader(ESBHeaderConstant.VERSION));
		//请求ID
		response.addHeader(ESBHeaderConstant.REQUESTID, request.getHeader(ESBHeaderConstant.REQUESTID));
		//业务ID
		response.addHeader(ESBHeaderConstant.BUSINESSID, request.getHeader(ESBHeaderConstant.BUSINESSID));
		//消息格式
		response.addHeader(ESBHeaderConstant.MESSAGEFORMAT, request.getHeader(ESBHeaderConstant.MESSAGEFORMAT));
		//转换格式
		response.addHeader(ESBHeaderConstant.EXCHANGEPATTERN, request.getHeader(ESBHeaderConstant.EXCHANGEPATTERN));
		//相应ID
		response.addHeader(ESBHeaderConstant.RESPONSEID, requestId);
		//目标系统
		response.addHeader(ESBHeaderConstant.TARGETSYSTEM, sysType);
		//原系统
		response.addHeader(ESBHeaderConstant.SOURCESYSTEM, request.getHeader(ESBHeaderConstant.SOURCESYSTEM));
	}
}
