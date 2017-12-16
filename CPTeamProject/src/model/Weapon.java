package model;

public class Weapon extends Item{
	
	public int damage;

	public Weapon(String name, int price, int damage) {
		super(name, price);
		this.damage = damage;
	}
}
