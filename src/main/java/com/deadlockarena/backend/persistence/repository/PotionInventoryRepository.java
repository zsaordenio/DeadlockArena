package com.deadlockarena.backend.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.deadlockarena.backend.persistence.domain.item.PotionInventory;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public interface PotionInventoryRepository extends CrudRepository<PotionInventory, Long> {
	PotionInventory findByChampionString(String championString);
}
