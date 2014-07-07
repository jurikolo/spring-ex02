package org.talestats.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class City {
	
	static final Logger logger = LoggerFactory.getLogger(City.class);
	
	/*@Scheduled(cron = "0-59 * * * * *")
	public void run() {
		logger.debug("Hello World!!!");
		System.out.println("Do some task");
	}*/
	
	@Scheduled(fixedRate=5000)
	public void run() {
		logger.debug("Hello World!!!");
		System.out.println("Do some task");
	}
}
