package com.authorization.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authorization.database.UserMapper;
import com.authorization.model.UserInfo;
import com.authorization.model.response.ResponseUserInfo;
import com.authorization.service.AccessTokenService;
import com.authorization.service.UserService;
import com.authorization.utils.CryptoUtil;
import com.authorization.utils.UserInfoTransUtil;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private AccessTokenService accessTokenService;

	private CryptoUtil cryptoUtil = new CryptoUtil();

	@Override
	public ResponseUserInfo login(UserInfo userInfo) {

		UserInfo result = userMapper.getUser(userInfo.getId());

		if (result != null) {
			String encPasswd = cryptoUtil.sha256(result.getSalt() + userInfo.getPassword());
			if (result.getPassword().equals(encPasswd))
				return accessTokenService.generateToken(result);
		}

		return null;

	}

	@Override
	public UserInfo join(UserInfo userInfo) {

		UserInfo result = userMapper.getUserByID(userInfo.getId());

		if (result == null) {
			String salt = cryptoUtil.randomKey(10);
			String encPasswd = cryptoUtil.sha256(salt + userInfo.getPassword());
			userInfo.setSalt(salt);
			userInfo.setPassword(encPasswd);
			userMapper.insertUser(userInfo);
		}
		return result;
	}

}
