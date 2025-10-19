package com.bptn.rpg.model.character;

import com.bptn.rpg.model.item.Consumable;

public abstract class Character implements Commands {
    private final String name;
    private int level;
    private int experience;
    private int gold;
    private int maxHealth;
    private int health;
    private int strength;
    private boolean defending;
    private boolean isFleeing = false;

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

    public boolean getIsFleeing() {
        return isFleeing;
    }

    public void setIsFleeing(boolean isFleeing) {
        this.isFleeing = isFleeing;
    }

    public void gainExperience(int amount) {
        experience += amount;

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

    public int takeDamage(int damage) {
        if (defending) {
            damage /= 2;
        }

        setHealth(Math.max(0, health - damage));
        return damage;
    }

    @Override
    public void defend() {
        setDefending(true);
    }

    @Override
    public void useItem(Consumable item, Character target) {
        int previousHp = getHealth();
        int healAmount = target.getHealth() + item.getPotency();
        int health = Math.min(healAmount, getMaxHealth());

        setHealth(health);
        System.out.println(getName() + " recovered " + (getHealth() - previousHp) + " health using a " + item.getName());
    }

    @Override
    public void flee() {
        double fleeChance = Math.random();

        if (fleeChance > 0.5) {
            setIsFleeing(true);
        }
    }
}
