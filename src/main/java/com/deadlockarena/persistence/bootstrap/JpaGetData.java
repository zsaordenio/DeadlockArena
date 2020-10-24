package com.deadlockarena.persistence.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deadlockarena.persistence.entity.Champion;
import com.deadlockarena.persistence.repository.ChampionRepository;

@Component
public class JpaGetData {

	@Autowired
	private ChampionRepository championRepository;

	public Champion evalChampion(String championString) {
		return championRepository.findByChampion(championString);
	}
}
