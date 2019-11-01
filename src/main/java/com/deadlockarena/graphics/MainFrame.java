package com.deadlockarena.graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;

import com.deadlockarena.Game;
import com.deadlockarena.config.SpringUtils;
import com.deadlockarena.constant.JavaData;
import com.deadlockarena.logic.Coordinate;
import com.deadlockarena.logic.Grid;
import com.deadlockarena.logic.MainLogic;
import com.deadlockarena.persistence.bootstrap.JpaGetData;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -8478413270802946942L;

	@Autowired
	private JpaGetData jpaGetData;

	private AnimationAndSound aAS;
	private GridBagConstraints gbc;

	private SelectButton [ ] [ ] selectButtons;
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
		this.selectButtons = new SelectButton [ JavaData.SELECT_ROW_COUNT ] [ JavaData.SELECT_COL_COUNT ];
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
				stats = new JLabel(JavaData.DEFAULT_STATUS_STRING);
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

	public void addSelectButtons() {
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		for (int i = 0; i < selectButtons.length; i++) {
			for (int j = 0; j < selectButtons [ i ].length; j++) {
				SelectButton sb = new SelectButton();
				this.selectButtons [ i ] [ j ] = sb;
				this.panelWest_a.add(sb, gbc);
				this.gbc.gridy++;
			}
			this.gbc.gridx++;
			this.gbc.gridy = 0;
		}
	}

	public void populateSelectButtons(int player, Grid grid1, Grid grid2) {
		if (this.jpaGetData == null) {
			this.jpaGetData = SpringUtils.jgd;
		}
		for (int i = 0; i < selectButtons.length; i++) {
			for (int j = 0; j < selectButtons [ i ].length; j++) {
				this.selectButtons [ i ] [ j ].populate(
						jpaGetData.evalChampion(
								JavaData.CHAMPIONS [ i * JavaData.SELECT_ROW_COUNT + j ]),
						player, grid1, grid2, this);
			}
		}
	}

	public void addSlotButtons(Grid grid1, Grid grid2) {
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		for (int player = 1; player <= 2; player++) {
			JPanel jPanel;
			SlotButton [ ] [ ] slotButtons;
			if (player == 1) {
				jPanel = panelCenter_a;
				slotButtons = grid1.getSlotButtons();
			} else {
				jPanel = panelCenter_b;
				slotButtons = grid2.getSlotButtons();
			}
			for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
				for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
					SlotButton slotButton = new SlotButton(player == 1 ? "bottom" : "top",
							new Coordinate(i, j));
					slotButtons [ i ] [ j ] = slotButton;
					jPanel.add(slotButton, gbc);
					gbc.gridy++;
				}
				gbc.gridx++;
				gbc.gridy = 0;
			}
		}
	}

	public void populateSlotButtons(Game g, Grid grid) {
		for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
			for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
				grid.getSlotButton(i, j).populate(jpaGetData.evalChampion(
						JavaData.CHAMPIONS [ i * JavaData.SELECT_ROW_COUNT + j ]), this, g);
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

	public void updateButtonPictures(Grid grid1, Grid grid2) {
		for (int i = 0; i < selectButtons.length; i++) {
			for (int j = 0; j < selectButtons [ i ].length; j++) {
				SelectButton selectButton = selectButtons [ i ] [ j ];
				if (selectButton.isEnabled()) {
					selectButton.setIcon(selectButton.getNormalImage());
				} else {
					selectButton.setIcon(selectButton.getGrayedImage());
				}
			}
		}
		for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
			for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
				SlotButton slotButton1 = grid1.getSlotButton(i, j);
				if (slotButton1.isEnabled()) {
					slotButton1.setIcon(slotButton1.getNormalImage());
				} else {
					slotButton1.setIcon(slotButton1.getGrayedImage());
				}
				SlotButton slotButton2 = grid2.getSlotButton(i, j);
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

	public void clearAllBorders(Grid grid1, Grid grid2) {
		for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
			for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
				grid1.getSlotButtons() [ i ] [ j ].setBorder(JavaData.DEFAULT_BORDER);
				grid2.getSlotButtons() [ i ] [ j ].setBorder(JavaData.DEFAULT_BORDER);
			}
		}
	}

	public void resetListeners(Grid grid1, Grid grid2) {
		for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
			for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
				SlotButton slotButton1 = grid1.getSlotButtons() [ i ] [ j ];
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
				SlotButton slotButton2 = grid2.getSlotButtons() [ i ] [ j ];
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

//	public void disableAll(int player) {
//		for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
//			for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
//				if (player == 2) {
//					this.slotButtons2 [ i ] [ j ].setEnabled(false);
//				} else {
//					this.slotButtons1 [ i ] [ j ].setEnabled(false);
//				}
//			}
//		}
//	}
	public void disableAll(Grid grid) {
		for (int i = 0; i < grid.getSlotButtons().length; i++) {
			for (int j = 0; j < grid.getSlotButtons() [ i ].length; j++) {
				grid.getSlotButtons() [ i ] [ j ].setEnabled(false);
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
