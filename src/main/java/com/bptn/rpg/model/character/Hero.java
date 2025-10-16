package com.bptn.rpg.model.character;

import com.bptn.rpg.model.Inventory;
import com.bptn.rpg.model.item.Consumable;
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
		Weapon woodenSword = new Weapon("Wooden Sword", Rarity.COMMON, 30, 5);
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

	@Override
	public void attack(Character target) {
		// Calculate total damage
		int attackPower = weapon.getDamage() + getStrength();
		int levelBonus = (int) (attackPower * (0.1 * level));
		int totalDamage = attackPower + levelBonus;

		// apply damage to enemy
		System.out.println(getName() + " dealt " + totalDamage + " damage!");
		target.takeDamage(totalDamage);
	}

	@Override
	public void defend() {
		setDefending(true);
		System.out.println(getName() + "takes a defensive stance and will take reduced damage next turn");
	}

	@Override
	public void useItem(Consumable item, Character target) {
		// Calculate amount to be healed
		int healAmount = target.getHealth() + item.getPotency();
		int health = Math.min(healAmount, getMaxHealth()); // Ensure health doesn't exceed max health.

		// Apply new health to hero
		setHealth(health);
		System.out.println(getName() + " recovered " + item.getPotency() + " using a " + item.getName());
	}

	@Override
	public boolean flee() {
		double fleeChance = Math.random();
		// 50% chance to escape
		if (fleeChance > 0.5) {
			System.out.println(getName() + " successfully fled the battle!");
			return true;
		} else {
			System.out.println(getName() + " tried to flee but couldn't get away!");
			return false;
		}
	}
}
