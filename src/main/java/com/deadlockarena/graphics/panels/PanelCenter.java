package com.deadlockarena.graphics.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.deadlockarena.Game;
import com.deadlockarena.constant.JavaData;
import com.deadlockarena.graphics.buttons.DeadButton;
import com.deadlockarena.graphics.buttons.SlotButton;
import com.deadlockarena.logic.Coordinate;
import com.deadlockarena.logic.SlotGrid;

public class PanelCenter extends JPanel {
	private static final long serialVersionUID = 1503169593552440629L;

	private JPanel panelTop, panelCenter, panelBottom;
	public DeadButton [ ] deadsChampions1, deadsChampions2;

	public PanelCenter() {
		super(new BorderLayout());
		super.setPreferredSize(new Dimension(800, 500));
		super.setBackground(JavaData.DEFAULT_BACKGROUND);

		this.setupPanelTop();
		this.setupPanelCenter();
		this.setupPanelBottom();
	}

	public void setupPanelTop() {
		this.panelTop = new JPanel(new GridBagLayout());
		this.panelTop.setBackground(JavaData.DEFAULT_BACKGROUND);
		super.add(this.panelTop, BorderLayout.NORTH);
	}

	public void setupPanelCenter() {
		this.panelCenter = new JPanel(new BorderLayout());
		this.panelCenter.setBackground(JavaData.DEFAULT_BACKGROUND);
		super.add(this.panelCenter, BorderLayout.CENTER);
	}

	public void setupPanelBottom() {
		this.panelBottom = new JPanel(new GridBagLayout());
		this.panelBottom.setBackground(JavaData.DEFAULT_BACKGROUND);
		super.add(this.panelBottom, BorderLayout.SOUTH);
	}

	public void addSlotButtons(Game game, SlotGrid slotGrid1, SlotGrid slotGrid2) {
		for (int player = 1; player <= 2; player++) {
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			JPanel jPanel = player == 2 ? this.panelTop : this.panelBottom;
			SlotGrid slotGrid = player == 2 ? slotGrid2 : slotGrid1;
			for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
				for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
					SlotButton slotButton = new SlotButton(game, player == 2 ? "top" : "bottom",
							new Coordinate(i, j));
					slotGrid.setJButton(i, j, slotButton);
					jPanel.add(slotButton, gbc);
					gbc.gridx++;
				}
				gbc.gridy++;
				gbc.gridx = 0;
			}
		}
	}
}
