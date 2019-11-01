package com.deadlockarena.logic;

import javax.swing.JButton;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.exception.CornerCaseException;
import com.deadlockarena.exception.InstanceMismatchException;
import com.deadlockarena.exception.UnmatchedSizeException;
import com.deadlockarena.graphics.DeadButton;
import com.deadlockarena.graphics.SlotButton;
import com.deadlockarena.persistence.entity.Champion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public abstract class Grid {

	protected JButton [ ] [ ] jButtons;
	protected String position;

	/**
	 * Retrieve the slotButton in the 2D SlotButton array given the i'th and j'th
	 * coordinates.
	 * 
	 * @param i - i'th coordinate.
	 * @param j - j'th coordinate.
	 * @return the j button.
	 */
	public abstract JButton getJButton(int i, int j);

	/**
	 * Set the jButton of an element in jButtons.
	 * 
	 * @param i       - i'th coordinate.
	 * @param j       - j'th coordinate.
	 * @param jButton - the jButton to set it to.
	 */
	public void setJButton(int i, int j, JButton jButton) {
		this.jButtons [ i ] [ j ] = jButton;
	}

	/**
	 * Get the coordinates of the grid.
	 * 
	 * @param sB - the slotButton to be evaluated.
	 * @return the coordinate.
	 * @throws CornerCaseException
	 */
	public Coordinate getCoord(JButton jB) throws CornerCaseException {
		for (int i = 0; i < jButtons.length; i++) {
			for (int j = 0; j < jButtons [ i ].length; j++) {
				if (jButtons [ i ] [ j ].equals(jB)) {
					return new Coordinate(i, j);
				}
			}
		}
		throw new CornerCaseException("Grid never found the right coordinate");
	}
}