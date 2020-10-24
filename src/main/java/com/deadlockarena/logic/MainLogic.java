package com.deadlockarena.logic;

import javax.swing.JTextArea;

import com.deadlockarena.config.SpringUtils;
import com.deadlockarena.constant.JavaData;
import com.deadlockarena.graphics.SlotButton;
import com.deadlockarena.persistence.bootstrap.JpaGetData;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;

@Data
public class MainLogic {

	private JpaGetData jpaGetData;

	private StanceLogic stanceLogic;
	private AttackLogic attackLogic;
	private MessageProcessor messageProcessor;

	public MainLogic() {
		this.stanceLogic = new StanceLogic();
		this.attackLogic = new AttackLogic();
		this.messageProcessor = new MessageProcessor();
		this.jpaGetData = SpringUtils.jgd;
	}

//	public void populateSlotButtons(Game game, SlotGrid slotGrid) {
//		for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
//			for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
//				(slotGrid.getJButton(i, j)).populate(game);
//			}
//		}
//	}

	public void switchListeners(SlotButton [ ] [ ] slotButtons1, SlotButton [ ] [ ] slotButtons2,
			int player, JTextArea messages) {
		for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
			for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
				// TO-DO based on player
				if (player == 2) {
					if (slotButtons1 [ i ] [ j ].getChampion() != null) {
						// slotButtons1 [ i ].alterMouseAdapter0_4();
					}
					slotButtons2 [ i ] [ j ].setEnabled(false);
					slotButtons1 [ i ] [ j ].setEnabled(true);
				} else {
					if (slotButtons2 [ i ] [ j ].getChampion() != null) {
						// slotButtons2 [ i ].alterMouseAdapter0_4();
					}
					slotButtons2 [ i ] [ j ].setEnabled(true);
					slotButtons1 [ i ] [ j ].setEnabled(false);
				}
			}
		}
		this.messageProcessor.endTurn(messages);
		// switchPlayerLabel();
		this.messageProcessor.nextPlayer(messages, player);
	}

	public void updateAllCoolDowns(SlotGrid grid) {
		for (int i = 0; i < grid.getJButtons().length; i++) {
			for (int j = 0; j < grid.getJButtons() [ i ].length; j++) {
				if (grid.getJButton(i, j).getChampion() != null) {
					Champion h = grid.getJButton(i, j).getChampion();
					h.setCurrentSkill1CD(h.getCurrentSkill1CD() - 1);
					h.setCurrentSkill2CD(h.getCurrentSkill2CD() - 1);
					h.setCurrentSkill3CD(h.getCurrentSkill3CD() - 1);
					h.setCurrentSkill4CD(h.getCurrentSkill4CD() - 1);
					h.setCurrentSkill5CD(h.getCurrentSkill5CD() - 1);
				}
			}
		}
	}
}