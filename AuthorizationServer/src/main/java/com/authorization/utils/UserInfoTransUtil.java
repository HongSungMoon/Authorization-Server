package com.authorization.utils;

import com.authorization.model.UserInfo;
import com.authorization.model.response.ResponseUserInfo;

public class UserInfoTransUtil {
	
	public ResponseUserInfo changeModel(UserInfo userInfo, String access_token) {
		ResponseUserInfo rUserInfo = new ResponseUserInfo();
		rUserInfo.setAccess_token(access_token);
		rUserInfo.setEmail(userInfo.getEmail());
		rUserInfo.setId(userInfo.getId());
		rUserInfo.setName(userInfo.getName());
		return rUserInfo;
	}

}
