package com.sunny.renthouse.module.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.sunny.renthouse.module.base.struts.ModuleManager;
import com.sunny.renthouse.module.context.AppContext;
import com.sunny.renthouse.module.context.XssConfigContext;
import com.sunny.renthouse.module.web.xss.ParametersValidator;

public class FrameworkFilter extends DefaultFilter{

	private static ServletContext servletContext;
	    
    public static ServletContext getServletContext() {
        return servletContext;
    }
    
    @Override
    public void init(FilterConfig config) throws ServletException {
    	getServletContext(config);
        ModuleManager.export(servletContext);
        
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(XssConfigContext.EXPRESSION, config.getInitParameter(XssConfigContext.EXPRESSION));
        map.put(XssConfigContext.TACTICS, config.getInitParameter(XssConfigContext.TACTICS));
        map.put(XssConfigContext.PATH, config.getInitParameter(XssConfigContext.PATH));
        AppContext.setParametersValidator(new ParametersValidator(new XssConfigContext(map)));
    }
    
    public static void getServletContext(FilterConfig config) {
    	servletContext = config.getServletContext();
    }
	
}
