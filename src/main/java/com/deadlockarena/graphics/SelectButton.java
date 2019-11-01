package com.deadlockarena.graphics;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.deadlockarena.Game;
import com.deadlockarena.constant.JavaData;
import com.deadlockarena.logic.Grid;
import com.deadlockarena.logic.SelectGrid;
import com.deadlockarena.logic.SlotGrid;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

@Data
@EqualsAndHashCode(callSuper = true)
public class SelectButton extends JButton {
	private static final long serialVersionUID = 8876199740027195332L;

	private boolean selected;
	private Champion champion;
	private Color color;
	private JLabel championLabel, championPicture;
	private ImageIcon normalImage, grayedImage;

	public SelectButton() {
		super.setFont(JavaData.BASIC_FONT);
		super.setPreferredSize(new Dimension(JavaData.PIXEL * 4 / 5, JavaData.PIXEL * 4 / 5));
		this.selected = false;
	}

	public void populate(Game game, Champion champion) {

		this.champion = champion;

		this.setGraphics();
		this.evaluateColorByLogic();

		MainFrame mainFrame = game.getMainFrame();
		SelectGrid selectGrid = game.getSelectGrid();
		SlotGrid slotGrid1 = game.getSlotGrid1();
		SlotGrid slotGrid2 = game.getSlotGrid2();
		int player = game.getPlayer();

		MouseListener mL = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SelectButton.this.isEnabled()) {
					mainFrame.updateButtonPictures(slotGrid1, slotGrid2, selectGrid);
					// mainFrame.getAAS().playSound("select");
					mainFrame.display(champion);
					mainFrame.setCurrent(SelectButton.this);
					mainFrame.getCurrent().setSelected(true);
					mainFrame.getCurrent().setEnabled(false);
					SelectButton.this.enableAllGrids(slotGrid1, slotGrid2, selectGrid, player);
				}
			}

			public void mouseExited(MouseEvent e) {
				if (SelectButton.this == null) {
					mainFrame.display(champion);
				} else {
					mainFrame.unDisplay(color);
				}
			}

			public void mouseEntered(MouseEvent e) {
				mainFrame.display(champion);
				if (SelectButton.this.isEnabled()) {
					mainFrame.updateButtonPictures(slotGrid1, slotGrid2, selectGrid);
					SelectButton.this.setBackground(Color.gray);
				}
			}
		};
		this.addMouseListener(mL);
	}

	private void setGraphics() {

		this.normalImage = new ImageIcon(
				new ImageIcon(getClass().getResource(JavaData.PICS_PATH + champion.getChampion() + "Icon.png")).getImage()
						.getScaledInstance(JavaData.PIXEL / 2, JavaData.PIXEL / 2,
								Image.SCALE_SMOOTH));
		//super.setLayout(null);
		this.grayedImage = new ImageIcon(GrayFilter.createDisabledImage(normalImage.getImage()));

		this.championPicture = new JLabel(normalImage);
		this.championPicture.setBounds(20, 20, JavaData.PIXEL / 2, JavaData.PIXEL / 2);
		//super.add(championPicture, championPicture.getBounds());

		this.championLabel = new JLabel(champion.getChampion());
		this.championLabel.setForeground(JavaData.DEFAULT_BACKGROUND);
		this.championLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		this.championLabel.setBounds(5, 8, 96, 10);
		//super.add(championLabel, championLabel.getBounds());

	}

	private void evaluateColorByLogic() {
		switch (champion.getLogic()) {
		case 1:
			this.color = Color.pink;
			break;
		case 2:
			this.color = Color.green;
			break;
		case 3:
			this.color = Color.cyan;
			break;
		}
		super.setBackground(color);
	}

	private void enableAllGrids(SlotGrid slotGrid1, SlotGrid slotGrid2, SelectGrid selectGrid,
			int player) {
		for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
			for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
				if (slotGrid1.getJButton(i, j).getChampion() == null && player == 1) {
					slotGrid1.getJButton(i, j).setEnabled(true);
				}
				if (slotGrid2.getJButton(i, j).getChampion() == null && player == 2) {
					slotGrid2.getJButton(i, j).setEnabled(true);
				}
			}
		}
		for (int i = 0; i < JavaData.SELECT_ROW_COUNT; i++) {
			for (int j = 0; j < JavaData.SELECT_COL_COUNT; j++) {
				selectGrid.getJButton(i, j).setEnabled(false);
			}
		}
	}

}
