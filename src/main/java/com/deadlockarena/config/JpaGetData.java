package com.deadlockarena.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deadlockarena.exception.RemainderException;
import com.deadlockarena.persistence.entity.Champion;
import com.deadlockarena.persistence.entity.StatusBox;
import com.deadlockarena.persistence.repository.ChampionRepository;
import com.deadlockarena.persistence.repository.PotionInventoryRepository;

@Component
public class JpaGetData {

	private PotionInventoryRepository potionInventoryRepository;
	private ChampionRepository championRepository;

	@Autowired
	public JpaGetData(PotionInventoryRepository potionInventoryRepository,
			ChampionRepository championRepository) {
		this.potionInventoryRepository = potionInventoryRepository;
		this.championRepository = championRepository;
	}

	public Champion evalChampion(String championString) throws RemainderException {
		Champion c = null;
		try {
			c = championRepository.findByChampion(championString);
			c.setPotionInventory(potionInventoryRepository.findByChampionString(championString));
		} catch (Exception e) {
			e.printStackTrace();
		}
		c.setStatusBox(new StatusBox());
		return c;
	}
}
