package org.talestats.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class City {
	
	static final Logger logger = LoggerFactory.getLogger(City.class);
	
	@Scheduled(fixedRate=60000)
	public void run() {
		logger.debug("Hello World!!!");
		System.out.println("Do some task");
	}
}
