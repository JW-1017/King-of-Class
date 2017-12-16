package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import model.Battle;
import model.FinalProject;
import model.MidTermExam;
import model.Prof_Choi;
import model.SimpleReport;

public class GUIMain extends JFrame {
	public static final int mapW = 512;
	public static final int mapH = 545;
	public static final int doubledMapW = mapW * 2;
	public static final int doubledMapH = mapH * 2;
	public static final int MOVE_UNIT = 17;
	public static final int doubledMOVE_UNIT = 17 * 2;
	public static final int screenSizeW = 192 * 5;
	public static final int screenSizeH = 192 * 5;
	public Map_Classroom map_classroom;
	public Map1 map1;
	public Map_Battle map_battle;
	public Map_ProfOffice map_office;
	public GUIMain frame = this;
	public Title title;
	public EndingView opening;
	public Menu menu;
	public Map_Father currMap = map1;
	public JTextArea name;
	Border border = BorderFactory.createLineBorder(Color.WHITE, 6, true);

	private int sel;	
	Random rand = new Random();				
	
	File file4 = new File("btn.wav");
	Clip clip4 = AudioSystem.getClip();

	File file5 = new File("load.wav");
	Clip clip5 = AudioSystem.getClip();

	public GUIMain() throws LineUnavailableException, UnsupportedAudioFileException {

		try {
			clip4.open(AudioSystem.getAudioInputStream(file4));
			clip5.open(AudioSystem.getAudioInputStream(file5));

			title = new Title();
			opening = new EndingView();
			map_classroom = new Map_Classroom();
			map1 = new Map1();
			map_battle = new Map_Battle();
			map_office = new Map_ProfOffice();
			menu = new Menu(frame);
		} catch (IOException ie) {
			ie.printStackTrace();
		}

		this.setTitle("학점왕 version 1.0");
		this.setSize(screenSizeW, screenSizeH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		title.la.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
public void mouseExited(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {
				clip4.flush();
				clip4.setFramePosition(0);
				clip4.start();
			}

			public void mouseClicked(MouseEvent e) {
				clip5.flush();
				clip5.setFramePosition(0);
				clip5.start();
				title.musicPause();
				opening.musicStart();
				setContentPane(opening);
				opening.requestFocus();
			}
		});

		opening.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				if (opening.getFlag() == 2) {
				} else {
					opening.musicPause();
					map1.musicStart();
					setContentPane(map1);
					map1.requestFocus();
				}
			}
		});
		
		map1.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_A){
					map1.musicPause();
					menu.musicStart();
					menu.write();
					currMap = map1;
					map1.mapSwitching();
					setContentPane(menu);
					menu.requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					map1.setUp();
					map1.moveDown();} 
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					map1.setDown();
					map1.moveUp();} 
				else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					map1.setLeft();
					map1.moveLeft();} 
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					map1.setRight();
					map1.moveRight();}
				repaint();
				
				if (map1.getMapY() > 928*3 - 72 - 400+72) {
					map1.mapSwitching();
					map_classroom.setMapX(736*3- 960+800-50-200-500+450-800-320);
					map_classroom.setMapY(1000-100-210);
					map_classroom.musicStart();
					currMap = map_classroom;
					setContentPane(map_classroom);
					map_classroom.requestFocus();
				}
			}
		});
		map_classroom.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_A){
					map_classroom.musicPause();
					menu.musicStart();
					menu.write();
					map_classroom.mapSwitching();
					setContentPane(menu);
					menu.requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					map_classroom.setUp();
					map_classroom.moveDown();} 
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					map_classroom.setDown();
					map_classroom.moveUp();} 
				else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					map_classroom.setLeft();
					map_classroom.moveLeft();} 
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					map_classroom.setRight();
					map_classroom.moveRight();}


				map_classroom.plusStack();
				if (map_classroom.getMapX() > 3840 - (960 / 2)) {
					map_classroom.mapSwitching();
					map1.setMapX(0 - 960 / 2 + 192);
					currMap = map1;
					setContentPane(map1);
					map1.musicStart();
					map1.requestFocus();
				} else if (map_classroom.getMapX() < -400) {
					map_classroom.mapSwitching();
					remove(map_classroom);
					map_office.setMapX( (321 * 6) - (960 / 2) -72 + 30);
					setContentPane(map_office);
					map_office.musicStart();
					currMap = map_office;
					map_office.requestFocus();
					map1.setMapX(doubledMapW + screenSizeW / 2);
				}
				if (map_classroom.getStack() == 10) {
					try {	
						sel = rand.nextInt(3) +1;
						map_battle.setMonster(sel);
						map_battle.setBattleArea(sel);
						map_battle.battle = null;  
						if (sel == 1){
							map_battle.battle = new Battle(frame, new SimpleReport());
							map_battle.printTxt.setText(null);
							map_battle.printTxt.append("[ 레포트와 마주쳤다! ]");
						}
						else if (sel == 2){
							map_battle.battle = new Battle(frame, new MidTermExam());
							map_battle.printTxt.setText(null);
							map_battle.printTxt.append("[ 중간고사와 마주쳤다! ]");
						}
						else if (sel == 3){
							map_battle.battle = new Battle(frame, new FinalProject());
							map_battle.printTxt.setText(null);
							map_battle.printTxt.append("[ 팀 프로젝트와 마주쳤다! ]");
						}
					} catch (LineUnavailableException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						e1.printStackTrace();
					}
					map_classroom.mapSwitching();
					map_battle.musicStart();
					setContentPane(map_battle);
					map_battle.requestFocus();
				}
				repaint();
				map_classroom.icon.setLocation(map_classroom.heroX, map_classroom.heroY); // 레이블의
			}
		});

		map_office.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_A){
					map_office.musicPause();
					menu.musicStart();
					menu.write();
					map_office.mapSwitching();
					setContentPane(menu);
					menu.requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					map_office.setUp();
					map_office.moveDown();} 
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					map_office.setDown();
					map_office.moveUp();} 
				else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					map_office.setLeft();
					map_office.moveLeft();} 
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					map_office.setRight();
					map_office.moveRight();}
				else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						map_battle.setMonster(4);
						map_battle.setBattleArea(4);
						map_battle.setBg();
						map_battle.battle = null;
						map_battle.battle = new Battle(frame, new Prof_Choi());
						map_battle.printTxt.setText(null);
						map_battle.printTxt.append("[ 보스와 조우했다! ]");
					} catch (LineUnavailableException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						e1.printStackTrace();
					}
					map_office.musicPause();
					map_battle.musicStart();
					map_office.mapSwitching();

					remove(map_office);
					setContentPane(map_battle);
					map_battle.requestFocus();
				}
				if (map_office.getMapX() > 321 * 6 - 960 / 2 - 72) {
					map_office.mapSwitching();
					remove(map_office);
					map_office.musicPause();
					map_classroom.setMapX(0 - 960 / 2 + 192) ;
					setContentPane(map_classroom);
					currMap = map_classroom;
					map_classroom.musicStart();
					map_classroom.requestFocus();
				}
				repaint();
			}
		});
		menu.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					menu.musicPause();
					currMap.musicStart();
					setContentPane(currMap);
					currMap.requestFocus();
				}
			}
		});
		this.getContentPane().setLayout(null);
		this.setContentPane(title);
		title.musicStart();
		title.setFocusable(true);
		setVisible(true);
	}
}