package com.deadlockarena.backend.persistence.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.deadlockarena.backend.persistence.domain.base.BaseEntity;
import com.deadlockarena.backend.persistence.domain.item.HpPotion;
import com.deadlockarena.backend.persistence.domain.item.MpPotion;
import com.deadlockarena.constant.JavaData;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(schema = "PVO")
@EqualsAndHashCode(callSuper = false)
public class PotionInventory extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -419261549452638522L;
	
	@Column(name = "HP_POTIONS_AMT")
	private int hpPotionsAmt;

	@Column(name = "MP_POTIONS_AMT")
	private int mpPotionsAmt;
	
	@Column(name = "CHAMPION_STRING")
	private String championString;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<HpPotion> hpPotions = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MpPotion> mpPotions = new ArrayList<>();

	public PotionInventory(int hpPotionsAmt, int mpPotionsAmt) {
		this.hpPotionsAmt = hpPotionsAmt;
		this.mpPotionsAmt = mpPotionsAmt;
		hpPotions = new ArrayList<>();
		mpPotions = new ArrayList<>();
		generatePotions();
	}

	private void generatePotions() {
		for(int i = 0; i < hpPotionsAmt; i++) {
			hpPotions.add(new HpPotion(JavaData.random.nextInt((900 - 400) + 1) + 400));
		}
		for(int i = 0; i < mpPotionsAmt; i++) {
			mpPotions.add(new MpPotion(JavaData.random.nextInt((50 - 25) + 1) + 25));
		}
	}

}
