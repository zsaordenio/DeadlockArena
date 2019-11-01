package com.deadlockarena.logic;

import javax.swing.JButton;

import com.deadlockarena.graphics.SelectButton;
import com.deadlockarena.graphics.SlotButton;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SelectGrid extends Grid {

	public SelectGrid(SelectButton [][] selectButtons, String position) {
		super(selectButtons, position);
	}
	
	@Override
	public SelectButton getJButton(int i, int j) {
		return (SelectButton) super.jButtons [ i ] [ j ];
	}
	
	@Override
	public SelectButton [][] getJButtons(){
		return (SelectButton [][]) super.jButtons;
	}


}
