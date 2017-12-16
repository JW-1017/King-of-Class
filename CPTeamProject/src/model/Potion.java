package model;

public class Potion extends Item{
	
	public int recover;

	public Potion(String name, int price, int recover) {
		super(name, price);
		this.recover = recover;
	}	
}
