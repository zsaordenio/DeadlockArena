package com.deadlockarena.logic;

import javax.swing.JButton;

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
	public void updatePictures() {
		for (int i = 0; i < super.jButtons.length; i++) {
			for (int j = 0; j < super.jButtons [ i ].length; j++) {
				SelectButton jButton = getJButton(i, j);
				if (jButton.isEnabled()) {
					jButton.setIcon(jButton.getNormalImage());
				} else {
					jButton.setIcon(jButton.getGrayedImage());
				}
			}
		}
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
