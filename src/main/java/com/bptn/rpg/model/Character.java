package com.bptn.rpg.model;

public class Character {
	private String name;
	private int hp;
	private int xp;
	private int gold;
	// TODO: Implement inventory model class
	// private Inventory inventory;

	public Character(String name) {
		this.name = name;
		this.hp = 100;
		this.xp = 0;
		this.gold = 100;
		// inventory = new Inventory();
	}

	// Getters and Setters.
	public String getName() {
		return name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
}
