package model;

public class Prof_Choi extends Enemy{

	public Prof_Choi() {
		super(2000, 1000, 30, 30, 30);
		super.setName("Prof_Choi");
		super.setReturnExp(300);
		super.setReturnMoney(2000);
	}

	@Override
	public void skill1(Fightable f) {
		// TODO Auto-generated method stub
		super.setDamage(super.getStrength()*4);
		super.setAtkRange((int)(super.getDamage()/5));
		
		this.attack(f, 3);
	}
	@Override
	public void skill2(Fightable f) {
		// TODO Auto-generated method stub
		super.setDamage(super.getStrength());
		super.setAtkRange((int)(super.getDamage()/5));
		
		this.attack(f, 4);
	}

	@Override
	public void attack(Fightable f, int special) {
		// TODO Auto-generated method stub
		if(super.getLuck() >= rand.nextInt(100) + 1){
			((Player)f).setCurHp(((Player)f).getCurHp() - special*(super.getDamage()+super.getAtkRange()));
			return;
		}
		((Player)f).setCurHp(((Player)f).getCurHp() - super.getDamage()+super.getAtkRange());
	}
}
