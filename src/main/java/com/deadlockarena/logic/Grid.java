package com.deadlockarena.logic;

import com.deadlockarena.backend.persistence.domain.entity.Champion;
import com.deadlockarena.constant.JavaData;
import com.deadlockarena.exception.RemainderException;
import com.deadlockarena.graphics.AppPrincipalFrame;
import com.deadlockarena.graphics.DeadButton;
import com.deadlockarena.graphics.SlotButton;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Grid {

	public SlotButton [ ] [ ] array1;
	public SlotButton [ ] [ ] array2;

	// Fill in the 2D champion array
	public Grid(SlotButton [ ] slotList1, SlotButton [ ] slotList2) {
		array1 = new SlotButton [ 4 ] [ 5 ];
		array2 = new SlotButton [ 4 ] [ 5 ];
		int ctr = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				array1 [ i ] [ j ] = slotList1 [ ctr ];
				array2 [ i ] [ j ] = slotList2 [ ctr ];
				ctr++;
			}
		}
	}

	public void checkForDeads(AppPrincipalFrame aPF) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				if (array1 [ i ] [ j ].getChampion() == null)
					continue;
				else if (array1 [ i ] [ j ].getChampion().isDead())
					transferchampion(array1 [ i ] [ j ], aPF);
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				if (array2 [ i ] [ j ].getChampion() == null)
					continue;
				else if (array2 [ i ] [ j ].getChampion().isDead())
					transferchampion(array2 [ i ] [ j ], aPF);
			}
		}
	}

	// Helper method for checkForDeads()
	public void transferchampion(SlotButton sB, AppPrincipalFrame aPF) {
		Champion h = sB.getChampion();
		int SIDE = sB.getSide();
		DeadButton [ ] deads;

		deads = SIDE == 2? aPF.getDeads2() : aPF.getDeads1();

		for (int i = 0; i < deads.length; i++)
			if (deads [ i ].getChampion() == null) {
				deads [ i ].insertDead(h);
				break;
			}

		sB.setBackground(JavaData.DEFAULTBACKGROUND);
		sB.removeAll();
		sB.setChampion(null);
		sB.removeMouseListener(sB.getML2());
		sB.removeMouseListener(sB.getML4());

		aPF.clearSkillButtons(aPF.getPlayer()); //error prone 
		aPF.clearPanelEast(aPF.getPlayer()); //error prone?
		aPF.setSlot(null);

	}

	// Detects the number of championes in the grid
	public int getSize(boolean player) {
		SlotButton [ ] [ ] sBGrid = player ? array2 : array1;
		int s = 0;
		for (int i = 0; i < sBGrid.length; i++) {
			for (int j = 0; j < sBGrid [ i ].length; j++) {
				if (sBGrid [ i ] [ j ] != null)
					s++;
			}
		}
		return s;
	}

	public int [ ] getCoord(SlotButton sB, int player) throws RemainderException {
		SlotButton [ ] [ ] arr = player == 1 ? array1 : array2;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr [ i ].length; j++) {
				if (arr [ i ] [ j ].equals(sB)) {
					return new int [ ] { i , j };
				}
			}
		}
		throw new RemainderException(
				"Grid: Never found the right coordinate. Return value is null");

	}
}