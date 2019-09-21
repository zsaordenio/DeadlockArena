package com.deadlockarena.backend.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.deadlockarena.backend.persistence.domain.entity.Champion;
import com.deadlockarena.backend.persistence.domain.item.PotionInventory;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public interface ChampionRepository extends CrudRepository<Champion, Long> {
	
	
	List<Champion> findAll();
}
