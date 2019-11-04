package com.deadlockarena.graphics.buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.deadlockarena.Game;
import com.deadlockarena.constant.JavaData;
import com.deadlockarena.graphics.MainFrame;
import com.deadlockarena.logic.SelectGrid;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SelectButton extends JButton {
	private static final long serialVersionUID = 8876199740027195332L;

	private boolean selected;
	private Champion champion;
	private Color color;
	private JLabel championLabel, championPicture;
	private ImageIcon originalSizeImage, normalImage, grayedImage;
	private MouseListener mL;

	public SelectButton(Game game, Champion champion) {
		super.setFont(JavaData.BASIC_FONT);
		super.setPreferredSize(new Dimension(JavaData.PIXEL * 4 / 5, JavaData.PIXEL * 4 / 5));
		this.selected = false;
		this.champion = champion;

		this.setGraphics();
		this.evaluateColorByLogic();
		this.setupMouseListeners(game);
	}

	private void setGraphics() {

		this.originalSizeImage = new ImageIcon(
				getClass().getResource(JavaData.PICS_PATH + champion.getChampion() + "Icon.png"));
		this.normalImage = new ImageIcon(originalSizeImage.getImage()
				.getScaledInstance(JavaData.PIXEL / 2, JavaData.PIXEL / 2, Image.SCALE_SMOOTH));
		this.grayedImage = new ImageIcon(GrayFilter.createDisabledImage(normalImage.getImage()));

		super.setLayout(null);
		this.championPicture = new JLabel(normalImage);
		this.championPicture.setBounds(20, 20, JavaData.PIXEL / 2, JavaData.PIXEL / 2);
		super.add(championPicture);

		this.championLabel = new JLabel(champion.getChampion());
		this.championLabel.setForeground(JavaData.DEFAULT_BACKGROUND);
		this.championLabel.setFont(JavaData.SELECT_BUTTON_CHAMPION_FONT);
		this.championLabel.setBounds(5, 5, JavaData.PIXEL, 10);
		super.add(championLabel);
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

	private void setupMouseListeners(Game game) {
		MainFrame mainFrame = game.getMainFrame();
		SelectGrid selectGrid = game.getSelectGrid();
		SelectButton thisButton = this;
		this.mL = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (thisButton.isEnabled()) {
					// mainFrame.getAAS().playSound("select");
					mainFrame.displayPreview(champion, originalSizeImage);
					selectGrid.disableAll();
					thisButton.setSelected(true);
					game.setCurrentSelect(thisButton);
					game.selectMLMousePressed();
				}
			}

			public void mouseExited(MouseEvent e) {
				if (thisButton.isEnabled()) {
					mainFrame.unDisplayPreview(color);
					thisButton.setBackground(thisButton.getColor());
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (thisButton.isEnabled()) {
					mainFrame.displayPreview(champion, originalSizeImage);
					thisButton.setBackground(Color.gray);
				}
			}
		};
	}

}
