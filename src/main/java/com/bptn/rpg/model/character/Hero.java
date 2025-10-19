package com.bptn.rpg.model.character;

import com.bptn.rpg.model.Inventory;
import com.bptn.rpg.model.item.Consumable;
import com.bptn.rpg.model.item.Rarity;
import com.bptn.rpg.model.item.Weapon;

import java.util.Random;

public class Hero extends Character {
    private Weapon weapon;
    private final Inventory inventory;
    private boolean isFleeing = false;

    public Hero(String name, int gold) {
        super(name, 1, 0, gold, 120, 10);
        this.inventory = new Inventory();
        this.weapon = new Weapon("Wooden Sword", Rarity.COMMON, 30, 6);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void equipWeapon(Weapon weapon) {
        inventory.addItem(this.weapon);
        inventory.removeItem(weapon);
        this.weapon = weapon;
    }

    public void levelUp() {
        super.levelUp();
        addMaxHealth(25);
        addStrength(5);
    }

    public boolean getIsFleeing() {
        return isFleeing;
    }

    public void setIsFleeing(boolean isFleeing) {
        this.isFleeing = isFleeing;
    }

    @Override
    public void attack(Character target) {
        Random random = new Random();

        // Calculate total damage
        int baseDamage = weapon.getDamage() + getStrength();

        // Damage multiplier
        double variability = 0.85 + (0.3 * random.nextDouble());
        int damage = (int) (baseDamage * variability);

        // Level scaling Bonus
        int levelBonus = (int) (getLevel() * 1.5);

        damage += levelBonus;

        // apply damage to enemy
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
        int previousHp = getHealth();
        int healAmount = target.getHealth() + item.getPotency();
        int health = Math.min(healAmount, getMaxHealth());

        setHealth(health);
        System.out.println(getName() + " recovered " + (getHealth() - previousHp) + " health using a " + item.getName());
    }

    @Override
    public boolean flee() {
        double fleeChance = Math.random();

        if (fleeChance > 0.5) {
            System.out.println(getName() + " successfully fled the battle!");
            setIsFleeing(true);
            return true;
        } else {
            System.out.println(getName() + " tried to flee but couldn't get away!");
            return false;
        }
    }
}
