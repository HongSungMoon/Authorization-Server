package com.authorization.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.authorization.model.UserInfo;

@Controller
public class AuthorizationController {
	
	@CrossOrigin
	@RequestMapping(value="/login", method= RequestMethod.POST)
    public ResponseEntity<String> home(@RequestBody UserInfo loginInfo) {
		System.out.println(loginInfo.getId() + " : " + loginInfo.getPassword());
		return new ResponseEntity<String>("success", HttpStatus.OK);
    }

}
