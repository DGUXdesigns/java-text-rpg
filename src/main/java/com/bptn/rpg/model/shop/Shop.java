package com.bptn.rpg.model.shop;

import java.util.ArrayList;
import java.util.List;

import com.bptn.rpg.model.character.Hero;
import com.bptn.rpg.model.item.Consumable;
import com.bptn.rpg.model.item.Item;
import com.bptn.rpg.model.item.ItemType;
import com.bptn.rpg.model.item.Rarity;
import com.bptn.rpg.model.item.Weapon;
import com.bptn.rpg.utils.Messages;

public class Shop {
    private final String name;
    private final List<Item> stock;

    public Shop(String name) {
        this.name = name;
        this.stock = new ArrayList<>();
        initializeStock();
    }

    private void initializeStock() {
        stock.add(new Weapon("Copper Sword", Rarity.COMMON, 50, 10));
        stock.add(new Weapon("Iron Sword", Rarity.UNCOMMON, 100, 13));
        stock.add(new Weapon("Silver Axe", Rarity.RARE, 200, 18));
        stock.add(new Weapon("Knight's Blade", Rarity.EPIC, 350, 25));
        stock.add(new Weapon("Dragon Slayer", Rarity.LEGENDARY, 500, 40));
        stock.add(new Consumable("Potion", Rarity.COMMON, 25, 100));
        stock.add(new Consumable("Hi-Potion", Rarity.UNCOMMON, 75, 250));
    }

    public String getName() {
        return name;
    }

    public List<Item> getStock() {
        return stock;
    }

    public void buyItem(Hero hero, Item item) {
        boolean success = hero.removeGold(item.getPrice());

        if (success) {
            hero.getInventory().addItem(item);

            if (item.getItemType().equals(ItemType.WEAPON)) {
                stock.remove(item);
            }

            Messages.purchaseSuccess(item);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 1;

        for (Item item : stock) {
            sb.append(index++).append(". ").append(item.getItemType()).append(": ").append(item)
                    .append(" | Price: ").append(item.getPrice()).append(" Gold\n");
        }

        return sb.toString();
    }
}
