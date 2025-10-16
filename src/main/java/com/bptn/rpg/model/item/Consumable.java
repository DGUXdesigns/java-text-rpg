package com.bptn.rpg.model.item;

public class Consumable extends Item {
	private int potency;

	public Consumable(String name, Rarity rarity, int price, int potency) {
		super(name, ItemType.CONSUMABLE, rarity, price);
		this.potency = potency;
	}

	public int getPotency() {
		return potency;
	}

	@Override
	public String toString() {
		return super.toString() + " | Potency" + potency;
	}
}
