package com.deadlockarena.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deadlockarena.backend.persistence.domain.entity.Champion;
import com.deadlockarena.backend.persistence.domain.entity.StatusBox;
import com.deadlockarena.backend.persistence.repository.ChampionRepository;
import com.deadlockarena.backend.persistence.repository.PotionInventoryRepository;
import com.deadlockarena.exception.RemainderException;

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
