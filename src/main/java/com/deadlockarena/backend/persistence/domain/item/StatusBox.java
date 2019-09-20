package com.deadlockarena.backend.persistence.domain.item;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.deadlockarena.backend.persistence.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(schema = "PVO")
@EqualsAndHashCode(callSuper = true)
public class StatusBox extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -9091165808780653117L;

	// damage over time
	private int poisoned; // chemist, assassin
	private int burned; // chemist, pyromancer
	private int bleed; // assassin
	private int decayed; // screamer
	private int drained; // reaper

	// disability
	private int taunted; // cyborg
	private int binded; // sniper
	private int blinded; // holyknight
	private int dazed; // trickster
	private int silenced; // screamer, holyknight
	private int stunned; // berserker

	// debuff
	private int cursed; // screamer
	private int shocked; // knight
	private int intimidated; // berserker
	private int infatuate; // dancer's charm
	private int division; // barbarian's skull crush // defenses in half
	private int exhaustion; // // attacks in half

	// buff
	private int deflect; // swordmage
	private int ironSkinned; // cyborg
	private int decoy; // trickster
	private int encouraged; // dragoon
	private int screenProtection; // bard
	private int bodyBless; // holyknight
	private int soulBless; // holyknight

	// recovery
	private int rejuvenate; // bard
	private int regenerate; // guardian
}
