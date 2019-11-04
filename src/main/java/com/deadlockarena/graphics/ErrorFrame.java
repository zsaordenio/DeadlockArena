package com.deadlockarena.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
