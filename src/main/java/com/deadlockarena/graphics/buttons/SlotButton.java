package com.deadlockarena.graphics.buttons;

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
import com.deadlockarena.exception.CornerCaseException;
import com.deadlockarena.graphics.frames.MainFrame;
import com.deadlockarena.logic.AttackLogic;
import com.deadlockarena.logic.Coordinate;
import com.deadlockarena.logic.SelectGrid;
import com.deadlockarena.logic.SlotGrid;
import com.deadlockarena.logic.StanceLogic;
import com.deadlockarena.persistence.bootstrap.JpaGetData;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SlotButton extends JButton {
	private static final long serialVersionUID = 1436902681342190255L;

	private boolean selected;
	private Champion champion;
	private String position;
	private Coordinate coordinate;
	private JLabel championLabel, championPicture;
	private ImageIcon normalImage, grayedImage;
	private MouseListener mL1, mL2, mL3, mL4, mL5;

	public SlotButton(Game game, String position, Coordinate coordinate) {
		super();
		super.setPreferredSize(new Dimension(JavaData.PIXEL * 4 / 5, JavaData.PIXEL * 4 / 5));
		super.setEnabled(false);
		super.setFont(JavaData.BASIC_FONT);
		this.selected = false;
		this.position = position;
		this.coordinate = coordinate;
		// Selecting champion
		this.addMouseListener1(game);
		// Initial select
		this.addMouseListener2(game);
		// Accepting movement
		this.addMouseListener3(game);
		// Accepting attack
		this.addMouseListener4(game);
		// panelEast's content generator
		this.addMouseListener5(game);
	}

	private void addMouseListener1(final Game game) {
		this.mL1 = new MouseAdapter() {
			final MainFrame mainFrame = game.getMainFrame();
			final SlotGrid slotGrid1 = game.getSlotGrid1();
			final SlotGrid slotGrid2 = game.getSlotGrid2();
			final SlotButton thisSlot = SlotButton.this;
			final int player = game.getPlayer();

			public void mousePressed(MouseEvent e) {
				final SelectButton currentSelect = game.getCurrentSelect();
				if (thisSlot.isEnabled()) {
					// game.getMainFrame().getAAS().playSound("select");
					thisSlot.setSelected(true);
					thisSlot.setChampion(currentSelect.getChampion());
					thisSlot.setBackground(currentSelect.getColor());

					mainFrame.getOrderList()
							.push(new JButton [ ] { SlotButton.this , game.getCurrentSelect() });

					// TO-DO replace with new path on nexus
					thisSlot.normalImage = new ImageIcon(new ImageIcon(getClass()
							.getResource(JavaData.PICS_PATH + champion.getChampion() + "Icon.png"))
									.getImage().getScaledInstance(JavaData.PIXEL / 2,
											JavaData.PIXEL / 2, Image.SCALE_SMOOTH));
					thisSlot.grayedImage = new ImageIcon(
							GrayFilter.createDisabledImage(thisSlot.normalImage.getImage()));

					thisSlot.setLayout(null);
					thisSlot.championPicture = new JLabel(thisSlot.normalImage);
					thisSlot.championPicture.setBounds(20, 20, JavaData.PIXEL / 2,
							JavaData.PIXEL / 2);
					thisSlot.add(championPicture);

					thisSlot.championLabel = new JLabel(champion.getChampion());
					thisSlot.championLabel.setForeground(JavaData.DEFAULT_BACKGROUND);
					thisSlot.championLabel.setFont(JavaData.SELECT_BUTTON_CHAMPION_FONT);
					thisSlot.championLabel.setBounds(5, 5, JavaData.PIXEL, 10);
					thisSlot.add(championLabel);

					game.setCurrentSelect(null);
					if (player == 2) {
						game.getSlotGrid2().disableAll();
					} else {
						game.getSlotGrid1().disableAll();
					}
					game.getSelectGrid().enableAllIfNotSelected();
					// mainFrame.updateButtonPictures(slotGrid1, slotGrid2, selectGrid);
					// ------------------------------------------------
//					game.setTotalCount(game.getTotalCount() + 1);
//					if (game.getTotalCount() == 9) {
//						mainFrame.getOrderList().clear();
//						// TO-DO create a method
//						for (int i = 0; i < selectGrid.getJButtons().length; i++) {
//							for (int j = 0; j < selectGrid.getJButtons() [ j ].length; j++) {
//								selectGrid.getJButtons() [ i ] [ j ].setSelected(false);
//								selectGrid.getJButtons() [ i ] [ j ].setEnabled(true);
//							}
//						}
//						game.switchPlayer();
//						mainFrame.switchPlayerLabel(game.getPlayer());
//					} else if (game.getTotalCount() == 18) {
//						game.switchPlayer();
//						mainFrame.getOrderList().clear();
//						mainFrame.switchPlayerLabel(game.getPlayer());
//						// TO-DO move to phase 2 and create a method somewhere
//						// mainFrame.gamePlay();
//					}
					// ------------------------------------------------

				}
			}

			public String toString() {
				return "mL1";
			}
		};
	}

	private void addMouseListener2(Game game) {
		MainFrame mainFrame = game.getMainFrame();
		SelectGrid selectGrid = game.getSelectGrid();
		SlotGrid slotGrid1 = game.getSlotGrid1();
		SlotGrid slotGrid2 = game.getSlotGrid2();
		int player = game.getPlayer();
		StanceLogic stanceLogic = game.getMainLogic().getStanceLogic();
		AttackLogic attackLogic = game.getMainLogic().getAttackLogic();
		Champion champ = this.champion;
		SlotGrid slotGrid = player == 2 ? slotGrid2 : slotGrid1;
		mL2 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SlotButton.this.isEnabled()) {
					mainFrame.getPanelSouth().getAAS().playSound("select");
					game.getMainLogic().getAttackLogic().getTargets().clear();
					if (SlotButton.this.isEnabled()) {
						// game.setCurrenSlot(SlotButton.this);
						// mainFrame.resetListeners();
					}
					// setSkillButtons();
					mainFrame.getPanelEast().setPanelEast(SlotButton.this, player);
				}
			}

			public void mouseEntered(MouseEvent mE) {
				if (SlotButton.this.isEnabled()) {
					try {
						if (slotGrid.getJButton(coordinate.getI(), coordinate.getJ() + 1)
								.getChampion() == null) {
							slotGrid.getJButton(coordinate.getI(), coordinate.getJ() + 1)
									.setBorder(JavaData.MOVE_BORDER);
						}
					} catch (IndexOutOfBoundsException e) {
						/* Ignore */}
					try {
						if (slotGrid.getJButton(coordinate.getI(), coordinate.getJ() - 1)
								.getChampion() == null) {
							slotGrid.getJButton(coordinate.getI(), coordinate.getJ() - 1)
									.setBorder(JavaData.MOVE_BORDER);
						}
					} catch (IndexOutOfBoundsException e) {
						/* Ignore */}
					try {
						if (slotGrid.getJButton(coordinate.getI() + 1, coordinate.getJ())
								.getChampion() == null) {
							slotGrid.getJButton(coordinate.getI() + 1, coordinate.getJ())
									.setBorder(JavaData.MOVE_BORDER);
						}
					} catch (IndexOutOfBoundsException e) {
						/* Ignore */}
					try {
						if (slotGrid.getJButton(coordinate.getI() - 1, coordinate.getJ())
								.getChampion() == null) {
							slotGrid.getJButton(coordinate.getI() - 1, coordinate.getJ())
									.setBorder(JavaData.MOVE_BORDER);
						}
					} catch (IndexOutOfBoundsException e) {
						/* Ignore */}

					try {
						if (champ != null && stanceLogic.isValidStance(champ.getLogic(),
								SlotButton.this, player == 2 ? slotGrid2 : slotGrid1)) {
							try {
								attackLogic.highlight(champ.getLogic(),
										player == 2 ? slotGrid2 : slotGrid1,
										player == 2 ? slotGrid1 : slotGrid2);
							} catch (CornerCaseException e) {
								e.printStackTrace();
							}
						}
					} catch (CornerCaseException e) {
						e.printStackTrace();
					}
					slotGrid1.updatePictures();
					slotGrid2.updatePictures();
					selectGrid.updatePictures();
				}
			}

			public void mouseExited(MouseEvent e) {
				if (SlotButton.this.isEnabled()) {
					try {
						slotGrid.getJButton(coordinate.getI(), coordinate.getJ() + 1)
								.setBorder(JavaData.DEFAULT_BORDER);
					} catch (Exception exc) {
						/* Ignore */}
					try {
						slotGrid.getJButton(coordinate.getI(), coordinate.getJ() - 1)
								.setBorder(JavaData.DEFAULT_BORDER);
					} catch (Exception exc) {
						/* Ignore */}
					try {
						slotGrid.getJButton(coordinate.getI() + 1, coordinate.getJ())
								.setBorder(JavaData.DEFAULT_BORDER);
					} catch (Exception exc) {
						/* Ignore */}
					try {
						slotGrid.getJButton(coordinate.getI() - 1, coordinate.getJ())
								.setBorder(JavaData.DEFAULT_BORDER);
					} catch (Exception exc) {
						/* Ignore */}
					attackLogic.unHighlight(slotGrid);
				}
				slotGrid1.updatePictures();
				slotGrid2.updatePictures();
				selectGrid.updatePictures();
			}

			public String toString() {
				return "mL2";
			}
		};
	}

	private void addMouseListener3(Game game) {
		MainFrame mainFrame = game.getMainFrame();
		SelectGrid selectGrid = game.getSelectGrid();
		SlotGrid slotGrid1 = game.getSlotGrid1();
		SlotGrid slotGrid2 = game.getSlotGrid2();
		SlotButton slot = game.getCurrentSlot();
		mL3 = new MouseAdapter() {
			public void mousePressed(MouseEvent evt) { // swap
				if (SlotButton.this.isEnabled()) {
					mainFrame.getPanelSouth().getAAS().playSound("select");

					SlotButton.this.setBackground(slot.getBackground());
					SlotButton.this.setChampion(slot.getChampion());
					SlotButton.this.setChampionLabel(slot.getChampionLabel());
					SlotButton.this.setChampionPicture(slot.getChampionPicture());
					SlotButton.this.setNormalImage(slot.getNormalImage());
					SlotButton.this.setGrayedImage(slot.getGrayedImage());
					SlotButton.this.alterMouseAdapter0_3(); // 3->0

					slot.setBackground(JavaData.DEFAULT_BACKGROUND);
					slot.setText("");
					slot.removeAll();
					slot.setChampion(null);
					slot.setNormalImage(null);
					slot.setGrayedImage(null);

					Coordinate beforeCoord = slotGrid1.getCoord(slot);
					Coordinate afterCoord = slotGrid1.getCoord(SlotButton.this);

					String dir = "";
					if (beforeCoord.getI() > afterCoord.getI()) {
						dir = "up";
					} else if (beforeCoord.getI() < afterCoord.getI()) {
						dir = "down";
					} else if (beforeCoord.getJ() > afterCoord.getJ()) {
						dir = "left";
					} else if (beforeCoord.getJ() < afterCoord.getJ()) {
						dir = "right";
					}

					// messageProcessor.generateMove(mainFrame.getMessages(), mainFrame.getMove());
					// error prone?
					// mainFrame.getMP().generateMessage(mainFrame.getMessages(), SlotButton.this,
					// dir);
					mainFrame.resetListeners(slotGrid1, slotGrid2);
					slotGrid1.clearBorders();
					slotGrid2.clearBorders();
					game.evalTurns(slot);
					try {
						SlotButton.this.getMouseListeners() [ 2 ].mouseEntered(null);
					} catch (ArrayIndexOutOfBoundsException exc) {
						/* Ignore */ }
					game.setCurrentSlot(null);
					slotGrid1.updatePictures();
					slotGrid2.updatePictures();
					selectGrid.updatePictures();
				}
			}

			public String toString() {
				return "mL3";
			}
		};
	}

	private void addMouseListener4(Game game) {
		MainFrame mainFrame = game.getMainFrame();
		SelectGrid selectGrid = game.getSelectGrid();
		SlotGrid slotGrid1 = game.getSlotGrid1();
		SlotGrid slotGrid2 = game.getSlotGrid2();
		int player = game.getPlayer();
		AttackLogic attackLogic = game.getMainLogic().getAttackLogic();
		SlotButton slot = game.getCurrentSlot();
		SlotGrid slotGrid = player == 2 ? slotGrid2 : slotGrid1;
		mL4 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) { // attack
				if (SlotButton.this.isEnabled()) {

					attackLogic.attack(slot, SlotButton.this);
					slotGrid.disableAll();

					// TO-DO side vs player and player vs player
					setSkillButtons(slot, player, player);
					mainFrame.getPanelEast().setPanelEast(SlotButton.this, player);
					mainFrame.resetListeners(slotGrid1, slotGrid2);
					slotGrid1.clearBorders();
					slotGrid2.clearBorders();
					game.evalTurns(SlotButton.this);
					try {
						SlotButton.this.getMouseListeners() [ 2 ].mouseEntered(null);
					} catch (ArrayIndexOutOfBoundsException exc) {
						/* Ignore */ }
					game.setCurrentSlot(null);
					slotGrid1.updatePictures();
					slotGrid2.updatePictures();
					selectGrid.updatePictures();
				}
			}

			public String toString() {
				return "mL4";
			}
		};
	}

	private void addMouseListener5(Game game) {
//		this.mL5 = new MouseAdapter() {
//			public void mouseEntered(MouseEvent e) {
//				if (mainFrame.getSlot() != null && side == player)
//					mainFrame.setPanelEast(mainFrame.getSlot(), side);
//				else
//					mainFrame.setPanelEast(SlotButton.this, side);
//				setSkillButtons();
//				mainFrame.updateButtonPictures();
//			}
//
//			public void mouseExited(MouseEvent e) {
//				if (mainFrame.getSlot() != null && side == player)
//					return;
//				mainFrame.clearSkillButtons(player); // error
//														// prone
//				mainFrame.clearPanelEast(side);
//			}
//
//			public String toString() {
//				return "mL5";
//			}
//		};
	}

	public SlotButton(String position) {
		super();
		super.setBackground(JavaData.DEFAULT_BACKGROUND);
		super.setLayout(null);
		super.addMouseListener(mL1);
		this.position = position;
	}

	void switchFunctionality() {
		removeMouseListener(mL1);
		addMouseListener(mL5);
		if (champion != null) {
			addMouseListener(mL2);
		}
	}

	public void alterMouseAdapter0_4() {
		if (getMouseListeners().length == 2) {
			addMouseListener(mL4);
		} else if (getMouseListeners() [ 2 ].equals(mL4)) {
			removeMouseListener(mL4);
		}
	}

	public void alterMouseAdapter0_3() {
		if (getMouseListeners().length == 2) {
			addMouseListener(mL3);
		} else if (getMouseListeners() [ 2 ].equals(mL3)) {
			removeMouseListener(mL3);
		}
	}

	public void alterMouseAdapter0_2() {
		if (getMouseListeners().length == 2) {
			addMouseListener(mL2);
		} else if (getMouseListeners() [ 2 ].equals(mL2)) {
			removeMouseListener(mL2);
		}
	}

	public void alterMouseAdapter2_4() {
		if (getMouseListeners().length == 2) {
			addMouseListener(mL4);
		} else if (getMouseListeners() [ 2 ].equals(mL2)) {
			removeMouseListener(mL2);
			addMouseListener(mL4);
		} else {
			removeMouseListener(mL4);
			addMouseListener(mL2);
		}
	}

	public void setSkillButtons(SlotButton slot, int side, int player) {
		Champion localchampion = null;
		if (champion == null) {
			return;
		} else if (slot == null && champion != null || slot != null && side != player) {
			localchampion = champion;
		} else if (slot != null && side == player) {
			localchampion = slot.getChampion();
		} else {
			return;
		}

		// TO-DO create skill Grid
		// TO-DO side vs player?
//		if (side == 2) {
//			for (int i = 0; i < skillButtons2().length; i++) {
//				skillButtons2() [ i ].setSkillButton(
//						"pics/" + localchampion + "IconS" + (i + 1) + ".png",
//						1 - localchampion.evalFraction(i));
//			}
//		} else {
//			for (int i = 0; i < skillButtons1().length; i++) {
//				skillButtons1() [ i ].setSkillButton(
//						"pics/" + localchampion + "IconS" + (i + 1) + ".png",
//						1 - localchampion.evalFraction(i));
//			}
//		}
	}

}
