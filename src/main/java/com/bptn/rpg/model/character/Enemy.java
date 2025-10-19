package com.bptn.rpg.model.character;

import com.bptn.rpg.model.item.Consumable;

import java.util.Random;

public class Enemy extends Character {
    private final Random random = new Random();


    public Enemy(String name, int maxHealth, int strength, int level, int experience, int gold) {
        super(name, level, experience, gold, maxHealth, strength);
    }

    @Override
    public int attack(Character target) {
        int damage = getStrength() + random.nextInt(5) + (getLevel() * 2);
        target.takeDamage(damage);
        return damage;
    }
}
