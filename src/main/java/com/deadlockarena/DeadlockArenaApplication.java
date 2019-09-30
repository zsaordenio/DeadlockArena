package com.deadlockarena;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.deadlockarena.graphics.AppPrincipalFrame;

@SpringBootApplication
public class DeadlockArenaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(DeadlockArenaApplication.class)
				.headless(false).run(args);
		AppPrincipalFrame appPrincipalFrame = ctx.getBean(AppPrincipalFrame.class);
	}

}
