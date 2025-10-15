package com.bptn.rpg.model.item;

public class Weapon extends Item {
	private int damage;

	public Weapon(String name, Rarity rarity, int value, int damage) {
		super(name, ItemType.WEAPON, rarity, value);
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	@Override
	public String toString() {
		return super.toString() + " | Damage: " + damage;
	}
}
