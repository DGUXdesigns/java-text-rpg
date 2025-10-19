package com.bptn.rpg;

import java.util.Scanner;

import com.bptn.rpg.model.character.Enemy;
import com.bptn.rpg.model.character.FinalBoss;
import com.bptn.rpg.model.character.Hero;
import com.bptn.rpg.service.BattleService;
import com.bptn.rpg.service.InventoryMenu;
import com.bptn.rpg.service.ShopService;
import com.bptn.rpg.utils.BattleUtils;
import com.bptn.rpg.utils.InputUtil;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static boolean gameRunning = true;

    public static void main(String[] args) {
        // Create Game Assets
        Hero hero = new Hero(getHeroName(scanner), 100);
        BattleService battle = new BattleService(scanner);
        ShopService shop = new ShopService(scanner);
        InventoryMenu inventoryMenu = new InventoryMenu(hero);

        // Game loop
        while (gameRunning) {
            startGame(hero, shop, inventoryMenu, battle);
        }
    }

    private static String getHeroName(Scanner scanner) {
        String prompt = """
                =============================
                ⭐ What is your name Hero? ⭐
                =============================
                """;

        String heroName = "";

        while (heroName.isEmpty()) {
            System.out.print(prompt);
            heroName = scanner.nextLine().trim();

            if (heroName.isEmpty()) {
                System.out.println("You must enter a name! Please try again.\n");
            } else {
                heroName = heroName.substring(0, 1).toUpperCase() + heroName.substring(1).toLowerCase();
            }
        }

        System.out.println("\nWelcome, " + heroName + "! I wish you luck on you Adventure");
        return heroName;
    }

    private static void startGame(Hero hero, ShopService shop, InventoryMenu inventoryMenu, BattleService battle) {
        String[] options = {"Shop", "Inventory", "Training Dungeon", "Slay The Dragon"};
        int index = 1;

        System.out.println("\n--- Main Menu ---");
        for (String option : options) {
            System.out.println(" " + index++ + ") " + option);
        }

        System.out.println(" 0) Exit the game");

        int choice = InputUtil.getInt("\nWhat Will You do?");

        if (choice == 0) {
            gameRunning = false;
            System.out.println("Thanks For Playing!");
            return;
        }

        if (choice >= 1 && choice <= options.length) {
            String selection = options[choice - 1];

            switch (selection) {
                case "Shop" -> shop.open(hero);
                case "Inventory" -> inventoryMenu.handleInventory();
                case "Training Dungeon" -> {
                    Enemy enemy = BattleUtils.generateRandomMonster(hero.getLevel());
                    battle.startBattle(hero, enemy, false);
                }
                case "Slay The Dragon" -> battle.startBattle(hero, new FinalBoss(), true);
            }
        } else {
            System.out.println("Please enter an option from the menu: ");
        }
    }
}
