package com.deadlockarena.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.deadlockarena.persistence.entity.Champion;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public interface ChampionRepository extends CrudRepository<Champion, Long> {
	
	List<Champion> findAll();
	Champion findById(int id);
	Champion findByChampion(String champion);
}
