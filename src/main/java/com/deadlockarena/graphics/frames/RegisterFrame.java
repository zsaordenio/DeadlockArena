package com.deadlockarena.graphics.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.validation.FieldValidator;

public class RegisterFrame extends UserFrame {
	private static final long serialVersionUID = 608897382992804907L;

	private JTextField usernameField, passwordField, confirmPasswordField, emailAddressField,
			firstNameField, lastNameField;

	private JTextArea validationMessages;

	public RegisterFrame() {
		super(new Dimension(600, 800));
		super.setTitle("Register");
		this.validationMessages = new JTextArea();
		this.validationMessages.setPreferredSize(new Dimension(600, 180));
		this.validationMessages.setBackground(JavaData.DEFAULT_BACKGROUND);
		this.validationMessages.setForeground(Color.RED);
		this.validationMessages.setFont(JavaData.SMALL_FONT);
		this.validationMessages.setWrapStyleWord(true);
		this.validationMessages.setLineWrap(true);

		super.mainPanel.add(validationMessages, BorderLayout.NORTH);
	}

	@Override
	public void setupMainPanel(GridBagConstraints gbc) {
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		super.addTitleLabel(gbc);

		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.usernameField = super.addComponent(gbc, "Username:", 2);
		this.passwordField = super.addComponent(gbc, "Password:", 3);
		this.confirmPasswordField = super.addComponent(gbc, "Confirm Password:", 4);
		this.emailAddressField = super.addComponent(gbc, "Email Address:", 5);
		this.firstNameField = super.addComponent(gbc, "First Name:", 6);
		this.lastNameField = super.addComponent(gbc, "Last Name:", 7);

		gbc.fill = 0;
		super.addButtonPanel(gbc);

		super.mainPanel.add(super.interfacePanel, BorderLayout.CENTER);
		super.add(super.mainPanel, BorderLayout.CENTER);

		super.registerButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mE) {
				validateFields();
			}
		});
	}

	public void validateFields() {
		this.removeAllBorders();
		FieldValidator validator = new FieldValidator();
		StringBuilder stringBuilder = new StringBuilder();
		if (!validator.isValidUsername(usernameField.getText().trim().toUpperCase())) {
			stringBuilder.append("Username can only contain alphabetic characters and numbers\n");
			usernameField.setBorder(JavaData.VALIDATION_BORDER);
		}
		if (usernameField.getText().trim().toUpperCase().length() < 8
				|| usernameField.getText().trim().toUpperCase().length() > 20) {
			stringBuilder.append("Username must be between 8 and 20 characters\n");
			usernameField.setBorder(JavaData.VALIDATION_BORDER);
		}
		// ------------------------------------------------------------------------------------
		if (!validator.isValidPassword(passwordField.getText())
				|| !validator.isValidPassword(confirmPasswordField.getText())) {
			stringBuilder.append("Password must contain an uppercase, a lowercase, a number, and a symbol: @$!%*?&\n");
			passwordField.setBorder(JavaData.VALIDATION_BORDER);
			confirmPasswordField.setBorder(JavaData.VALIDATION_BORDER);
		}
		if (passwordField.getText().length() < 8 || passwordField.getText().length() > 20) {
			stringBuilder.append("Password must be between 8 and 20 characters\n");
			passwordField.setBorder(JavaData.VALIDATION_BORDER);
			confirmPasswordField.setBorder(JavaData.VALIDATION_BORDER);
		}
		if (!passwordField.getText().equals(confirmPasswordField.getText())) {
			stringBuilder.append("Password fields must match\n");
			passwordField.setBorder(JavaData.VALIDATION_BORDER);
			confirmPasswordField.setBorder(JavaData.VALIDATION_BORDER);
		}
		// ------------------------------------------------------------------------------------
		if (!validator.isValidEmail(emailAddressField.getText().trim())) {
			stringBuilder.append("Invalid Email Address\n");
			emailAddressField.setBorder(JavaData.VALIDATION_BORDER);
		}
		// ------------------------------------------------------------------------------------
		if (!validator.isValidName(firstNameField.getText().trim().toUpperCase())) {
			stringBuilder.append("Invalid First Name\n");
			firstNameField.setBorder(JavaData.VALIDATION_BORDER);
		}
		if (!validator.isValidName(lastNameField.getText().trim().toUpperCase())) {
			stringBuilder.append("Invalid Last Name\n");
			lastNameField.setBorder(JavaData.VALIDATION_BORDER);
		}

		this.validationMessages.setText(stringBuilder.toString());
	}

	private void removeAllBorders() {
		for (Field f : RegisterFrame.class.getDeclaredFields()) {
			if (f.getType() == JTextField.class) {
				try {
					((JTextField) f.get(this)).setBorder(JavaData.DEFAULT_BORDER);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
