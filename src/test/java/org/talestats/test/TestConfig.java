package org.talestats.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = org.talestats.config.WebAppConfig.class)
public class TestConfig {
	
	@Test
	public void doTest() {
		System.out.println("test");
	}
}
