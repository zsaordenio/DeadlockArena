package com.deadlockarena.logic;

import lombok.Data;

@Data
public class MainLogic {
	private StanceLogic stanceLogic;
	private AttackLogic attackLogic;
	private Grid grid1;
	private Grid grid2;
	private MessageProcessor messageProcessor;
	
	private int player; // 1 is false, 2 is true
	private int totalCount; // 0-18
	private int move;
	private int currentCAP_TURN;
}