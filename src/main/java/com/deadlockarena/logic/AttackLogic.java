package com.deadlockarena.logic;

import java.util.ArrayList;
import java.util.List;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.exception.CornerCaseException;
import com.deadlockarena.graphics.MainFrame;
import com.deadlockarena.graphics.SlotButton;

import lombok.Data;

@Data
public class AttackLogic {

	private MainFrame aPF;
	private List<SlotButton> targets = new ArrayList<>();

	/**
	 * Any champion on first row. If first row empty, any hero on second row. Can
	 * never attack third, fourth, or fifth row
	 * 
	 * @param targetGrid
	 */
	public void attack1(Grid targetGrid) {
		boolean targetDetected = false;
		for (int i = 3; i >= 0; i--) {
			if (!targetDetected) {
				for (int j = 0; j < 5; j++) {
					if (targetGrid.getArray() [ i ] [ j ].getChampion() != null) {
						targetDetected = true;
						targets.add(targetGrid.getArray() [ i ] [ j ]);
					}
				}
			}
		}
	}

	/**
	 * First champion of any column.
	 * 
	 * @param targetGrid - grid of potential targets
	 */
	public void attack2(Grid targetGrid) {
		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < 3; i++) {
				if (targetGrid.getArray() [ i ] [ j ].getChampion() != null) {
					targets.add(targetGrid.getArray() [ i ] [ j ]);
					break;
				}
			}
		}

	}

	/**
	 * Any champion of the entire grid.
	 * 
	 * @param targetGrid - grid of potential targets
	 */
	public void attack3(Grid targetGrid) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				if (targetGrid.getArray() [ i ] [ j ].getChampion() != null) {
					targets.add(targetGrid.getArray() [ i ] [ j ]);
				}
			}
		}

	}

	/**
	 * Highlight certain buttons.
	 * 
	 * @param logic
	 * @param thisGrid
	 * @param targetGrid
	 * @throws CornerCaseException
	 */
	public void highlight(int logic, Grid thisGrid, Grid targetGrid) throws CornerCaseException {
		switch (logic) {
		case 1:
			attack1(targetGrid);
			break;
		case 2:
			attack2(targetGrid);
			break;
		case 3:
			attack3(targetGrid);
			break;
		default:
			throw new CornerCaseException("AttackLogic: Incorrect logic input");
		}
		for (int i = 0; i < targets.size(); i++) {
			targets.get(i).setBorder(JavaData.ATTACK_BORDER);
			targets.get(i).setEnabled(true);
		}
	}

	/**
	 * Disable highlight of certain buttons.
	 * 
	 * @param targetGrid - grid of potential targets.
	 */
	public void unHighlight(Grid targetGrid) {
		targets.clear();

		for (int i = 0; i < targetGrid.getArray().length; i++) {
			for (int j = 0; j < targetGrid.getArray() [ i ].length; j++) {
				targetGrid.getArray() [ i ] [ j ].setBorder(JavaData.DEFAULT_BORDER);
				targetGrid.getArray() [ i ] [ j ].setEnabled(false);
			}
		}

	}

	/**
	 * Attack the target.
	 * 
	 * @param targetButton - button of target to attack.
	 * @return whether or not the attack was executed.
	 */
	public boolean attack(SlotButton targetButton) {
		if (aPF.getSlot() == null) {
			// handles case: When user drinks potion, turn is up, attempts to
			// attack other player
			aPF.evenAllListeners();
			return false;
		}
		aPF.getSlot().getChampion().attack(aPF, targetButton);
		return true;
	}

}