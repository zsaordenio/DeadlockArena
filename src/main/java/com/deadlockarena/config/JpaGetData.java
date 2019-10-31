package com.deadlockarena.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deadlockarena.exception.CornerCaseException;
import com.deadlockarena.persistence.entity.Champion;
import com.deadlockarena.persistence.entity.StatusBox;
import com.deadlockarena.persistence.repository.ChampionRepository;
import com.deadlockarena.persistence.repository.PotionInventoryRepository;

@Component
public class JpaGetData {

	@Autowired
	private ChampionRepository championRepository;

	public Champion evalChampion(String championString) throws CornerCaseException {
		Champion c = null;
		c = championRepository.findByChampion(championString);
		c.setStatusBox(new StatusBox());
		return c;
	}
}
