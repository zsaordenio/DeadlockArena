package com.deadlockarena.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.deadlockarena.Game;
import com.deadlockarena.config.SpringUtils;
import com.deadlockarena.constant.JavaData;
import com.deadlockarena.logic.Coordinate;
import com.deadlockarena.logic.Grid;
import com.deadlockarena.logic.SelectGrid;
import com.deadlockarena.logic.SlotGrid;
import com.deadlockarena.persistence.bootstrap.JpaGetData;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -8478413270802946942L;

	private JpaGetData jpaGetData;
	private AnimationAndSound aAS;

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



	public MainFrame() {
		super.setTitle("Deadlock Arena");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(new BorderLayout());
		super.setPreferredSize(new Dimension(1700, 1000));
		// super.setLocationRelativeTo(null);
		super.pack();
		super.setVisible(true);

		this.aAS = new AnimationAndSound();
		this.orderList = new Stack<>();
		this.jpaGetData = SpringUtils.jgd;
	}

	public void addPanels() {
		panelWest = new JPanel(new BorderLayout());
		panelWest.setBackground(JavaData.DEFAULT_BACKGROUND);
		{
			panelWest_a = new JPanel(new GridBagLayout());
			panelWest_a.setBackground(JavaData.DEFAULT_BACKGROUND);
			panelWest_b = new JPanel(new GridBagLayout());
			panelWest_b.setBackground(JavaData.DEFAULT_BACKGROUND);
			{
				iconLabel = new JLabel();
				stats = new JLabel("");
				stats.setFont(JavaData.PANEL_EAST_FONT);
				stats.setForeground(Color.white);
				description = new JTextArea();
				description.setWrapStyleWord(true);
				description.setLineWrap(true);
				description.setFont(JavaData.BASIC_FONT);
				description.setForeground(Color.white);
				description.setBackground(JavaData.DEFAULT_BACKGROUND);
				championLabel = new JLabel();
				championLabel.setFont(JavaData.CHAMPION_FONT);
				championLabel.setForeground(Color.white);
				championLabel.setText("");
			}
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5,5,5,5);
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			gbc.weightx = 0;
			panelWest_b.add(iconLabel, gbc);
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.gridwidth = 2;
			gbc.weightx = .5;
			gbc.ipadx = 30;
			panelWest_b.add(description, gbc);
			gbc.gridx = 3;
			gbc.gridy = 1;
			gbc.gridwidth = 2;
			gbc.weightx = 0;
			gbc.ipadx = 80;
			panelWest_b.add(stats, gbc);
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 5;
			gbc.ipadx = 0;
			panelWest_b.add(championLabel, gbc);
			panelWest_b.setBorder(JavaData.ATTACK_BORDER);
			panelWest_b.setPreferredSize(new Dimension(600,250));
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
		panelWest.setPreferredSize(new Dimension(600, 500));
		panelCenter.setPreferredSize(new Dimension(800, 500));
		panelSouth.setPreferredSize(new Dimension(500, 50));

		super.add(panelWest, BorderLayout.WEST);
		super.add(panelCenter, BorderLayout.CENTER);
		super.add(panelNorth, BorderLayout.NORTH);
		super.add(panelSouth, BorderLayout.SOUTH);
	}

	public void addSelectButtons(Game game, SelectGrid selectGrid) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		for (int i = 0; i < selectGrid.getJButtons().length; i++) {
			for (int j = 0; j < selectGrid.getJButtons() [ i ].length; j++) {
				SelectButton selectButton = new SelectButton(game,
						jpaGetData.evalChampion(JavaData.CHAMPIONS [ i ] [ j ]));
				selectGrid.setJButton(i, j, selectButton);
				this.panelWest_a.add(selectButton, gbc);
				gbc.gridx++;
			}
			gbc.gridy++;
			gbc.gridx = 0;
		}
	}

	public void addSlotButtons(Game game, SlotGrid slotGrid1, SlotGrid slotGrid2) {
		for (int player = 1; player <= 2; player++) {
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			JPanel jPanel = player == 2 ? this.panelCenter_a : this.panelCenter_b;
			SlotGrid slotGrid = player == 2 ? slotGrid2 : slotGrid1;
			for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
				for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
					SlotButton slotButton = new SlotButton(game, player == 2 ? "top" : "bottom",
							new Coordinate(i, j));
					slotGrid.setJButton(i, j, slotButton);
					jPanel.add(slotButton, gbc);
					gbc.gridx++;
				}
				gbc.gridy++;
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
			this.iconLabel2.setIcon(new ImageIcon("pics/" + sB.getChampion() + "Icon.png"));
			this.championLabel2.setText(sB.getChampion().toString());
			this.stats2.setText(JavaData.getStatsText(champion));
			this.hp2.onChampion(sB);
			this.mp2.onChampion(sB);
		} else {
			iconLabel1.setIcon(new ImageIcon("pics/" + sB.getChampion() + "Icon.png"));
			this.championLabel1.setText(sB.getChampion().toString());
			this.stats1.setText(JavaData.getStatsText(champion));
			this.hp1.onChampion(sB);
			this.mp1.onChampion(sB);
		}
	}

	public void clearPanelEast(int player) {
		if (player == 2) {
			championLabel2.setText("?");
			iconLabel2.setIcon(new ImageIcon("pics/DefaultIcon.png"));
			stats2.setText(JavaData.STATUS_STRING);
			hp2.offchampion();
			mp2.offchampion();
		} else {
			championLabel1.setText("?");
			iconLabel1.setIcon(new ImageIcon("pics/DefaultIcon.png"));
			stats1.setText(JavaData.STATUS_STRING);
			hp1.offchampion();
			mp1.offchampion();
		}
	}

	public void switchPlayerLabel(int player) {
		playerLabel.setText("     Player " + (player == 2 ? 2 : 1));
	}

	// TO-DO move to grid??
	public void updateButtonPictures(SlotGrid slotGrid1, SlotGrid slotGrid2,
			SelectGrid selectGrid) {
		for (int i = 0; i < selectGrid.getJButtons().length; i++) {
			for (int j = 0; j < selectGrid.getJButtons() [ i ].length; j++) {
				SelectButton selectButton = selectGrid.getJButton(i, j);
				if (selectButton.isEnabled()) {
					selectButton.setIcon(selectButton.getNormalImage());
				} else {
					selectButton.setIcon(selectButton.getGrayedImage());
				}
			}
		}
		for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
			for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
				SlotButton slotButton1 = slotGrid1.getJButton(i, j);
				if (slotButton1.isEnabled()) {
					slotButton1.setIcon(slotButton1.getNormalImage());
				} else {
					slotButton1.setIcon(slotButton1.getGrayedImage());
				}
				SlotButton slotButton2 = slotGrid2.getJButton(i, j);
				if (slotButton2.isEnabled()) {
					slotButton2.setIcon(slotButton2.getNormalImage());
				} else {
					slotButton2.setIcon(slotButton2.getGrayedImage());
				}
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

	public void resetListeners(SlotGrid grid1, SlotGrid grid2) {
		for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
			for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
				SlotButton slotButton1 = grid1.getJButton(i, j);
				if (slotButton1.getBorder().equals(JavaData.MOVE_BORDER)) {
					if (slotButton1.getChampion() == null) {
						slotButton1.alterMouseAdapter0_3();
					} else {
						slotButton1.alterMouseAdapter0_2();
					}
				} else if (slotButton1.getBorder().equals(JavaData.ATTACK_BORDER)) {
					slotButton1.alterMouseAdapter2_4();
				} else if (slotButton1.getChampion() != null) {
					slotButton1.alterMouseAdapter0_2();
				}
				// ---------------------------------------------------------------
				SlotButton slotButton2 = grid2.getJButton(i, j);
				if (slotButton2.getBorder().equals(JavaData.MOVE_BORDER)) {
					if (slotButton2.getChampion() != null) {
						slotButton2.alterMouseAdapter0_2();
					} else {
						slotButton2.alterMouseAdapter0_3();
					}
				} else if (slotButton2.getBorder().equals(JavaData.ATTACK_BORDER)) {
					slotButton2.alterMouseAdapter2_4();
				} else if (slotButton2.getChampion() != null) {
					slotButton2.alterMouseAdapter0_2();
				}
			}
		}
	}

	// TO-DO check player before calling
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

	public void displayPreview(Champion champion, ImageIcon imageIcon) {
		this.iconLabel.setIcon(imageIcon);
		this.stats.setText(JavaData.getStatsText(champion));
		this.description.setText(champion.getDescription());
		this.championLabel.setText(champion.getChampion());

	}

	public void unDisplayPreview(Color color) {
		this.iconLabel.setIcon(null);
		this.stats.setText("");
		this.description.setText("");
		this.championLabel.setText("");
	}
}
