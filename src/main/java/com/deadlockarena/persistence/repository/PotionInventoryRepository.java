package com.deadlockarena.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.deadlockarena.persistence.entity.PotionInventory;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public interface PotionInventoryRepository
		extends JpaRepository<PotionInventory, String>, JpaSpecificationExecutor<PotionInventory> {
	PotionInventory findByChampionString(String championString);

	PotionInventory findById(int id);
}
