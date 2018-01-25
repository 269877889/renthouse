package com.sunny.renthouse.module.login.server.service.impl;

import com.sunny.renthouse.module.login.server.dao.ILoginDao;
import com.sunny.renthouse.module.login.server.service.ILoginService;
import com.sunny.renthouse.module.login.shared.domain.UserLoginEntity;

public class LoginServiceImpl implements ILoginService {

	private ILoginDao logindao;
	
	@Override
	public int queryhasUser(UserLoginEntity userloginentity) {
		
		return logindao.queryhasUser(userloginentity);
	}

	public ILoginDao getLogindao() {
		return logindao;
	}

	public void setLogindao(ILoginDao logindao) {
		this.logindao = logindao;
	}

}
