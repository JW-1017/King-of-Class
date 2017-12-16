package model;

public interface Fightable {
	
	public boolean isAlive();
	public void attack(Fightable f, int special);
	public void skill1(Fightable f);
	public void skill2(Fightable f);
}
