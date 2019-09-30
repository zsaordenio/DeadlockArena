package com.deadlockarena.logic;

import java.util.ArrayList;
import java.util.List;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.exception.RemainderException;
import com.deadlockarena.graphics.AppPrincipalFrame;
import com.deadlockarena.graphics.SlotButton;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class AttackLogic {

	private Grid g;
	private final AppPrincipalFrame aPF;
	private List<SlotButton> targets = new ArrayList<>();

	@Autowired
	public AttackLogic(AppPrincipalFrame aPF) {
		this.aPF = aPF;
	}

	// Any hero on first row
	// If first row empty, any hero on second row
	// Can never attack third, fourth, or fifth row
	public void attack1(int player) {
		boolean targetDetected = false;
		if (player == 2) {
			for (int i = 3; i >= 0; i--) {
				if (!targetDetected) {
					for (int j = 0; j < 5; j++) {
						if (g.getArray1() [ i ] [ j ].getChampion() != null) {
							targetDetected = true;
							targets.add(g.getArray1() [ i ] [ j ]);
						}
					}
				}
			}
		} else {
			for (int i = 0; i < 4; i++) {
				if (!targetDetected) {
					for (int j = 0; j < 5; j++) {
						if (g.getArray2() [ i ] [ j ].getChampion() != null) {
							targetDetected = true;
							targets.add(g.getArray2() [ i ] [ j ]);
						}
					}
				}
			}
		}
	}

	// Any first hero of any column
	public void attack2(int player) {
		if (player == 2) {
			for (int j = 0; j < 5; j++) {
				for (int i = 3; i >= 0; i--) {
					if (g.getArray1() [ i ] [ j ].getChampion() != null) {
						targets.add(g.getArray1() [ i ] [ j ]);
						break;
					}
				}
			}
		} else {
			for (int j = 0; j < 5; j++) {
				for (int i = 0; i < 3; i++) {
					if (g.array2 [ i ] [ j ].getChampion() != null) {
						targets.add(g.array2 [ i ] [ j ]);
						break;
					}
				}
			}
		}
	}

	public void attack3(int player) {
		if (player == 2) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 5; j++) {
					if (g.array1 [ i ] [ j ].getChampion() != null) {
						targets.add(g.array1 [ i ] [ j ]);
					}
				}
			}
		} else {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 5; j++) {
					if (g.array2 [ i ] [ j ].getChampion() != null) {
						targets.add(g.array2 [ i ] [ j ]);
					}
				}
			}
		}
	}

	public void highlight(int logic, int player) throws RemainderException {
		switch (logic) {
		case 1:
			attack1(player);
			break;
		case 2:
			attack2(player);
			break;
		case 3:
			attack3(player);
			break;
		default:
			throw new RemainderException("AttackLogic: Incorrect logic input");
		}
		for (int i = 0; i < targets.size(); i++) {
			targets.get(i).setBorder(JavaData.ATTACKBORDER);
			targets.get(i).setEnabled(true);
		}
	}

	public void unHighlight(int player) {
		targets.clear();
		if (player == 2) {
			for (int i = 0; i < g.array1.length; i++) {
				for (int j = 0; j < g.array1 [ i ].length; j++) {
					g.array1 [ i ] [ j ].setBorder(JavaData.DEFAULTBORDER);
					g.array1 [ i ] [ j ].setEnabled(false);
				}
			}
		} else {
			for (int i = 0; i < g.array2.length; i++) {
				for (int j = 0; j < g.array2 [ i ].length; j++) {
					g.array2 [ i ] [ j ].setBorder(JavaData.DEFAULTBORDER);
					g.array2 [ i ] [ j ].setEnabled(false);
				}
			}
		}
	}

	// Attack!
	public boolean attack(SlotButton targetButton) {
		// handles case: When user drinks potion, turn is up, attempts to attack other player
		if (aPF.getSlot() == null) {
			aPF.evenAllListeners();
			return false;
		}
		aPF.getSlot().getChampion().attack(aPF, targetButton);
		return true;
	}

}