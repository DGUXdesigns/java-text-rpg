package com.bptn.rpg.model.character;

import com.bptn.rpg.model.Inventory;
import com.bptn.rpg.model.item.Rarity;
import com.bptn.rpg.model.item.Weapon;

import java.util.Random;

public class Hero extends Character {
    private Weapon weapon;
    private final Inventory inventory;


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

    @Override
    public int attack(Character target) {
        Random random = new Random();

        // Calculate total damage
        int baseDamage = weapon.getDamage() + getStrength();

        // Damage multiplier
        double variability = 0.85 + (0.3 * random.nextDouble());
        int damage = (int) (baseDamage * variability);

        // Level scaling Bonus
        int levelBonus = (int) (getLevel() * 1.5);

        damage += levelBonus;
        return target.takeDamage(damage);
    }


}
