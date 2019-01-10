package com.authorization;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.authorization.redis.RedisService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AuthorizationServerApplication.class})
public class RedisTest {
	
	
	@Autowired
	private RedisService redisService = new RedisService();
	
	@Test
	public void allKeys() {
//		redisService.deleteExpiredToken();
	}

}
