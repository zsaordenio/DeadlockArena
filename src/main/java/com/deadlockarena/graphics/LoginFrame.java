package com.deadlockarena.graphics;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

public final class LoginFrame extends UserFrame {
	private static final long serialVersionUID = -9097625338469065099L;

	public LoginFrame(String titleName) {
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

		gbc.fill = 0;
		super.addButtonPanel(gbc);

		super.mainPanel.add(super.interfacePanel, BorderLayout.CENTER);
		super.add(super.mainPanel, BorderLayout.CENTER);
	}

}
