package com.bptn.rpg.service;

import com.bptn.rpg.model.Inventory;
import com.bptn.rpg.model.character.Character;
import com.bptn.rpg.model.character.Enemy;
import com.bptn.rpg.model.character.Hero;
import com.bptn.rpg.model.item.Consumable;
import com.bptn.rpg.model.item.Item;
import com.bptn.rpg.model.item.ItemType;
import com.bptn.rpg.utils.InputUtil;
import com.bptn.rpg.utils.Messages;

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
                    
                    =========================
                       ⚔️ Battle start! ⚔️
                    =========================""");
            System.out.println("A " + enemy.getName() + " appears!\n");

        }

        boolean isHeroTurn = true;


        while (hero.isAlive() && enemy.isAlive()) {
            Character activeChar = isHeroTurn ? hero : enemy;

            Messages.turnStart(activeChar);

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
            endBattle(isFinalBattle);
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

            try {
                int choice = InputUtil.getInt("What's Your move: ");

                switch (choice) {
                    case 1 -> {
                        int damage = hero.attack(enemy);
                        Messages.attack(hero, enemy, damage, enemy.isDefending());
                        actionTaken = true;
                    }
                    case 2 -> {
                        hero.defend();
                        Messages.defend(hero);
                        actionTaken = true;
                    }
                    case 3 -> {
                        boolean usedItem = handleInventory();

                        if (usedItem) {
                            actionTaken = true;
                        }
                    }
                    case 4 -> {
                        hero.flee();
                        Messages.flee(hero);

                        if (hero.getIsFleeing()) {
                            enemy.setHealth(0);
                        }

                        actionTaken = true;
                    }
                    default -> Messages.invalidOption();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private void enemyTurn() {
        if (enemy.isDefending()) {
            enemy.setDefending(false);
        }

        boolean threshold = enemy.getHealth() <= (enemy.getMaxHealth() * 0.75);

        if (threshold && random.nextInt(100) < 30) {
            enemy.defend();
            Messages.defend(enemy);
        } else {
            int damage = enemy.attack(hero);
            Messages.attack(enemy, hero, damage, hero.isDefending());

        }
    }

    private void battleUI() {
        System.out.print(hero.getName() + "'s HP: " + hero.getHealth() + "/" + hero.getMaxHealth());
        System.out.println(" | " + enemy.getName() + "'s HP: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
        System.out.println("""
                --- Battle Menu ---
                 1) Attack
                 2) Defend
                 3) Inventory
                 4) Flee
                """);
    }

    private boolean handleInventory() {
        Inventory inventory = hero.getInventory();
        List<Item> consumables = inventory.getItemsByType(ItemType.CONSUMABLE);

        if (consumables.isEmpty()) {
            System.out.println("No potions in you inventory!");
            return false;
        }

        // Display items
        System.out.println("--- Items ---");
        StringBuilder sb = new StringBuilder();

        int index = 1;

        for (Item item : consumables) {
            sb.append(" ").append(index++).append(") ").append(item.getName()).append("\n");
        }
        sb.append(" 0) Back");
        System.out.println(sb);

        // Manage player choice
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                return false;
            }

            if (choice < 0 || choice > consumables.size()) {
                Messages.invalidOption();
                return false;
            }

            Item selectedItem = consumables.get(choice - 1);
            hero.useItem((Consumable) selectedItem, hero);
            inventory.removeItem(selectedItem);
            return true;

        } catch (InputMismatchException e) {
            Messages.invalidInput();
            scanner.nextLine();
            playerTurn();
        }

        return false;
    }

    private void endBattle(boolean isFinalBattle) {
        if (isFinalBattle && enemy.getHealth() == 0) {
            Messages.victory();
            System.exit(0);
        }

        if (!hero.isAlive()) {
            Messages.defeated(hero);
            System.exit(0);
        } else if (enemy.getHealth() == 0) {
            hero.gainExperience(enemy.getExperience());
            System.out.println(hero.getExperience());
            hero.addGold(enemy.getGold());
            Messages.enemyDefeated(hero, enemy);
        }
    }
}
