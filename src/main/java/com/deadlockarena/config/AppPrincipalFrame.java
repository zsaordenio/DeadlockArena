package com.deadlockarena.config;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Stack;

import javax.swing.*;

import org.springframework.stereotype.Component;

@Component
public class AppPrincipalFrame extends JFrame {

	private static final long serialVersionUID = -8478413270802946942L;

	  public static GridBagConstraints gbc;

	    private static JPanel panelWest, panelWest_a, panelWest_b, panelCenter,
	            panelCenter_a, panelCenter_b, panelCenter_c, panelCenter_c_a, panelCenter_c_b,
	            panelEast, panelEast_a, panelEast_b, panelNorth, panelSouth;

	    //panelWest components
	    public static JLabel iconLabel, stats, heroLabel;
	    public static JTextArea description;

	    //panelCenter components
	  //  public static DeadButton[] deads1, deads2;

	    //panelEast components
	    public static JLabel iconLabel1, iconLabel2,
	            stats1, stats2,
	            description1, description2,
	            heroLabel1, heroLabel2;

	    //panelNorth components
	    public static JLabel titleLabel, playerLabel;

	    //panelSouth components
	 //   public CancelButton cancel;
	 //   public BooleanButton soundButton, musicButton, loopButton;
	    public JButton soundtrackButton;

	    public String soundtrack;
	    public JTextArea messages;
	 //   public SkillButton[] skillButtons1, skillButtons2;
	 //   public PotionButton hp1, mp1, hp2, mp2;

	 //   public SelectButton[] selectList;
	 //   public SlotButton[] slotList1;
	 //   public SlotButton[] slotList2;
	    public Stack<JButton[]> orderList;

	 //   public SelectButton current;
	 //   public SlotButton slot;
	    public boolean player; // 1 is false, 2 is true
	    public int totalCount; // 0-18
	    public int move;
	    public int currentCapTurn;
	    
	public AppPrincipalFrame() {
		domain();
        creation();
        initFields();
//        addPanels();
//        addButtons();
		initUI();
	}

	public void domain() {
		try {
			FileWriter writer = new FileWriter("systemConfig.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write("\nUsername: " + System.getProperty("user.name") + "\nDimension: "
					+ Toolkit.getDefaultToolkit().getScreenSize());
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    private void creation() {
    	setTitle("Deadlock Arena");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1700, 1000));
        setVisible(true);
    }
    
    private void initFields() {
        gbc = new GridBagConstraints();
        totalCount = 0;
        move = 0;
        currentCapTurn = 1;
       // AnimationAndSound.changedMusic = false;
       // selectList = new SelectButton[JavaData.HEROESCOUNT];
       // slotList1 = new SlotButton[JavaData.SLOTSCOUNT];
       // slotList2 = new SlotButton[JavaData.SLOTSCOUNT];
        orderList = new Stack<>();
       // JavaData.initData();
       // AnimationAndSound.initSoundTracks();
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
