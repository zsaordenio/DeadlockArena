package com.deadlockarena.logic;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.exception.CornerCaseException;
import com.deadlockarena.exception.UnmatchedSizeException;
import com.deadlockarena.graphics.DeadButton;
import com.deadlockarena.graphics.SlotButton;
import com.deadlockarena.persistence.entity.Champion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Grid {

	@NonNull
	private SlotButton [ ] [ ] slotButtons;

	@NonNull
	private String position;

	/**
	 * Fill in the 2D slotButtons
	 * 
	 * @param slotList 1D champion slotButtons
	 * @throws UnmatchedSizeException
	 */
	public Grid() throws UnmatchedSizeException {
		this.slotButtons = new SlotButton [ JavaData.SLOT_ROW_COUNT ] [ JavaData.SLOT_COL_COUNT ];
	}

	/**
	 * Retrieve the slotButton in the 2D SlotButton array given the i'th and j'th
	 * coordinates.
	 * 
	 * @param i - i'th coordinate
	 * @param j - j'th coordinate
	 * @return the slot button
	 */
	public SlotButton getSlotButton(int i, int j) {
		return slotButtons [ i ] [ j ];
	}

	/**
	 * 
	 * @param deads - list of dead champion buttons
	 * @throws CornerCaseException
	 */
	public void checkForDeads(DeadButton [ ] deads) throws CornerCaseException {
		for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
			for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
				if (slotButtons [ i ] [ j ].getChampion() == null) {
					continue;
				} else if (slotButtons [ i ] [ j ].getChampion().isDead()) {
					transferchampion(slotButtons [ i ] [ j ], deads);
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
	 */
	public int getNumberOfChampions() {
		int count = 0;
		for (int i = 0; i < slotButtons.length; i++) {
			for (int j = 0; j < slotButtons [ i ].length; j++) {
				if (slotButtons [ i ] [ j ] != null) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Get the coordinates of the grid
	 * 
	 * @param sB - the slotButton to be evaluated
	 * @return the coordinate
	 * @throws CornerCaseException
	 */
	public Coordinate getCoord(SlotButton sB) throws CornerCaseException {
		for (int i = 0; i < slotButtons.length; i++) {
			for (int j = 0; j < slotButtons [ i ].length; j++) {
				if (slotButtons [ i ] [ j ].equals(sB)) {
					return new Coordinate(i, j);
				}
			}
		}
		throw new CornerCaseException("Grid never found the right coordinate");
	}
}