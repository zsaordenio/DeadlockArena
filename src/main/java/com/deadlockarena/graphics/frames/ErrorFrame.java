package com.deadlockarena.graphics.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.deadlockarena.constant.JavaData;

public class ErrorFrame extends JFrame {
	private static final long serialVersionUID = 8327907479291314653L;

	public ErrorFrame(String msg) {
		super();
		super.setLayout(new BorderLayout());
		super.setTitle("Error");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setPreferredSize(new Dimension(1200, 300));
		super.setResizable(false);

		JPanel mainPanel = new JPanel(new BorderLayout());
		JTextArea errorLabel = new JTextArea(msg);
		errorLabel.setBackground(JavaData.DEFAULT_BACKGROUND);
		errorLabel.setForeground(Color.white);
		errorLabel.setWrapStyleWord(true);
		errorLabel.setLineWrap(true);
		errorLabel.setFont(JavaData.BASIC_FONT);
		mainPanel.add(errorLabel, BorderLayout.CENTER);
		super.add(mainPanel, BorderLayout.CENTER);
		
		super.pack();
		super.setLocationRelativeTo(null);
		super.setVisible(true);
	}

}
