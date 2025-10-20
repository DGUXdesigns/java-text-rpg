package com.bptn.rpg.service;

import java.util.List;

import com.bptn.rpg.model.character.Hero;
import com.bptn.rpg.model.item.Item;
import com.bptn.rpg.model.shop.Shop;
import com.bptn.rpg.utils.InputUtil;
import com.bptn.rpg.utils.Messages;

public class ShopService {
    private final Shop shop;

    public ShopService() {
        this.shop = new Shop("Hero Shop");
    }

    public void open(Hero hero) {
        boolean running = true;

        while (running) {
            displayShopMenu(hero);

            int choice = InputUtil.getInt(Messages.RESET + "\nWhat would you like to purchase:");
            List<Item> stock = shop.getStock();

            if (choice == 0) {
                running = false;
            } else if (choice >= 1 && choice <= stock.size()) {
                shop.buyItem(hero, stock.get(choice - 1));
            } else {
                Messages.invalidOption();
            }

        }
    }

    private void displayShopMenu(Hero hero) {
        String sb = Messages.CYAN + "=================\n" +
                "--- " + shop.getName() + " ---\n" +
                "=================";
        System.out.println(sb);
        System.out.println(Messages.YELLOW + "Gold: " + hero.getGold() + "\n" + Messages.CYAN);
        System.out.println(shop);
        System.out.println(" 0) Leave the Shop");
    }
}
