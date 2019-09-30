package com.deadlockarena.graphics;

import com.deadlockarena.config.JpaGetData;

import java.awt.*;
import java.io.Serializable;

import javax.swing.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppPrincipalFrame extends JFrame implements CommandLineRunner, Serializable {
  private static final long serialVersionUID = -8478413270802946942L;

  private final JpaGetData jpaGetData;

  @Autowired
  public AppPrincipalFrame(JpaGetData jpaGetData) {
    initUI();
    this.jpaGetData = jpaGetData;
  }

  @Override
  public void run(String... arg0) {
    EventQueue.invokeLater(() -> {
      try {
        new AppPrincipalFrame(jpaGetData).setVisible(true);
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
