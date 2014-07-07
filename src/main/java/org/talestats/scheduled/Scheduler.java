package org.talestats.scheduled;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.talestats.config.WebAppConfig;

public class Scheduler {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		
		ctx.register(WebAppConfig.class);
		ctx.refresh();
		
		City city = ctx.getBean(City.class);
		city.run();
		
	}
}