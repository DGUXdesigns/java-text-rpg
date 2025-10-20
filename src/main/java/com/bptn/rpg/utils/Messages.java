package com.bptn.rpg.utils;

import com.bptn.rpg.model.character.Character;
import com.bptn.rpg.model.item.Item;

public class Messages {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static void attack(Character attacker, Character target, int damage, boolean isDefending) {
        System.out.println(RED + "\uD83D\uDDE1️ " + attacker.getName() + " attacks " + target.getName() + " for " + damage + " damage!\n" + RESET);

        if (isDefending) {
            System.out.println(BLUE + "\uD83D\uDEE1 " + target.getName() + " was defending and reduced the damage to " + (damage / 2) + "!\n" + RESET);
        }
    }

    public static void defend(Character defender) {
        System.out.println(BLUE + "\uD83D\uDEE1 " + defender.getName() + " takes a defensive stance and will take reduced damage next turn.\n" + RESET);
    }

    public static void flee(Character hero) {
        if (hero.getIsFleeing()) {
            System.out.println(YELLOW + "\uD83D\uDCA8 " + hero.getName() + " flees from battle!\n" + RESET);
        } else {
            System.out.println(YELLOW + "\uD83E\uDD1A " + hero.getName() + " tried to run but couldn't get away\n" + RESET);
        }
    }

    public static void enemyDefeated(Character hero, Character enemy) {
        System.out.println(GREEN + hero.getName() + " defeated " + enemy.getName() + "!\n");
        System.out.println("✨ " + hero.getName() + " gained " + enemy.getExperience() + " experience points\n");
        System.out.println("\uD83E\uDE99 " + enemy.getGold() + " gold added to your inventory. (\uD83D\uDCB0 Total Gold: " + hero.getGold() + ")\n" + RESET);
    }

    public static void defeated(Character character) {
        System.out.println(PURPLE + "--- 💀 GAME OVER 💀 ---");
        System.out.println(character.getName() + " was defeated!" + RESET);
    }

    public static void victory() {
        System.out.println(GREEN + "🏆 You are victorious! You've slain the Dragon, Congrats on Beating the game 🏆");
        System.out.println("--- Thanks For Playing! ---" + RESET);
    }

    public static void turnStart(Character character) {
        System.out.println(CYAN + character.getName() + "'s turn\n" + RESET);
    }

    public static void invalidOption() {
        System.out.println(RED + "Invalid option. Please choose from the available menu" + RESET);
    }

    public static void invalidInput() {
        System.out.println(RED + "Invalid input. Please enter a number" + RESET);
    }

    public static void emptyInput() {
        System.out.println(RED + "Please enter an option" + RESET);
    }

    public static void purchaseSuccess(Item item) {
        System.out.println(GREEN + "Purchased " + item.getName() + " for " + item.getPrice() + " gold" + RESET);
    }
}
