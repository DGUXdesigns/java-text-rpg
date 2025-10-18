package com.bptn.rpg.model.character;

import com.bptn.rpg.model.item.Consumable;

import java.util.Random;

public class Enemy extends Character {
    private final int experienceReward;
    private final int goldReward;
    private final Random random = new Random();


    public Enemy(String name, int health, int strength, int level, int experienceReward, int goldReward) {
        super(name, level, health, strength);
        this.experienceReward = experienceReward;
        this.goldReward = goldReward;
    }

    public int getExperienceReward() {
        return experienceReward;
    }

    public int getGoldReward() {
        return goldReward;
    }

    @Override
    public void attack(Character target) {
        int damage = getStrength() + random.nextInt(5) + (getLevel() * 2);
        System.out.println(getName() + " dealt " + damage + " damage!");
        target.takeDamage(damage);
    }

    @Override
    public void defend() {
        setDefending(true);
        System.out.println(getName() + "takes a defensive stance and will take reduced damage next turn");
    }

    @Override
    public void useItem(Consumable item, Character target) {
        // TODO Auto-generated method stub

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
