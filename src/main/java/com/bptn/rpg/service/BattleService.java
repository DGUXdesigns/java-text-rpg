package com.bptn.rpg.service;

import com.bptn.rpg.model.Inventory;
import com.bptn.rpg.model.character.Enemy;
import com.bptn.rpg.model.character.Hero;
import com.bptn.rpg.model.item.Consumable;
import com.bptn.rpg.model.item.Item;
import com.bptn.rpg.model.item.ItemType;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleService {
    private final Scanner scanner;
    private final Random random = new Random();
    private Hero hero;
    private Enemy enemy;

    public BattleService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startBattle(Hero hero, Enemy enemy, boolean isFinalBattle) {
        this.hero = hero;
        this.enemy = enemy;

        if (isFinalBattle) {
            System.out.println("""
                    
                    ==============================
                    ⚔️ FINAL BOSS BATTLE START! ⚔️
                    ==============================""");
            System.out.println("\n🔥 A fearsome " + enemy.getName() + " descends from the sky! 🔥\n");

        } else {
            System.out.println("""
                    
                    ===================
                    ⚔️ Battle start! ⚔️
                    ===================""");
            System.out.println("A " + enemy.getName() + " appears!\n");

        }

        boolean isHeroTurn = true;


        while (hero.isAlive() && enemy.isAlive()) {
            String name = isHeroTurn ? hero.getName() : enemy.getName();

            System.out.println(name + "'s turn\n");

            if (isHeroTurn) {
                playerTurn();
            } else {
                enemyTurn();
            }

            isHeroTurn = !isHeroTurn;
        }

        if (hero.getIsFleeing()) {
            hero.setIsFleeing(false);
        } else {
            endBattle();
        }
    }

    private void playerTurn() {
        if (hero.getIsFleeing()) {
            hero.setIsFleeing(false);
        }

        if (hero.isDefending()) {
            hero.setDefending(false);
        }

        boolean actionTaken = false;

        while (!actionTaken) {
            battleUI();
            int choice = -1;

            try {
                choice = scanner.nextInt();
                scanner.hasNextLine();

                switch (choice) {
                    case 1 -> {
                        hero.attack(enemy);
                        actionTaken = true;
                    }
                    case 2 -> {
                        hero.defend();
                        actionTaken = true;
                    }
                    case 3 -> {
                        handleInventory();
                        actionTaken = true;
                    }
                    case 4 -> {
                        if (hero.flee()) {
                            // End battle loop by setting enemy health to 0
                            enemy.setHealth(0);
                        }
                        actionTaken = true;
                    }
                    default -> System.out.println("Invalid Option. Please enter a number between 1 and 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private void enemyTurn() {
        // AI logic for enemy actions
        if (enemy.isDefending()) {
            enemy.setDefending(false);
        }

        if (enemy.getHealth() < 50 && random.nextInt(100) < 30) {
            enemy.defend();
        } else {
            enemy.attack(hero);
        }
    }

    private void battleUI() {
        String message = """
                What will you do?
                 1) Attack
                 2) Defend
                 3) Inventory
                 4) Flee
                """;

        System.out.print(hero.getName() + " HP: " + hero.getHealth() + "/" + hero.getMaxHealth());
        System.out.println(" | " + enemy.getName() + " HP: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
        System.out.println(message);
    }

    private void handleInventory() {
        Inventory inventory = hero.getInventory();
        List<Item> consumables = inventory.getItemsByType(ItemType.CONSUMABLE);

        if (consumables.isEmpty()) {
            System.out.println("No potions in you inventory!");
            playerTurn();
            return;
        }

        // Display items
        StringBuilder sb = new StringBuilder();
        sb.append("--- Items ---\n");

        int index = 1;

        for (Item item : consumables) {
            sb.append(index++).append(") ").append(item.getName()).append("\n");
        }

        sb.append("0) Back");
        System.out.println(sb);

        // Manage player choice
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                playerTurn();
                return;
            }

            if (choice < 0 || choice > consumables.size()) {
                System.out.println("Invalid selection!");
                playerTurn();
                return;
            }

            Item selectedItem = consumables.get(choice - 1);
            hero.useItem((Consumable) selectedItem, hero);
            inventory.removeItem(selectedItem);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number");
            scanner.nextLine();
            playerTurn();
        }


    }

    private void endBattle() {
        if (!hero.isAlive()) {
            System.out.println("\n " + hero.getName() + " was defeated...");
            System.out.println("💀 GAME OVER 💀");
            System.exit(0);
        } else if (enemy.getHealth() == 0) {
            System.out.println(hero.getName() + " defeated " + enemy.getName() + "!");
            hero.gainExperience(enemy.getExperience());
            hero.addGold(enemy.getGold());
        }
    }

    private void endBossBattle() {
        if (!hero.isAlive()) {
            System.out.println("\n" + hero.getName() + " was slain by the " + enemy.getName() + "...");
            System.out.println("💀 GAME OVER 💀");
            System.exit(0);
        } else if (enemy.getHealth() == 0) {
            System.out.println("\n🎉 " + hero.getName() + " has defeated the " + enemy.getName() + "! 🎉");
            hero.gainExperience(enemy.getExperience());
            hero.addGold(enemy.getGold());
            System.out.println("🏆 You are victorious! You've slain the Dragon, Congrats on Beating the game 🏆");
            System.out.println("Thanks For Playing!");
            System.exit(0);
        }
    }
}
