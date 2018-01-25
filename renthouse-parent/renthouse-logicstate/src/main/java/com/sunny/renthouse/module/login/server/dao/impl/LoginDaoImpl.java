package com.sunny.renthouse.module.login.server.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sunny.renthouse.module.login.server.dao.ILoginDao;
import com.sunny.renthouse.module.login.shared.domain.UserLoginEntity;

public class LoginDaoImpl extends SqlSessionDaoSupport implements ILoginDao  {

	private final static String MAPPERNAMESPACE = "com.sunny.renthouse.module.login.server";
	@Override
	public int queryhasUser(UserLoginEntity userloginentity) {
		return getSqlSession().selectOne(MAPPERNAMESPACE+".queryhasUser", userloginentity);
	}

}
