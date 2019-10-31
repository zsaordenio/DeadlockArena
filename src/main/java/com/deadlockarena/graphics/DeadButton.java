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
@EqualsAndHashCode(callSuper = true)
public class DeadButton extends JButton {
	static final long serialVersionUID = -1629109863348198757L;
	private Champion champion;

	public DeadButton() {
		super();
		Image newImg = new ImageIcon("pics/DefaultIcon.png ").getImage()
				.getScaledInstance(JavaData.PIXEL / 2, JavaData.PIXEL / 2, Image.SCALE_SMOOTH);
		setPreferredSize(new Dimension(JavaData.PIXEL / 2, JavaData.PIXEL / 2));
		setIcon(new ImageIcon(newImg));
		this.champion = null;
	}

	public void insertDead(Champion champion) {
		this.champion = champion;
		setIcon(new ImageIcon(new ImageIcon("pics/" + champion + "Icon.png").getImage()
				.getScaledInstance(JavaData.PIXEL / 2, JavaData.PIXEL / 2, Image.SCALE_SMOOTH)));
	}

}
