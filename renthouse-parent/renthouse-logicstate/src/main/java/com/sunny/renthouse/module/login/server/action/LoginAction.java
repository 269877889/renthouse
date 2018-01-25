package com.sunny.renthouse.module.login.server.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.dispatcher.DefaultActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sunny.renthouse.module.login.server.service.ILoginService;
import com.sunny.renthouse.module.login.shared.domain.UserLoginEntity;

public class LoginAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = -1855828244923752153L;

	/**
	 * 
	 */
	
	private ILoginService loginservice;
	
	private UserLoginEntity userloginentity;
	
	private HttpServletRequest request;
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String login(){
		int i = loginservice.queryhasUser(userloginentity);
		if(i == 1){
			HttpSession session = request.getSession();
			session.setAttribute("username", userloginentity.getUserName());
			session.setMaxInactiveInterval(36000);
		}else{
			return ERROR;
		}
		
		
		return SUCCESS;
	}

//	public ILoginService getLoginservice() {
//		return loginservice;
//	}
//
//	public UserLoginEntity getUserloginentity() {
//		return userloginentity;
//	}

	public void setLoginservice(ILoginService loginservice) {
		this.loginservice = loginservice;
	}

	public void setUserloginentity(UserLoginEntity userloginentity) {
		this.userloginentity = userloginentity;
	}
	
	
}
