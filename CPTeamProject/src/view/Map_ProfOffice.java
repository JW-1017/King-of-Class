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

public class Map_ProfOffice extends Map_Father{

	public static final int mapW = 321;
	public static final int mapH = 223;
	public static final int scaledMapW = mapW*6;
	public static final int scaledMapH = mapH*6;
	
	public static final int MOVE_UNIT = 72;
	
	public static final int screenSizeW = 960;
	public static final int screenSizeH = 960;

	public int heroX = 470-40+5 ;
	public int heroY = 350 ;

	public int screenX = scaledMapW - screenSizeW/2 + 30 + 30;
	public int screenY = scaledMapH / 2 - screenSizeH/2 + 200 + 120;
	
	public  Image imgMap = new ImageIcon("map_profOffice.png").getImage();
	Image scaledMap = imgMap.getScaledInstance(scaledMapW, scaledMapH, Image.SCALE_FAST);

	public ImageIcon charUp = new ImageIcon("charUp.png");
	public ImageIcon charDown = new ImageIcon("charDown.png");
	public ImageIcon charLeft = new ImageIcon("charLeft.png");
	public ImageIcon charRight = new ImageIcon("charRight.png");
	public JLabel icon = new JLabel(charDown);
	
	void setUp(){icon.setIcon(charUp);}
	void setDown(){icon.setIcon(charDown);}
	void setLeft(){icon.setIcon(charLeft);}
	void setRight(){icon.setIcon(charRight);}
		
	
	//public static Music mapMusic = new Music("mapTown.mp3");
	File file = new File("profOffice.wav");
	Clip clip = AudioSystem.getClip();
	
	public Map_ProfOffice () throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		this.setLayout(null);
		this.setSize(screenSizeW, screenSizeW);

		icon.setSize(MOVE_UNIT, MOVE_UNIT); // 레이블의 크기 50x20을 설정
		icon.setLocation(heroX, heroY);// 레이블의 위치 (30,30)으로 설정
		this.add(icon); // 레이블 컴포넌트를 컨텐트팬을 추가
		
		clip.open(AudioSystem.getAudioInputStream(file));	
		// clip.loop(20);
	}
	public void mapSwitching(){musicPause();}
	
	void callMap(){ 
		this.musicStart();
		this.requestFocus();
	}
	
	public void setMapY(int y){ screenY = y ;}
	public void setMapX(int x){ screenX = x ;}

	public int getMapX(){ return screenX ; }
	public int getMapY(){ return screenY ; }

	public void musicStart(){clip.loop(20);};
	public void musicPause(){clip.stop();};

	public void moveUp (){		screenY += MOVE_UNIT;}
	public void moveDown(){		screenY -= MOVE_UNIT;	}
	public void moveLeft(){		screenX -= MOVE_UNIT;}
	public void moveRight(){screenX += MOVE_UNIT;}
	
	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(scaledMap, 0, 0, screenSizeW, screenSizeW, screenX, screenY, screenX + screenSizeW, screenY + screenSizeH, this);		
		icon.setLocation(heroX, heroY);
	}
}