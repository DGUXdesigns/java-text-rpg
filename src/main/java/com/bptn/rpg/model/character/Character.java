package com.bptn.rpg.model.character;

public class Character {
	private String name;
	private int health;
	private int strength;

	public Character(String name, int health, int strength) {
		this.name = name;
		this.health = health;
		this.strength = strength;
	}

	// Getters and Setters.
	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getStrength() {
		return strength;
	}

	public void addStrength(int amount) {
		strength += amount;
		System.out.println("Strength: " + (strength - amount) + " > " + strength);
	}

	public static void main(String[] args) {
		Character player = new Character("Hero", 100, 50);
		player.addStrength(10);
	}
}
