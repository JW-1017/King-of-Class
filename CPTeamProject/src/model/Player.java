package model;

public class Player extends Unit implements Fightable{

	private int fullExp;
	private int curExp;
	private int money;
	private int level;
	private int[] expTable = {10, 5, 100, 200, 500};
	public Inven inventory = new Inven();
	public Weapon weapon;
	public Armor armor;

	private Player() {
		super(2000, 2000, 20, 20, 20);
		this.level = 1;
		this.money = 1000;
		this.curExp = 0;
		this.fullExp = 5;
		
		weapon = new Weapon("방망이", 10, 0);
		armor = new Armor("천쪼가리", 5, 0);
		for(int i = 0; i  < 10; i++){
			inventory.potionList.add(new Potion("빨간포션", 200, 500));
		}
	}
	private static class Singleton {
		private static final Player instance = new Player();
	}
	public static Player getInstance () {
		return Singleton.instance;
	}

	public int getFullExp() {
		return fullExp;
	}
	public void setFullExp(int fullExp) {
		this.fullExp = fullExp;
	}
	public int getCurExp() {
		return curExp;
	}
	public void setCurExp(int curExp) {
		this.curExp = curExp;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

	public boolean levelUpJudge(){
		if(this.curExp >= this.fullExp){
			levelUp();
			return true;
		}
		return false;
	}
	public void levelUp(){
		this.level++;
		this.curExp = this.curExp - this.fullExp;
		this.fullExp = expTable[level-1];
		this.money+=(1000*level);
		super.setLuck(super.getLuck()+10);
		super.setStrength(super.getStrength()+10);
		super.setDefense(super.getDefense()+10);
		super.setFullHp(super.getFullHp()+1000);
		super.setCurHp(super.getFullHp());
	}

	public void getPotion(){
		if(this.inventory.potionList.size() == 0){
			return;
		}
		super.setCurHp(super.getCurHp()+this.inventory.potionList.get(0).recover);
		this.inventory.potionList.remove(0);
	}
	public void equip(Item o){
		if(o instanceof Weapon){
			this.weapon = (Weapon)o;
		}
		else if(o instanceof Armor){
			this.armor = (Armor)o;
		}
	}
	
	@Override
	public void attack(Fightable f, int special) {
		if(super.getLuck() >= rand.nextInt(100) + 1){
			((Enemy)f).getAttacked(special*(super.getDamage()+super.getAtkRange()));
			return;
		}
		((Enemy)f).setCurHp(((Enemy)f).getCurHp() - super.getDamage()+super.getAtkRange());
	}
	
	@Override
	public void skill1(Fightable f) {	//스킬1 시험 베끼기.
		super.setDamage(weapon.damage + super.getStrength()*5);
		super.setAtkRange((int)(super.getDamage()/3));
		
		this.attack(f, 2);
	}
	
	@Override
	public void skill2(Fightable f) {	//스킬2 밤새서 공부하기.
		super.setDamage(weapon.damage + super.getStrength()*3);
		super.setAtkRange((int)(super.getDamage()/10));

		this.attack(f, 4);
	}
	
	public void skill3(Fightable f) {	//스킬3 질문하기.
		super.setDamage(weapon.damage + super.getStrength()*4);
		super.setAtkRange((int)(super.getDamage()/5));

		this.attack(f, 3);
	}
}