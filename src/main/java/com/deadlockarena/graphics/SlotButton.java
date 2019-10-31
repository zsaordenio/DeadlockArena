package com.deadlockarena.graphics;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.deadlockarena.config.JpaGetData;
import com.deadlockarena.constant.JavaData;
import com.deadlockarena.exception.CornerCaseException;
import com.deadlockarena.logic.AttackLogic;
import com.deadlockarena.logic.Coordinate;
import com.deadlockarena.logic.Grid;
import com.deadlockarena.logic.StanceLogic;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Data
@EqualsAndHashCode(callSuper = true)
public class SlotButton extends JButton {
	private static final long serialVersionUID = 1436902681342190255L;

	private Coordinate coordinate;
	private String position;

	private boolean selected;
	private Champion champion;

	private JpaGetData jpaGetData;

	private JLabel championLabel, championPicture;
	private ImageIcon normalImage, grayedImage;

	// Selecting champion
//	private MouseListener mL1 = new MouseAdapter() {
//		public void mousePressed(MouseEvent e) {
//			if (SlotButton.this.isEnabled()) {
//				mainFrame.getAAS().playSound("select");
//				SlotButton.this.selected = true;
//				try {
//					SlotButton.this.setChampion(
//							jpaGetData.evalChampion(mainFrame.getCurrent().getChampion().toString()));
//				} catch (CornerCaseException exc) {
//					exc.printStackTrace();
//				}
//				SlotButton.this.setBackground(mainFrame.getCurrent().getBackground());
//				mainFrame.getOrderList().push(new JButton [ ] { SlotButton.this , mainFrame.getCurrent() });
//				mainFrame.setCurrent(null);
//
//				normalImage = new ImageIcon(
//						new ImageIcon("pics/" + champion + "Icon.png").getImage().getScaledInstance(
//								JavaData.PIXEL / 2, JavaData.PIXEL / 2, Image.SCALE_SMOOTH));
//				setLayout(null);
//				grayedImage = new ImageIcon(GrayFilter.createDisabledImage(normalImage.getImage()));
//
//				championPicture = new JLabel(grayedImage);
//				championPicture.setBounds(20, 20, JavaData.PIXEL / 2, JavaData.PIXEL / 2);
//				championLabel = new JLabel(champion.toString());
//				championLabel.setForeground(JavaData.DEFAULT_BACKGROUND);
//				championLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
//				championLabel.setBounds(5, 8, 96, 10);
//				add(championPicture);
//				add(championLabel);
//
//				for (int i = 0; i < JavaData.SLOT_COUNT; i++)
//					if (mainFrame.getPlayer() == 1)
//						mainFrame.getSlotList1() [ i ].setEnabled(false);
//					else
//						mainFrame.getSlotList2() [ i ].setEnabled(false);
//
//				for (int i = 0; i < JavaData.CHAMPION_COUNT; i++)
//					if (!mainFrame.getSelectList() [ i ].isSelected())
//						mainFrame.getSelectList() [ i ].setEnabled(true);
//
//				mainFrame.setTotalCount(mainFrame.getTotalCount() + 1);
//				if (mainFrame.getTotalCount() == 9) {
//					mainFrame.getOrderList().clear();
//					for (int i = 0; i < JavaData.CHAMPION_COUNT; i++) {
//						mainFrame.getSelectList() [ i ].setSelected(false);
//						mainFrame.getSelectList() [ i ].setEnabled(true);
//					}
//					mainFrame.switchPlayer();
//					mainFrame.switchPlayerLabel();
//				} else if (mainFrame.getTotalCount() == 18) {
//					mainFrame.getOrderList().clear();
//					mainFrame.switchPlayer();
//					mainFrame.switchPlayerLabel();
//					// mainFrame.gamePlay();
//				}
//				mainFrame.updateButtonPictures();
//			}
//		}
//
//		public String toString() {
//			return "mL1";
//		}
//	};
//
//	// Initial select
//	private MouseListener mL2 = new MouseAdapter() {
//		public void mousePressed(MouseEvent e) {
//			if (SlotButton.this.isEnabled()) {
//				mainFrame.getAAS().playSound("select");
//				al.getTargets().clear();
//				if (SlotButton.this.isEnabled()) {
//					mainFrame.setSlot(SlotButton.this);
//					mainFrame.resetListeners();
//				}
//				setSkillButtons();
//				mainFrame.setPanelEast(SlotButton.this, mainFrame.getPlayer());
//			}
//		}
//
//		public void mouseEntered(MouseEvent e) {
//			if (SlotButton.this.isEnabled()) {
//				SlotButton [ ] [ ] arr;
//				int [ ] coord = new int [ ] { -1 , -1 };
//				try {
//					coord = g.getCoord(SlotButton.this, mainFrame.getPlayer());
//				} catch (CornerCaseException exc) {
//					exc.printStackTrace();
//				}
//				arr = mainFrame.getPlayer() == 1 ? g.getArray1() : g.getArray2();
//				try {
//					if (arr [ coord [ 0 ] ] [ coord [ 1 ] + 1 ].getChampion() == null)
//						arr [ coord [ 0 ] ] [ coord [ 1 ] + 1 ].setBorder(JavaData.MOVE_BORDER);
//				} catch (Exception exc) {
//					/* Ignore */}
//				try {
//					if (arr [ coord [ 0 ] ] [ coord [ 1 ] - 1 ].getChampion() == null)
//						arr [ coord [ 0 ] ] [ coord [ 1 ] - 1 ].setBorder(JavaData.MOVE_BORDER);
//				} catch (Exception exc) {
//					/* Ignore */}
//				try {
//					if (arr [ coord [ 0 ] + 1 ] [ coord [ 1 ] ].getChampion() == null)
//						arr [ coord [ 0 ] + 1 ] [ coord [ 1 ] ].setBorder(JavaData.MOVE_BORDER);
//				} catch (Exception exc) {
//					/* Ignore */}
//				try {
//					if (arr [ coord [ 0 ] - 1 ] [ coord [ 1 ] ].getChampion() == null)
//						arr [ coord [ 0 ] - 1 ] [ coord [ 1 ] ].setBorder(JavaData.MOVE_BORDER);
//				} catch (Exception exc) {
//					/* Ignore */}
//				try {
//					if (SlotButton.this.getChampion() != null
//							&& sl.isValidStance(SlotButton.this.getChampion().getLogic(),
//									SlotButton.this, mainFrame.getPlayer()))
//						al.highlight(SlotButton.this.getChampion().getLogic(), mainFrame.getPlayer());
//				} catch (CornerCaseException exc) {
//					exc.printStackTrace();
//				}
//				mainFrame.updateButtonPictures();
//			}
//		}
//
//		public void mouseExited(MouseEvent e) {
//			if (SlotButton.this.isEnabled()) {
//				SlotButton [ ] [ ] arr;
//				int [ ] coord = { -1 , -1 };
//				try {
//					coord = g.getCoord(SlotButton.this, mainFrame.getPlayer());
//				} catch (CornerCaseException exc) {
//					exc.printStackTrace();
//				}
//				arr = mainFrame.getPlayer() == 1 ? g.array1 : g.array2;
//				try {
//					arr [ coord [ 0 ] ] [ coord [ 1 ] + 1 ].setBorder(JavaData.DEFAULT_BORDER);
//				} catch (Exception exc) {
//					/* Ignore */}
//				try {
//					arr [ coord [ 0 ] ] [ coord [ 1 ] - 1 ].setBorder(JavaData.DEFAULT_BORDER);
//				} catch (Exception exc) {
//					/* Ignore */}
//				try {
//					arr [ coord [ 0 ] + 1 ] [ coord [ 1 ] ].setBorder(JavaData.DEFAULT_BORDER);
//				} catch (Exception exc) {
//					/* Ignore */}
//				try {
//					arr [ coord [ 0 ] - 1 ] [ coord [ 1 ] ].setBorder(JavaData.DEFAULT_BORDER);
//				} catch (Exception exc) {
//					/* Ignore */}
//				al.unHighlight(mainFrame.getPlayer());
//			}
//			mainFrame.updateButtonPictures();
//		}
//
//		public String toString() {
//			return "mL2";
//		}
//	};
//
//	// Accepting movement
//	private MouseListener mL3 = new MouseAdapter() {
//		public void mousePressed(MouseEvent e) { // swap
//			if (SlotButton.this.isEnabled()) {
//				mainFrame.getAAS().playSound("select");
//
//				SlotButton.this.setBackground(mainFrame.getSlot().getBackground());
//				SlotButton.this.setChampion(mainFrame.getSlot().getChampion());
//				SlotButton.this.setchampionLabel(mainFrame.getSlot().getChampionLabel());
//				SlotButton.this.setchampionPicture(mainFrame.getSlot().getchampionPicture());
//				SlotButton.this.setNormalImage(mainFrame.getSlot().getNormalImage());
//				SlotButton.this.setGrayedImage(mainFrame.getSlot().getGrayedImage());
//				SlotButton.this.alterMouseAdapter0_3(); // 3->0
//
//				mainFrame.getSlot().setBackground(JavaData.DEFAULT_BACKGROUND);
//				mainFrame.getSlot().setText("");
//				mainFrame.getSlot().removeAll();
//				mainFrame.getSlot().setChampion(null);
//				mainFrame.getSlot().setNormalImage(null);
//				mainFrame.getSlot().setGrayedImage(null);
//
//				int [ ] beforeCoord = new int [ ] { -1 , -1 };
//				int [ ] afterCoord = new int [ ] { -1 , -1 };
//				try {
//					beforeCoord = g.getCoord(mainFrame.getSlot(), mainFrame.getPlayer());
//					afterCoord = g.getCoord(SlotButton.this, mainFrame.getPlayer());
//				} catch (CornerCaseException exc) {
//					exc.printStackTrace();
//				}
//
//				String dir = "";
//				if (beforeCoord [ 0 ] > afterCoord [ 0 ])
//					dir = "up";
//				else if (beforeCoord [ 0 ] < afterCoord [ 0 ])
//					dir = "down";
//				else if (beforeCoord [ 1 ] > afterCoord [ 1 ])
//					dir = "left";
//				else if (beforeCoord [ 1 ] < afterCoord [ 1 ])
//					dir = "right";
//
//				mainFrame.getMP().generateMove(mainFrame.getMessages(), mainFrame.getMove());
//				// error prone?
//				// mainFrame.getMP().generateMessage(mainFrame.getMessages(), SlotButton.this, dir);
//				mainFrame.resetListeners();
//				mainFrame.clearAllBorders();
//				mainFrame.evalTurns();
//				try {
//					SlotButton.this.getMouseListeners() [ 2 ].mouseEntered(null);
//				} catch (ArrayIndexOutOfBoundsException exc) {
//					/* Ignore */ }
//				mainFrame.setSlot(null);
//				mainFrame.updateButtonPictures();
//			}
//		}
//
//		public String toString() {
//			return "mL3";
//		}
//	};
//
//	// Accepting attack
//	private MouseListener mL4 = new MouseAdapter() {
//		public void mousePressed(MouseEvent e) { // attack
//			if (SlotButton.this.isEnabled()) {
//
//				al.attack(SlotButton.this);
//				mainFrame.disableAll();
//				setSkillButtons();
//				mainFrame.setPanelEast(SlotButton.this, mainFrame.getPlayer());
//				mainFrame.resetListeners();
//				mainFrame.clearAllBorders();
//				mainFrame.evalTurns();
//				try {
//					SlotButton.this.getMouseListeners() [ 2 ].mouseEntered(null);
//				} catch (ArrayIndexOutOfBoundsException exc) {
//					/* Ignore */ }
//				mainFrame.setSlot(null);
//				mainFrame.updateButtonPictures();
//			}
//		}
//
//		public String toString() {
//			return "mL4";
//		}
//	};
//
//	// panelEast's content generator
//	private MouseListener mL5 = new MouseAdapter() {
//		public void mouseEntered(MouseEvent e) {
//			if (mainFrame.getSlot() != null && side == mainFrame.getPlayer())
//				mainFrame.setPanelEast(mainFrame.getSlot(), side);
//			else
//				mainFrame.setPanelEast(SlotButton.this, side);
//			setSkillButtons();
//			mainFrame.updateButtonPictures();
//		}
//
//		public void mouseExited(MouseEvent e) {
//			if (mainFrame.getSlot() != null && side == mainFrame.getPlayer())
//				return;
//			mainFrame.clearSkillButtons(mainFrame.getPlayer()); // error prone
//			mainFrame.clearPanelEast(side);
//		}
//
//		public String toString() {
//			return "mL5";
//		}
//	};

