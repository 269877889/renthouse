package com.sunny.renthouse.module.mobile.server.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import com.sunny.renthouse.module.mobile.shared.RequestParameterEntity;
import com.sunny.renthouse.module.mobile.shared.ResponseParameterEntity;
import com.sunny.renthouse.module.service.IService;

public interface IMobileRestService<T> extends IService {

	ResponseParameterEntity<T> processRestRequest(RequestParameterEntity request,@Context HttpServletRequest request2,
			@Context HttpServletResponse response2);
}