package com.deadlockarena.logic;

import com.deadlockarena.graphics.buttons.SelectButton;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SelectGrid extends Grid {

	public SelectGrid(SelectButton [ ] [ ] selectButtons) {
		super(selectButtons);
	}

	@Override
	public SelectButton getJButton(int i, int j) {
		return (SelectButton) super.jButtons [ i ] [ j ];
	}

	@Override
	public SelectButton [ ] [ ] getJButtons() {
		return (SelectButton [ ] [ ]) super.jButtons;
	}

	@Override
	public void addMouseListener(int mLNumber) {
		for (int i = 0; i < super.jButtons.length; i++) {
			for (int j = 0; j < super.jButtons [ i ].length; j++) {
				this.getJButton(i, j).addMouseListener(this.getJButton(i, j).getML());
			}
		}
	}

}
