package com.deadlockarena.graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deadlockarena.config.JpaGetData;
import com.deadlockarena.config.SpringUtils;
import com.deadlockarena.constant.JavaData;
import com.deadlockarena.exception.CornerCaseException;
import com.deadlockarena.logic.Grid;
import com.deadlockarena.logic.MessageProcessor;
import com.deadlockarena.persistence.entity.Champion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Component
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -8478413270802946942L;

	@Autowired
	private JpaGetData jpaGetData;

	private AnimationAndSound aAS;
	private GridBagConstraints gbc;

	private SelectButton [ ] selectButtons;
	private SlotButton [ ] slotButtons1, slotButtons2;
	private Stack<JButton [ ]> orderList;

	// TO-DO make the panels their own class
	private JPanel panelWest, panelWest_a, panelWest_b, panelCenter, panelCenter_a, panelCenter_b,
			panelCenter_c, panelCenter_c_a, panelCenter_c_b, panelEast, anelEast_a, panelEast_b,
			panelNorth, panelSouth;

	// panelWest components
	private JLabel iconLabel, stats, championLabel;
	private JTextArea description;

	// panelCenter components
	public DeadButton [ ] deads1, deads2;

	// panelEast components
	private JLabel iconLabel1, iconLabel2, stats1, stats2, description1, description2,
			championLabel1, championLabel2;

	// panelNorth components
	private JLabel titleLabel, playerLabel;

	// panelSouth components
	private CancelButton cancel;

	private JTextArea messages;
	private SkillButton [ ] skillButtons1, skillButtons2;
	private PotionButton hp1, mp1, hp2, mp2;

	private SelectButton current;
	private SlotButton slot;

	public MainFrame() {
		super.setTitle("Deadlock Arena");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(new BorderLayout());
		super.setPreferredSize(new Dimension(1700, 1000));
		super.setVisible(true);

		this.gbc = new GridBagConstraints();
		this.aAS = new AnimationAndSound();
		this.selectButtons = new SelectButton [ JavaData.CHAMPION_COUNT ];
		this.slotButtons1 = new SlotButton [ JavaData.SLOT_COUNT ];
		this.slotButtons2 = new SlotButton [ JavaData.SLOT_COUNT ];
		this.orderList = new Stack<>();
	}

	public void addPanels() {
		panelWest = new JPanel(new BorderLayout());
		panelWest.setBackground(JavaData.DEFAULT_BACKGROUND);
		{
			panelWest_a = new JPanel(new GridBagLayout());
			panelWest_a.setBackground(JavaData.DEFAULT_BACKGROUND);
			panelWest_b = new JPanel(new BorderLayout());
			panelWest_b.setBackground(JavaData.DEFAULT_BACKGROUND);
			{
				iconLabel = new JLabel(new ImageIcon("pics/DefaultIcon.png"));
				iconLabel.setBorder(JavaData.ATTACK_BORDER);
				description = new JTextArea();
				description.setWrapStyleWord(true);
				description.setLineWrap(true);
				description.setFont(JavaData.BASIC_FONT);
				description.setForeground(Color.white);
				description.setBackground(JavaData.DEFAULT_BACKGROUND);
				description.setBorder(JavaData.ATTACK_BORDER);
				stats = new JLabel(JavaData.DEFAULTSTATUSSTRING);
				stats.setFont(JavaData.PANEL_EAST_FONT);
				stats.setForeground(Color.white);
				stats.setBorder(JavaData.ATTACK_BORDER);
				championLabel = new JLabel();
				championLabel.setFont(JavaData.CHAMPION_FONT);
				championLabel.setForeground(Color.white);
				championLabel.setBorder(JavaData.ATTACK_BORDER);
				championLabel.setText("?");
			}
			panelWest_b.add(iconLabel, BorderLayout.WEST);
			panelWest_b.add(description, BorderLayout.CENTER);
			panelWest_b.add(stats, BorderLayout.EAST);
			panelWest_b.add(championLabel, BorderLayout.NORTH);
		}
		panelWest.add(panelWest_a, BorderLayout.NORTH);
		panelWest.add(panelWest_b, BorderLayout.SOUTH);
		// ---------------------------------------------------------------------------
		panelCenter = new JPanel(new BorderLayout());
		panelCenter.setBackground(JavaData.DEFAULT_BACKGROUND);
		{
			panelCenter_a = new JPanel(new GridBagLayout());
			panelCenter_a.setBackground(JavaData.DEFAULT_BACKGROUND);
			panelCenter_b = new JPanel(new GridBagLayout());
			panelCenter_b.setBackground(JavaData.DEFAULT_BACKGROUND);
			panelCenter_c = new JPanel(new BorderLayout());
			panelCenter_c.setBackground(JavaData.DEFAULT_BACKGROUND);
		}
		panelCenter.add(panelCenter_a, BorderLayout.NORTH);
		panelCenter.add(panelCenter_b, BorderLayout.SOUTH);
		panelCenter.add(panelCenter_c, BorderLayout.CENTER);
		// ---------------------------------------------------------------------------
		panelNorth = new JPanel(new BorderLayout());
		panelNorth.setBackground(JavaData.DEFAULT_BACKGROUND);
		{
			titleLabel = new JLabel(" Deadlock Arena");
			titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
			titleLabel.setForeground(Color.red);
		}
		panelNorth.add(titleLabel, BorderLayout.WEST);
		// ---------------------------------------------------------------------------
		panelSouth = new JPanel();
		panelSouth.setBackground(JavaData.DEFAULT_BACKGROUND);
		{
			playerLabel = new JLabel("     Player 1");
			playerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
			playerLabel.setForeground(Color.red);
			cancel = new CancelButton(this);
		}
		panelSouth.add(aAS.getSoundButton());
		panelSouth.add(aAS.getMusicButton());
		panelSouth.add(aAS.getLoopButton());
		panelSouth.add(aAS.getSoundtrackButton());
		panelSouth.add(playerLabel);
		panelSouth.add(cancel);
		// ---------------------------------------------------------------------------
		panelWest.setPreferredSize(new Dimension(500, 500));
		panelCenter.setPreferredSize(new Dimension(700, 500));
		panelSouth.setPreferredSize(new Dimension(500, 50));

		add(panelWest, BorderLayout.WEST);
		add(panelCenter, BorderLayout.CENTER);
		add(panelNorth, BorderLayout.NORTH);
		add(panelSouth, BorderLayout.SOUTH);
	}

	public void addButtons() {
		if (jpaGetData == null) {
			jpaGetData = SpringUtils.ctx.getBean(JpaGetData.class);
		}
		gbc.gridx = 0;
		gbc.gridy = 0;
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 6; i++) {
				try {
					SelectButton sb = new SelectButton(j,
							jpaGetData.evalChampion(JavaData.CHAMPIONS [ j * 6 + i ]));
					sb.setFont(JavaData.BASIC_FONT);
					sb.setPreferredSize(
							new Dimension(JavaData.PIXEL * 4 / 5, JavaData.PIXEL * 4 / 5));

					selectButtons [ j * 6 + i ] = sb;
					panelWest_a.add(sb, gbc);
					gbc.gridy += 1;
				} catch (CornerCaseException e) {
					e.printStackTrace();
				}

			}
			gbc.gridx++;
			gbc.gridy = 0;
		}
		gbc.gridx = 0;
		gbc.gridy = 0;
		for (int player = 1; player <= 2; player++) {
			JPanel p;
			SlotButton [ ] sL;
			if (player == 1) {
				p = panelCenter_a;
				sL = slotButtons1;
			} else {
				p = panelCenter_b;
				sL = slotButtons2;
			}
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 5; j++) {
					SlotButton sb = new SlotButton(player == 1 ? "bottom" : "top");
					sb.setFont(JavaData.BASIC_FONT);
					sb.setEnabled(false);
					sb.setPreferredSize(
							new Dimension(JavaData.PIXEL * 4 / 5, JavaData.PIXEL * 4 / 5));
					sL [ i * 5 + j ] = sb;
					p.add(sb, gbc);
					gbc.gridx++;
				}
				gbc.gridy += 1;
				gbc.gridx = 0;
			}
		}
	}

	public void setPanelEast(SlotButton sB, int player) {
		Champion champion = sB.getChampion();
		if (champion == null) {
			return;
		}
		if (player == 2) {
			iconLabel2.setIcon(new ImageIcon("pics/" + sB.getChampion() + "Icon.png"));
			championLabel2.setText(sB.getChampion().toString());
			stats2.setText("<html>" + "<font color=" + champion.evalColor() + ">HP: "
					+ champion.getCurrentHp() + " / " + champion.getMaxHp() + "</font><br/>"
					+ "MP: " + champion.getMaxMp() + " / " + champion.getMaxMp() + "<br/>"
					+ "Damage: " + champion.getMinDmg() + " - " + champion.getMaxDmg() + "<br/>"
					+ "Defense: " + champion.getDefense() + "<br/>" + "Critical: "
					+ champion.getCritical() + "%<br/>" + "Dodge: " + champion.getDodge() + "%"
					+ "</html>");
			hp2.onChampion(sB);
			mp2.onChampion(sB);
		} else {
			iconLabel1.setIcon(new ImageIcon("pics/" + sB.getChampion() + "Icon.png"));
			championLabel1.setText(sB.getChampion().toString());
			stats1.setText("<html>" + "<font color=" + champion.evalColor() + ">HP: "
					+ champion.getCurrentHp() + " / " + champion.getMaxHp() + "</font><br/>"
					+ "MP: " + champion.getMaxMp() + " / " + champion.getMaxMp() + "<br/>"
					+ "Damage: " + champion.getMinDmg() + " - " + champion.getMaxDmg() + "<br/>"
					+ "Defense: " + champion.getDefense() + "<br/>" + "Critical: "
					+ champion.getCritical() + "%<br/>" + "Dodge: " + champion.getDodge() + "%"
					+ "</html>");
			hp1.onChampion(sB);
			mp1.onChampion(sB);
		}
	}

	public void clearPanelEast(int player) {
		if (player == 2) {
			championLabel2.setText("?");
			iconLabel2.setIcon(new ImageIcon("pics/DefaultIcon.png"));
			stats2.setText(JavaData.STATUSSTRING);
			hp2.offchampion();
			mp2.offchampion();
		} else {
			championLabel1.setText("?");
			iconLabel1.setIcon(new ImageIcon("pics/DefaultIcon.png"));
			stats1.setText(JavaData.STATUSSTRING);
			hp1.offchampion();
			mp1.offchampion();
		}
	}

	public void switchPlayerLabel(int player) {
		playerLabel.setText("     Player " + (player == 2 ? 2 : 1));
	}

	public void updateButtonPictures() {
		for (SelectButton sB : selectButtons) {
			if (sB.isEnabled()) {
				sB.setIcon(sB.getNormalImage());
			} else {
				sB.setIcon(sB.getGrayedImage());
			}
		}
		for (SlotButton sB : slotButtons1) {
			if (sB.isEnabled()) {
				sB.setIcon(sB.getNormalImage());
			} else {
				sB.setIcon(sB.getGrayedImage());
			}
		}
		for (SlotButton sB : slotButtons2) {
			if (sB.isEnabled()) {
				sB.setIcon(sB.getNormalImage());
			} else {
				sB.setIcon(sB.getGrayedImage());
			}
		}
	}

