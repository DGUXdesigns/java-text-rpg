package com.bptn.rpg.model.item;

public class Item {
	private String name;
	private ItemType type;
	private Rarity rarity;
	private int value;

	public Item(String name, ItemType type, Rarity rarity, int value) {
		this.name = name;
		this.type = type;
		this.rarity = rarity;
		this.value = value;
	}

	// Getters & Setters
	public String getName() {
		return name;
	}

	public ItemType getItemType() {
		return type;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return name + " (" + rarity + ")";
	}
}
