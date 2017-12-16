package view;


import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music implements AudioClip {

	File file;
	Clip clip;

	static int length;

	public Music(String filename) throws LineUnavailableException, IOException, UnsupportedAudioFileException  {
		file = new File(filename);
		clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(file));
	}

	public void play() {
		clip.start();
		try {
			Thread.sleep(clip.getMicrosecondLength() / 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loop() {
		clip.loop(20);
	}

	public void stop() {
		clip.stop();
	}

}