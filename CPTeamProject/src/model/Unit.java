package model;

import java.util.Random;

public class Unit {
	
	private String name;
	private int curHp;
	private int fullHp;
	private int luck;
	private int strength;
	private int damage;
	private int defense;
	private int atkRange;								// ���ݷ� ��������
	private int defRange;								// ���� ���� ����
	
	Random rand = new Random();
	
	public Unit(int curHp, int fullHp, int luck, int strength, int defense) {
		this.curHp = curHp;
		this.fullHp = fullHp;
		this.luck = luck;
		this.strength = strength;
		this.defense = defense;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCurHp() {
		return curHp;
	}
	public void setCurHp(int curHp) {
		this.curHp = curHp;
		if(this.curHp > this.fullHp){
			this.curHp = this.fullHp;
		}
		if(curHp <= 0){
			this.curHp = 0;
		}
	}
	public int getFullHp() {
		return fullHp;
	}
	public void setFullHp(int fullHp) {
		this.fullHp = fullHp;
	}
	public int getLuck() {
		return luck;
	}
	public void setLuck(int luck) {
		this.luck = luck;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int getAtkRange() {
		return atkRange;
	}
	public void setAtkRange(int atkRange) {
		this.atkRange = rand.nextInt(this.damage);
	}

	public int getDefRange() {
		return defRange;
	}
	public void setDefRange(int defRange) {
		this.defRange = rand.nextInt(this.defense);
	}

	public boolean isAlive(){
		if(this.curHp <= 0){
			return false;
		}
		return true;
	}
	public void getAttacked(int damage) { 
		if(this.isAlive()){
			curHp = curHp-damage;
			if(curHp <= 0){
				this.curHp = 0;
			}
		}
		else {
			if(curHp <= 0){
				this.curHp = 0;
			}
			return; 		// ���� �Ұ�;
		}
	}
	public boolean isCritical(){	//ũ��Ƽ�� Ȯ��.
		int random=rand.nextInt(100)+1;		//random=1~100 ���� ��. 
		if(random > getLuck())	//���� luck�� 30�̸�, random�� 1~30�϶���(30%Ȯ��) true ����.
			return false;
		else {
			return true;
		}
	}
}