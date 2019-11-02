package com.deadlockarena.logic;

import java.awt.event.MouseListener;
import java.util.Optional;

import javax.swing.JButton;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.exception.CornerCaseException;
import com.deadlockarena.exception.InstanceMismatchException;
import com.deadlockarena.graphics.DeadButton;
import com.deadlockarena.graphics.SlotButton;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public final class SlotGrid extends Grid {

	private String position;

	/**
	 * 
	 * @param slotButtons
	 * @param position
	 */
	public SlotGrid(SlotButton [ ] [ ] slotButtons, String position) {
		super(slotButtons);
		this.position = position;
	}

	/**
	 * 
	 * @param deads - list of dead champion buttons
	 * @throws CornerCaseException
	 * @throws InstanceMismatchException
	 */
	public void checkForDeads(DeadButton [ ] deads) throws CornerCaseException {
		for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
			for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
				Champion champion = this.getJButton(i, j).getChampion();
				if (champion == null) {
					continue;
				} else if (champion.isDead()) {
					transferchampion(getJButton(i, j), deads);
				} else {
					throw new CornerCaseException("checkForDeads() in Grid.class");
				}
			}
		}
	}

	/**
	 * 
	 * @param sB    - slot button of champion to transfer
	 * @param deads - list of dead champion buttons
	 */
	private void transferchampion(SlotButton slotButton, DeadButton [ ] deads) {
		Champion h = slotButton.getChampion();

		for (int i = 0; i < deads.length; i++)
			if (deads [ i ].getChampion() == null) {
				deads [ i ].insertDead(h);
				break;
			}

		slotButton.setBackground(JavaData.DEFAULT_BACKGROUND);
		slotButton.removeAll();
		slotButton.setChampion(null);
//		slotButton.removeMouseListener(slotButton.getML2());
//		slotButton.removeMouseListener(slotButton.getML4());

//		mainFrame.clearSkillButtons(mainFrame.getPlayer()); // error prone
//		mainFrame.clearPanelEast(mainFrame.getPlayer()); // error prone?
//		mainFrame.setSlot(null);
	}

	/**
	 * Get the number of champions on the grid
	 * 
	 * @return the number of Champions
	 * @throws InstanceMismatchException
	 */
	public int getNumberOfChampions() {
		int count = 0;
		for (int i = 0; i < super.jButtons.length; i++) {
			for (int j = 0; j < super.jButtons [ i ].length; j++) {
				if (super.jButtons [ i ] [ j ] != null) {
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public SlotButton getJButton(int i, int j) {
		return (SlotButton) super.jButtons [ i ] [ j ];
	}

	@Override
	public SlotButton [ ] [ ] getJButtons() {
		return (SlotButton [ ] [ ]) super.jButtons;
	}

	@Override
	public void addMouseListener(int mLNumber) {

		for (int i = 0; i < jButtons.length; i++) {
			for (int j = 0; j < jButtons [ i ].length; j++) {
				this.getJButton(i, j)
						.addMouseListener(chooseMouseListener(mLNumber, this.getJButton(i, j)));
			}
		}
	}

	private MouseListener chooseMouseListener(int mLNumber, SlotButton slotButton) {
		MouseListener mL = null;
		switch (mLNumber) {
		case 1:
			mL = slotButton.getML1();
			break;
		case 2:
			mL = slotButton.getML2();
			break;
		case 3:
			mL = slotButton.getML3();
			break;
		case 4:
			mL = slotButton.getML4();
			break;
		case 5:
			mL = slotButton.getML5();
			break;
		}
		return mL;
	}

}
