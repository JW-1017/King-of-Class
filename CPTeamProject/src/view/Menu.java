
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
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
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import model.Player;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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

public class Menu extends JLayeredPane  {
	GUIMain guimain ;
	public Menu menu = this;
	public Player user = Player.getInstance();
	int stack = 0;
	public static final int screenSizeW = 192 * 5;
	public static final int screenSizeH = 192 * 5;
	public int bgW = (int) (500*1.92);
	public int bgH = (int) (500*1.92);
	public int printTxtW = 800;
	public int printTxtH = 600;
	public int defaultX = 300;
	public int posY = 300;
	public int screenPosX = 960/2;
	public int screenPosY = 960/2;
	public String blank = "                                                                                                                        ";
	String mapName = "menubg.gif";
	public JTextArea printTxt;
	public JTextArea txtBg;
	Border border = BorderFactory.createLineBorder(Color.WHITE, 6, true);
	public  Image imgMap = new ImageIcon("MenuBg.gif").getImage();
	Image scaledMap = imgMap.getScaledInstance(bgW, bgH, Image.SCALE_FAST);

	public JButton button_potion;
	public JButton button_equip;

	public ImageIcon playerImg = new ImageIcon("hero.jpg");
	public JLabel lblPlayer = new JLabel(playerImg);

	// public static Music mapMusic = new Music("mapTown.mp3");
	File file = new File("menubg.wav");
	Clip clip = AudioSystem.getClip();

	public Menu(GUIMain guimain) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		this.guimain = guimain;
		this.setLayout(null);
		this.setSize(screenSizeW, screenSizeW);

		//printTxt = new JTextArea("");
		txtBg = new JTextArea(){
            @Override
            /*
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = printTxtW;
                int h = printTxtH;

                GradientPaint gp;
                gp = new GradientPaint(0, 0, new Color(51,51,51), 0, h, new Color(20,20,20));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
             */
            
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
        		Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                LinearGradientPaint lgp = new LinearGradientPaint(
                        new Point(0, 0), 
                        new Point(0, printTxtH), 
                        new float[]{0.142f, 1f}, 
                        new Color[]{Color.BLUE, Color.RED});

                g2d.setPaint(lgp);
                g2d.fill(new Rectangle(0, 0, printTxtW, printTxtH));
                g2d.dispose();
            }
        };
        txtBg.setBounds(50, 50, 800, 600);
        txtBg.setEditable(false);
        this.add(txtBg,0,0);

        printTxt = new JTextArea();
		printTxt.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
//		printTxt.setBackground(new Color(0, 0, 0));
		printTxt.setForeground(Color.WHITE);
		printTxt.setLineWrap(true);
		printTxt.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		printTxt.setBounds(50, 50, 800, 600);
		printTxt.setOpaque(false);
		printTxt.setEditable(false);
		this.add(printTxt,1,0);

		button_potion = new JButton("Potion");
		button_potion.setBounds(490+200-20, 350+60+10+60-20, 150, 80-20);
		button_potion.setBackground(new Color(0, 0, 204));
		button_potion.setForeground(Color.WHITE);
		button_potion.setFocusPainted(false);
		button_potion.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));// http://answers.yahoo.com/question/index?qid=20070906133202AAOvnIP
		button_potion.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		this.add(button_potion,99,0);
		
		button_potion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.getPotion();
				write();
				guimain.setContentPane(menu);
				menu.requestFocus();
			}
		});
/*
		button_equip = new JButton("장비");
		button_equip.setBounds(600, 200, 150, 100);
		button_equip.setBackground(new Color(0, 0, 204));
		button_equip.setForeground(Color.WHITE);
		button_equip.setFocusPainted(false);
		button_equip.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));// http://answers.yahoo.com/question/index?qid=20070906133202AAOvnIP
		button_equip.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		this.add(button_equip);
*/
		lblPlayer.setBounds(90, 90+60-20, playerImg.getIconWidth(), playerImg.getIconHeight());
		this.add(lblPlayer,99,0);
		clip.open(AudioSystem.getAudioInputStream(file));
	}

	public void write() {
		printTxt.setText(null);
		printTxt.append("\n\n");
		printTxt.append(blank + "LV : " + user.getLevel() + "\n");
		printTxt.append(blank + "경험치 : " + user.getCurExp() + " / " + user.getFullExp() + "\n");
		printTxt.append(blank + "HP : " + user.getCurHp() + " / " + user.getFullHp() + "\n");
		printTxt.append(blank + "힘 :" + user.getStrength() + "\n");
		printTxt.append(blank + "방어력 :" + user.getDefense() + "\n");
		printTxt.append(blank + "운 :" + user.getLuck() + "\n\n");
		printTxt.append(blank + "소지금 :" + user.getMoney() + "원\n\n");
		printTxt.append(blank + "물약갯수 :" + user.inventory.potionList.size() + "\n\n");
		printTxt.append("         이름 : 공대생\n\n");
	}
	public void mapSwitching() {this.musicPause();}
	public void musicStart() {
		clip.setFramePosition(0);
		clip.loop(20);};
	public void musicPause() {clip.stop();};
	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(scaledMap, 0, 0, screenSizeW, screenSizeW, 0, 0, screenSizeW, screenSizeH, this);
	}
}