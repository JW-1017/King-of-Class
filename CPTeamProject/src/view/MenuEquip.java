
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import model.Player;

import javax.swing.JLabel;
import javax.sound.sampled.AudioSystem;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MenuEquip extends JPanel {

	public Player user = Player.getInstance();
	int stack = 0;
	public static final int mapW = 320;
	public static final int mapH = 282;
	public static final int doubledMapW = mapW * 12;
	public static final int doubledMapH = mapH * 12;
	public static final int screenSizeW = 192 * 5;
	public static final int screenSizeH = 192 * 5;
	public int posX = 300;
	public int posY = 300;
	public int screenPosX = doubledMapW / 2;
	public int screenPosY = doubledMapW / 2;
	String mapName = "classroom.png";
	public JTextArea printTxt;
	Border border = BorderFactory.createLineBorder(Color.WHITE, 6, true);

	public Image imgMap = new ImageIcon(mapName).getImage();
	Image scaledMap = imgMap.getScaledInstance(doubledMapW, doubledMapH, Image.SCALE_FAST);
	public JButton button_potion;
	public JButton button_equip;
	public ImageIcon imgChar = new ImageIcon("charOnion.png");
	public JLabel la = new JLabel(imgChar);

	// public static Music mapMusic = new Music("mapTown.mp3");
	File file = new File("clasrrom.wav");
	Clip clip = AudioSystem.getClip();

	public MenuEquip() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		this.setLayout(null);
		this.setSize(screenSizeW, screenSizeW);

		printTxt = new JTextArea("");
		printTxt.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
		printTxt.setBackground(new Color(0, 0, 204));
		printTxt.setForeground(Color.WHITE);
		printTxt.setLineWrap(true);
		printTxt.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		printTxt.setBounds(50, 50, 500, 500);
		printTxt.setEditable(false);
		this.add(printTxt);

		button_potion = new JButton("물약 흡입");
		button_potion.setBounds(600, 50, 150, 100);
		button_potion.setBackground(new Color(0, 0, 204));
		button_potion.setForeground(Color.WHITE);
		button_potion.setFocusPainted(false);
		button_potion.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));// http://answers.yahoo.com/question/index?qid=20070906133202AAOvnIP
		button_potion.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		this.add(button_potion);

		button_equip = new JButton("장비");
		button_equip.setBounds(600, 200, 150, 100);
		button_equip.setBackground(new Color(0, 0, 204));
		button_equip.setForeground(Color.WHITE);
		button_equip.setFocusPainted(false);
		button_equip.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));// http://answers.yahoo.com/question/index?qid=20070906133202AAOvnIP
		button_equip.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		this.add(button_equip);

		button_potion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.getPotion();
				write();
			}
		});
		clip.open(AudioSystem.getAudioInputStream(file));
	}

	public void write() {
		printTxt.setText(null);
		printTxt.append("이름 : 공대생\n\n");
		printTxt.append("LV : " + user.getLevel() + "\n");
		printTxt.append("경험치 : " + user.getCurExp() + " / " + user.getFullExp() + "\n");
		printTxt.append("HP : " + user.getCurHp() + " / " + user.getFullHp() + "\n");
		printTxt.append("힘 :" + user.getStrength() + "\n");
		printTxt.append("방어력 :" + user.getDefense() + "\n");
		printTxt.append("운 :" + user.getLuck() + "\n\n");
		printTxt.append("소지금 :" + user.getMoney() + "원\n\n");
		printTxt.append("물약갯수 :" + user.inventory.potionList.size() + "\n\n");
	}

	public void mapSwitching() {
		this.musicPause();
	}

	public void musicStart() {
		clip.loop(20);
	};

	public void musicPause() {
		clip.stop();
	};

	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(scaledMap, 0, 0, screenSizeW, screenSizeW, screenPosX, screenPosY, screenPosX + screenSizeW,
				screenPosY + screenSizeH, this);
		la.setLocation(posX, posY);
	}
}