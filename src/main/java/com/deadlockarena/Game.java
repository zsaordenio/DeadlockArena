package com.deadlockarena;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.graphics.MainFrame;
import com.deadlockarena.graphics.SlotButton;
import com.deadlockarena.logic.MainLogic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Game {
	private MainFrame mainFrame;
	private MainLogic mainLogic;

	private int player;
	private int totalCount; // 0-18

	private int move;
	private int currentCap;

	public Game() {
		this.mainLogic = new MainLogic();
		this.mainFrame = new MainFrame();
	}

	public void executeFirstPhase() {
		this.mainFrame.addPanels();
		this.mainFrame.addButtons();
	}

	public void evalTurns(SlotButton slot) {
		this.move++;
		if (this.move == currentCap) {
			slot = null;
			if (player == 2) {
				mainLogic.updateAllCoolDowns(mainFrame.getSlotButtons2());
			} else {
				mainLogic.updateAllCoolDowns(mainFrame.getSlotButtons1());
			}
			mainLogic.switchListeners(mainFrame.getSlotButtons1(), mainFrame.getSlotButtons2(),
					player, mainFrame.getMessages());
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
