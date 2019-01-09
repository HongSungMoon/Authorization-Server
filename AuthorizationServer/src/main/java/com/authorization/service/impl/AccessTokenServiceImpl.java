package com.authorization.service.impl;

import org.springframework.stereotype.Service;

import com.authorization.model.UserInfo;
import com.authorization.model.response.ResponseUserInfo;
import com.authorization.service.AccessTokenService;
import com.authorization.utils.TokenGenerateUtil;
import com.authorization.utils.UserInfoTransUtil;

@Service("accessTokenService")
public class AccessTokenServiceImpl implements AccessTokenService {
	
	private TokenGenerateUtil tokenGenerateUtil = new TokenGenerateUtil();
	
	private UserInfoTransUtil userInfoTransUtil = new UserInfoTransUtil();

	@Override
	public ResponseUserInfo generateToken(UserInfo result) {
		
		String access_token = tokenGenerateUtil.tokenGenerate();
		System.out.println("access_token : " + access_token);
		ResponseUserInfo responseInfo = userInfoTransUtil.changeModel(result, access_token);
		
		return responseInfo;
	}

}
