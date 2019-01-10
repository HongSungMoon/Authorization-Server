package com.authorization.database;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.authorization.model.UserInfo;

@Mapper
public interface UserMapper {

	public UserInfo getUser(String id);
	
	public void insertUser(UserInfo userInfo);
	
	public UserInfo getUserByID(String id);

	public String getPassword(UserInfo userInfo);

	public void resetPassword(HashMap<String, String> map);

}
