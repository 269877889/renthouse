package com.sunny.renthouse.module.context;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class XssConfigContext {

	
	public static final String REPLACE_EMPTY = "REPLACE_EMPTY";
	
	public static final String REPLACE_ESCAPE = "REPLACE_ESCAPE";

	public static final String REPLACE_FULLANGLE = "REPLACE_FULLANGLE";

	public static final String REDIRECT = "REDIRECT";

	public static final String DEFAULT_EXPRESSION = "(?i)(script)+";

	public static final String EXPRESSION = "expression";

	public static final String TACTICS = "tactics";
	

	public static final String PATH = "path";
	
	Map<String, Object> context;

	public XssConfigContext(Map<String, Object> context) {
		this.context = context;
	}
	
	public String getExpression() {
		String expression = (String) context.get(EXPRESSION); 
		if(StringUtils.isBlank(expression)) {
			return DEFAULT_EXPRESSION;
		}
		return expression;
	}

	public String getTactics() {
		String tactics = (String) context.get(TACTICS);
		if(StringUtils.isBlank(tactics)) {
			return REPLACE_EMPTY;
		}
		return tactics;
	}
	
	public String getPath() {
		String path = (String) context.get(PATH);
		return path;
	}
}
