package com.deadlockarena.backend.persistence.domain.entity;

import java.io.Serializable;

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

	private int recovery;
}
