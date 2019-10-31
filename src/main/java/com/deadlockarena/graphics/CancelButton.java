package com.deadlockarena.graphics;
import javax.swing.JButton;

import com.deadlockarena.constant.JavaData;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Data
@EqualsAndHashCode(callSuper = true)
public class CancelButton extends JButton {
	private static final long serialVersionUID = 4505212405863517256L;

	private MainFrame aPF;
	
	public CancelButton(MainFrame aPF) {
		this.aPF = aPF;
		addMouseListener(mL1);
		setFont(JavaData.BASIC_FONT);
	}

	private MouseListener mL1 = new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			aPF.getAAS().playSound("select");
			if (aPF.getCurrent() != null) {
				aPF.getCurrent().setSelected(false);
				aPF.setCurrent(null);
				for (int i = 0; i < JavaData.CHAMPION_COUNT; i++) {
					if (!aPF.getSelectList() [ i ].isSelected())
						aPF.getSelectList() [ i ].setEnabled(true);
				}
				for (int i = 0; i < JavaData.SLOT_COUNT; i++) {
					if (aPF.getSlotList1() [ i ].getChampion() == null && aPF.getPlayer() == 1)
						aPF.getSlotList1() [ i ].setEnabled(false);
					if (aPF.getSlotList2() [ i ].getChampion() == null && aPF.getPlayer() == 2)
						aPF.getSlotList2() [ i ].setEnabled(false);
				}
			} else {
				if (aPF.getOrderList().empty())
					return;
				JButton [ ] buttons = aPF.getOrderList().pop();
				SlotButton slotButton = (SlotButton) buttons [ 0 ];
				slotButton.setChampion(null);
				slotButton.setSelected(false);
				slotButton.setBackground(JavaData.DEFAULT_BACKGROUND);
				slotButton.setText("");
				SelectButton selectButton = (SelectButton) buttons [ 1 ];
				selectButton.setEnabled(true);
				selectButton.setSelected(false);
				aPF.setTotalCount(aPF.getTotalCount()-1);
			}
		}
	};

	private MouseListener mL2 = new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			aPF.getAAS().playSound("select");
			if (aPF.getSlot() != null) {
				aPF.clearSkillButtons(aPF.getPlayer()); //error prone 
				aPF.clearPanelEast(aPF.getPlayer());
				aPF.clearAllBorders();
				aPF.evenAllListeners();
				aPF.disableAll();
				aPF.setSlot(null);
			}
			aPF.updateButtonPictures();
		}
	};

	public void alterMouseAdapter1_2() {
		removeMouseListener(mL1);
		addMouseListener(mL2);
	}

}
