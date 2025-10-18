package com.bptn.rpg.service;

import com.bptn.rpg.model.character.Enemy;
import com.bptn.rpg.model.character.Hero;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class BattleService {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private Hero hero;
    private Enemy enemy;

    public void startBattle(Hero hero, Enemy enemy) {
        this.hero = hero;
        this.enemy = enemy;

        // Initialize battle
        System.out.println("\n A " + enemy.getName() + " appears!");
        System.out.println("⚔️Battle start!\n");

        boolean isHeroTurn = true;

        while (hero.isAlive() && enemy.isAlive()) {
            if (isHeroTurn) {
                System.out.println(hero.getName() + "'s turn");
                playerTurn();
            } else {
                System.out.println(enemy.getName() + "'s turn");
                enemyTurn();
            }

            isHeroTurn = !isHeroTurn;
        }

        endBattle();
    }

    private void playerTurn() {
        int command = -1;

        while (command < 1 || command > 4) {
            battleUI();

            try {
                command = scanner.nextInt();
                scanner.hasNextLine(); // Consume leftover newline

                switch (command) {
                    case 1:
                        hero.attack(enemy);
                        break;
                    case 2:
                        hero.defend();
                        break;
                    case 3:
                        System.out.println("Feature still in the works");
                        break;
                    case 4:
                        hero.flee();
                        break;
                    default:
                        System.out.println("Invalid Option. Please enter a number between 1 and 4");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private void enemyTurn() {
        // AI logic for enemy actions
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

        System.out.print(hero.getName() + " HP: " + hero.getMaxHealth() + "/" + hero.getHealth());
        System.out.println(" | " + enemy.getName() + " HP: " + enemy.getHealth());
        System.out.println(message);
    }

    private void endBattle() {
        if (hero.isAlive()) {
            System.out.println(hero.getName() + " defeated " + enemy.getName() + "!");
            hero.gainExperience(enemy.getExperienceReward());
            hero.getInventory().addGold(enemy.getGoldReward());
        } else {
            System.out.println("\n " + hero.getName() + " was defeated...");
            System.out.println("💀 GAME OVER 💀");
            System.exit(0);
        }
    }
}
