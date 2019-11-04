package com.deadlockarena.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JButton;

import com.deadlockarena.constant.JavaData;

public final class LoginFrame extends UserFrame {
	private static final long serialVersionUID = -9097625338469065099L;

	private JButton loginButton;
	
	public LoginFrame() {
		super(new Dimension(600,600));
		super.setTitle("Login");
		this.addLoginButton();
	}

	@Override
	public void setupMainPanel(GridBagConstraints gbc) {
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		super.addTitleLabel(gbc);

		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		super.addComponent(gbc, "Username:", 2);
		super.addComponent(gbc, "Password:", 3);

		gbc.fill = 0;
		super.addButtonPanel(gbc);

		super.mainPanel.add(super.interfacePanel, BorderLayout.CENTER);
		super.add(super.mainPanel, BorderLayout.CENTER);
	}
	
	private void addLoginButton() {
		this.loginButton = new JButton("Login");
		this.loginButton.setFont(JavaData.BASIC_FONT);
		this.loginButton.setPreferredSize(new Dimension(120, 30));
		super.buttonPanel.add(this.loginButton);
	}

}
