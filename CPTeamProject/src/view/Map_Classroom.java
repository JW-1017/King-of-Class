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

public class Map_Classroom extends Map_Father{

	int stack = 0;
	public static final int mapW = 736;
	public static final int mapH = 864;
	public static final int scaledMapW = mapW*3;
	public static final int scaledMapH = mapH*3;

	public static final int MOVE_UNIT = 72;
	
	public static final int screenW = 960;
	public static final int screenH = 960;
	public int mapPosX = 0;
	public int mapPosY = 0;
	
	public int heroX = 470-40+5 ;
	public int heroY = 350 ;

	String mapName = "map_castle.png";	
	public  Image map = new ImageIcon(mapName).getImage();
	Image scaledMap = map.getScaledInstance(scaledMapW, scaledMapH, Image.SCALE_FAST);

	public ImageIcon charUp = new ImageIcon("charUp.png");
	public ImageIcon charDown = new ImageIcon("charDown.png");
	public ImageIcon charLeft = new ImageIcon("charLeft.png");
	public ImageIcon charRight = new ImageIcon("charRight.png");
	public JLabel icon = new JLabel(charDown);
	
	void setUp(){icon.setIcon(charUp);}
	void setDown(){icon.setIcon(charDown);}
	void setLeft(){icon.setIcon(charLeft);}
	void setRight(){icon.setIcon(charRight);}
		
	File file = new File("clasrrom.wav");
	Clip clip = AudioSystem.getClip();
	
	public Map_Classroom () throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		this.setLayout(null);
		this.setSize(screenW, screenW);

		icon.setSize(MOVE_UNIT, MOVE_UNIT); // 레이블의 크기 50x20을 설정
		icon.setLocation(heroX, heroY);// 레이블의 위치 (30,30)으로 설정
		this.add(icon); // 레이블 컴포넌트를 컨텐트팬을 추가
		
		clip.open(AudioSystem.getAudioInputStream(file));	
	}

	public void moveUp (){mapPosY += MOVE_UNIT;}
	public void moveDown(){	mapPosY -= MOVE_UNIT;}
	public void moveLeft(){	mapPosX -= MOVE_UNIT;}
	public void moveRight(){mapPosX += MOVE_UNIT;}
	
	public void mapSwitching(){
		this.resetStack();
		this.musicPause();		
	}

	public void setMapY(int y){ mapPosY = y ;}
	public void setMapX(int x){ mapPosX = x ;}

	public int getMapX(){ return mapPosX ; }
	public int getMapY(){ return mapPosY ; }

	int getStack(){ return stack ;}
	void plusStack(){ stack++ ;}
	void resetStack(){ stack = 0 ;}

	public void musicStart(){clip.loop(20);};
	public void musicPause(){clip.stop();};
	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(scaledMap, 0, 0, screenW, screenH,
				mapPosX, mapPosY, 
				mapPosX+screenW, mapPosY+screenH, this);
		icon.setLocation(heroX, heroY);
	}	
}