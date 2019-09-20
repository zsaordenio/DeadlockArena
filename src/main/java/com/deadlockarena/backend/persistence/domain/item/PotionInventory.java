package com.deadlockarena.backend.persistence.domain.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.deadlockarena.backend.persistence.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(schema = "PVO")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PotionInventory extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -419261549452638522L;

	@OneToMany(mappedBy = "potionInventory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<HpPotion> hpPotions;

	@OneToMany(mappedBy = "potionInventory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MpPotion> mpPotions;

	public PotionInventory(ArrayList<Object> objects, ArrayList<Object> objects1) {

	}
}
