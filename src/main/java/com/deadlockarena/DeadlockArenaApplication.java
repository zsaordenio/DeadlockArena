package com.deadlockarena;

import java.awt.*;

import javax.swing.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.deadlockarena.config.AppPrincipalFrame;

@SpringBootApplication
public class DeadlockArenaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(DeadlockArenaApplication.class)
				.headless(false).run(args);
		AppPrincipalFrame appPrincipalFrame = ctx.getBean(AppPrincipalFrame.class);
	}

}
