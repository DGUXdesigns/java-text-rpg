package com.bptn.rpg.model.character;

public abstract class Character implements Commands {
    private final String name;
    private int level;
    private int experience;
    private int gold;
    private int maxHealth;
    private int health;
    private int strength;
    private boolean defending;

    public Character(String name, int level, int experience, int gold, int maxHealth, int strength) {
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.gold = gold;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.strength = strength;
        this.defending = false;
    }

    // Getters and Setters.
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public void gainExperience(int amount) {
        experience += amount;
        System.out.println(getName() + " gained " + amount + " experience points");

        int previousLevel = getLevel();
        int previousMaxHp = getMaxHealth();
        int previousStrength = getStrength();

        while (experience >= (100 * getLevel())) {
            levelUp();
            setHealth(maxHealth);
        }

        if (previousLevel < level) {
            System.out.println("\n" + getName() + " is now level " + level + "!");
            System.out.println("HP: " + previousMaxHp + " > " + maxHealth);
            System.out.println("Strength: " + previousStrength + " > " + strength);
        }
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        if (amount > 0) {
            gold += amount;
            System.out.println(amount + " gold added to your inventory.\n");
        }

    }

    public boolean removeGold(int amount) {
        if (amount <= gold) {
            gold -= amount;
            return true;
        }

        System.out.println("Not enough gold!");
        return false;
    }

    public void levelUp() {
        level++;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void addMaxHealth(int amount) {
        maxHealth += amount;

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
    }

    public boolean isDefending() {
        return defending;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        // Handles damage if unit is defending
        if (defending) {
            damage /= 2; // reduces damage by 50%
            System.out.println(name + " was defending and reduced the damage to " + damage + "!");
            defending = false;
        }

        setHealth(Math.max(0, health - damage));
    }
}
