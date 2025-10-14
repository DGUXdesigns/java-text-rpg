package com.bptn.rpg.model.item;

public class Consumable extends Item {
	private int potency;

	public Consumable(String name, Rarity rarity, int value, int potency) {
		super(name, ItemType.CONSUMABLE, rarity, value);
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
