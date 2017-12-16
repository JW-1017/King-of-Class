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

public class Map1 extends Map_Father{

	public static final int mapW = 1056;
	public static final int mapH = 928;
	public static final int ScaledMapW = mapW*3;
	public static final int ScaledMapH = mapH*3;
	public static final int scaledUnit = 72;
	public static final int screenSizeW = 192*5;
	public static final int screenSizeH = 192*5;
	public int posX = 261 ;
	public int posY = 385 ;
	public int screenPosX = 0 + 500 + 750;
	public int screenPosY = 0 + 300 +400;
	
	public  Image imgMap = new ImageIcon("Map_Town2.png").getImage();
	Image scaledMap = imgMap.getScaledInstance(ScaledMapW, ScaledMapH, Image.SCALE_FAST);

	public ImageIcon charUp = new ImageIcon("charUp.png");
	public ImageIcon charDown = new ImageIcon("charDown.png");
	public ImageIcon charLeft = new ImageIcon("charLeft.png");
	public ImageIcon charRight = new ImageIcon("charRight.png");
	public JLabel icon = new JLabel(charUp);
	
	void setUp(){icon.setIcon(charUp);}
	void setDown(){icon.setIcon(charDown);}
	void setLeft(){icon.setIcon(charLeft);}
	void setRight(){icon.setIcon(charRight);}
	
	File file = new File("mapTown.wav");
	Clip clip = AudioSystem.getClip();

	public Map1 () throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		this.setLayout(null);
		this.setSize(screenSizeW, screenSizeW);

		icon.setSize(scaledUnit, scaledUnit); // 레이블의 크기 50x20을 설정
		icon.setLocation(posX, posY);// 레이블의 위치 (30,30)으로 설정
		this.add(icon); // 레이블 컴포넌트를 컨텐트팬을 추가
		
		clip.open(AudioSystem.getAudioInputStream(file));
		
		
		// clip.loop(20);
	}
	public void mapSwitching(){musicPause();}
	
	void callMap(){ 
		this.musicStart();
		this.requestFocus();
	}
	
	public void setMapY(int y){ screenPosY = y ;}
	public void setMapX(int x){ screenPosX = x ;}

	public int getMapX(){ return screenPosX ; }
	public int getMapY(){ return screenPosY ; }

	public void musicStart(){clip.loop(20);};
	public void musicPause(){clip.stop();};

	public void moveUp (){screenPosY += scaledUnit;}
	public void moveDown(){screenPosY -= scaledUnit;}
	public void moveLeft(){screenPosX -= scaledUnit;}
	public void moveRight(){screenPosX += scaledUnit;}
	
	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(scaledMap, 0, 0, screenSizeW, screenSizeW, screenPosX, screenPosY, screenPosX+ screenSizeW, screenPosY+screenSizeH, this);
		icon.setLocation(posX, posY);
	}
}