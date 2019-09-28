package com.deadlockarena.backend.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.deadlockarena.backend.persistence.domain.entity.Champion;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public interface ChampionRepository extends CrudRepository<Champion, Long> {
	List<Champion> findAll();
	
	Champion findById(String id);
	Champion findByChampion(String champion);
}
