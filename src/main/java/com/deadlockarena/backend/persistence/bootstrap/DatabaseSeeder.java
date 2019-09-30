package com.deadlockarena.backend.persistence.bootstrap;

import com.deadlockarena.backend.persistence.domain.entity.PotionInventory;
import com.deadlockarena.backend.persistence.domain.item.HpPotion;
import com.deadlockarena.backend.persistence.domain.item.MpPotion;
import com.deadlockarena.backend.persistence.repository.ChampionRepository;
import com.deadlockarena.backend.persistence.repository.PotionInventoryRepository;

import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class DatabaseSeeder implements CommandLineRunner {

	private final PotionInventoryRepository potionInventoryRepository;
	private final ChampionRepository championRepository;

	@Autowired
	public DatabaseSeeder(PotionInventoryRepository potionInventoryRepository, ChampionRepository championRepository) {
		this.potionInventoryRepository = potionInventoryRepository;
		this.championRepository = championRepository;
	}

	@Override
	@Transactional
	public void run(String... args) {


		LOG.info("Creating Hp Potion objects...");
		HpPotion hpPotion = new HpPotion();
		hpPotion.setRecovery(5);
		List<HpPotion> hpPotions = Collections.singletonList(hpPotion);
		LOG.info("Creating Mp Potion objects...");
		MpPotion mpPotion = new MpPotion();
		mpPotion.setRecovery(5);
		List<MpPotion> mpPotions = Collections.singletonList(mpPotion);

		LOG.info("Creating Potion Inventory object...");
		PotionInventory potionInventory = new PotionInventory();
		potionInventory.setHpPotions(hpPotions);
		potionInventory.setMpPotions(mpPotions);

		LOG.info("Saving Potion Inventory object into database...");
		potionInventoryRepository.save(potionInventory);

		LOG.info("Persisted to database successfully...");

	}
}
