package com.deadlockarena.graphics.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.graphics.AnimationAndSound;
import com.deadlockarena.graphics.buttons.CancelButton;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PanelSouth extends JPanel {
	private static final long serialVersionUID = -7528143263696509126L;

	private JLabel playerLabel;
	private CancelButton cancelButton;
	//TO-DO split into animation and sound
	private AnimationAndSound aAS;

	public PanelSouth() {
		super(new FlowLayout());
		super.setPreferredSize(new Dimension(500, 50));
		super.setBackground(JavaData.DEFAULT_BACKGROUND);

		this.playerLabel = new JLabel("     Player 1");
		this.playerLabel.setForeground(JavaData.DEFAULT_FOREGROUND);
		this.playerLabel.setFont(JavaData.CHAMPION_FONT);
		this.cancelButton = new CancelButton();
		this.aAS = new AnimationAndSound();

		super.add(aAS.getSoundButton());
		super.add(aAS.getMusicButton());
		super.add(aAS.getLoopButton());
		super.add(aAS.getSoundtrackButton());
		super.add(playerLabel);
		super.add(cancelButton);
	}

	public void switchPlayerLabel(int player) {
		this.playerLabel.setText("     Player " + (player == 2 ? 2 : 1));
	}
}
