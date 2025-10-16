package com.bptn.rpg.model.item;

public class Item {
	private String name;
	private ItemType type;
	private Rarity rarity;
	private int price;

	public Item(String name, ItemType type, Rarity rarity, int price) {
		this.name = name;
		this.type = type;
		this.rarity = rarity;
		this.price = price;
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

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return name + " (" + rarity + ")";
	}
}
