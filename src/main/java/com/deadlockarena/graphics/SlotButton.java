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
	private MouseListener mL1 = new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			if (SlotButton.this.isEnabled()) {
				aPF.getAAS().playSound("select");
				SlotButton.this.selected = true;
				try {
					SlotButton.this.setChampion(
							jpaGetData.evalChampion(aPF.getCurrent().getChampion().toString()));
				} catch (CornerCaseException exc) {
					exc.printStackTrace();
				}
				SlotButton.this.setBackground(aPF.getCurrent().getBackground());
				aPF.getOrderList().push(new JButton [ ] { SlotButton.this , aPF.getCurrent() });
				aPF.setCurrent(null);

				normalImage = new ImageIcon(
						new ImageIcon("pics/" + champion + "Icon.png").getImage().getScaledInstance(
								JavaData.PIXEL / 2, JavaData.PIXEL / 2, Image.SCALE_SMOOTH));
				setLayout(null);
				grayedImage = new ImageIcon(GrayFilter.createDisabledImage(normalImage.getImage()));

				championPicture = new JLabel(grayedImage);
				championPicture.setBounds(20, 20, JavaData.PIXEL / 2, JavaData.PIXEL / 2);
				championLabel = new JLabel(champion.toString());
				championLabel.setForeground(JavaData.DEFAULT_BACKGROUND);
				championLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
				championLabel.setBounds(5, 8, 96, 10);
				add(championPicture);
				add(championLabel);

				for (int i = 0; i < JavaData.SLOT_COUNT; i++)
					if (aPF.getPlayer() == 1)
						aPF.getSlotList1() [ i ].setEnabled(false);
					else
						aPF.getSlotList2() [ i ].setEnabled(false);

				for (int i = 0; i < JavaData.CHAMPION_COUNT; i++)
					if (!aPF.getSelectList() [ i ].isSelected())
						aPF.getSelectList() [ i ].setEnabled(true);

				aPF.setTotalCount(aPF.getTotalCount() + 1);
				if (aPF.getTotalCount() == 9) {
					aPF.getOrderList().clear();
					for (int i = 0; i < JavaData.CHAMPION_COUNT; i++) {
						aPF.getSelectList() [ i ].setSelected(false);
						aPF.getSelectList() [ i ].setEnabled(true);
					}
					aPF.switchPlayer();
					aPF.switchPlayerLabel();
				} else if (aPF.getTotalCount() == 18) {
					aPF.getOrderList().clear();
					aPF.switchPlayer();
					aPF.switchPlayerLabel();
					// aPF.gamePlay();
				}
				aPF.updateButtonPictures();
			}
		}

		public String toString() {
			return "mL1";
		}
	};

	// Initial select
	private MouseListener mL2 = new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			if (SlotButton.this.isEnabled()) {
				aPF.getAAS().playSound("select");
				al.getTargets().clear();
				if (SlotButton.this.isEnabled()) {
					aPF.setSlot(SlotButton.this);
					aPF.resetListeners();
				}
				setSkillButtons();
				aPF.setPanelEast(SlotButton.this, aPF.getPlayer());
			}
		}

		public void mouseEntered(MouseEvent e) {
			if (SlotButton.this.isEnabled()) {
				SlotButton [ ] [ ] arr;
				int [ ] coord = new int [ ] { -1 , -1 };
				try {
					coord = g.getCoord(SlotButton.this, aPF.getPlayer());
				} catch (CornerCaseException exc) {
					exc.printStackTrace();
				}
				arr = aPF.getPlayer() == 1? g.getArray1() : g.getArray2();
				try {
					if (arr [ coord [ 0 ] ] [ coord [ 1 ] + 1 ].getChampion() == null)
						arr [ coord [ 0 ] ] [ coord [ 1 ] + 1 ].setBorder(JavaData.MOVE_BORDER);
				} catch (Exception exc) {
					/* Ignore */}
				try {
					if (arr [ coord [ 0 ] ] [ coord [ 1 ] - 1 ].getChampion() == null)
						arr [ coord [ 0 ] ] [ coord [ 1 ] - 1 ].setBorder(JavaData.MOVE_BORDER);
				} catch (Exception exc) {
					/* Ignore */}
				try {
					if (arr [ coord [ 0 ] + 1 ] [ coord [ 1 ] ].getChampion() == null)
						arr [ coord [ 0 ] + 1 ] [ coord [ 1 ] ].setBorder(JavaData.MOVE_BORDER);
				} catch (Exception exc) {
					/* Ignore */}
				try {
					if (arr [ coord [ 0 ] - 1 ] [ coord [ 1 ] ].getChampion() == null)
						arr [ coord [ 0 ] - 1 ] [ coord [ 1 ] ].setBorder(JavaData.MOVE_BORDER);
				} catch (Exception exc) {
					/* Ignore */}
				try {
					if (SlotButton.this.getChampion() != null
							&& sl.isValidStance(SlotButton.this.getChampion().getLogic(),
									SlotButton.this, aPF.getPlayer()))
						al.highlight(SlotButton.this.getChampion().getLogic(), aPF.getPlayer());
				} catch (CornerCaseException exc) {
					exc.printStackTrace();
				}
				aPF.updateButtonPictures();
			}
		}

		public void mouseExited(MouseEvent e) {
			if (SlotButton.this.isEnabled()) {
				SlotButton [ ] [ ] arr;
				int [ ] coord = { -1 , -1 };
				try {
					coord = g.getCoord(SlotButton.this, aPF.getPlayer());
				} catch (CornerCaseException exc) {
					exc.printStackTrace();
				}
				arr = aPF.getPlayer() == 1? g.array1 : g.array2;
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
				al.unHighlight(aPF.getPlayer());
			}
			aPF.updateButtonPictures();
		}

		public String toString() {
			return "mL2";
		}
	};

	// Accepting movement
	private MouseListener mL3 = new MouseAdapter() {
		public void mousePressed(MouseEvent e) { // swap
			if (SlotButton.this.isEnabled()) {
				aPF.getAAS().playSound("select");

				SlotButton.this.setBackground(aPF.getSlot().getBackground());
				SlotButton.this.setChampion(aPF.getSlot().getChampion());
				SlotButton.this.setchampionLabel(aPF.getSlot().getChampionLabel());
				SlotButton.this.setchampionPicture(aPF.getSlot().getchampionPicture());
				SlotButton.this.setNormalImage(aPF.getSlot().getNormalImage());
				SlotButton.this.setGrayedImage(aPF.getSlot().getGrayedImage());
				SlotButton.this.alterMouseAdapter0_3(); // 3->0

				aPF.getSlot().setBackground(JavaData.DEFAULT_BACKGROUND);
				aPF.getSlot().setText("");
				aPF.getSlot().removeAll();
				aPF.getSlot().setChampion(null);
				aPF.getSlot().setNormalImage(null);
				aPF.getSlot().setGrayedImage(null);

				int [ ] beforeCoord = new int [ ] { -1 , -1 };
				int [ ] afterCoord = new int [ ] { -1 , -1 };
				try {
					beforeCoord = g.getCoord(aPF.getSlot(), aPF.getPlayer());
					afterCoord = g.getCoord(SlotButton.this, aPF.getPlayer());
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

				aPF.getMP().generateMove(aPF.getMessages(), aPF.getMove());
				//error prone?
			//	aPF.getMP().generateMessage(aPF.getMessages(), SlotButton.this, dir);
				aPF.resetListeners();
				aPF.clearAllBorders();
				aPF.evalTurns();
				try {
					SlotButton.this.getMouseListeners() [ 2 ].mouseEntered(null);
				} catch (ArrayIndexOutOfBoundsException exc) {
					/* Ignore */ }
				aPF.setSlot(null);
				aPF.updateButtonPictures();
			}
		}

		public String toString() {
			return "mL3";
		}
	};

	// Accepting attack
	private MouseListener mL4 = new MouseAdapter() {
		public void mousePressed(MouseEvent e) { // attack
			if (SlotButton.this.isEnabled()) {

				al.attack(SlotButton.this);
				aPF.disableAll();
				setSkillButtons();
				aPF.setPanelEast(SlotButton.this, aPF.getPlayer());
				aPF.resetListeners();
				aPF.clearAllBorders();
				aPF.evalTurns();
				try {
					SlotButton.this.getMouseListeners() [ 2 ].mouseEntered(null);
				} catch (ArrayIndexOutOfBoundsException exc) {
					/* Ignore */ }
				aPF.setSlot(null);
				aPF.updateButtonPictures();
			}
		}

		public String toString() {
			return "mL4";
		}
	};

	// panelEast's content generator
	private MouseListener mL5 = new MouseAdapter() {
		public void mouseEntered(MouseEvent e) {
			if (aPF.getSlot() != null && side == aPF.getPlayer())
				aPF.setPanelEast(aPF.getSlot(), side);
			else
				aPF.setPanelEast(SlotButton.this, side);
			setSkillButtons();
			aPF.updateButtonPictures();
		}

		public void mouseExited(MouseEvent e) {
			if (aPF.getSlot() != null && side == aPF.getPlayer())
				return;
			aPF.clearSkillButtons(aPF.getPlayer()); //error prone 
			aPF.clearPanelEast(side);
		}

		public String toString() {
			return "mL5";
		}
	};

	public SlotButton(int side) {
		super();
		this.side = side;
		this.setBackground(JavaData.DEFAULT_BACKGROUND);
		setLayout(null);
		addMouseListener(mL1);
	}

	void switchFunctionality() {
		removeMouseListener(mL1);
		addMouseListener(mL5);
		if (champion != null)
			addMouseListener(mL2);
	}

	void alterMouseAdapter0_4() {
		if (getMouseListeners().length == 2)
			addMouseListener(mL4);
		else if (getMouseListeners() [ 2 ].equals(mL4))
			removeMouseListener(mL4);
	}

	void alterMouseAdapter0_3() {
		if (getMouseListeners().length == 2)
			addMouseListener(mL3);
		else if (getMouseListeners() [ 2 ].equals(mL3))
			removeMouseListener(mL3);
	}

	void alterMouseAdapter0_2() {
		if (getMouseListeners().length == 2)
			addMouseListener(mL2);
		else if (getMouseListeners() [ 2 ].equals(mL2))
			removeMouseListener(mL2);
	}

	void alterMouseAdapter2_4() {
		if (getMouseListeners().length == 2)
			addMouseListener(mL4);
		else if (getMouseListeners() [ 2 ].equals(mL2)) {
			removeMouseListener(mL2);
			addMouseListener(mL4);
		} else {
			removeMouseListener(mL4);
			addMouseListener(mL2);
		}
	}

	public void setSkillButtons() {
		Champion localchampion;
		if (champion == null)
			return;
		else if (aPF.getSlot() == null && champion != null
				|| aPF.getSlot() != null && side != aPF.getPlayer())
			localchampion = champion;
		else if (aPF.getSlot() != null && aPF.getSlot().getSide() == aPF.getPlayer())
			localchampion = aPF.getSlot().getChampion();
		else
			return;

		if (side == 2)
			for (int i = 0; i < aPF.getSkillButtons2().length; i++)
				aPF.getSkillButtons2() [ i ].setSkillButton(
						"pics/" + localchampion + "IconS" + (i + 1) + ".png",
						1 - localchampion.evalFraction(i));
		else
			for (int i = 0; i < aPF.getSkillButtons1().length; i++)
				aPF.getSkillButtons1() [ i ].setSkillButton(
						"pics/" + localchampion + "IconS" + (i + 1) + ".png",
						1 - localchampion.evalFraction(i));
	}

	void setchampionLabel(JLabel championLabel) {
		this.championLabel = championLabel;
		if (championLabel == null)
			return;
		add(championLabel);
	}

	JLabel getchampionPicture() {
		return championPicture;
	}

	void setchampionPicture(JLabel championPicture) {
		this.championPicture = championPicture;
		if (championPicture == null)
			return;
		add(championPicture);
	}

	void setImage(ImageIcon ic) {
		if (ic == null)
			return;
		championPicture.setIcon(ic);
	}

}
