package com.authorization.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public void exam() {
	    
	      // value operation
	      ValueOperations<String, Object> values = redisTemplate.opsForValue();
	       
	      // set
//	      values.set("victolee", new Employee("01", "victolee"));
	       
	      // get
	      System.out.println("Employee added : " + values.get("victolee"));
	    
	}
	     
}
