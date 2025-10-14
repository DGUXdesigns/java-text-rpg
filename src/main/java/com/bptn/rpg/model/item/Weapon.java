package com.bptn.rpg.model.item;

public class Weapon extends Item {
	private int strength;

	public Weapon(String name, Rarity rarity, int value, int strength) {
		super(name, ItemType.WEAPON, rarity, value);
		this.strength = strength;
	}

	public int getStrength() {
		return strength;
	}

	@Override
	public String toString() {
		return super.toString() + " | Strength: " + strength;
	}
}
