package model;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import view.GUIMain;

public class Battle {

	private Random rand = new Random();
	private int select = 0;
	public Player user = Player.getInstance();
	public Enemy mon;
	GUIMain frame;

	File file9 = new File("win.wav");
	Clip clip9 = AudioSystem.getClip();

	public Battle(GUIMain frame, Enemy mon)
			throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		clip9.open(AudioSystem.getAudioInputStream(file9));

		this.frame = frame;
		this.mon = mon;
	}

	public void playerSkill1() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		user.skill1(mon);
		frame.map_battle.showHP();
		if (!mon.isAlive()) {
			battleEnd();
			return;
		} else {
			frame.map_battle.printTxt.append("\n   < 적의 턴 >\n");			
			enemyAttack();
		}
		update();
	}

	public void playerSkill2() {
		user.skill2(mon);
		frame.map_battle.showHP();
		if (!mon.isAlive()) {
			battleEnd();
			return;
		} else {
			frame.map_battle.printTxt.append("\n   < 적의 턴 >\n");			
			enemyAttack();
		}
		update();
	}

	public void playerSkill3() {
		user.skill3(mon);
		frame.map_battle.showHP();
		if (!mon.isAlive()) {
			battleEnd();
			return;
		} else {
			frame.map_battle.printTxt.append("\n   < 적의 턴 >\n");			
			enemyAttack();
		}
		update();
	}

	public void playerHeal() {
		frame.map_battle.printTxt.setText(null);
		if (user.inventory.potionList.isEmpty()) {
			frame.map_battle.printTxt.append("핫식스를 다 먹어서 더 이상 먹을 수 없다 \n");			
		}
		else{
			user.getPotion();		
			frame.map_battle.printTxt.append("핫식스 흡입 \n");
			frame.map_battle.showPlayerHP();			
		}
		update();
	}

	public void playerRun() {
		frame.map_battle.printTxt.setText(null);
		frame.map_battle.printTxt.append("도망쳤다 \n");
		mapSwitching();
	}

	public void enemyAttack() {
		select = rand.nextInt(2) + 1;
		if (select == 1) {enemySkill1();} else {enemySkill2();}
		frame.map_battle.showPlayerHP();
		isLose();
		update();
	}

	public void enemySkill1() {
		mon.skill1(user);
		frame.map_battle.printTxt.append("찾아도 답이 안나오는 자료조사\n");
	}

	public void enemySkill2() {
		mon.skill2(user);
		frame.map_battle.printTxt.append("디버깅하다 화병걸리는 프로그래밍 과제 \n");
	}

	void isLose() {
		if (!user.isAlive()) {
			frame.map_battle.printTxt.append("아군의 패배");
			mapSwitching();
		}
	}
	public void battleEnd() {
		user.setMoney(user.getMoney() + mon.getReturnMoney());
		user.setCurExp(user.getCurExp() + mon.getReturnExp());

		if (user.levelUpJudge()) {
			frame.map_battle.printTxt.append("LEVEL UP ! ! \n");
		}
		frame.map_battle.printTxt.append("\n이겼다 !");
		this.mapSwitching();
	}

	private void mapSwitching() {
		try {
			update();
			frame.map_battle.musicPause();

			clip9.flush();
			clip9.setFramePosition(0);

			clip9.start();

			Thread.sleep(1150);

			if (mon instanceof Prof_Choi) {
				frame.opening.txtShift();
				frame.opening.musicStart();
				frame.setContentPane(frame.opening);
				frame.opening.requestFocus();
			} else {
				frame.map_classroom.musicStart();
				frame.map_battle.mapSwitching();
				frame.setContentPane(frame.map_classroom);
				frame.map_classroom.requestFocus();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update() {
		frame.map_battle.printTxt.update(frame.map_battle.printTxt.getGraphics());
	}
}
