package com.deadlockarena.graphics;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

public class RegisterFrame extends UserFrame {
	private static final long serialVersionUID = 608897382992804907L;

	public RegisterFrame(String titleName) {
		super(titleName);
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
		super.addComponent(gbc, "Confirm Password:", 4);
		super.addComponent(gbc, "Email Address:", 5);
		super.addComponent(gbc, "First Name:", 6);
		super.addComponent(gbc, "Last Name:", 7);

		gbc.fill = 0;
		super.addButtonPanel(gbc);

		super.mainPanel.add(super.interfacePanel, BorderLayout.CENTER);
		super.add(super.mainPanel, BorderLayout.CENTER);
	}

}
