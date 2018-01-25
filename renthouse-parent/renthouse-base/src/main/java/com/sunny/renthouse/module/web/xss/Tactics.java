package com.sunny.renthouse.module.web.xss;

import com.sunny.renthouse.module.exception.ParametersValidatorException;

public interface Tactics {
	String process(String target, String regex) throws ParametersValidatorException;
}
