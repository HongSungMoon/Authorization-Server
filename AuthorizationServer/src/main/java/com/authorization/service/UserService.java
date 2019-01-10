package com.authorization.service;

import com.authorization.model.UserInfo;
import com.authorization.model.response.ResponseUserInfo;

public interface UserService {

	public ResponseUserInfo login(UserInfo userInfo, String ip);

	public UserInfo join(UserInfo userInfo);

	boolean userInfoCheck(UserInfo userInfo);

	public String resetPassword(UserInfo userInfo);
	
}
