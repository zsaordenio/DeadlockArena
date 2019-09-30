package com.deadlockarena.config;

import java.awt.*;

import javax.swing.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppPrincipalFrame extends JFrame implements CommandLineRunner {

	public AppPrincipalFrame() {
		initUI();
	}

	@Override
	public void run(String... arg0) {
		EventQueue.invokeLater(() -> {
			try {
				new AppPrincipalFrame().setVisible(true);
			} catch (Exception e) {
				LOG.error("An error occurred instantiating AppPrincipalFrame", e);
			}
		});
	}

	private void initUI() {
		var quitButton = new JButton("Welcome to Deadlock Arena. Run Success");
		quitButton.addActionListener(event -> System.exit(0));

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
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addComponent(arg[0]));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addComponent(arg[0]));
	}
}
