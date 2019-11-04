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

public final class LoginFrame extends JFrame {
	private static final long serialVersionUID = -9097625338469065099L;

	private JPanel mainPanel, loadingPanel;
	private JLabel loadingLabel;

	public LoginFrame() {
		super.setTitle("Deadlock Arena");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(new BorderLayout());
		super.setPreferredSize(new Dimension(500, 400));
		super.setResizable(false);
		super.pack();
		super.setLocationRelativeTo(null);
		super.setVisible(true);

		setupMainPanel();
		setupLoadingPanel();
		super.add(this.mainPanel, BorderLayout.CENTER);
	}

	private void setupMainPanel() {
		this.mainPanel = new JPanel(new BorderLayout());
		this.mainPanel.setBackground(Color.BLACK);
		this.mainPanel.setBorder(JavaData.ATTACK_BORDER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		JPanel loginPanel = new JPanel(new GridBagLayout());
		loginPanel.setBackground(Color.BLACK);

		JLabel titleLabel = new JLabel("Deadlock Arena");
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		titleLabel.setForeground(Color.red);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		loginPanel.add(titleLabel, gbc);
		gbc.gridheight = 1;

		gbc.fill = GridBagConstraints.HORIZONTAL;
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(JavaData.BASIC_FONT);
		usernameLabel.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		loginPanel.add(usernameLabel, gbc);

		JTextField usernameField = new JTextField();
		usernameField.setFont(JavaData.BASIC_FONT);
		usernameField.setPreferredSize(new Dimension(200, 30));
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		loginPanel.add(usernameField, gbc);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(JavaData.BASIC_FONT);
		passwordLabel.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		loginPanel.add(passwordLabel, gbc);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setFont(JavaData.BASIC_FONT);
		passwordField.setPreferredSize(new Dimension(200, 30));
		passwordField.setEchoChar('*');
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		loginPanel.add(passwordField, gbc);

		gbc.fill = 0;
		JPanel buttonPanel = new JPanel();
		{
			buttonPanel.setBackground(Color.BLACK);

			JButton registerButton = new JButton("Register");
			registerButton.setFont(JavaData.BASIC_FONT);
			registerButton.setPreferredSize(new Dimension(120, 30));
			buttonPanel.add(registerButton);

			JButton loginButton = new JButton("Login");
			loginButton.setFont(JavaData.BASIC_FONT);
			loginButton.setPreferredSize(new Dimension(120, 30));
			buttonPanel.add(loginButton);

			loginButton.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					loadingLabel.setVisible(true);
				}
			});
		}

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		loginPanel.add(buttonPanel, gbc);
		this.mainPanel.add(loginPanel, BorderLayout.CENTER);
	}

	private void setupLoadingPanel() {
		this.loadingPanel = new JPanel(new BorderLayout());
		this.loadingPanel.setBackground(Color.BLACK);
		this.loadingPanel.setPreferredSize(new Dimension(200, 100));

		this.loadingLabel = new JLabel(
				new ImageIcon(getClass().getResource(JavaData.PICS_PATH + "ajax-loader.gif")),
				JLabel.CENTER);
		loadingLabel.setVisible(false);

		this.loadingPanel.add(loadingLabel, BorderLayout.NORTH);
		this.mainPanel.add(this.loadingPanel, BorderLayout.SOUTH);
	}
}
