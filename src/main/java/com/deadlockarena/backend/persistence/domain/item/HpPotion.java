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
public class HpPotion extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 5997993036238160285L;

	private int recovery;
}
