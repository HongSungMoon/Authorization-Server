package com.authorization.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.authorization.redis.RedisService;

@Component
public class TokenExpiredScheduler {

	@Autowired
	private RedisService redisService;

//	 @Scheduled(cron = "0 0 0 * * *")
	@Scheduled(fixedDelay = 600000)
	public void removeExpiredURL() {

		redisService.deleteExpiredToken(600000);
		
	}

//	// Expired Condition Setting
//	public Date getPivotDate() {
//
//		Date date = new Date();
//		Calendar cal = Calendar.getInstance();
//
//		cal.setTime(date);
//		cal.add(CalendarHour, -2);
//		
//		date = cal.getTime();
//		
//		return date;
//	}

}
