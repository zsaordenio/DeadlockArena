package com.deadlockarena;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.graphics.MainFrame;
import com.deadlockarena.graphics.SelectButton;
import com.deadlockarena.graphics.SlotButton;
import com.deadlockarena.logic.MainLogic;
import com.deadlockarena.logic.SelectGrid;
import com.deadlockarena.logic.SlotGrid;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Game {
	private MainFrame mainFrame;
	private MainLogic mainLogic;

	private SelectGrid selectGrid;
	private SlotGrid slotGrid1, slotGrid2;

	private SelectButton currentSelect;
	private SlotButton currentSlot;

	private int player;
	private int totalCount; // 0-18

	private int move;
	private int currentCap;

	public Game() {
		this.mainFrame = new MainFrame();
		this.mainLogic = new MainLogic();

		this.selectGrid = new SelectGrid(
				new SelectButton [ JavaData.SELECT_ROW_COUNT ] [ JavaData.SELECT_COL_COUNT ]);
		this.slotGrid1 = new SlotGrid(
				new SlotButton [ JavaData.SLOT_ROW_COUNT ] [ JavaData.SLOT_COL_COUNT ], "bottom");
		this.slotGrid2 = new SlotGrid(
				new SlotButton [ JavaData.SLOT_ROW_COUNT ] [ JavaData.SLOT_COL_COUNT ], "top");

		this.player = 1;
		this.totalCount = 0;
		this.move = 0;
		this.currentCap = 1;
	}

	public void executePhase1() {
		this.mainFrame.addPanels();
		this.mainFrame.addSelectButtons(this, selectGrid);
		this.mainFrame.addSlotButtons(this, slotGrid1, slotGrid2);

		this.selectGrid.addMouseListener(1);
		this.slotGrid1.addMouseListener(1);
		// this.slotGrid1.addMouseListener(SlotButton.mL2);
	}

	// TO-DO move some functionality here
	public void executePhase2() {

	}

	public void evalTurns(SlotButton slot) {
		this.move++;
		if (this.move == currentCap) {
			slot = null;
			if (player == 2) {
				mainLogic.updateAllCoolDowns(slotGrid2);
			} else {
				mainLogic.updateAllCoolDowns(slotGrid1);
			}
			// mainLogic.switchListeners(slotGrid1, slotGrid2, player,
			// mainFrame.getMessages());
			mainFrame.clearSkillButtons(player);
			mainFrame.clearPanelEast(player);
			if (currentCap < JavaData.CAP_TURN) {
				currentCap++;
			}
			this.move = 0;
		}
	}

	public void switchPlayer() {
		this.player = this.player == 2 ? 1 : 2;
	}

	public void selectMLMousePressed() {
		this.slotGrid1.enableAll();
	}
}
