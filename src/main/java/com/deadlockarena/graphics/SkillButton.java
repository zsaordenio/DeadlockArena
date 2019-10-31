package com.deadlockarena.graphics;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.deadlockarena.constant.JavaData;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Data
@EqualsAndHashCode(callSuper = true)
public class SkillButton extends JButton {
	private static final long serialVersionUID = -498760128876833290L;

	private BufferedImage color;
	private BufferedImage gray;
	private double fraction;

	public SkillButton() {
		setBackground(JavaData.DEFAULT_BACKGROUND);
		try {
			this.gray = ImageIO.read(new File("pics/gray.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSkillButton(String filename, double fraction) {
		if (filename == null) {
			this.color = null;
			repaint();
			return;
		}
		this.fraction = fraction;
		try {
			color = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		if (color == null) {
			super.paintComponent(g);
			return;
		}
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.drawImage(gray, 0, 0, this);
		g2d.drawImage(maskedEffect(), 0, 0, this);
		g2d.dispose();
	}

	public BufferedImage maskedEffect() {
		int width = color.getWidth();
		int height = color.getHeight();
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		Arc2D.Double expose = new Arc2D.Double(-(width / 2d), -(height / 2d), width * 2d,
				height * 2d, 90, -(360.0d * fraction), Arc2D.PIE);
		g2d.fill(expose);
		g2d.setComposite(AlphaComposite.SrcIn);
		g2d.drawImage(color, 0, 0, this);
		g2d.dispose();
		return img;
	}

}
