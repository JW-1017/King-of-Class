package model;

abstract public class Enemy extends Unit implements Fightable{	
	
	private int returnExp;
	private int returnMoney;
	
	public Enemy(int curHp, int fullHp, int luck, int strength, int defense) {
		super(curHp, fullHp, luck, strength, defense);
	}

	public int getReturnExp() {
		return returnExp;
	}
	public void setReturnExp(int returnExp) {
		this.returnExp = returnExp;
	}

	public int getReturnMoney() {
		return returnMoney;
	}
	public void setReturnMoney(int returnMoney) {
		this.returnMoney = returnMoney;
	}
	
	abstract public void skill1(Fightable f);
	abstract public void skill2(Fightable f);
}