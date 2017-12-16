package model;

public class Armor extends Item{
	
	public int defPow;

	public Armor(String name, int price, int defPow) {
		super(name, price);
		this.defPow = defPow;
	}
}
