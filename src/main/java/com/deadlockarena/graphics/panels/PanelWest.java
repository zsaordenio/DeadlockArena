package com.deadlockarena.graphics.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.deadlockarena.Game;
import com.deadlockarena.config.SpringUtils;
import com.deadlockarena.constant.JavaData;
import com.deadlockarena.graphics.buttons.SelectButton;
import com.deadlockarena.logic.SelectGrid;
import com.deadlockarena.persistence.bootstrap.JpaGetData;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PanelWest extends JPanel {
	private static final long serialVersionUID = 3428482793791303013L;

	private JpaGetData jpaGetData;

	private JPanel panelTop, panelBottom;

	private JLabel iconLabel, championLabel, status;
	private JTextArea description;

	public PanelWest() {
		super(new BorderLayout());
		super.setPreferredSize(new Dimension(600, 500));
		super.setBackground(JavaData.DEFAULT_BACKGROUND);
		this.jpaGetData = SpringUtils.jgd;

		this.setupPanelTop();
		this.setupPanelBottom();
	}

	private void setupPanelTop() {
		this.panelTop = new JPanel(new GridBagLayout());
		this.panelTop.setBackground(JavaData.DEFAULT_BACKGROUND);
		super.add(this.panelTop, BorderLayout.NORTH);
	}

	private void setupPanelBottom() {
		this.panelBottom = new JPanel(new GridBagLayout());
		this.panelBottom.setPreferredSize(new Dimension(600, 250));
		this.panelBottom.setBackground(JavaData.DEFAULT_BACKGROUND);
		this.panelBottom.setBorder(JavaData.ATTACK_BORDER);

		this.iconLabel = new JLabel();

		this.championLabel = new JLabel();
		this.championLabel.setForeground(JavaData.DEFAULT_FOREGROUND);
		this.championLabel.setFont(JavaData.CHAMPION_FONT);
		this.championLabel.setText("");

		this.status = new JLabel();
		this.status.setForeground(JavaData.DEFAULT_FOREGROUND);
		this.status.setFont(JavaData.PANEL_EAST_FONT);

		this.description = new JTextArea();
		this.description.setBackground(JavaData.DEFAULT_BACKGROUND);
		this.description.setForeground(JavaData.DEFAULT_FOREGROUND);
		this.description.setFont(JavaData.BASIC_FONT);
		this.description.setWrapStyleWord(true);
		this.description.setLineWrap(true);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		this.panelBottom.add(this.iconLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = .5;
		gbc.ipadx = 30;
		this.panelBottom.add(this.description, gbc);

		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0;
		gbc.ipadx = 80;
		this.panelBottom.add(this.status, gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		gbc.ipadx = 0;
		this.panelBottom.add(this.championLabel, gbc);

		super.add(this.panelBottom, BorderLayout.SOUTH);
	}

	public void addSelectButtons(Game game, SelectGrid selectGrid) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		for (int i = 0; i < selectGrid.getJButtons().length; i++) {
			for (int j = 0; j < selectGrid.getJButtons() [ i ].length; j++) {
				SelectButton selectButton = new SelectButton(game,
						jpaGetData.evalChampion(JavaData.CHAMPIONS [ i ] [ j ]));
				selectGrid.setJButton(i, j, selectButton);
				this.panelTop.add(selectButton, gbc);
				gbc.gridx++;
			}
			gbc.gridy++;
			gbc.gridx = 0;
		}
	}

	public void displayPreview(Champion champion, ImageIcon imageIcon) {
		this.iconLabel.setIcon(imageIcon);
		this.status.setText(JavaData.getStatsText(champion));
		this.description.setText(champion.getDescription());
		this.championLabel.setText(champion.getChampion());

	}

	public void unDisplayPreview(Color color) {
		this.iconLabel.setIcon(null);
		this.status.setText("");
		this.description.setText("");
		this.championLabel.setText("");
	}
}
