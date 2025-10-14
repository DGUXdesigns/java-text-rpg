package com.bptn.rpg.model.character;

import com.bptn.rpg.model.Inventory;

public class Character {
	private String name;
	private int hp;
	private int xp;
	private Inventory inventory;

	public Character(String name) {
		this.name = name;
		this.hp = 100;
		this.xp = 0;
		this.inventory = new Inventory();
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

	public Inventory getInventory() {
		return inventory;
	}
}
