package com.deadlockarena.logic;

import com.deadlockarena.exception.RemainderException;
import com.deadlockarena.graphics.SlotButton;

import lombok.Data;

@Data
public class StanceLogic {

	private Grid g;

	// Must be hero on first row
	// If first row empty, any hero on second row
	// Can never be on third, fourth, or fifth row to attack
	boolean stance1(SlotButton sB, int player) {
		int [ ] coords = new int [ ] { -1 , -1 };
		try {
			coords = g.getCoord(sB, player);
		} catch (RemainderException exc) {
			exc.printStackTrace();
		}
		if (player == 2) {
			if (coords [ 0 ] == 0) {
				return true;
			} else if (coords [ 0 ] == 1) {
				for (int j = 0; j < 5; j++) {
					if (g.getArray2() [ coords [ 0 ] - 1 ] [ j ].getChampion() != null)
						return false;
				}
				return true;
			} else if (coords [ 0 ] == 2) {
				for (int j = 0; j < 5; j++) {
					if (g.getArray2() [ coords [ 0 ] - 1 ] [ j ].getChampion() != null)
						return false;
				}
				for (int j = 0; j < 5; j++) {
					if (g.getArray2() [ coords [ 0 ] - 2 ] [ j ].getChampion() != null)
						return false;
				}
				return true;
			} else if (coords [ 0 ] == 3) {
				for (int j = 0; j < 5; j++) {
					if (g.getArray2() [ coords [ 0 ] - 1 ] [ j ].getChampion() != null)
						return false;
				}
				for (int j = 0; j < 5; j++) {
					if (g.getArray2() [ coords [ 0 ] - 2 ] [ j ].getChampion() != null)
						return false;
				}
				for (int j = 0; j < 5; j++) {
					if (g.getArray2() [ coords [ 0 ] - 3 ] [ j ].getChampion() != null)
						return false;
				}
				return true;
			} else {
				return false;
			}
		} else {
			if (coords [ 0 ] == 3) {
				return true;
			} else if (coords [ 0 ] == 2) {
				for (int j = 0; j < 5; j++) {
					if (g.getArray1() [ coords [ 0 ] + 1 ] [ j ].getChampion() != null)
						return false;
				}
				return true;
			} else if (coords [ 0 ] == 1) {
				for (int j = 0; j < 5; j++) {
					if (g.getArray1() [ coords [ 0 ] + 1 ] [ j ].getChampion() != null)
						return false;
				}
				for (int j = 0; j < 5; j++) {
					if (g.getArray1() [ coords [ 0 ] + 2 ] [ j ].getChampion() != null)
						return false;
				}
				return true;
			} else if (coords [ 0 ] == 0) {
				for (int j = 0; j < 5; j++) {
					if (g.getArray1() [ coords [ 0 ] + 1 ] [ j ].getChampion() != null)
						return false;
				}
				for (int j = 0; j < 5; j++) {
					if (g.getArray1() [ coords [ 0 ] + 2 ] [ j ].getChampion() != null)
						return false;
				}
				for (int j = 0; j < 5; j++) {
					if (g.getArray1() [ coords [ 0 ] + 3 ] [ j ].getChampion() != null)
						return false;
				}
				return true;
			} else {
				return false;
			}
		}

	}

	// Must be first hero of any column
	boolean stance2(SlotButton sB, int player) {
		int [ ] coords = new int [ ] { -1 , -1 };
		try {
			coords = g.getCoord(sB, player);
		} catch (RemainderException exc) {
			exc.printStackTrace();
		}

		if (player == 2) {
			if (coords [ 0 ] == 0)
				return true;
			for (int i = 0; i < coords [ 0 ]; i++) {
				if (g.getArray2() [ i ] [ coords [ 1 ] ].getChampion() != null)
					return false;
			}
			return true;
		} else {
			if (coords [ 0 ] == 3)
				return true;
			for (int i = 3; i > coords [ 0 ]; i--) {
				if (g.getArray1() [ i ] [ coords [ 1 ] ].getChampion() != null)
					return false;
			}
			return true;
		}
	}

	// Check for valid stance
	public boolean isValidStance(int logic, SlotButton sB, int player)
			throws RemainderException {
		boolean validStance = false;
		switch (logic) {
		case 1:
			validStance = stance1(sB, player);
			break;
		case 2:
			validStance = stance2(sB, player);
			break;
		case 3:
			validStance = true;
			break;
		default:
			throw new RemainderException("StanceLogic: Incorrect logic input");
		}
		return validStance;
	}
}