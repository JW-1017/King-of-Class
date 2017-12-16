package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.sun.javafx.scene.paint.GradientUtils.Point;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class EndingView extends JPanel {

	int flag = 1;
	private Image bg = new ImageIcon("prologueBg.jpg").getImage();
	private Image bg2 = new ImageIcon("ending.jpg").getImage();
	String str = " \n\n 학사경고의 위기를 탈출하라. \n\n\n 학점을 올리고 학점왕이 되어라";
	String str2 = " \n\n\n\n 해냈다 ! \n\n\n 마침내 전과목 A+의 과업을 달성하였다. \n\n\n 그렇다. 나는 학점왕이 된 것이다. ";

	public MarqeuePanel text_panel;
	MarqeuePanel text_panel2;
	MarqeuePanel text_panel3;

	File file = new File("opening.wav");
	Clip clip = AudioSystem.getClip();

	File file2 = new File("ending.wav");
	Clip clip2 = AudioSystem.getClip();

	public EndingView() throws LineUnavailableException, IOException, UnsupportedAudioFileException {

		clip.open(AudioSystem.getAudioInputStream(file));
		clip2.open(AudioSystem.getAudioInputStream(file2));

		text_panel = new MarqeuePanel(new Dimension(800, 400), new Point(), str);
		text_panel.setBounds(300, 0, 400, 700);

		text_panel2 = new MarqeuePanel(new Dimension(800, 400), new Point(), str2);
		text_panel2.setBounds(300, 0, 400, 700);

		this.add(text_panel);
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(960, 960);
		
		//text_panel.setBounds(300,0,800,400);
		this.add(text_panel);

		this.setOpaque(false);
		setSize(960, 960);
				
		this.setBounds(0,0,960,960);
		this.setLayout(null);
		
		setVisible(true);
	}

	public void setPanel() {}

	public int getFlag() {return flag;}

	public void musicStart() {
		if (flag == 1) {
			clip.start();
		} else if (flag == 2) {
			clip2.start();
		}
	};

	public void musicPause() {clip.stop();};

	public void txtShift() {
		flag = 2;
		this.remove(text_panel);
		this.add(text_panel2);
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		if (flag == 2) {
			g.drawImage(bg2, 0, 0, 959, 959, this);
		} else {
			g.drawImage(bg, 0, 0, 959, 959, this);
		}
	}
}