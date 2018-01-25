package com.sunny.renthouse.module.web.xss;

import com.sunny.renthouse.module.exception.ParametersValidatorException;

public class ReplaceFullAngle implements Tactics{


	@Override
	public String process(String target, String regex)
			throws ParametersValidatorException {
		if(null == target || target.isEmpty()) {
			return target;
		}
//		StringBuilder sb = new StringBuilder(target.length() + 16);
//		for (int i = 0; i < target.length(); i++) {
//            char c = target.charAt(i);
//            switch (c) {
//                case '>':
//                    sb.append("＞");// 全角大于号
//                    break;
//                case '<':
//                    sb.append("＜");// 全角小于号
//                    break;
//                default:
//                    sb.append(c);
//                    break;
//            }
//        }
		target = target.replaceAll(">", "＞");
		target = target.replaceAll("<", "＜");
		target = target.replaceAll("&lt;", "＆lt;");
		target = target.replaceAll("&gt;", "＆gt;");
		return target;
	}
	
	
	
	
/*	switch (c) {
    case '>':
        sb.append("＞");// 全角大于号
        break;
    case '<':
        sb.append("＜");// 全角小于号
        break;
    case '\'':
        sb.append("’");// 全角单引号
        break;
    case '\"':
        sb.append("”");// 全角双引号
        break;
    case '&':
        sb.append("&");// 全角&
        break;
    case '#':
        sb.append("＃");// 全角#
        break;
    case '\\':
        sb.append('＼');// 全角斜线
        break;
    case '%':
        sb.append('％'); //全角%
        break;
    default:
        sb.append(c);
        break;
}*/
}
