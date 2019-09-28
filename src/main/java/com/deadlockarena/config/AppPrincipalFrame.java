package com.deadlockarena.config;

import java.awt.*;

import javax.swing.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppPrincipalFrame extends JFrame {

	private static final long serialVersionUID = -8478413270802946942L;

	public AppPrincipalFrame() {
		initUI();
	}

	private void initUI() {
		var quitButton = new JButton("Welcome to Deadlock Arena. Run Success");
		quitButton.addActionListener((event) -> {
			System.exit(0);
		});

		setTitle("Deadlock Arena");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		createLayout(quitButton);
	}

	private void createLayout(JComponent... arg) {
		var pane = getContentPane();
		var groupLayout = new GroupLayout(pane);
		pane.setLayout(groupLayout);

		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addComponent(arg [ 0 ]));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addComponent(arg [ 0 ]));
	}

}
