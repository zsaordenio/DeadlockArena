package com.deadlockarena.graphics;

import javax.swing.*;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.logic.Grid;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

	public void populate(final Champion champion, final int player,
			final Grid grid1, final Grid grid2, final MainFrame mainFrame) {

		this.selected = false;
		this.champion = champion;

		this.normalImage = new ImageIcon(new ImageIcon("pics/" + champion + "Icon.png").getImage()
				.getScaledInstance(JavaData.PIXEL / 2, JavaData.PIXEL / 2, Image.SCALE_SMOOTH));
		super.setLayout(null);
		this.grayedImage = new ImageIcon(GrayFilter.createDisabledImage(normalImage.getImage()));

		this.championPicture = new JLabel(normalImage);
		this.championPicture.setBounds(20, 20, JavaData.PIXEL / 2, JavaData.PIXEL / 2);
		super.add(championPicture, championPicture.getBounds());

		this.championLabel = new JLabel(champion.getChampion());
		this.championLabel.setForeground(JavaData.DEFAULT_BACKGROUND);
		this.championLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		this.championLabel.setBounds(5, 8, 96, 10);
		super.add(championLabel, championLabel.getBounds());

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
		MouseListener mL = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SelectButton.this.isEnabled()) {
					mainFrame.updateButtonPictures();
					mainFrame.getAAS().playSound("select");
					display(mainFrame);
					mainFrame.setCurrent(SelectButton.this);
					for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
						for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
							if (grid1.getArray() [ i ] [ j ].getChampion() == null && player == 1) {
								grid1.getArray() [ i ] [ j ].setEnabled(true);
							}
							if (grid2.getArray() [ i ] [ j ].getChampion() == null && player == 2) {
								grid2.getArray() [ i ] [ j ].setEnabled(true);
							}
						}
					}
					for (int i = 0; i < JavaData.SELECT_ROW_COUNT; i++) {
						for (int j = 0; j < JavaData.SELECT_COL_COUNT; j++) {
							mainFrame.getSelectButtons() [ i ] [ j ].setEnabled(false);
						}
					}
					mainFrame.getCurrent().setSelected(true);
					mainFrame.getCurrent().setEnabled(false);
				}
			}

			public void mouseExited(MouseEvent e) {
				if (SelectButton.this == null)
					mainFrame.getCurrent().display(mainFrame);
				else {
					unDisplay(mainFrame);
				}
			}

			public void mouseEntered(MouseEvent e) {
				SelectButton.this.display(mainFrame);
				if (SelectButton.this.isEnabled()) {
					mainFrame.updateButtonPictures();
					SelectButton.this.setBackground(Color.gray);
				}
			}
		};
		this.addMouseListener(mL);
	}

	public void display(MainFrame mainFrame) {
		mainFrame.getIconLabel().setIcon(new ImageIcon("pics/" + champion.toString() + "Icon.png"));
		mainFrame.getStats().setText(JavaData.getStatsText(champion));
		mainFrame.getDescription().setText(champion.getDescription());
		mainFrame.getChampionLabel().setText(champion.toString());
	}

	public void unDisplay(MainFrame mainFrame) {
		mainFrame.getIconLabel().setIcon(new ImageIcon("pics/DefaultIcon.png"));
		mainFrame.getStats().setText(JavaData.DEFAULT_STATUS_STRING);
		mainFrame.getDescription().setText("");
		mainFrame.getChampionLabel().setText("?");
		super.setBackground(color);
	}

}
