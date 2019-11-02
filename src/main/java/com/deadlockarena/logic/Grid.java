package com.deadlockarena.logic;

import java.awt.event.MouseListener;

import javax.swing.JButton;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.exception.CornerCaseException;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Grid {

	protected JButton [ ] [ ] jButtons;

	public void enableAll() {
		for (int i = 0; i < jButtons.length; i++) {
			for (int j = 0; j < jButtons [ i ].length; j++) {
				this.getJButton(i, j).setEnabled(true);
			}
		}
	}

	public void disableAll() {
		for (int i = 0; i < jButtons.length; i++) {
			for (int j = 0; j < jButtons [ i ].length; j++) {
				this.getJButton(i, j).setEnabled(false);
			}
		}
	}

	public void enableAllIfSelected() {
		for (int i = 0; i < jButtons.length; i++) {
			for (int j = 0; j < jButtons [ i ].length; j++) {
				if (!this.getJButton(i, j).isSelected()) {
					this.getJButton(i, j).setEnabled(true);
				}
			}
		}
	}

	public void clearBorders() {
		for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
			for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
				this.getJButton(i, j).setBorder(JavaData.DEFAULT_BORDER);
			}
		}
	}

	public abstract void addMouseListener(int mLNumber);

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
	public Coordinate getCoord(JButton jB) {
		for (int i = 0; i < jButtons.length; i++) {
			for (int j = 0; j < jButtons [ i ].length; j++) {
				if (jB.equals(jButtons [ i ] [ j ])) {
					return new Coordinate(i, j);
				}
			}
		}
		return null;
	}

}