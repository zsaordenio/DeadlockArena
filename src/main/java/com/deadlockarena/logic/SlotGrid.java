package com.deadlockarena.logic;

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
public class SlotGrid extends Grid {

	/**
	 * 
	 * @param slotButtons
	 * @param position
	 */
	public SlotGrid(SlotButton [ ] [ ] slotButtons, String position) {
		super(slotButtons, position);
	}

	/**
	 * 
	 * @param deads - list of dead champion buttons
	 * @throws CornerCaseException
	 * @throws InstanceMismatchException
	 */
	public void checkForDeads(DeadButton [ ] deads)
			throws CornerCaseException, InstanceMismatchException {
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
	private void transferchampion(SlotButton sB, DeadButton [ ] deads) {
		Champion h = sB.getChampion();

		for (int i = 0; i < deads.length; i++)
			if (deads [ i ].getChampion() == null) {
				deads [ i ].insertDead(h);
				break;
			}

		sB.setBackground(JavaData.DEFAULT_BACKGROUND);
		sB.removeAll();
		sB.setChampion(null);
//		sB.removeMouseListener(sB.getML2());
//		sB.removeMouseListener(sB.getML4());

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

}
