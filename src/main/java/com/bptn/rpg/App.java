package com.bptn.rpg;

import java.util.Scanner;

import com.bptn.rpg.model.character.Enemy;
import com.bptn.rpg.model.character.Hero;
import com.bptn.rpg.service.BattleService;
import com.bptn.rpg.service.InventoryMenu;
import com.bptn.rpg.service.ShopService;
import com.bptn.rpg.utils.BattleUtils;

public class App {
    public static void main(String[] args) {
        // Get Hero Name
        Scanner scanner = new Scanner(System.in);
        System.out.println("===========================");
        System.out.println("⚔️ What is your name Hero?");
        System.out.println("===========================");

        String heroName = scanner.next();

        // Create Hero
        Hero hero = new Hero(heroName);
        BattleService battle = new BattleService();
        ShopService shop = new ShopService(scanner);
        InventoryMenu inventoryMenu = new InventoryMenu(hero);

        boolean gameRunning = true;

        while (gameRunning) {
            System.out.println("Where to?");

            String[] options = {"Shop", "Inventory", "Training Dungeon", "Slay The Dragon"};
            int index = 1;

            for (String option : options) {
                System.out.println(index++ + ") " + option);
            }

            System.out.println("0) Exit the game");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            if (choice == 0) {
                gameRunning = false;
                System.out.println("Thanks For Playing!");
            } else if (choice >= 1 && choice <= options.length) {
                String selection = options[choice - 1];

                switch (selection) {
                    case "Shop" -> {
                        shop.open(hero);
                    }
                    case "Inventory" -> {
                        inventoryMenu.handleInventory();
                    }
                    case "Training Dungeon" -> {
                        Enemy enemy = BattleUtils.generateRandomMonster(hero.getLevel());
                        battle.startBattle(hero, enemy);
                    }
                    case "Slay The Dragon" -> {
                        // TODO: Implement Dragon boss
                        battle.startFinalBattle(hero);
                    }
                    default -> System.out.println("Invalid choice, try again.");
                }
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
