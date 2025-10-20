package com.bptn.rpg.model.character;

import com.bptn.rpg.utils.Messages;

import java.util.Random;

public class FinalBoss extends Enemy {
    private final Random random = new Random();
    private boolean wasDefending = false;

    public FinalBoss() {
        super("Black Dragon", 300, 25, 10, 500, 1000);
    }

    @Override
    public int attack(Character target) {
        double chance = Math.random();
        int damage;

        if (wasDefending) {
            damage = getStrength() * 4 + random.nextInt(20);
            System.out.println(Messages.YELLOW + getName() + " unleashes its ULTIMATE ATTACK after charging up! It deals " + damage + " massive damage!");
            wasDefending = false;
            return target.takeDamage(damage);
        }

        if (chance < 0.3) {
            damage = getStrength() * 2 + random.nextInt(10);
            System.out.println(Messages.YELLOW + getName() + " uses Fire Breath! It deals " + damage + " damage!");
        } else {
            damage = getStrength() + random.nextInt(5) + (getLevel() * 2);
            System.out.println(Messages.YELLOW + getName() + " attacks! It deals " + damage + " damage!");
        }

        wasDefending = false;
        return target.takeDamage(damage);
    }

    @Override
    public void defend() {
        System.out.println(Messages.PURPLE + getName() + " roars menacingly and prepares for a strong attack!");
        wasDefending = true;
    }
}
