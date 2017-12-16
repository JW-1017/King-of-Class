package model;

public class MidTermExam extends Enemy{
	
	public MidTermExam() {
		super(800, 700, 10, 10, 10);
		super.setName("MidTermExam");
		super.setReturnExp(10);
		super.setReturnMoney(100);
	}

	@Override
	public void skill1(Fightable f) {
		// TODO Auto-generated method stub
		super.setDamage(super.getStrength()*3);
		super.setAtkRange((int)(super.getDamage()/5));
		
		this.attack(f, 2);
	}
	@Override
	public void skill2(Fightable f) {
		// TODO Auto-generated method stub
		super.setDamage(super.getStrength()*2);
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
