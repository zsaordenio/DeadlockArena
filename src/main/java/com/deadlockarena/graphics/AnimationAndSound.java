package com.deadlockarena.graphics;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.sampled.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.exception.CornerCaseException;

import lombok.Data;

import java.io.*;

@Data
public class AnimationAndSound {

	private AudioInputStream inputStreamSound;
	private AudioInputStream inputStreamMusic;
	private Clip music;
	private String [ ] soundtracks;
	private boolean changedMusic;
	private final int DELAY = 20;

	private BooleanButton soundButton, musicButton, loopButton;
	private JButton soundtrackButton;
	private String soundtrack;

	public AnimationAndSound() {
		changedMusic = false;
		initSoundTracks();

		soundtrackButton = new JButton("Soundtracks");
		soundtrackButton.setFont(JavaData.BASIC_FONT);
		soundtrackButton.setEnabled(false);
		soundtrackButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (soundtrackButton.isEnabled()) {
					String selected = (String) JOptionPane.showInputDialog(null,
							"Please choose a song", "Soundtracks", JOptionPane.QUESTION_MESSAGE,
							null, soundtracks, soundtrack);
					if (selected != null) {
						soundtrack = selected;
						if (loopButton.isOn()) {
							changedMusic = true;
							stopMusic();
							loopMusic();
						} else {
							stopMusic();
							initMusic();
							startMusic();
						}
					} else
						return;
				}
			}
		});
		try {
			soundButton = new BooleanButton("pics/sound");
			musicButton = new BooleanButton("pics/music");
			loopButton = new BooleanButton("pics/loop");
		} catch (CornerCaseException exc) {
			exc.printStackTrace();
		}
		initMusic();
	}

	// TO-DO use sshConnect to access the files
	private void initSoundTracks() {
		File folder = new File("music/");
		File [ ] listOfFiles = folder.listFiles();

		soundtracks = new String [ listOfFiles.length ];
		for (int i = 0; i < listOfFiles.length; i++) {
			soundtracks [ i ] = listOfFiles [ i ].getName().replace(".wav", "");
		}
		soundtrack = soundtracks [ JavaData.random.nextInt(soundtracks.length) ];
	}

	public void shakeButton(JButton button) {
		final Point point = button.getLocation();
		Runnable r = new Runnable() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					try {
						button.setLocation(new Point(point.x - 10, point.y));
						Thread.sleep(DELAY);
						button.setLocation(point);
						Thread.sleep(DELAY);
						button.setLocation(new Point(point.x + 10, point.y));
						Thread.sleep(DELAY);
						button.setLocation(point);
						Thread.sleep(DELAY);
					} catch (InterruptedException exc) {
						exc.printStackTrace();
					}
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
	}

	public void playSound(String soundType) {
		if (soundButton.isOn()) {
			String sound = selectSount(soundType);
			try {
				inputStreamSound = AudioSystem
						.getAudioInputStream(new File("sound/" + sound + ".wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(inputStreamSound);
				clip.loop(0);
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}

	public void loopMusic() {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while (true) {
					shufflePlaylist();
					byte [ ] buffer = new byte [ 4096 ];
					for (String fileString : soundtracks) {
						try {
							inputStreamMusic = AudioSystem
									.getAudioInputStream(new File("music/" + fileString + ".wav"));
							AudioFormat format = inputStreamMusic.getFormat();
							SourceDataLine line = AudioSystem.getSourceDataLine(format);
							line.open(format);
							line.start();
							while (inputStreamMusic.available() > 0) {
								if (!loopButton.isOn() || !musicButton.isOn() || changedMusic)
									return;
								int len = inputStreamMusic.read(buffer);
								line.write(buffer, 0, len);
							}
							line.drain();
							line.close();
						} catch (Exception exc) {
							exc.printStackTrace();
						}
					}
				}
			}
		};
		if (!loopButton.isOn() || !musicButton.isOn() || changedMusic) {
			changedMusic = false;
			return;
		}
		Thread loopingMusic = new Thread(r);
		loopingMusic.start();

	}

	private void initMusic() {
		if (soundtrack == null)
			return;
		try {
			inputStreamMusic = AudioSystem
					.getAudioInputStream(new File("music/" + soundtrack + ".wav"));
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void startMusic() {
		if (musicButton.isOn()) {
			try {
				music = AudioSystem.getClip();
				initMusic();
				music.open(inputStreamMusic);
				music.loop(Clip.LOOP_CONTINUOUSLY);
				controlVolume(music, -15);
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}

	public void stopMusic() {
		if (music != null) {
			music.close();
		}
	}

	private void controlVolume(Clip c, float f) {
		FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(f);
		// Reduce volume by f decibels.
	}

	private String selectSount(String soundType) {
		String sound = "";
		switch (soundType) {
		case "melee":
			switch (JavaData.random.nextInt(2)) {
			case 0:
				sound = "melee1";
				break;
			case 1:
				sound = "melee2";
				break;
			}
			break;
		case "dodge":
			sound = "dodge";
			break;
		case "select":
			sound = "80921__justinbw__buttonchime02up";
			break;
		}
		return sound;
	}

	private void shufflePlaylist() {
		int n = soundtracks.length;
		JavaData.random.nextInt();
		for (int i = 0; i < n; i++) {
			int change = i + JavaData.random.nextInt(n - i);
			String placeHolder = soundtracks [ i ];
			soundtracks [ i ] = soundtracks [ change ];
			soundtracks [ change ] = placeHolder;
		}
	}
}
