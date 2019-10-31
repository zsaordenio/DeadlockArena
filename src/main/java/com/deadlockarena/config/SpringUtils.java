package com.deadlockarena.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils {

	public static ApplicationContext ctx;

	/**
	 * A quick workaround if @autowired is delayed in the spring bean creation queue.
	 *
	 * Make Spring inject the application context
	 * and save it on a static variable,
	 * so that it can be accessed from any point in the application.
	 */
//	@Autowired
//	private void setApplicationContext(ApplicationContext applicationContext) {
//		ctx = applicationContext;
//	}
}