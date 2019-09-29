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

import com.deadlockarena.backend.persistence.domain.entity.Champion;
import com.deadlockarena.config.JpaGetData;
import com.deadlockarena.constant.JavaData;
import com.deadlockarena.exception.RemainderException;
import com.deadlockarena.logic.Grid;
import com.deadlockarena.logic.MessageProcessor;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class AppPrincipalFrame extends JFrame {

	private static final long serialVersionUID = -8478413270802946942L;

	@Autowired private JpaGetData jpaGetData;
	private AnimationAndSound aAS;
	private GridBagConstraints gbc;
	private MessageProcessor mP;
	private Grid grid;

	private JPanel panelWest, panelWest_a, panelWest_b, panelCenter, panelCenter_a, panelCenter_b,
			panelCenter_c, panelCenter_c_a, panelCenter_c_b, panelEast, panelEast_a, panelEast_b,
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

	private SelectButton [ ] selectList;
	private SlotButton [ ] slotList1;
	private SlotButton [ ] slotList2;
	private Stack<JButton [ ]> orderList;

	private SelectButton current;
	private SlotButton slot;
	private int player; // 1 is false, 2 is true
	private int totalCount; // 0-18
	private int move;
	private int currentCapTurn;

	public AppPrincipalFrame() {
		domain();
		creation();
		initFields();
		addPanels();
		addButtons();
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
		aAS = new AnimationAndSound();

		selectList = new SelectButton [ JavaData.championESCOUNT ];
		slotList1 = new SlotButton [ JavaData.SLOTSCOUNT ];
		slotList2 = new SlotButton [ JavaData.SLOTSCOUNT ];
		orderList = new Stack<>();
	}

	private void addPanels() {
		panelWest = new JPanel(new BorderLayout());
		panelWest.setBackground(JavaData.DEFAULTBACKGROUND);
		{
			panelWest_a = new JPanel(new GridBagLayout());
			panelWest_a.setBackground(JavaData.DEFAULTBACKGROUND);
			panelWest_b = new JPanel(new BorderLayout());
			panelWest_b.setBackground(JavaData.DEFAULTBACKGROUND);
			{
				iconLabel = new JLabel(new ImageIcon("pics/DefaultIcon.png"));
				iconLabel.setBorder(JavaData.ATTACKBORDER);
				description = new JTextArea();
				description.setWrapStyleWord(true);
				description.setLineWrap(true);
				description.setFont(JavaData.BASICFONT);
				description.setForeground(Color.white);
				description.setBackground(JavaData.DEFAULTBACKGROUND);
				description.setBorder(JavaData.ATTACKBORDER);
				stats = new JLabel(JavaData.DEFAULTSTATUSSTRING);
				stats.setFont(JavaData.PANELEASTFONT);
				stats.setForeground(Color.white);
				stats.setBorder(JavaData.ATTACKBORDER);
				championLabel = new JLabel();
				championLabel.setFont(JavaData.championFONT);
				championLabel.setForeground(Color.white);
				championLabel.setBorder(JavaData.ATTACKBORDER);
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
		panelCenter.setBackground(JavaData.DEFAULTBACKGROUND);
		{
			panelCenter_a = new JPanel(new GridBagLayout());
			panelCenter_a.setBackground(JavaData.DEFAULTBACKGROUND);
			panelCenter_b = new JPanel(new GridBagLayout());
			panelCenter_b.setBackground(JavaData.DEFAULTBACKGROUND);
			panelCenter_c = new JPanel(new BorderLayout());
			panelCenter_c.setBackground(JavaData.DEFAULTBACKGROUND);
		}
		panelCenter.add(panelCenter_a, BorderLayout.NORTH);
		panelCenter.add(panelCenter_b, BorderLayout.SOUTH);
		panelCenter.add(panelCenter_c, BorderLayout.CENTER);
		// ---------------------------------------------------------------------------
		panelNorth = new JPanel(new BorderLayout());
		panelNorth.setBackground(JavaData.DEFAULTBACKGROUND);
		{
			titleLabel = new JLabel(" Deadlock Arena");
			titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
			titleLabel.setForeground(Color.red);
		}
		panelNorth.add(titleLabel, BorderLayout.WEST);
		// ---------------------------------------------------------------------------
		panelSouth = new JPanel();
		panelSouth.setBackground(JavaData.DEFAULTBACKGROUND);
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

	private void addButtons() {
		gbc.gridx = 0;
		gbc.gridy = 0;
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 6; i++) {
				try {
					System.out.println("2222222222222222222222222222222222" + jpaGetData);
					SelectButton sb = new SelectButton(j,
							jpaGetData.evalChampion(JavaData.championES [ j * 6 + i ]));
					sb.setFont(JavaData.BASICFONT);
					sb.setPreferredSize(
							new Dimension(JavaData.PIXEL * 4 / 5, JavaData.PIXEL * 4 / 5));

					selectList [ j * 6 + i ] = sb;
					panelWest_a.add(sb, gbc);
					gbc.gridy += 1;
				} catch (RemainderException e) {
					e.printStackTrace();
				}

			}
			gbc.gridx++;
			gbc.gridy = 0;
		}
		gbc.gridx = 0;
		gbc.gridy = 0;
		for (int player = 1; player < 3; player++) {
			JPanel p;
			SlotButton [ ] sL;
			if (player == 1) {
				p = panelCenter_a;
				sL = slotList1;
			} else {
				p = panelCenter_b;
				sL = slotList2;
			}
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 5; j++) {
					SlotButton sb = new SlotButton(player);
					sb.setFont(JavaData.BASICFONT);
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

	public void setPanelEast(SlotButton sB, int  player) {
		Champion h = sB.getChampion();
		if (h == null)
			return;
		if (player == 2) {
			iconLabel2.setIcon(new ImageIcon("pics/" + sB.getChampion() + "Icon.png"));
			championLabel2.setText(sB.getChampion().toString());
			String color = h.evalColor();
			stats2.setText("<html>" + "<font color=" + color + ">HP: " + h.getCurrentHp() + " / "
					+ h.getMaxHp() + "</font><br/>" + "MP: " + h.getMaxMp() + " / " + h.getMaxMp()
					+ "<br/>" + "Damage: " + h.getMinDmg() + " - " + h.getMaxDmg() + "<br/>"
					+ "Defense: " + h.getDefense() + "<br/>" + "Critical: " + h.getCritical()
					+ "%<br/>" + "Dodge: " + h.getDodge() + "%" + "</html>");
			hp2.onchampion(sB);
			mp2.onchampion(sB);
		} else {
			iconLabel1.setIcon(new ImageIcon("pics/" + sB.getChampion() + "Icon.png"));
			championLabel1.setText(sB.getChampion().toString());
			String color = h.evalColor();
			stats1.setText("<html>" + "<font color=" + color + ">HP: " + h.getCurrentHp() + " / "
					+ h.getMaxHp() + "</font><br/>" + "MP: " + h.getMaxMp() + " / " + h.getMaxMp()
					+ "<br/>" + "Damage: " + h.getMinDmg() + " - " + h.getMaxDmg() + "<br/>"
					+ "Defense: " + h.getDefense() + "<br/>" + "Critical: " + h.getCritical()
					+ "%<br/>" + "Dodge: " + h.getDodge() + "%" + "</html>");
			hp1.onchampion(sB);
			mp1.onchampion(sB);
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

	public void switchPlayer() {
		if (player == 1)
			player = 2;
		else
			player = 1;
	}

	public void switchPlayerLabel() {
		playerLabel.setText("     Player " + (player == 1? 1 : 2));
	}

	public void updateButtonPictures() {
		for (SelectButton sB : selectList) {
			if (sB.isEnabled())
				sB.setImage(sB.getNormalImage());
			else
				sB.setImage(sB.getGrayedImage());
		}
		for (SlotButton sB : slotList1) {
			if (sB.isEnabled())
				sB.setImage(sB.getNormalImage());
			else
				sB.setImage(sB.getGrayedImage());
		}
		for (SlotButton sB : slotList2) {
			if (sB.isEnabled())
				sB.setImage(sB.getNormalImage());
			else
				sB.setImage(sB.getGrayedImage());
		}
	}

	public void evenAllListeners() {
		for (SlotButton currentButton : slotList1) {
			for (int i = 2; i < currentButton.getMouseListeners().length; i++)
				currentButton.removeMouseListener(currentButton.getMouseListeners() [ i ]);
			if (currentButton.getChampion() != null)
				currentButton.addMouseListener(currentButton.getML2());
		}
		for (SlotButton currentButton : slotList2) {
			for (int i = 2; i < currentButton.getMouseListeners().length; i++)
				currentButton.removeMouseListener(currentButton.getMouseListeners() [ i ]);
			if (currentButton.getChampion() != null)
				currentButton.addMouseListener(currentButton.getML2());
		}
	}

	public void clearAllBorders() {
		for (int i = 0; i < JavaData.SLOTSCOUNT; i++) {
			slotList1 [ i ].setBorder(JavaData.DEFAULTBORDER);
			slotList2 [ i ].setBorder(JavaData.DEFAULTBORDER);
		}
	}

	public void resetListeners() {
		for (int i = 0; i < JavaData.SLOTSCOUNT; i++) {
			if (slotList1 [ i ].getBorder().equals(JavaData.MOVEBORDER)) {
				if (slotList1 [ i ].getChampion() == null)
					slotList1 [ i ].alterMouseAdapter0_3();
				else
					slotList1 [ i ].alterMouseAdapter0_2();
			} else if (slotList1 [ i ].getBorder().equals(JavaData.ATTACKBORDER))
				slotList1 [ i ].alterMouseAdapter2_4();
			else if (slotList1 [ i ].getChampion() != null)
				slotList1 [ i ].alterMouseAdapter0_2();
			// ---------------------------------------------------------------
			if (slotList2 [ i ].getBorder().equals(JavaData.MOVEBORDER)) {
				if (slotList2 [ i ].getChampion() != null)
					slotList2 [ i ].alterMouseAdapter0_2();
				else
					slotList2 [ i ].alterMouseAdapter0_3();
			} else if (slotList2 [ i ].getBorder().equals(JavaData.ATTACKBORDER))
				slotList2 [ i ].alterMouseAdapter2_4();
			else if (slotList2 [ i ].getChampion() != null)
				slotList2 [ i ].alterMouseAdapter0_2();
		}
	}

	public void disableAll() {
		for (int i = 0; i < JavaData.SLOTSCOUNT; i++) {
			if (player == 2) {
				slotList1 [ i ].setEnabled(false);
			} else {
				slotList2 [ i ].setEnabled(false);
			}
		}
	}

	public void evalTurns() {
		move++;
		if (move == currentCapTurn) {
			slot = null;
			updateAllCoolDowns();
			switchListeners();
			clearSkillButtons(player);
			clearPanelEast(player);
			if (currentCapTurn < JavaData.CAPTURN)
				currentCapTurn++;
			move = 0;
		}
	}

	public void updateAllCoolDowns() {
		if (player == 2) {
			for (int i = 0; i < getSlotList2().length; i++)
				if (getSlotList2() [ i ].getChampion() != null) {
					Champion h = getSlotList2() [ i ].getChampion();
					h.setCurrentSkill1CD(h.getCurrentSkill1CD() - 1);
					h.setCurrentSkill2CD(h.getCurrentSkill2CD() - 1);
					h.setCurrentSkill3CD(h.getCurrentSkill3CD() - 1);
					h.setCurrentSkill4CD(h.getCurrentSkill4CD() - 1);
					h.setCurrentSkill5CD(h.getCurrentSkill5CD() - 1);
				}
		} else {
			for (int i = 0; i < getSlotList1().length; i++)
				if (getSlotList1() [ i ].getChampion() != null) {
					Champion h = getSlotList1() [ i ].getChampion();
					h.setCurrentSkill1CD(h.getCurrentSkill1CD() - 1);
					h.setCurrentSkill2CD(h.getCurrentSkill2CD() - 1);
					h.setCurrentSkill3CD(h.getCurrentSkill3CD() - 1);
					h.setCurrentSkill4CD(h.getCurrentSkill4CD() - 1);
					h.setCurrentSkill5CD(h.getCurrentSkill5CD() - 1);
				}
		}
	}

	public void switchListeners() {
		for (int i = 0; i < JavaData.SLOTSCOUNT; i++) {
			if (player == 2) {
				if (slotList1 [ i ].getChampion() != null)
					slotList1 [ i ].alterMouseAdapter0_4();
				slotList2 [ i ].setEnabled(false);
				slotList1 [ i ].setEnabled(true);
			} else {
				if (slotList2 [ i ].getChampion() != null)
					slotList2 [ i ].alterMouseAdapter0_4();
				slotList2 [ i ].setEnabled(true);
				slotList1 [ i ].setEnabled(false);
			}
		}
		mP.endTurn(messages);
		switchPlayer();
		switchPlayerLabel();
		mP.nextPlayer(messages, player);
	}

	private static void activateRob() {
		try {
			Robot rob = new Robot();
			rob.setAutoDelay(1);
			for (int i = 0; i < JavaData.coords.length; i++) {
				for (int count = 0; (MouseInfo.getPointerInfo().getLocation()
						.getX() != JavaData.coords [ i ] [ 0 ] * 3 / 2
						|| MouseInfo.getPointerInfo().getLocation()
								.getY() != JavaData.coords [ i ] [ 1 ] * 3 / 2)
						&& count < 15; count++) {
					rob.mouseMove((int) JavaData.coords [ i ] [ 0 ] * 3 / 2,
							(int) JavaData.coords [ i ] [ 1 ] * 3 / 2);
				}
				rob.mousePress(InputEvent.BUTTON1_MASK);
				rob.mouseRelease(InputEvent.BUTTON1_MASK);
			}
		} catch (AWTException exc) {
			exc.printStackTrace();
		}
	}

	public void clearSkillButtons(int player) {
		if (player == 2)
			for (int i = 0; i < skillButtons2.length; i++)
				skillButtons2 [ i ].setSkillButton(null, -1);
		else
			for (int i = 0; i < skillButtons1.length; i++)
				skillButtons1 [ i ].setSkillButton(null, -1);
	}

}
