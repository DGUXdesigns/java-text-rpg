package com.bptn.rpg.model.character;

import com.bptn.rpg.model.Inventory;
import com.bptn.rpg.model.item.Rarity;
import com.bptn.rpg.model.item.Weapon;

public class Hero extends Character {
	private int level;
	private int experience;
	private int maxHealth;
	private Weapon weapon;
	private Inventory inventory;

	public Hero(String name, int health, int strength) {
		super(name, health, strength);
		this.level = 1;
		this.maxHealth = health;
		this.experience = 0;
		this.inventory = new Inventory();

		// Give player starting weapon and equip it
		Weapon woodenSword = new Weapon("Wooden Sword", Rarity.COMMON, 30, 50);
		inventory.addItem(woodenSword);

		this.weapon = woodenSword;
	}

	// Getters
	public int getLevel() {
		return level;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public int getExperience() {
		return experience;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public Inventory getInventory() {
		return inventory;
	}

	// Main methods
	public void equipWeapon(Weapon weapon) {
		this.weapon = weapon;
		System.out.println(getName() + " eqipped a " + weapon);
	}

	public void gainExperience(int amount) {
		experience += amount;
		System.out.println(getName() + " gained " + amount + " experience points");

		if (experience > (100 * level)) {
			levelUp();
		}
	}

	public void increaseMaxHealth(int amount) {
		maxHealth += amount;
		System.out.println("HP: " + (maxHealth - amount) + " > " + maxHealth);
	}

	public void levelUp() {
		level++;
		System.out.println(getName() + " Leveled up to " + level + "!");
		increaseMaxHealth(25);
		addStrength(5);
	}

	// TODO: Add combat Commands

}
