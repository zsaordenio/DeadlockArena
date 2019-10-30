package com.deadlockarena.persistence.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
public class MpPotion implements Serializable {
	private static final long serialVersionUID = -1868118488485731732L;

	private int recovery;
}
