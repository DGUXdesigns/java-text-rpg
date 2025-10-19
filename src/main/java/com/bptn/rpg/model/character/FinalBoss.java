package com.bptn.rpg.model.character;

import java.util.Random;

public class FinalBoss extends Enemy {
    private final Random random = new Random();

    public FinalBoss() {
        super("Black Dragon", 300, 25, 10, 500, 1000);
    }

    @Override
    public int attack(Character target) {
        double chance = Math.random();

        if (chance < 0.3) {
            int fireDamage = getStrength() * 2 + random.nextInt(10);
            System.out.println(getName() + " uses FIRE BREATH! It deals " + fireDamage + " damage!");
            return target.takeDamage(fireDamage);
        } else {
            int damage = getStrength() + random.nextInt(5) + (getLevel() * 2);
            System.out.println(getName() + " attacks! It deals " + damage + " damage!");
            return target.takeDamage(damage);
        }
    }

    @Override
    public void defend() {
        System.out.println(getName() + " roars menacingly and prepares to strike!");
    }
}
