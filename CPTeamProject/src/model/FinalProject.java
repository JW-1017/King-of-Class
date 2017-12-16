package model;

public class FinalProject extends Enemy{
	
	public FinalProject() {
		super(1000, 900, 15, 20, 20);
		super.setName("FinalProject");
		super.setReturnExp(30);
		super.setReturnMoney(500);
	}

	@Override
	public void skill1(Fightable f) {
		// TODO Auto-generated method stub
		super.setDamage(super.getStrength()*4);
		super.setAtkRange((int)(super.getDamage()/5));
		
		this.attack(f, 2);
	}
	@Override
	public void skill2(Fightable f) {
		// TODO Auto-generated method stub
		super.setDamage(super.getStrength()*3);
		super.setAtkRange((int)(super.getDamage()/10));
		
		this.attack(f, 3);
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
