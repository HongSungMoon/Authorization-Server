package com.authorization.service;

import com.authorization.model.UserInfo;
import com.authorization.model.response.ResponseUserInfo;

public interface AccessTokenService {

	public ResponseUserInfo generateToken(UserInfo result);
	
}
