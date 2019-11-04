package com.deadlockarena.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.deadlockarena.constant.JavaData;

public class ErrorFrame extends JFrame {
	private static final long serialVersionUID = 8327907479291314653L;

	public ErrorFrame(String msg) {
		super.setTitle("Error");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(new BorderLayout());
		super.setPreferredSize(new Dimension(500, 200));
		super.setResizable(false);
		super.pack();
		super.setLocationRelativeTo(null);
		super.setVisible(true);

		JPanel mainPanel = new JPanel(new GridBagLayout());
		{
			mainPanel.setBackground(Color.BLACK);
			mainPanel.setBorder(JavaData.ATTACK_BORDER);
			JLabel errorLabel = new JLabel(msg);
			{
				errorLabel.setForeground(Color.WHITE);
				errorLabel.setFont(JavaData.BASIC_FONT);
				mainPanel.add(errorLabel);
			}
			super.add(mainPanel, BorderLayout.CENTER);
		}
	}

}
