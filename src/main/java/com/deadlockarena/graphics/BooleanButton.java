package com.deadlockarena.graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.exception.CornerCaseException;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Data
@EqualsAndHashCode(callSuper = true)
public class BooleanButton extends JButton {
	private static final long serialVersionUID = -4636335771363956811L;
	
	private String fileName;
	private boolean on;

	public BooleanButton(String fileName) throws CornerCaseException {
		this.setPreferredSize(new Dimension(JavaData.PIXEL / 2, JavaData.PIXEL / 2));
		String start = "";
		if (fileName.equals("pics/music")) {
			start = "offIcon.png";
			this.on = false;
		} else if (fileName.equals("pics/sound")) {
			start = "onIcon.png";
			this.on = true;
		} else if (fileName.equals("pics/loop")) {
			start = "onIcon.png";
			this.on = true;
		} else {
			throw new CornerCaseException("BooleanButton: Incorrect filename input");
		}
		Image image = new ImageIcon(fileName + start).getImage()
				.getScaledInstance(JavaData.PIXEL / 2, JavaData.PIXEL / 2, Image.SCALE_SMOOTH);
		this.setIcon(new ImageIcon(image));
		addMouseListener(mL);
		this.fileName = fileName;
	}

	MouseListener mL = new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			BooleanButton bb = BooleanButton.this;

			if (bb.isOn())
				bb.setIcon(new ImageIcon(
						new ImageIcon(fileName + "OffIcon.png").getImage().getScaledInstance(
								JavaData.PIXEL / 2, JavaData.PIXEL / 2, Image.SCALE_SMOOTH)));
			else
				bb.setIcon(new ImageIcon(
						new ImageIcon(fileName + "OnIcon.png").getImage().getScaledInstance(
								JavaData.PIXEL / 2, JavaData.PIXEL / 2, Image.SCALE_SMOOTH)));

			bb.invert();
			//evalFunctionality();
		}
	};

	void invert() {
		on = !on;
	}

	void evalFunctionality(AnimationAndSound aAS) {
		String func = fileName.substring(fileName.lastIndexOf("/") + 1);
		switch (func) {
		case "sound":
			break;
		case "music":
			if (on) {
				aAS.stopMusic();
				if (aAS.getLoopButton().isOn())
					aAS.loopMusic();
				else
					aAS.startMusic();
			} else {
				aAS.stopMusic();
			}
			break;
		case "loop":
			if (isEnabled()) {
				aAS.stopMusic();
				if (on) {
					aAS.getSoundtrackButton().setEnabled(false);
					if (aAS.getMusicButton().isOn())
						aAS.loopMusic();
				} else {
					aAS.getSoundtrackButton().setEnabled(true);
					if (aAS.getMusicButton().isOn())
						aAS.startMusic();
				}
			}
		default:
		}
	}

}
