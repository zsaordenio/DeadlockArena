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
import javax.swing.SwingConstants;

import com.deadlockarena.constant.JavaData;

public abstract class UserFrame extends JFrame{
	private static final long serialVersionUID = 6910328681026851809L;
	
	protected JPanel mainPanel, loadingPanel, interfacePanel;
	protected JLabel loadingLabel;
	
	public UserFrame(String titleName) {
		super.setTitle(titleName);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(new BorderLayout());
		super.setPreferredSize(new Dimension(600, 600));
		super.setResizable(false);
		super.pack();
		super.setLocationRelativeTo(null);
		super.setVisible(true);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		this.interfacePanel = new JPanel(new GridBagLayout());
		this.interfacePanel.setBackground(Color.BLACK);
		this.interfacePanel.setPreferredSize(new Dimension(600,500));
		
		this.mainPanel = new JPanel(new BorderLayout());
		this.mainPanel.setBackground(Color.BLACK);
		this.mainPanel.setBorder(JavaData.ATTACK_BORDER);
	
		this.setupMainPanel(gbc);
		this.setupLoadingPanel();
	}
	
	public abstract void setupMainPanel(GridBagConstraints gbc);
	
	protected void addTitleLabel(GridBagConstraints gbc) {
		JLabel titleLabel = new JLabel("Deadlock Arena");
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		titleLabel.setForeground(Color.red);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		this.interfacePanel.add(titleLabel, gbc);
		gbc.gridheight = 1;
	}

	protected void addComponent(GridBagConstraints gbc, String labelName, int gridy) {
		gbc.gridy = gridy;
		JLabel label = new JLabel(labelName, SwingConstants.RIGHT);
		label.setFont(JavaData.BASIC_FONT);
		label.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		this.interfacePanel.add(label, gbc);

		JTextField field;
		if (labelName.contains("Password")) {
			field = new JPasswordField();
			((JPasswordField) field).setEchoChar('*');
		} else {
			field = new JTextField();
		}
		field.setFont(JavaData.BASIC_FONT);
		field.setPreferredSize(new Dimension(200, 30));
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		this.interfacePanel.add(field, gbc);
	}

	protected void addButtonPanel(GridBagConstraints gbc) {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);

		JButton registerButton = new JButton("Register");
		registerButton.setFont(JavaData.BASIC_FONT);
		registerButton.setPreferredSize(new Dimension(120, 30));
		buttonPanel.add(registerButton);

		registerButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				loadingLabel.setVisible(true);
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 2;
		this.interfacePanel.add(buttonPanel, gbc);
	}
	
	protected void setupLoadingPanel() {
		this.loadingPanel = new JPanel(new BorderLayout());
		this.loadingPanel.setBackground(Color.BLACK);
		this.loadingPanel.setPreferredSize(new Dimension(600, 100));

		this.loadingLabel = new JLabel(
				new ImageIcon(getClass().getResource(JavaData.PICS_PATH + "ajax-loader.gif")),
				JLabel.CENTER);
		loadingLabel.setVisible(false);

		this.loadingPanel.add(loadingLabel, BorderLayout.NORTH);
		this.mainPanel.add(this.loadingPanel, BorderLayout.SOUTH);
	}
}
