package com.sunny.renthouse.module.login.server.service;

import com.sunny.renthouse.module.login.shared.domain.UserLoginEntity;

public interface ILoginService {

	int queryhasUser(UserLoginEntity userloginentity);
	
}