//	public void evenAllListeners() {
//		for (SlotButton currentButton : slotButtons1) {
//			for (int i = 2; i < currentButton.getMouseListeners().length; i++)
//				currentButton.removeMouseListener(currentButton.getMouseListeners() [ i ]);
//			if (currentButton.getChampion() != null) {
//				currentButton.addMouseListener(currentButton.getML2());
//			}
//		}
//		for (SlotButton currentButton : slotButtons2) {
//			for (int i = 2; i < currentButton.getMouseListeners().length; i++)
//				currentButton.removeMouseListener(currentButton.getMouseListeners() [ i ]);
//			if (currentButton.getChampion() != null) {
//				currentButton.addMouseListener(currentButton.getML2());
//			}
//		}
//	}

	public void clearAllBorders() {
		for (int i = 0; i < JavaData.SLOT_COUNT; i++) {
			slotButtons1 [ i ].setBorder(JavaData.DEFAULT_BORDER);
			slotButtons2 [ i ].setBorder(JavaData.DEFAULT_BORDER);
		}
	}

//	public void resetListeners() {
//		for (int i = 0; i < JavaData.SLOT_COUNT; i++) {
//			if (slotButtons1 [ i ].getBorder().equals(JavaData.MOVE_BORDER)) {
//				if (slotButtons1 [ i ].getChampion() == null) {
//					slotButtons1 [ i ].alterMouseAdapter0_3();
//				} else {
//					slotButtons1 [ i ].alterMouseAdapter0_2();
//				}
//			} else if (slotButtons1 [ i ].getBorder().equals(JavaData.ATTACK_BORDER)) {
//				slotButtons1 [ i ].alterMouseAdapter2_4();
//			} else if (slotButtons1 [ i ].getChampion() != null) {
//				slotButtons1 [ i ].alterMouseAdapter0_2();
//			}
//			// ---------------------------------------------------------------
//			if (slotButtons2 [ i ].getBorder().equals(JavaData.MOVE_BORDER)) {
//				if (slotButtons2 [ i ].getChampion() != null) {
//					slotButtons2 [ i ].alterMouseAdapter0_2();
//				} else {
//					slotButtons2 [ i ].alterMouseAdapter0_3();
//				}
//			} else if (slotButtons2 [ i ].getBorder().equals(JavaData.ATTACK_BORDER)) {
//				slotButtons2 [ i ].alterMouseAdapter2_4();
//			} else if (slotButtons2 [ i ].getChampion() != null) {
//				slotButtons2 [ i ].alterMouseAdapter0_2();
//			}
//		}
//	}

	public void disableAll(int player) {
		for (int i = 0; i < JavaData.SLOT_COUNT; i++) {
			if (player == 2) {
				slotButtons1 [ i ].setEnabled(false);
			} else {
				slotButtons2 [ i ].setEnabled(false);
			}
		}
	}

	public void clearSkillButtons(int player) {
		if (player == 2) {
			for (int i = 0; i < skillButtons2.length; i++) {
				skillButtons2 [ i ].setSkillButton(null, -1);
			}
		} else {
			for (int i = 0; i < skillButtons1.length; i++) {
				skillButtons1 [ i ].setSkillButton(null, -1);
			}
		}
	}

}
