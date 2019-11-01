package com.deadlockarena.graphics;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.deadlockarena.Game;
import com.deadlockarena.constant.JavaData;
import com.deadlockarena.logic.Coordinate;
import com.deadlockarena.logic.Grid;
import com.deadlockarena.persistence.bootstrap.JpaGetData;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
public class SlotButton extends JButton {
	private static final long serialVersionUID = 1436902681342190255L;

	private String position;
	private Coordinate coordinate;

	private boolean selected;
	private Champion champion;

	private JpaGetData jpaGetData;

	private JLabel championLabel, championPicture;
	private ImageIcon normalImage, grayedImage;

	private MouseListener mL1, mL2, mL3, mL4;

	public SlotButton(String position, Coordinate coordinate) {
		super.setFont(JavaData.BASIC_FONT);
		super.setEnabled(false);
		super.setPreferredSize(new Dimension(JavaData.PIXEL * 4 / 5, JavaData.PIXEL * 4 / 5));
		this.position = position;
		this.coordinate = coordinate;
		this.selected = false;
	}
	


	public void populate(final Champion champion, final MainFrame mainFrame, final Game game) {

		// Selecting champion
		this.mL1 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SlotButton.super.isEnabled()) {
					mainFrame.getAAS().playSound("select");
					SlotButton.this.setSelected(true);

					SlotButton.this.setChampion(jpaGetData
							.evalChampion(mainFrame.getCurrent().getChampion().getChampion()));

					SlotButton.super.setBackground(mainFrame.getCurrent().getBackground());
					mainFrame.getOrderList()
							.push(new JButton [ ] { SlotButton.this , mainFrame.getCurrent() });
					mainFrame.setCurrent(null);

					// TO-DO replace with new path on nexus
//					normalImage = new ImageIcon(new ImageIcon("pics/" + champion + "Icon.png")
//							.getImage().getScaledInstance(JavaData.PIXEL / 2, JavaData.PIXEL / 2,
//									Image.SCALE_SMOOTH));
					setLayout(null);
					grayedImage = new ImageIcon(
							GrayFilter.createDisabledImage(normalImage.getImage()));

					championPicture = new JLabel(grayedImage);
					championPicture.setBounds(20, 20, JavaData.PIXEL / 2, JavaData.PIXEL / 2);
					championLabel = new JLabel(champion.toString());
					championLabel.setForeground(JavaData.DEFAULT_BACKGROUND);
					championLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
					championLabel.setBounds(5, 8, 96, 10);
					add(championPicture);
					add(championLabel);

					for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
						for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
							if (game.getPlayer() == 2) {
								game.getGrid2().getSlotButtons() [ i ] [ j ].setEnabled(false);
							} else {
								game.getGrid1().getSlotButtons() [ i ] [ j ].setEnabled(false);
							}
						}
					}

					for (int i = 0; i < mainFrame.getSelectButtons().length; i++) {
						for (int j = 0; j < mainFrame.getSelectButtons() [ j ].length; j++) {
							if (!mainFrame.getSelectButtons() [ i ] [ j ].isSelected()) {
								mainFrame.getSelectButtons() [ i ] [ j ].setEnabled(true);
							}
						}
					}

					game.setTotalCount(game.getTotalCount() + 1);
					if (game.getTotalCount() == 9) {
						mainFrame.getOrderList().clear();
						for (int i = 0; i < mainFrame.getSelectButtons().length; i++) {
							for (int j = 0; j < mainFrame.getSelectButtons() [ j ].length; j++) {
								mainFrame.getSelectButtons() [ i ] [ j ].setSelected(false);
								mainFrame.getSelectButtons() [ i ] [ j ].setEnabled(true);
							}
						}
						game.switchPlayer();
						mainFrame.switchPlayerLabel(game.getPlayer());
					} else if (game.getTotalCount() == 18) {
						game.switchPlayer();
						mainFrame.getOrderList().clear();
						mainFrame.switchPlayerLabel(game.getPlayer());
						// mainFrame.gamePlay();
					}
					mainFrame.updateButtonPictures();
				}
			}
		};

		// Initial select
		this.mL2 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SlotButton.this.isEnabled()) {
					mainFrame.getAAS().playSound("select");
					game.getMainLogic().getAttackLogic().getTargets().clear();
					if (SlotButton.this.isEnabled()) {
						mainFrame.setSlot(SlotButton.this);
						mainFrame.resetListeners();
					}
					setSkillButtons();
					mainFrame.setPanelEast(SlotButton.this, mainFrame.getPlayer());
				}
			}

			public void mouseEntered(MouseEvent mE) {
				if (SlotButton.this.isEnabled()) {
					Coordinate coord = null;
					if (game.getPlayer() == 2) {
						coord = game.getMainLogic().getGrid2().getCoord(SlotButton.this);
					} else {
						coord = game.getMainLogic().getGrid1().getCoord(SlotButton.this);
					}
					SlotButton [ ] [ ] arr = game.getPlayer() == 2
							? game.getMainLogic().getGrid2().getArray()
							: game.getMainLogic().getGrid1().getArray();
					try {
						if (arr [ coord.getI() ] [ coord.getJ() + 1 ].getChampion() == null) {
							arr [ coord.getI() ] [ coord.getJ() + 1 ]
									.setBorder(JavaData.MOVE_BORDER);
						}
					} catch (IndexOutOfBoundsException e) {
						/* Ignore */}
					try {
						if (arr [ coord.getI() ] [ coord.getJ() - 1 ].getChampion() == null) {
							arr [ coord.getI() ] [ coord.getJ() - 1 ]
									.setBorder(JavaData.MOVE_BORDER);
						}
					} catch (IndexOutOfBoundsException e) {
						/* Ignore */}
					try {
						if (arr [ coord.getI() + 1 ] [ coord.getJ() ].getChampion() == null) {
							arr [ coord.getI() + 1 ] [ coord.getJ() ]
									.setBorder(JavaData.MOVE_BORDER);
						}
					} catch (IndexOutOfBoundsException e) {
						/* Ignore */}
					try {
						if (arr [ coord.getI() - 1 ] [ coord.getJ() ].getChampion() == null) {
							arr [ coord.getI() - 1 ] [ coord.getJ() ]
									.setBorder(JavaData.MOVE_BORDER);
						}
					} catch (IndexOutOfBoundsException e) {
						/* Ignore */}
					try {

						if (SlotButton.this.getChampion() != null
								&& sl.isValidStance(SlotButton.this.getChampion().getLogic(),
										SlotButton.this, mainFrame.getPlayer())) {
							al.highlight(SlotButton.this.getChampion().getLogic(),
									mainFrame.getPlayer());
						}
					} catch (CornerCaseException e) {
						e.printStackTrace();
					}
					mainFrame.updateButtonPictures();
				}
			}

			public void mouseExited(MouseEvent e) {
				if (SlotButton.this.isEnabled()) {
					SlotButton [ ] [ ] arr;
					int [ ] coord = { -1 , -1 };
					try {
						coord = g.getCoord(SlotButton.this, mainFrame.getPlayer());
					} catch (CornerCaseException exc) {
						exc.printStackTrace();
					}
					arr = mainFrame.getPlayer() == 1 ? g.array1 : g.array2;
					try {
						arr [ coord [ 0 ] ] [ coord [ 1 ] + 1 ].setBorder(JavaData.DEFAULT_BORDER);
					} catch (Exception exc) {
						/* Ignore */}
					try {
						arr [ coord [ 0 ] ] [ coord [ 1 ] - 1 ].setBorder(JavaData.DEFAULT_BORDER);
					} catch (Exception exc) {
						/* Ignore */}
					try {
						arr [ coord [ 0 ] + 1 ] [ coord [ 1 ] ].setBorder(JavaData.DEFAULT_BORDER);
					} catch (Exception exc) {
						/* Ignore */}
					try {
						arr [ coord [ 0 ] - 1 ] [ coord [ 1 ] ].setBorder(JavaData.DEFAULT_BORDER);
					} catch (Exception exc) {
						/* Ignore */}
					al.unHighlight(mainFrame.getPlayer());
				}
				mainFrame.updateButtonPictures();
			}

			public String toString() {
				return "mL2";
			}
		};

		// Accepting movement
		this.mL3 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) { // swap
				if (SlotButton.this.isEnabled()) {
					mainFrame.getAAS().playSound("select");

					SlotButton.this.setBackground(mainFrame.getSlot().getBackground());
					SlotButton.this.setChampion(mainFrame.getSlot().getChampion());
					SlotButton.this.setchampionLabel(mainFrame.getSlot().getChampionLabel());
					SlotButton.this.setchampionPicture(mainFrame.getSlot().getchampionPicture());
					SlotButton.this.setNormalImage(mainFrame.getSlot().getNormalImage());
					SlotButton.this.setGrayedImage(mainFrame.getSlot().getGrayedImage());
					SlotButton.this.alterMouseAdapter0_3(); // 3->0

					mainFrame.getSlot().setBackground(JavaData.DEFAULT_BACKGROUND);
					mainFrame.getSlot().setText("");
					mainFrame.getSlot().removeAll();
					mainFrame.getSlot().setChampion(null);
					mainFrame.getSlot().setNormalImage(null);
					mainFrame.getSlot().setGrayedImage(null);

					int [ ] beforeCoord = new int [ ] { -1 , -1 };
					int [ ] afterCoord = new int [ ] { -1 , -1 };
					try {
						beforeCoord = g.getCoord(mainFrame.getSlot(), mainFrame.getPlayer());
						afterCoord = g.getCoord(SlotButton.this, mainFrame.getPlayer());
					} catch (CornerCaseException exc) {
						exc.printStackTrace();
					}

					String dir = "";
					if (beforeCoord [ 0 ] > afterCoord [ 0 ])
						dir = "up";
					else if (beforeCoord [ 0 ] < afterCoord [ 0 ])
						dir = "down";
					else if (beforeCoord [ 1 ] > afterCoord [ 1 ])
						dir = "left";
					else if (beforeCoord [ 1 ] < afterCoord [ 1 ])
						dir = "right";

					mainFrame.getMP().generateMove(mainFrame.getMessages(), mainFrame.getMove());
					// error prone?
					// mainFrame.getMP().generateMessage(mainFrame.getMessages(), SlotButton.this,
					// dir);
					mainFrame.resetListeners();
					mainFrame.clearAllBorders();
					mainFrame.evalTurns();
					try {
						SlotButton.this.getMouseListeners() [ 2 ].mouseEntered(null);
					} catch (ArrayIndexOutOfBoundsException exc) {
						/* Ignore */ }
					mainFrame.setSlot(null);
					mainFrame.updateButtonPictures();
				}
			}

			public String toString() {
				return "mL3";
			}
		};

		// Accepting attack
		this.mL4 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) { // attack
				if (SlotButton.this.isEnabled()) {

					al.attack(SlotButton.this);
					mainFrame.disableAll();
					setSkillButtons();
					mainFrame.setPanelEast(SlotButton.this, mainFrame.getPlayer());
					mainFrame.resetListeners();
					mainFrame.clearAllBorders();
					mainFrame.evalTurns();
					try {
						SlotButton.this.getMouseListeners() [ 2 ].mouseEntered(null);
					} catch (ArrayIndexOutOfBoundsException exc) {
						/* Ignore */ }
					mainFrame.setSlot(null);
					mainFrame.updateButtonPictures();
				}
			}

			public String toString() {
				return "mL4";
			}
		};

		// panelEast's content generator
		this.mL5 = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if (mainFrame.getSlot() != null && side == mainFrame.getPlayer())
					mainFrame.setPanelEast(mainFrame.getSlot(), side);
				else
					mainFrame.setPanelEast(SlotButton.this, side);
				setSkillButtons();
				mainFrame.updateButtonPictures();
			}

			public void mouseExited(MouseEvent e) {
				if (mainFrame.getSlot() != null && side == mainFrame.getPlayer())
					return;
				mainFrame.clearSkillButtons(mainFrame.getPlayer()); // error
																	// prone
				mainFrame.clearPanelEast(side);
			}

			public String toString() {
				return "mL5";
			}
		};

	}

	public SlotButton(String position) {
		super();
		super.setBackground(JavaData.DEFAULT_BACKGROUND);
		super.setLayout(null);
		super.addMouseListener(mL1);
		this.position = position;
	}

