package com.deadlockarena;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.graphics.MainFrame;
import com.deadlockarena.graphics.SelectButton;
import com.deadlockarena.graphics.SlotButton;
import com.deadlockarena.logic.Grid;
import com.deadlockarena.logic.MainLogic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Game {
	private MainFrame mainFrame;
	private MainLogic mainLogic;

	private SelectButton [ ] [ ] selectButtons;
	
	private Grid grid1, grid2;

	private int player;
	private int totalCount; // 0-18

	private int move;
	private int currentCap;

	public Game() {
		this.mainLogic = new MainLogic();
		this.mainFrame = new MainFrame();
		
		this.selectButtons = new SelectButton [ JavaData.SELECT_ROW_COUNT ] [ JavaData.SELECT_COL_COUNT ];
		
		this.player = 1;
		this.totalCount = 0;
		this.move = 0;
		this.currentCap = 1;
	}

	public void executePhase1() {
		this.mainFrame.addPanels();

		this.mainFrame.addSelectButtons();
		this.mainFrame.populateSelectButtons(player, grid1, grid2);

		this.mainFrame.addSlotButtons(grid1, grid2);
		this.mainFrame.populateSlotButtons(this, grid1);
		this.mainFrame.populateSlotButtons(this, grid2);
	}

	public void executePhase2() {

	}

	public void evalTurns(SlotButton slot) {
		this.move++;
		if (this.move == currentCap) {
			slot = null;
			if (player == 2) {
				mainLogic.updateAllCoolDowns(grid2);
			} else {
				mainLogic.updateAllCoolDowns(grid1);
			}
			mainLogic.switchListeners(grid1, grid2, player, mainFrame.getMessages());
			mainFrame.clearSkillButtons(player);
			mainFrame.clearPanelEast(player);
			if (currentCap < JavaData.CAP_TURN) {
				currentCap++;
			}
			this.move = 0;
		}
	}

	public void switchPlayer() {
		if (player == 1) {
			player = 2;
		} else {
			player = 1;
		}
	}
}
