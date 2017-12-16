package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;

import model.Battle;

public class Map_Battle extends JLayeredPane implements ActionListener {
	int buttonW = 120;
	int buttonH = 60;
	int xDefault = 10;
	int yDefault = 670;
	int xUnit = 140;
	int yUnit = 80;
	int bgW = 960;
	int bgH = 960;
	Timer timer;

	// Player p;
	// Enemy e;
	public Map_Battle map_battle = this;
	public Battle battle;
	JLabel labelMonster;
	JButton button_attack;
	JButton button_attack2;
	JButton button_attack3;
	JButton button_run;
	JButton button_potion;
	ImageIcon monster1;
	ImageIcon monster2;
	ImageIcon monster3;
	ImageIcon bossMonster;
	public JTextArea printTxt;
	JScrollPane txtScroll;
	String newline = "\n";
	DefaultCaret caret;
	JScrollBar vertical;
	Border border = BorderFactory.createLineBorder(Color.WHITE, 6, true);
	int attackflag = 0;
	ImageIcon bgBoss ;
	ImageIcon bgBoss2 ;
	Image bgBossImg ;
	Image scaledBgBossImg ;
	JLabel lblBgBoss;	

	ImageIcon bg ;
	ImageIcon bg2 ;
	Image bgImg ;
	Image scaledBgImg ;
	JLabel lblBg;		
	
	ImageIcon effect ;
	ImageIcon effect2 ;
	ImageIcon effect3 ;
	ImageIcon nullEffect ;
	JLabel lblEffect ;
	JLabel lblEffect2 ;
	JLabel lblEffect3 ;
	
	File file = new File("battle.wav");
	Clip clip = AudioSystem.getClip();

	File file2 = new File("boss.wav");
	Clip clip2 = AudioSystem.getClip();

	File file3 = new File("attack1.wav");
	Clip clip3 = AudioSystem.getClip();

	File file4 = new File("btn.wav");
	Clip clip4 = AudioSystem.getClip();

	File file5 = new File("critical.wav");
	Clip clip5 = AudioSystem.getClip();

	File file6 = new File("heal.wav");
	Clip clip6 = AudioSystem.getClip();
	File file7 = new File("hit.wav");
	Clip clip7 = AudioSystem.getClip();
	File file8 = new File("escape.wav");
	Clip clip8 = AudioSystem.getClip();

	File file9 = new File("win2.wav");
	Clip clip9 = AudioSystem.getClip();
	boolean flag = false ;
	int battleArea = 1;
	public static final int screenSizeW = 192 * 5;
	public static final int screenSizeH = 192 * 5;

