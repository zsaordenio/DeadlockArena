package com.deadlockarena;

import java.awt.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.graphics.AnimationAndSound;
import com.deadlockarena.graphics.AppPrincipalFrame;

@SpringBootApplication
public class DeadlockArenaApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(DeadlockArenaApplication.class);
	
	@Override
	public void run(String... arg) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AppPrincipalFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
//
//	public static void main(String [ ] args) {
//		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(
//				DeadlockArenaApplication.class).headless(false).run(args);
//	}

}
