package com.deadlockarena.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.deadlockarena.constant.JavaData;

import lombok.Data;

@Data
@Entity
@Table(schema = "PVO")
public class PotionInventory implements Serializable {
	private static final long serialVersionUID = -419261549452638522L;

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "HP_POTIONS_AMT")
	private int hpPotionsAmt;

	@Column(name = "MP_POTIONS_AMT")
	private int mpPotionsAmt;

	@Column(name = "CHAMPION_STRING")
	private String championString;

	@Transient
	private List<HpPotion> hpPotions;

	@Transient
	private List<MpPotion> mpPotions;

	public PotionInventory(int hpPotionsAmt, int mpPotionsAmt) {
		this.hpPotionsAmt = hpPotionsAmt;
		this.mpPotionsAmt = mpPotionsAmt;
		hpPotions = new ArrayList<HpPotion>();
		mpPotions = new ArrayList<MpPotion>();
		generatePotions();
	}

	private void generatePotions() {
		for (int i = 0; i < hpPotionsAmt; i++) {
			hpPotions.add(new HpPotion(JavaData.RANDOM.nextInt((900 - 400) + 1) + 400));
		}
		for (int i = 0; i < mpPotionsAmt; i++) {
			mpPotions.add(new MpPotion(JavaData.RANDOM.nextInt((50 - 25) + 1) + 25));
		}
	}

}
