package com.sunny.renthouse.module.web.xss;

import com.sunny.renthouse.module.exception.ParametersValidatorException;

public class ReplaceEscape  implements Tactics {
	@Override
	public String process(String target, String regex) throws ParametersValidatorException {
		target = target.replaceAll(">", "&gt;");
		target = target.replaceAll("<", "&lt;");
		return target;
	}
}
