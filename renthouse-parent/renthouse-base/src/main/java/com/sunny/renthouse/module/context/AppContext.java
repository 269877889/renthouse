package com.sunny.renthouse.module.context;

import com.sunny.renthouse.module.web.xss.ParametersValidator;



public abstract class AppContext {
	/**
	 * 应用名
	 */
	private final String appName;
	
	/**
	 * 静态资源地址
	 */
	private final String staticServerAddress;
	
	/**
	 * 上下文路径
	 * 
	 */
	private final String contextPath;
	
	/**
	 * 单例示例
	 */
	private static AppContext context;
	
	/**
	 * 配置的服务器host name
	 */
	private String hostName;
	
	/**
	 * 参数校验,放置xss
	 */
	private static ParametersValidator parametersValidator;

	public static ParametersValidator getParametersValidator() {
		return parametersValidator;
	}

	public static void setParametersValidator(
			ParametersValidator parametersValidator) {
		AppContext.parametersValidator = parametersValidator;
	}

	public String getAppName() {
		return appName;
	}
	
	public String getStaticServerAddress() {
		return staticServerAddress;
	}
	
	public String getContextPath() {
        return contextPath;
    }
	
	public static AppContext getAppContext() {
        return context;
    }
	
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

    public AppContext(String appName, String staticServerAddress, String contextPath) {
		this.appName = appName;
		this.staticServerAddress = staticServerAddress;
		this.contextPath = contextPath;
	}
	
	public static synchronized void initAppContext(String appName, String staticServerAddress, String contextPath) {
		if (context == null) {
			context = new AppContext(appName, staticServerAddress, contextPath) {};
		}
	}
}
