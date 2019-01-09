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

@CrossOrigin
@RequestMapping("/user")
@Controller
public class AuthorizationController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ResponseUserInfo> login(@RequestBody UserInfo userInfo) {
		
		ResponseUserInfo result = userService.login(userInfo);
		
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		if(result != null)
			status = HttpStatus.OK;
		
		return new ResponseEntity<ResponseUserInfo>(result, status);
		
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ResponseEntity<String> reg(@RequestBody UserInfo userInfo) {
		
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		String result = "fail";
		UserInfo resultUserInfo = userService.join(userInfo);
		
		if(resultUserInfo == null) {
			status = HttpStatus.OK;
			result = "success";
		}
		System.out.println(userInfo.getId() + " : " + userInfo.getPassword()
		+ " : " + userInfo.getName()
		+ " : " + userInfo.getEmail());
		return new ResponseEntity<String>(result, status);
	}

}
