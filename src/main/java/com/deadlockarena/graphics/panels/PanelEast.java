package com.deadlockarena.graphics.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.graphics.buttons.PotionButton;
import com.deadlockarena.graphics.buttons.SkillButton;
import com.deadlockarena.graphics.buttons.SlotButton;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PanelEast extends JPanel {

	private static final long serialVersionUID = -5044872379478427452L;

	private JPanel panelTop, panelBottom;

	// panelEast components
	private JLabel iconLabel1, iconLabel2, status1, status2, description1, description2,
			championLabel1, championLabel2;
	private PotionButton hpPotionButton1, mpPotionButton1, hpPotionButton2, mpPotionButton2;
	private SkillButton [ ] skillButtons1, skillButtons2;

	public PanelEast() {
		super(new GridLayout(1, 1));
		super.setPreferredSize(new Dimension(700, 500));
		// TO-DO find a better layout
		this.panelTop = new JPanel(null); 
		this.panelBottom = new JPanel(null);
		this.panelTop.setBackground(JavaData.DEFAULT_BACKGROUND);
		this.panelBottom.setBackground(JavaData.DEFAULT_BACKGROUND);

		super.add(panelTop, BorderLayout.NORTH);
		super.add(panelBottom, BorderLayout.SOUTH);
	}

	public void setPanelEast(SlotButton slotButton, int player) {
		Champion champion = slotButton.getChampion();
		if (champion == null) {
			return;
		}
		if (player == 2) {
			this.iconLabel2.setIcon(new ImageIcon(getClass()
					.getResource(JavaData.PICS_PATH + champion.getChampion() + "Icon.png")));
			this.championLabel2.setText(slotButton.getChampion().toString());
			this.status2.setText(JavaData.getStatsText(champion));
			this.hpPotionButton2.onChampion(slotButton);
			this.mpPotionButton2.onChampion(slotButton);
		} else {
			iconLabel1.setIcon(new ImageIcon(getClass()
					.getResource(JavaData.PICS_PATH + champion.getChampion() + "Icon.png")));
			this.championLabel1.setText(slotButton.getChampion().toString());
			this.status1.setText(JavaData.getStatsText(champion));
			this.hpPotionButton1.onChampion(slotButton);
			this.mpPotionButton1.onChampion(slotButton);
		}
	}

	public void clearPanelEast(int player) {
		if (player == 2) {
			this.championLabel2.setText("");
			this.iconLabel2.setIcon(
					new ImageIcon(getClass().getResource(JavaData.PICS_PATH + "DefaultIcon.png")));
			this.status2.setText(JavaData.STATUS_STRING);
			this.hpPotionButton2.offchampion();
			this.mpPotionButton2.offchampion();
		} else {
			this.championLabel1.setText("");
			this.iconLabel1.setIcon(
					new ImageIcon(getClass().getResource(JavaData.PICS_PATH + "DefaultIcon.png")));
			this.status1.setText(JavaData.STATUS_STRING);
			this.hpPotionButton1.offchampion();
			this.mpPotionButton1.offchampion();
		}
	}

	public void clearSkillButtons(int player) {
		if (player == 2) {
			for (int i = 0; i < skillButtons2.length; i++) {
				skillButtons2 [ i ].setSkillButton(null, -1);
			}
		} else {
			for (int i = 0; i < skillButtons1.length; i++) {
				skillButtons1 [ i ].setSkillButton(null, -1);
			}
		}
	}

}
