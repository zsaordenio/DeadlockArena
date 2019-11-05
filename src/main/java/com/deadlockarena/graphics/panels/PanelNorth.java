package com.deadlockarena.graphics.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.deadlockarena.constant.JavaData;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PanelNorth extends JPanel {
	private static final long serialVersionUID = -5481287840768070722L;

	private JLabel titleLabel;

	public PanelNorth() {
		super(new BorderLayout());
		super.setBackground(JavaData.DEFAULT_BACKGROUND);

		this.titleLabel = new JLabel(" Deadlock Arena");
		this.titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
		this.titleLabel.setForeground(Color.red);
		this.add(titleLabel, BorderLayout.WEST);
	}

}
