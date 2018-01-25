package com.sunny.renthouse.module.web.xss;

import com.sunny.renthouse.module.exception.ParametersValidatorException;

public class ReplaceEmpty implements Tactics {
	@Override
	public String process(String target, String regex) throws ParametersValidatorException {
		return target.replaceAll(regex, "");
	}
}
