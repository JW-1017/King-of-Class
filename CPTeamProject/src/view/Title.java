package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.sound.sampled.AudioSystem;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Title extends JPanel {

	public static final int mapW = 512;
	public static final int mapH = 545;
	public static final int doubledMapW = mapW*12;
	public static final int doubledMapH = mapH*12;
	public static final int MOVE_UNIT = 16;
	public static final int doubledMOVE_UNIT = 16*12;
	public static final int screenSizeW = 192*5;
	public static final int screenSizeH = 192*5;
	public int posX = 380 ;
	public int posY = 470 ;
	public int screenPosX = doubledMapW/2;
	public int screenPosY = doubledMapW/2;
	int buttonW = 120;
	int buttonH = 60;

	public  Image scaledMap = new ImageIcon("title.jpg").getImage();	
	public ImageIcon imgChar = new ImageIcon("startButton2.png");
	public JLabel la = new JLabel(imgChar);

	File file = new File("title.wav");
	Clip clip = AudioSystem.getClip();

	File file2 = new File("titleClick.wav");
	Clip clip2 = AudioSystem.getClip();
	
	public Title() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		
		this.setLayout(null);
		this.setSize(screenSizeW, screenSizeH);

		la.setBounds(posX , posY, imgChar.getIconWidth(), imgChar.getIconHeight());
		this.add(la); // ·¹ÀÌºí ÄÄÆ÷³ÍÆ®¸¦ ÄÁÅÙÆ®ÆÒÀ» Ãß°¡

		clip.open(AudioSystem.getAudioInputStream(file));	
	//	clip2.open(AudioSystem.getAudioInputStream(file2));	
		
	}	

	public void mapSwitching(){musicPause();}
	public void musicStart(){clip.loop(20);};
	public void musicPause(){clip.stop();};

	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(scaledMap, 0, 0, screenSizeW, screenSizeH, 0, 0, screenSizeW, screenSizeH, this);
	}
}