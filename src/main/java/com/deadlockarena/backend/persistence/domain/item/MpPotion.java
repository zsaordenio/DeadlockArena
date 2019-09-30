package com.deadlockarena.backend.persistence.domain.item;

import com.deadlockarena.backend.persistence.domain.base.BaseEntity;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MpPotion extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -1868118488485731732L;

	private int recovery;
}
