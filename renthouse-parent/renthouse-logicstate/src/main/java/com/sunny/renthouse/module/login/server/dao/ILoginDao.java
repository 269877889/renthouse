package com.sunny.renthouse.module.login.server.dao;

import com.sunny.renthouse.module.login.shared.domain.UserLoginEntity;

public interface ILoginDao {

	int queryhasUser(UserLoginEntity userloginentity);
}
