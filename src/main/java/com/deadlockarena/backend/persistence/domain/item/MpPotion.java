package com.deadlockarena.backend.persistence.domain.item;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.deadlockarena.backend.persistence.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "MP_POTION", schema = "PVO")
public class MpPotion extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -1868118488485731732L;

	@ManyToOne
	@JoinColumn(name = "potion_inventory_id")
	private PotionInventory potionInventory;

	private int recovery;
}
