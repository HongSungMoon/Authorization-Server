package com.authorization.model;

import java.util.Date;

public class RedisUserInfo {
	
	private String ip;
	
	private Long timestamp;
	
	public RedisUserInfo() {
		timestamp = new Date().getTime();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTimestamp() {
		return Long.toString(timestamp);
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
}
