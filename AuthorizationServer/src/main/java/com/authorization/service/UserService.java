package com.authorization.service;

import com.authorization.model.UserInfo;
import com.authorization.model.response.ResponseUserInfo;

public interface UserService {

	public ResponseUserInfo login(UserInfo userInfo);

	public UserInfo join(UserInfo userInfo);
	
}
