package com.deadlockarena;

import java.awt.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.deadlockarena.config.JpaGetData;
import com.deadlockarena.config.SpringUtils;
import com.deadlockarena.constant.JavaData;
import com.deadlockarena.graphics.AnimationAndSound;
import com.deadlockarena.graphics.MainFrame;

@SpringBootApplication
public class DeadlockArenaApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(DeadlockArenaApplication.class);

	@Override
	public void run(String... arg) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Game();
			}
		});

	}

	public static void main(String [ ] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(
				DeadlockArenaApplication.class).headless(false).run(args);
	}

}
