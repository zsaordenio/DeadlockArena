package com.deadlockarena.logic;

import javax.swing.JTextArea;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.graphics.SelectButton;
import com.deadlockarena.persistence.entity.Champion;

public class MessageProcessor {

	public void generateMove(JTextArea messages, int move) {
		messages.append("Move " + (move + 1) + ": ");
	}

	// move
	public void generateMessage(JTextArea messages, SelectButton sB, String dir) {
		messages.append(sB.getChampion() + " moved " + dir + "\n");
	}

	public void generateMessage(JTextArea messages, Champion attacker, Champion target) {
		messages.append(attacker + " missed hitting " + target + "\n");
	}

	// attack
	public void generateMessage(JTextArea messages, Champion attacker, Champion target,
			int [ ] damage, boolean [ ] critical) {

		String damageStr = "";
		for (int i = 0; i < damage.length; i++)
			damageStr += damage [ i ] + (critical [ i ] ? " (CRIT!)" : "") + " + ";
		damageStr = damageStr.substring(0, damageStr.length() - 3);

		String msg = "";
		int chooseMsg = JavaData.RANDOM.nextInt(1);
		switch (chooseMsg) {
		case 0:
			msg = attacker + " dealt " + damageStr + " to " + target;
			break;
		/*
		 * case 1: msg += attacker + " struck " + damage + " points off of " + target +
		 * "'s health!"; break; case 2: msg += attacker + " shaved " + damage +
		 * " damage off of " + target + "'s health!"; break; case 3: msg += attacker +
		 * " dealt " + damage + " damage to " + target + "'s health!"; break; case 4:
		 * msg += attacker + " caused " + damage + " damage against " + target +
		 * "'s health!"; break;
		 */
		}
		messages.append(msg + "\n");
	}

	// drink
	public void generateMessage(JTextArea messages, Champion c, int healed, int total, boolean hp) {
		String hpStr = hp ? "hp" : "mp";
		messages.append(c + " recovered " + healed + " " + hpStr + ".\n");
	}

	public void nextPlayer(JTextArea messages, int player) {
		messages.append((player == 1? "Player 1" : "Player 2") + "\n");
	}

	public void endTurn(JTextArea messages) {
		messages.append("------------------------------------------------------------");
	}

}
