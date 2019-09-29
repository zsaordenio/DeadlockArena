package com.deadlockarena.backend.persistence.domain.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.deadlockarena.backend.persistence.domain.item.HpPotion;
import com.deadlockarena.backend.persistence.domain.item.MpPotion;
import com.deadlockarena.constant.JavaData;
import com.deadlockarena.graphics.AppPrincipalFrame;
import com.deadlockarena.graphics.SlotButton;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode
@Table(schema = "PVO")
public class Champion implements Serializable {
	private static final long serialVersionUID = 5195526083757043733L;

	@Id
	@Column(name = "ID")
	protected String id;

	@Column(name = "CHAMPION")
	protected String champion;
	@Column(name = "DESCRIPTION")
	protected String description;
	@Column(name = "LOGIC")
	protected int logic;

	@Column(name = "MAX_HP")
	protected int maxHp;
	@Column(name = "MAX_MP")
	protected int maxMp;
	@Column(name = "MIN_DMG")
	protected int minDmg;
	@Column(name = "MAX_DMG")
	protected int maxDmg;
	@Column(name = "DEFENSE")
	protected int defense;
	@Column(name = "CRITICAL")
	protected int critical;
	@Column(name = "DODGE")
	protected int dodge;

	@Column(name = "SKILL1")
	protected String skill1;
	@Column(name = "SKILL2")
	protected String skill2;
	@Column(name = "SKILL3")
	protected String skill3;
	@Column(name = "SKILL4")
	protected String skill4;
	@Column(name = "SKILL5")
	protected String skill5;

	@Column(name = "SKILL1_DESCRIPTION")
	protected String skill1Description;
	@Column(name = "SKILL2_DESCRIPTION")
	protected String skill2Description;
	@Column(name = "SKILL3_DESCRIPTION")
	protected String skill3Description;
	@Column(name = "SKILL4_DESCRIPTION")
	protected String skill4Description;
	@Column(name = "SKILL5_DESCRIPTION")
	protected String skill5Description;

	@Column(name = "SKILL1_MP_COST")
	protected int skill1MpCost;
	@Column(name = "SKILL2_MP_COST")
	protected int skill2MpCost;
	@Column(name = "SKILL3_MP_COST")
	protected int skill3MpCost;
	@Column(name = "SKILL4_MP_COST")
	protected int skill4MpCost;
	@Column(name = "SKILL5_MP_COST")
	protected int skill5MpCost;

	@Column(name = "SKILL1_CD")
	protected int skill1CD;
	@Column(name = "SKILL2_CD")
	protected int skill2CD;
	@Column(name = "SKILL3_CD")
	protected int skill3CD;
	@Column(name = "SKILL4_CD")
	protected int skill4CD;
	@Column(name = "SKILL5_CD")
	protected int skill5CD;

	@Transient
	protected int currentHp;
	@Transient
	protected int currentMp;
	@Transient
	protected int currentDmgMin;
	@Transient
	protected int currentDmgMax;
	@Transient
	protected int currentDefense;
	@Transient
	protected int currentCritical;
	@Transient
	protected int currentDodge;

	@Transient
	protected int currentSkill1CD;
	@Transient
	protected int currentSkill2CD;
	@Transient
	protected int currentSkill3CD;
	@Transient
	protected int currentSkill4CD;
	@Transient
	protected int currentSkill5CD;

	@Transient
	protected PotionInventory potionInventory;
	@Transient
	protected StatusBox statusBox;

	public int [ ] drinkPotion(boolean hp) {
		if (hp) {
			List<HpPotion> inv = potionInventory.getHpPotions();
			if (inv.isEmpty())
				return null;
			HpPotion p = inv.get(0);
			int amountRecovered = p.getRecovery();
			currentHp = currentHp + amountRecovered >= maxHp ? maxHp : currentHp + amountRecovered;
			inv.remove(0);
			return new int [ ] { amountRecovered , currentHp };
		} else {
			List<MpPotion> inv = potionInventory.getMpPotions();
			if (inv.isEmpty())
				return null;
			MpPotion p = inv.get(0);
			int amountRecovered = p.getRecovery();
			currentMp = currentMp + amountRecovered >= maxMp ? maxMp : currentMp + amountRecovered;
			inv.remove(0);
			return new int [ ] { amountRecovered , currentMp };
		}
	}

	public void attack(AppPrincipalFrame aPF, SlotButton targetButton) {
		Champion target = targetButton.getChampion();
		int damage = calculateNextDamage();

		boolean dodged = target.isDodgedHit();
		boolean critical = isCriticalHit();

		int finalDamage;
		if (!dodged) {
			finalDamage = damage - target.getDefense();
			if (critical)
				finalDamage *= 2;
			if (finalDamage < 0) // handles negative damages
				finalDamage = 0;
			target.setCurrentHp(target.getCurrentHp() - finalDamage);
			aPF.getGrid().checkForDeads(aPF);
			aPF.getAAS().shakeButton(targetButton);
			aPF.getAAS().playSound("melee");
			aPF.getMP().generateMove(aPF.getMessages(), aPF.getMove());
			aPF.getMP().generateMessage(aPF.getMessages(), this, target, new int [ ] { finalDamage },
					new boolean [ ] { critical });
		} else {
			aPF.getAAS().playSound("dodge");
			aPF.getMP().generateMove(aPF.getMessages(), aPF.getMove());
			aPF.getMP().generateMessage(aPF.getMessages(), this, target);
		}

	}

	public int calculateNextDamage() {
		return JavaData.random.nextInt((maxDmg - minDmg) + 1) + minDmg;
	}

	public boolean isCriticalHit() {
		return JavaData.random.nextInt(101) < critical;
	}

	public boolean isDodgedHit() {
		return JavaData.random.nextInt(101) < dodge;
	}

	public String evalColor() {
		double hpRatio = (double) currentHp / maxHp;
		if (hpRatio <= .25)
			return "red";
		else if (hpRatio <= .50)
			return "yellow";
		else
			return "white";
	}

	public boolean isDead() {
		if (currentHp < 0)
			currentHp = 0;
		return currentHp <= 0;
	}

	public double evalFraction(int i) {
		double frac = 0.0;
		switch (i) {
		case 0:
			frac = (double) currentSkill1CD / skill1CD;
			break;
		case 1:
			frac = (double) currentSkill2CD / skill2CD;
			break;
		case 2:
			frac = (double) currentSkill3CD / skill3CD;
			break;
		case 3:
			frac = (double) currentSkill4CD / skill4CD;
			break;
		case 4:
			frac = (double) currentSkill5CD / skill5CD;
			break;
		}
		return frac;
	}

}
