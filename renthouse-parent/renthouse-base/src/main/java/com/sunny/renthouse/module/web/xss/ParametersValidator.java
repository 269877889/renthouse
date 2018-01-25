package com.sunny.renthouse.module.web.xss;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.sunny.renthouse.module.context.XssConfigContext;
import com.sunny.renthouse.module.exception.ParametersValidatorException;

public class ParametersValidator {

	private XssConfigContext xssConfigContext;
	
	Set<Pattern> excludeParams = Collections.emptySet();
	
	
	public ParametersValidator(XssConfigContext xssConfigContext) {
		this.xssConfigContext = xssConfigContext;
		setExcludeParams(xssConfigContext.getExpression());
	}
	
	public void doValidator(HttpServletRequest request, HttpServletResponse response) throws ParametersValidatorException {
		if (request.getParameterMap() == null || request.getParameterMap().isEmpty()) {
			return;
		}
		
		processValidator(null, request, response);
	}
	
	public String doValidator(String json, HttpServletResponse response) throws ParametersValidatorException {
		if (StringUtils.isBlank(json)) {
			return json;
		}
		
		return processValidator(json, null, response);
	}
	
	private String processValidator(String json, HttpServletRequest request, HttpServletResponse response) throws ParametersValidatorException {
		Tactics tactics = null;
		boolean escape = false;
		if(XssConfigContext.REPLACE_EMPTY.equalsIgnoreCase(xssConfigContext.getTactics())) {
			tactics = new ReplaceEmpty();
		} else if(XssConfigContext.REPLACE_ESCAPE.equalsIgnoreCase(xssConfigContext.getTactics())) {
			tactics = new ReplaceEscape();
			escape = true;
		} else if(XssConfigContext.REPLACE_FULLANGLE.equalsIgnoreCase(xssConfigContext.getTactics())) {
			tactics = new ReplaceFullAngle();
			escape = true;
		} else if(XssConfigContext.REDIRECT.equalsIgnoreCase(xssConfigContext.getTactics())) {
			tactics = new Redirect(response,xssConfigContext.getPath());
		} else {
			tactics = new ReplaceEmpty();
		}
		
		if(request != null) {
			Map<String, String[]> params = request.getParameterMap();
			
			for (Map.Entry<String, String[]> entry : params.entrySet()) {
				String[] values = entry.getValue();
				if (values == null) {
					continue;
				}
				
				if(escape) {
					for(int i = 0; i < values.length; i++) {
						values[i] = tactics.process(values[i],null);
					}
				} else {
					for(int i = 0; i < values.length; i++) {
						values[i] = isExcluded(values[i],tactics);
					}
				}
			}
			
			return null;
		}
		if(json != null) {
			if (escape) {
				return tactics.process(json, null);
			}
			
			return isExcluded(json, tactics);
		}
		
		return null;
	}
	
	private String isExcluded(String paramValue, Tactics tactics) {
		if (StringUtils.isBlank(paramValue)) {
			return paramValue;
		}
		
        if (!this.excludeParams.isEmpty()) {
            for (Pattern pattern : excludeParams) {
                Matcher matcher = pattern.matcher(paramValue);
                if (matcher.find()) {
                	paramValue = tactics.process(paramValue, pattern.pattern());
                }
            }
        }
        
        return paramValue;
    }
	
	private void setExcludeParams(String commaDelim) {
        Collection<String> excludePatterns = asCollection(commaDelim);
        if (excludePatterns != null) {
            excludeParams = new HashSet<Pattern>();
            for (String pattern : excludePatterns) {
                excludeParams.add(Pattern.compile(pattern));
            }
        }
    }
	
	private Collection<String> asCollection(String commaDelim) {
        if (commaDelim == null || commaDelim.trim().length() == 0) {
            return null;
        }
        return commaDelimitedStringToSet(commaDelim);
    }
    
    private static Set<String> commaDelimitedStringToSet(String s) {
        Set<String> set = new HashSet<String>();
        String[] split = s.split(",");
        for (String aSplit : split) {
            String trimmed = aSplit.trim();
            if (trimmed.length() > 0)
                set.add(trimmed);
        }
        return set;
    }
}
