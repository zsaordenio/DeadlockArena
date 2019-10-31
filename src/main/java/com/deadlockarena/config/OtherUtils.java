package com.deadlockarena.config;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.deadlockarena.constant.JavaData;

public class OtherUtils {

	public static void logScreenDimensions() {
		FileWriter writer = null;
		try {
			writer = new FileWriter("systemConfig.txt", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		try {
			bufferedWriter.write("\nUsername: " + System.getProperty("user.name") + "\nDimension: "
					+ Toolkit.getDefaultToolkit().getScreenSize());
		} catch (HeadlessException | IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void activateRobot() {
		Robot rob = null;
		try {
			rob = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		rob.setAutoDelay(1);
		for (int i = 0; i < JavaData.coords.length; i++) {
			for (int count = 0; (MouseInfo.getPointerInfo().getLocation()
					.getX() != JavaData.coords [ i ] [ 0 ] * 3 / 2
					|| MouseInfo.getPointerInfo().getLocation()
							.getY() != JavaData.coords [ i ] [ 1 ] * 3 / 2)
					&& count < 15; count++) {
				rob.mouseMove((int) JavaData.coords [ i ] [ 0 ] * 3 / 2,
						(int) JavaData.coords [ i ] [ 1 ] * 3 / 2);
			}
			rob.mousePress(InputEvent.BUTTON1_MASK);
			rob.mouseRelease(InputEvent.BUTTON1_MASK);
		}
	}
}
