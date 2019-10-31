package com.deadlockarena.logic;

import com.deadlockarena.exception.CornerCaseException;
import com.deadlockarena.graphics.SlotButton;

import lombok.Data;

@Data
public class StanceLogic {

	/**
	 * Must be hero on first row If first row empty, any hero on second row. Can
	 * never be on third, fourth, or fifth row to attack.
	 * 
	 * @param sB       - slot button of the champion on the grid.
	 * @param thisGrid - the grid that the slot button is on.
	 * @return whether or not the stance is valid.
	 */
	public boolean stance1(SlotButton sB, Grid thisGrid) {
		Coordinate coords = sB.getCoordinate();
		// TO-DO: Analyze the position
		String position = sB.getPosition();

		if (coords.getI() == 0) {
			return true;
		} else if (coords.getI() == 1) {
			for (int j = 0; j < 5; j++) {
				if (thisGrid.getArray() [ coords.getI() - 1 ] [ j ].getChampion() != null)
					return false;
			}
			return true;
		} else if (coords.getI() == 2) {
			for (int j = 0; j < 5; j++) {
				if (thisGrid.getArray() [ coords.getI() - 1 ] [ j ].getChampion() != null)
					return false;
			}
			for (int j = 0; j < 5; j++) {
				if (thisGrid.getArray() [ coords.getI() - 2 ] [ j ].getChampion() != null)
					return false;
			}
			return true;
		} else if (coords.getI() == 3) {
			for (int j = 0; j < 5; j++) {
				if (thisGrid.getArray() [ coords.getI() - 1 ] [ j ].getChampion() != null)
					return false;
			}
			for (int j = 0; j < 5; j++) {
				if (thisGrid.getArray() [ coords.getI() - 2 ] [ j ].getChampion() != null)
					return false;
			}
			for (int j = 0; j < 5; j++) {
				if (thisGrid.getArray() [ coords.getI() - 3 ] [ j ].getChampion() != null)
					return false;
			}
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Must be the first hero of any column.
	 * 
	 * @param sB       - slot button of the champion on the grid
	 * @param thisGrid - the grid that the slot button is on.
	 * @return whether or not the stance is valid.
	 */
	public boolean stance2(SlotButton sB, Grid thisGrid) {
		Coordinate coords = sB.getCoordinate();
		// TO-DO: Analyze the position
		String position = sB.getPosition();
		
		if (coords.getI() == 0)
			return true;
		for (int i = 0; i < coords.getI(); i++) {
			if (thisGrid.getArray() [ i ] [ coords.getJ() ].getChampion() != null)
				return false;
		}
		return true;
	}

	/**
	 * Check to see if the stance is valid.
	 * 
	 * @param logic    - logic of the champion: 1, 2, or 3.
	 * @param sB       - slot button of the champion on the grid.
	 * @param thisGrid - the grid that the slot button is on.
	 * @return whether or not the stance is valid.
	 * @throws CornerCaseException
	 */
	public boolean isValidStance(int logic, SlotButton sB, Grid thisGrid)
			throws CornerCaseException {
		boolean validStance = false;
		switch (logic) {
		case 1:
			validStance = stance1(sB, thisGrid);
			break;
		case 2:
			validStance = stance2(sB, thisGrid);
			break;
		case 3:
			validStance = true;
			break;
		default:
			throw new CornerCaseException("StanceLogic: Incorrect logic input");
		}
		return validStance;
	}
}