	public Map_Battle() throws LineUnavailableException, IOException, UnsupportedAudioFileException {

		nullEffect = new ImageIcon("nullEffect.png");
		effect = new ImageIcon("effect.gif");
		effect2 = new ImageIcon("effect2.gif");
		effect3 = new ImageIcon("effect3.gif");

		lblEffect = new JLabel(effect);
		lblEffect.setBounds(160, 20, effect.getIconWidth(), effect.getIconHeight());

		lblEffect2 = new JLabel(effect2);
		lblEffect2.setBounds(160, 20, effect2.getIconWidth(), effect.getIconHeight());

		lblEffect3 =  new JLabel(effect3);
		lblEffect3.setBounds(160, 20, effect3.getIconWidth(), effect.getIconHeight());

		bossMonster = new ImageIcon("myria.gif");
		monster1 = new ImageIcon("mit.gif");
		monster2 = new ImageIcon("Chimera.gif");	
		monster3 = new ImageIcon("kaiser.gif");

		clip.open(AudioSystem.getAudioInputStream(file));
		clip2.open(AudioSystem.getAudioInputStream(file2));
		clip3.open(AudioSystem.getAudioInputStream(file3));
		clip4.open(AudioSystem.getAudioInputStream(file4));
		clip5.open(AudioSystem.getAudioInputStream(file5));
		clip6.open(AudioSystem.getAudioInputStream(file6));
		clip7.open(AudioSystem.getAudioInputStream(file7));
		clip8.open(AudioSystem.getAudioInputStream(file8));

		this.setSize(screenSizeW, screenSizeH);
		this.setLayout(null);
		
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(attackflag == 1){
        			map_battle.remove(lblEffect);
                	map_battle.repaint();
                	timer.stop();            		
            	}
            	else if(attackflag == 2){
        			map_battle.remove(lblEffect2);
                	map_battle.repaint();
                	timer.stop();            		
            	} else if(attackflag == 3){
        			map_battle.remove(lblEffect3);
                	map_battle.repaint();
                	timer.stop();
            		
            	}
            	
            }
        });
		
		button_attack = new JButton("Cheat");
		button_attack.setBounds(xDefault, yDefault, buttonW, buttonH);
		button_attack.setBackground(new Color(0, 0, 204));
		button_attack.setForeground(Color.WHITE);
		button_attack.setFocusPainted(false);
		button_attack.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));// http://answers.yahoo.com/question/index?qid=20070906133202AAOvnIP
		button_attack
				.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		button_attack.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				clip4.setFramePosition(0);
				clip4.start();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		button_attack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				map_battle.add(lblEffect,99,0);
				map_battle.repaint();
				attackflag =1 ;
				timer.start();

				clip3.flush();
				clip3.setFramePosition(0);
				clip3.start();
				
				try {
					printTxt.setText(null);
					printTxt.append("컨닝하기 ! !\n");
					battle.playerSkill1();
					} 
				catch (LineUnavailableException e1) {e1.printStackTrace();} 
				catch (IOException e1) {e1.printStackTrace();} 
				catch (UnsupportedAudioFileException e1) {e1.printStackTrace();}
			}
		});
		this.add(button_attack,1,0);

		button_attack2 = new JButton("Burn");
		button_attack2.setBounds(xDefault + xUnit, yDefault, buttonW, buttonH);
		button_attack2.setBackground(new Color(0, 0, 204));
		button_attack2.setForeground(Color.WHITE);
		button_attack2.setFocusPainted(false);
		button_attack2.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));// http://answers.yahoo.com/question/index?qid=20070906133202AAOvnIP
		button_attack2
				.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		button_attack2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				map_battle.add(lblEffect2,99,0);
				map_battle.repaint();
				attackflag =2 ;
				timer.start();
				printTxt.setText(null);
				printTxt.append("밤새서 공부하기 ! !\n");
				battle.playerSkill2();
			}
		});

		button_attack2.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {			}
			public void mousePressed(MouseEvent e) {			}
			public void mouseExited(MouseEvent e) {			}

			@Override
			public void mouseEntered(MouseEvent e) {
				clip4.flush();
				clip4.setFramePosition(0);
				clip4.start();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				clip7.flush();
				clip7.setFramePosition(0);
				clip7.start();
			}
		});
		this.add(button_attack2,1,0);
		
		
		button_attack3 = new JButton("Chance");
		button_attack3.setBounds(xDefault + xUnit * 2, yDefault, buttonW, buttonH);
		button_attack3.setBackground(new Color(0, 0, 204));
		button_attack3.setForeground(Color.WHITE);
		button_attack3.setFocusPainted(false);
		button_attack3.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));// http://answers.yahoo.com/question/index?qid=20070906133202AAOvnIP
		button_attack3.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		button_attack3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				map_battle.add(lblEffect3,99,0);
				map_battle.repaint();
				attackflag =3 ;
				timer.start();

				printTxt.setText(null);
				printTxt.append("지인 찬스 ! ! \n");
				battle.playerSkill3();
			}
		});

		button_attack3.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {			}
			public void mousePressed(MouseEvent e) {			}
			public void mouseExited(MouseEvent e) {			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				clip4.flush();
				clip4.setFramePosition(0);
				clip4.start();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				clip5.flush();
				clip5.setFramePosition(0);
				clip5.start();
			}
		});

		this.add(button_attack3,1,0);
		button_potion = new JButton("Potion");
		button_potion.setBounds(xDefault + xUnit * 3, yDefault, buttonW, buttonH);
		button_potion.setBackground(new Color(0, 0, 204));
		button_potion.setForeground(Color.WHITE);
		button_potion.setFocusPainted(false);
		button_potion.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));// http://answers.yahoo.com/question/index?qid=20070906133202AAOvnIP
		button_potion.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		button_potion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				battle.playerHeal();
				printTxt.setText(null);
				printTxt.append("레드불 흡임 \n");
				printTxt.append("혈중 카페인 농도가 상승하였다 ! \n");
				printTxt.append("흡입자가 각성하였다 \n\n");
				printTxt.append("[나의 HP] " + battle.user.getCurHp() + " / " + battle.user.getFullHp() + "\n");
			}
		});

		button_potion.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {			}
			public void mousePressed(MouseEvent e) {			}
			public void mouseExited(MouseEvent e) {			}

			@Override
			public void mouseEntered(MouseEvent e) {
				clip4.flush();
				clip4.setFramePosition(0);
				clip4.start();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				clip6.flush();
				clip6.setFramePosition(0);
				clip6.start();
			}
		});

		this.add(button_potion,1,0);
		button_run = new JButton("Run");
		button_run.setBounds(xDefault + xUnit * 4, yDefault, buttonW, buttonH);
		button_run.setBackground(new Color(0, 0, 204));
		button_run.setForeground(Color.WHITE);
		button_run.setFocusPainted(false);
		button_run.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));// http://answers.yahoo.com/question/index?qid=20070906133202AAOvnIP
		button_run.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		button_run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				battle.playerRun();
			}
		});
		
		button_run.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				clip4.setFramePosition(0);
				clip4.start();

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				clip8.flush();
				clip8.setFramePosition(0);
				clip8.start();

			}
		});

		this.add(button_run,1,0);
		
		bg = new ImageIcon("bgtree.gif");
		bgImg  = bg.getImage();
		scaledBgImg = bgImg.getScaledInstance((int)(500*3.416) , (int)(281*3.416),  Image.SCALE_DEFAULT);
		bg2 = new ImageIcon(scaledBgImg);
		lblBg =new JLabel(bg2);	
		lblBg.setBounds(0, 0, 960, 960);
		this.add(lblBg,0,0);

		bgBoss = new ImageIcon("bossbg.gif");
		bgBossImg  = bgBoss.getImage();
		scaledBgBossImg = bgBossImg.getScaledInstance((int)(720*2.4) , (int)(405*2.4),  Image.SCALE_DEFAULT);
		bgBoss2 = new ImageIcon(scaledBgBossImg);
		lblBgBoss =new JLabel(bgBoss2);	

		labelMonster = new JLabel(monster1);
		labelMonster.setBounds(230, -30, monster1.getIconWidth(), monster1.getIconHeight());
		//labelMonster.setBounds(200, 60, monster1.getIconWidth(), monster1.getIconHeight());
		this.add(labelMonster,1,1);
		
		printTxt = new JTextArea("전투시작." + newline);
		printTxt.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
		printTxt.setBackground(new Color(0, 0, 204));
		printTxt.setForeground(Color.WHITE);
		printTxt.setLineWrap(true);
		printTxt.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		printTxt.setBounds(50, screenSizeH / 2, screenSizeW - 50 * 2, 180);
		this.add(printTxt,98,0);
	}

	public void musicStart() {
		if (battleArea == 4) {
			clip2.start();
		} else {
			clip.start();
		}
	};

	public void showPlayerHP(){
		printTxt.append("[나의 HP] " + battle.user.getCurHp() + " / " + battle.user.getFullHp() + "\n");
	}
	
	public void showHP(){
		printTxt.append("[적의 HP] " + battle.mon.getCurHp() + " / " + battle.mon.getFullHp() + "\n");
		printTxt.append("[나의 HP] " + battle.user.getCurHp() + " / " + battle.user.getFullHp() + "\n");
	}
	
	public void musicPause() {
		if (battleArea == 4) {
			clip2.stop();
		} else {
			clip.stop();
		}
	};

	protected void paintComponent(Graphics g) {
		g.fillRect(0, 0, getWidth(), getHeight());

		if (battleArea == 2) {
			//g.drawImage(scaledbgBoss, 0, 0, null);
		} else if (battleArea == 1) {
			//g.drawImage(bg, 0, 0, null);
		}
	}

	void setBg(){
		this.remove(lblBg);
		lblBgBoss.setBounds(0, 0, 960, 960);
		this.add(lblBgBoss);
		repaint();	
	}
	
	void setBattleArea(int i) {battleArea = i;}

	public void mapSwitching() {musicPause();}

	void setMonster(int i) {
		if (i == 1) {
			labelMonster.setIcon(monster1);
		} 
		else if(i == 2){						///	
			labelMonster.setIcon(monster2);		///
		}										///
		else if(i == 3){						///	
			labelMonster.setIcon(monster3);		///
		}			
		else if (i == 4) {
			labelMonster.setIcon(bossMonster);
			labelMonster.setBounds(200, 60, bossMonster.getIconWidth(), bossMonster.getIconHeight());
			repaint();
		}
	}
	public void actionPerformed(ActionEvent e) {
	}
}