//	void switchFunctionality() {
//		removeMouseListener(mL1);
//		addMouseListener(mL5);
//		if (champion != null) {
//			addMouseListener(mL2);
//		}
//	}
//
//	public void alterMouseAdapter0_4() {
//		if (getMouseListeners().length == 2) {
//			addMouseListener(mL4);
//		}
//		else if (getMouseListeners() [ 2 ].equals(mL4)) {
//			removeMouseListener(mL4);
//		}
//	}
//
//	void alterMouseAdapter0_3() {
//		if (getMouseListeners().length == 2) {
//			addMouseListener(mL3);
//		}
//		else if (getMouseListeners() [ 2 ].equals(mL3)) {
//			removeMouseListener(mL3);
//		}
//	}
//
//	void alterMouseAdapter0_2() {
//		if (getMouseListeners().length == 2) {
//			addMouseListener(mL2);
//		}
//		else if (getMouseListeners() [ 2 ].equals(mL2)) {
//			removeMouseListener(mL2);
//		}
//	}
//
//	void alterMouseAdapter2_4() {
//		if (getMouseListeners().length == 2) {
//			addMouseListener(mL4);
//		}
//		else if (getMouseListeners() [ 2 ].equals(mL2)) {
//			removeMouseListener(mL2);
//			addMouseListener(mL4);
//		} else {
//			removeMouseListener(mL4);
//			addMouseListener(mL2);
//		}
//	}
//
	public void setSkillButtons() {
		Champion localchampion;
		if (champion == null) {
			return;
		} else if (mainFrame.getSlot() == null && champion != null
				|| mainFrame.getSlot() != null && side != mainFrame.getPlayer()) {
			localchampion = champion;
		} else if (mainFrame.getSlot() != null
				&& mainFrame.getSlot().getSide() == mainFrame.getPlayer()) {
			localchampion = mainFrame.getSlot().getChampion();
		} else {
			return;
		}

		if (side == 2) {
			for (int i = 0; i < mainFrame.getSkillButtons2().length; i++) {
				mainFrame.getSkillButtons2() [ i ].setSkillButton(
						"pics/" + localchampion + "IconS" + (i + 1) + ".png",
						1 - localchampion.evalFraction(i));
			}
		} else {
			for (int i = 0; i < mainFrame.getSkillButtons1().length; i++) {
				mainFrame.getSkillButtons1() [ i ].setSkillButton(
						"pics/" + localchampion + "IconS" + (i + 1) + ".png",
						1 - localchampion.evalFraction(i));
			}
		}
	}

}
