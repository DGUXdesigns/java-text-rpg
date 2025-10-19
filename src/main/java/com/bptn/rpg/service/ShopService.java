package com.bptn.rpg.service;

import java.util.List;
import java.util.Scanner;

import com.bptn.rpg.model.character.Hero;
import com.bptn.rpg.model.item.Item;
import com.bptn.rpg.model.shop.Shop;

public class ShopService {
    private final Shop shop;
    private final Scanner scanner;

    public ShopService(Scanner scanner) {
        this.shop = new Shop("Hero Shop");
        this.scanner = scanner;
    }

    public void open(Hero hero) {
        boolean running = true;
        StringBuilder sb = new StringBuilder();

        sb.append("=================\n");
        sb.append("--- ").append(shop.getName()).append(" ---\n");
        sb.append("=================\n");

        while (running) {
            System.out.println(sb);
            System.out.println("Gold: " + hero.getGold() + "\n");
            System.out.println(shop);
            System.out.println("0. Leave the Shop");

            int choice = scanner.nextInt();
            List<Item> stock = shop.getStock();

            if (choice == 0) {
                running = false;
            } else if (choice >= 1 && choice <= stock.size()) {
                shop.buyItem(hero, stock.get(choice - 1));
            } else {
                System.out.println("Invalid option. Please try again.");
            }

        }
    }
}
