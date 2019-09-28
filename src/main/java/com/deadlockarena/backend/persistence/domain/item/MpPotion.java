package com.deadlockarena.backend.persistence.domain.item;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.deadlockarena.backend.persistence.domain.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class MpPotion implements Serializable {
	private static final long serialVersionUID = -1868118488485731732L;

	private int recovery;
}