	public SlotButton(String position) {
		super();
		this.position = position;
		this.setBackground(JavaData.DEFAULT_BACKGROUND);
		setLayout(null);
		//addMouseListener(mL1);
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
//	public void setSkillButtons() {
//		Champion localchampion;
//		if (champion == null) {
//			return;
//		} else if (mainFrame.getSlot() == null && champion != null
//				|| mainFrame.getSlot() != null && side != mainFrame.getPlayer()) {
//			localchampion = champion;
//		} else if (mainFrame.getSlot() != null && mainFrame.getSlot().getSide() == mainFrame.getPlayer()) {
//			localchampion = mainFrame.getSlot().getChampion();
//		} else {
//			return;
//		}
//
//		if (side == 2) {
//			for (int i = 0; i < mainFrame.getSkillButtons2().length; i++) {
//				mainFrame.getSkillButtons2() [ i ].setSkillButton(
//						"pics/" + localchampion + "IconS" + (i + 1) + ".png",
//						1 - localchampion.evalFraction(i));
//			}
//		} else {
//			for (int i = 0; i < mainFrame.getSkillButtons1().length; i++) {
//				mainFrame.getSkillButtons1() [ i ].setSkillButton(
//						"pics/" + localchampion + "IconS" + (i + 1) + ".png",
//						1 - localchampion.evalFraction(i));
//			}
//		}
//	}

}
