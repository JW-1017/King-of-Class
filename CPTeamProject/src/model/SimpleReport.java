package model;

public class SimpleReport extends Enemy{

	public SimpleReport() {
		super(600, 500, 5, 5, 5);
		super.setName("SimpleReport");
		super.setReturnExp(5);
		super.setReturnMoney(50);
	}
	
	@Override
	public void attack(Fightable f, int special) {
		if(super.getLuck() >= rand.nextInt(100) + 1){
			((Player)f).setCurHp(((Player)f).getCurHp() - special*(super.getDamage()+super.getAtkRange()));
			return;
		}
		((Player)f).setCurHp(((Player)f).getCurHp() - super.getDamage()+super.getAtkRange());
	}
	
	@Override
	public void skill1(Fightable f) {
		// TODO Auto-generated method stub
		super.setDamage(super.getStrength()*2);
		super.setAtkRange((int)(super.getDamage()/5));
		
		this.attack(f, 2);
	}
	@Override
	public void skill2(Fightable f) {
		// TODO Auto-generated method stub
		super.setDamage(super.getStrength());
		super.setAtkRange((int)(super.getDamage()/5));
		
		this.attack(f, 3);
	}
}
