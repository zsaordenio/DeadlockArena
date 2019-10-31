package com.deadlockarena.graphics;

import javax.swing.*;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class SelectButton extends JButton {
	private static final long serialVersionUID = 8876199740027195332L;
	
	private Color color;
	private boolean selected;
	private Champion champion;
	private JLabel championLabel, championPicture;
	private ImageIcon normalImage, grayedImage;

	public SelectButton(int logic, Champion champion) {
		this.selected = false;
		this.champion = champion;

		normalImage = new ImageIcon(new ImageIcon("pics/" + champion + "Icon.png").getImage()
				.getScaledInstance(JavaData.PIXEL / 2, JavaData.PIXEL / 2, Image.SCALE_SMOOTH));
		setLayout(null);
		grayedImage = new ImageIcon(GrayFilter.createDisabledImage(normalImage.getImage()));

		championPicture = new JLabel(normalImage);
		championPicture.setBounds(20, 20, JavaData.PIXEL / 2, JavaData.PIXEL / 2);
		add(championPicture, championPicture.getBounds());
		championLabel = new JLabel(champion.toString());
		championLabel.setForeground(JavaData.DEFAULT_BACKGROUND);
		championLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		championLabel.setBounds(5, 8, 96, 10);
		add(championLabel, championLabel.getBounds());

		switch (logic + 1) {
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
		setBackground(color);
	/*	addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SelectButton.this.isEnabled()) {
					Java.updateButtonPictures();
					AnimationAndSound.playSound("select");
					display();
					GUI.current = SelectButton.this;
					for (int i = 0; i < JavaData.SLOT_COUNT; i++) {
						if (GUI.slotList1 [ i ].getchampion() == null && !GUI.player)
							GUI.slotList1 [ i ].setEnabled(true);
						if (GUI.slotList2 [ i ].getchampion() == null && GUI.player)
							GUI.slotList2 [ i ].setEnabled(true);
					}
					for (int i = 0; i < JavaData.CHAMPION_COUNT; i++) {
						GUI.selectList [ i ].setEnabled(false);
					}
					GUI.current.setSelected(true);
					GUI.current.setEnabled(false);
				}
			}

			public void mouseExited(MouseEvent e) {
				if (SelectButton.this == null)
					GUI.current.display();
				else
					unDisplay();
			}

			public void mouseEntered(MouseEvent e) {
				SelectButton.this.display();
				if (SelectButton.this.isEnabled()) {
					GUI.updateButtonPictures();
					SelectButton.this.setBackground(Color.gray);
				}
			}
		});*/
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
/*
	void display() {
		Champion h = SelectButton.this.champion;
		AppPrincipalFrame.iconLabel.setIcon(new ImageIcon("pics/" + h.toString() + "Icon.png"));
		AppPrincipalFrame.stats.setText("<html>" + "HP: " + h.getMaxHp() + "<br/>" + "MP: "
				+ h.getMaxMp() + "<br/>" + "Damage: " + h.getMinDmg() + " - " + h.getMaxDmg()
				+ "<br/>" + "Defense: " + h.getDefense() + "<br/>" + "Critical: " + h.getCritical()
				+ "%<br/>" + "Dodge: " + h.getDodge() + "%" + "</html>");
		AppPrincipalFrame.description.setText(h.getDescription());
		AppPrincipalFrame.championLabel.setText(h.toString());
	}

	private void unDisplay() {
		AppPrincipalFrame.iconLabel.setIcon(new ImageIcon("pics/DefaultIcon.png"));
		AppPrincipalFrame.stats.setText(JavaData.DEFAULTSTATUSSTRING);
		AppPrincipalFrame.description.setText("");
		AppPrincipalFrame.championLabel.setText("?");
		setBackground(color);
	}*/


}
