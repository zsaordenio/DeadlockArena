package com.deadlockarena.logic;

import javax.swing.JTextArea;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.graphics.SlotButton;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainLogic {

	private StanceLogic stanceLogic;
	private AttackLogic attackLogic;
	private Grid grid1;
	private Grid grid2;
	private MessageProcessor messageProcessor;

	public void switchListeners(SlotButton [ ] slotButtons1, SlotButton [ ] slotButtons2,
			int player, JTextArea messages) {
		for (int i = 0; i < JavaData.SLOT_COUNT; i++) {
			// TO-DO based on player
			if (player == 2) {
				if (slotButtons1 [ i ].getChampion() != null) {
				//	slotButtons1 [ i ].alterMouseAdapter0_4();
				}
				slotButtons2 [ i ].setEnabled(false);
				slotButtons1 [ i ].setEnabled(true);
			} else {
				if (slotButtons2 [ i ].getChampion() != null) {
				//	slotButtons2 [ i ].alterMouseAdapter0_4();
				}
				slotButtons2 [ i ].setEnabled(true);
				slotButtons1 [ i ].setEnabled(false);
			}
		}
		this.messageProcessor.endTurn(messages);

		// switchPlayerLabel();
		this.messageProcessor.nextPlayer(messages, player);
	}

	public void updateAllCoolDowns(SlotButton [ ] slotButtons) {
		// TO-DO based on player
		for (int i = 0; i < slotButtons.length; i++) {
			if (slotButtons [ i ].getChampion() != null) {
				Champion h = slotButtons [ i ].getChampion();
				h.setCurrentSkill1CD(h.getCurrentSkill1CD() - 1);
				h.setCurrentSkill2CD(h.getCurrentSkill2CD() - 1);
				h.setCurrentSkill3CD(h.getCurrentSkill3CD() - 1);
				h.setCurrentSkill4CD(h.getCurrentSkill4CD() - 1);
				h.setCurrentSkill5CD(h.getCurrentSkill5CD() - 1);
			}
		}
	}
}