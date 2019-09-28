package com.deadlockarena.backend.persistence.domain.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.deadlockarena.constant.JavaData;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(schema = "PVO")
@EqualsAndHashCode
public class PotionInventory implements Serializable {
	private static final long serialVersionUID = -419261549452638522L;

	@OneToOne(mappedBy = "potionInventory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private int hpPotionsAmt;

	@OneToOne(mappedBy = "potionInventory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private int mpPotionsAmt;
	
	private List<HpPotion> hpPotions;
	private List<MpPotion> mpPotions;

	public PotionInventory(int hpPotionsAmt, int mpPotionsAmt) {
		this.hpPotionsAmt = hpPotionsAmt;
		this.mpPotionsAmt = mpPotionsAmt;
		hpPotions = new ArrayList<HpPotion>();
		mpPotions = new ArrayList<MpPotion>();
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
