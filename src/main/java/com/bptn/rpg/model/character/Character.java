package com.bptn.rpg.model.character;

public abstract class Character implements Commands {
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
}
