package com.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.authorization.model.UserInfo;
import com.authorization.model.response.ResponseUserInfo;
import com.authorization.service.UserService;
import com.authorization.utils.IPAddressUtil;

@CrossOrigin
@RequestMapping("/user")
@Controller
public class AuthorizationController {

	@Autowired
	private UserService userService;
	
	private IPAddressUtil ipAddressUtil = new IPAddressUtil();
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ResponseUserInfo> login(@RequestBody UserInfo userInfo) {

		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		String ip = ipAddressUtil.getIPAddress();
		
		ResponseUserInfo result = userService.login(userInfo, ip);

		if (result != null)
			status = HttpStatus.OK;

		return new ResponseEntity<ResponseUserInfo>(result, status);

	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ResponseEntity<String> reg(@RequestBody UserInfo userInfo) {

		HttpStatus status = HttpStatus.UNAUTHORIZED;
		String result = "fail";
		
		UserInfo resultUserInfo = userService.join(userInfo);

		if (resultUserInfo != null) {
			status = HttpStatus.OK;
			result = "success";
		}

		return new ResponseEntity<String>(result, status);
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public ResponseEntity<String> password(@RequestBody UserInfo userInfo) {

		HttpStatus status = HttpStatus.UNAUTHORIZED;
		String result = "fail";
		
		boolean passwd = userService.userInfoCheck(userInfo);

		if (passwd) {
			String tempPasswd = userService.resetPassword(userInfo);
			status = HttpStatus.OK;
			result = tempPasswd;
		}

		return new ResponseEntity<String>(result, status);
	}
}
