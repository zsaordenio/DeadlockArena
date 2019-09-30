package com.deadlockarena.backend.persistence.bootstrap;

import com.deadlockarena.backend.persistence.domain.entity.Champion;
import com.deadlockarena.backend.persistence.repository.ChampionRepository;
import com.deadlockarena.backend.persistence.repository.PotionInventoryRepository;

import java.util.List;
import java.util.Objects;

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

		List<Champion> champions = championRepository.findAll();
		for (Champion c : champions) {
			LOG.info("Champion from database {}", c);
			LOG.info("success!");
		}

		Champion c = championRepository.findByChampion("Monk");
		if (Objects.nonNull(c)) {
			LOG.info("Id for {} is {}", c, c.getId());
			LOG.info("Status Box for {} is {}", c, c.getStatusBox());
			LOG.info("Potion Inventory for {} is {}", c, c.getPotionInventory());
			LOG.info("Max HP for {} is {}", c, c.getMaxHp());
		}


//		LOG.info("Creating Hp Potion objects...");
//		HpPotion hpPotion = new HpPotion();
//		hpPotion.setRecovery(5);
//		List<HpPotion> hpPotions = Collections.singletonList(hpPotion);
//		LOG.info("Creating Mp Potion objects...");
//		MpPotion mpPotion = new MpPotion();
//		mpPotion.setRecovery(5);
//		List<MpPotion> mpPotions = Collections.singletonList(mpPotion);
//
//		LOG.info("Creating Potion Inventory object...");
//		PotionInventory potionInventory = new PotionInventory();
//		potionInventory.setHpPotions(hpPotions);
//		potionInventory.setMpPotions(mpPotions);
//
//		LOG.info("Saving Potion Inventory object into database...");
//		potionInventoryRepository.save(potionInventory);
//
//		LOG.info("Persisted to database successfully...");

	}
}
