package com.bptn.rpg.model.item;

public class Weapon extends Item {
    private final int damage;

    public Weapon(String name, Rarity rarity, int price, int damage) {
        super(name, ItemType.WEAPON, rarity, price);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return super.toString() + " | Damage: " + damage;
    }
}
