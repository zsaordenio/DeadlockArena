package com.deadlockarena.backend.persistence.domain.item;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HpPotion implements Serializable {
	private static final long serialVersionUID = 5997993036238160285L;

	private int recovery;
}
