package com.deadlockarena;

import java.awt.*;
import java.sql.SQLException;

import org.hibernate.exception.GenericJDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.deadlockarena.graphics.ErrorFrame;
import com.deadlockarena.graphics.LoginFrame;

import oracle.net.ns.NetException;

@SpringBootApplication
public class DeadlockArenaApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(DeadlockArenaApplication.class);

	/**
	 * GUI testing.
	 */
	@Override
	public void run(String... arg) throws Exception {
		try {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					new LoginFrame();
					//new Game();
				}
			});
		} catch (Exception e) {
			new ErrorFrame(e.toString());
		}
	}

//	private final ChampionRepository championRepository;
//
//	@Autowired
//	public DeadlockArenaApplication(ChampionRepository championRepository) {
//		this.championRepository = championRepository;
//	}
//
//	/**
//	 * Persistence testing.
//	 */
//	@Override
//	@Transactional
//	public void run(String... args) throws Exception {
//
//		List<Champion> champions = championRepository.findAll();
//		for (Champion c : champions) {
//			System.out.println(c);
//			System.out.println("success!");
//		}
//		
//		Champion c = championRepository.findByChampion("Monk");
//		System.out.println(c.toString());
//		System.out.println(c.getChampion());
//		System.out.println(c.getId());
//		System.out.println(c.getMaxHp());
//	}

	public static void main(String [ ] args) {
		new SpringApplicationBuilder(DeadlockArenaApplication.class).headless(false).run(args);
	}

}
