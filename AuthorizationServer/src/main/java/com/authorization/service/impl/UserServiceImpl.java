package com.authorization.service.impl;

import java.util.HashMap;

import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.authorization.database.UserMapper;
import com.authorization.model.RedisUserInfo;
import com.authorization.model.UserInfo;
import com.authorization.model.response.ResponseUserInfo;
import com.authorization.redis.RedisService;
import com.authorization.service.AccessTokenService;
import com.authorization.service.UserService;
import com.authorization.utils.CryptoUtil;
import com.authorization.utils.MailSendUtil;
import com.authorization.utils.UserInfoTransUtil;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RedisService redisService;
	@Autowired
	private AccessTokenService accessTokenService;
	@Autowired
    private JavaMailSender javaMailSender;
	
	private CryptoUtil cryptoUtil = new CryptoUtil();
	private UserInfoTransUtil userInfoTransUtil = new UserInfoTransUtil();
	
	

	@Override
	public ResponseUserInfo login(UserInfo userInfo, String ip) {

		UserInfo result = userMapper.getUserByID(userInfo.getId());

		if (result != null) {
			String encPasswd = cryptoUtil.sha256(result.getSalt() + userInfo.getPassword());
			if (result.getPassword().equals(encPasswd)) {
				ResponseUserInfo responseUserInfo =  accessTokenService.generateToken(result);
				RedisUserInfo redisUserInfo = new RedisUserInfo();
				redisUserInfo.setIp(ip);
				redisUserInfo.setUser_type(responseUserInfo.getUser_type());
				
				//TODO: Redis에 데이터 추가
				redisService.insertToken(responseUserInfo.getAccess_token(), redisUserInfo);
				
				return responseUserInfo;
			}
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
			
			return userInfo;
		}
		return null;
	}

	@Override
	public boolean userInfoCheck(UserInfo userInfo) {
		
		String passwd = userMapper.getPassword(userInfo);
		
		if(passwd == null)
			return false;
		return true;
		
	}

	@Override
	public String resetPassword(UserInfo userInfo) {
		
		MailSendUtil mailSendUti = new MailSendUtil();
		mailSendUti.setJavaMailSender(javaMailSender);
		String tmpPasswd = cryptoUtil.randomKey(10);
		
		mailSendUti.sendSimpleMessage(userInfo.getEmail(), 
				"Password", "Your new password is \"" + tmpPasswd +"\"\n"
						+ "I recommend that you change your password after login to the homepage.");
		
		String salt = cryptoUtil.randomKey(10);
		String encPasswd = cryptoUtil.sha256(salt + tmpPasswd);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", userInfo.getId());
		map.put("salt", salt);
		map.put("password", encPasswd);
		
		userMapper.resetPassword(map);
		
		return tmpPasswd;
		
	}

}
