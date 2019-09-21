package com.deadlockarena.backend.persistence.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.deadlockarena.backend.persistence.domain.base.BaseEntity;
import com.deadlockarena.backend.persistence.domain.item.PotionInventory;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(schema = "PVO")
public class Champion extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 5195526083757043733L;

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

	/*
	protected int currentHp;
	protected int currentMp;
	protected int currentDmgMin;
	protected int currentDmgMax;
	protected int currentDefense;
	protected int currentCritical;
	protected int currentDodge;*/

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

	/*protected int currentSkill1CD;
	protected int currentSkill2CD;
	protected int currentSkill3CD;
	protected int currentSkill4CD;
	protected int currentSkill5CD;*/

	
	protected int inventory;

	protected int statusBox;
}
