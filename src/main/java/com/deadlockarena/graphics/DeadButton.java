package com.deadlockarena.graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Dimension;
import java.awt.Image;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeadButton extends JButton {
	static final long serialVersionUID = -1629109863348198757L;
	private Champion champion;

	DeadButton() {
		super();
		ImageIcon ic = new ImageIcon("pics/DefaultIcon.png ");
		Image img = ic.getImage();
		Image newImg = img.getScaledInstance(JavaData.PIXEL / 2, JavaData.PIXEL / 2,
				Image.SCALE_SMOOTH);
		ic = new ImageIcon(newImg);

		setPreferredSize(new Dimension(JavaData.PIXEL / 2, JavaData.PIXEL / 2));
		setIcon(ic);
		this.champion = null;
	}

	public void insertDead(Champion champion) {
		this.champion = champion;
		setIcon(new ImageIcon(new ImageIcon("pics/" + champion + "Icon.png").getImage()
				.getScaledInstance(JavaData.PIXEL / 2, JavaData.PIXEL / 2, Image.SCALE_SMOOTH)));
	}

}
