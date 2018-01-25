package com.sunny.renthouse.module.web.xss;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.sunny.renthouse.module.exception.ParametersValidatorException;

/**
 * 直接跳转的策略逻辑类
 * 
 * @since
 * @version
 */
class Redirect implements Tactics {
	
	private HttpServletResponse response;
	private String path;

	public Redirect(HttpServletResponse response,String path) {
		this.response = response;
		this.path = path;
	}

	@Override
	public String process(String target, String regex) throws ParametersValidatorException{
		try {
			response.sendRedirect(path);
			throw new ParametersValidatorException("redirect");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
