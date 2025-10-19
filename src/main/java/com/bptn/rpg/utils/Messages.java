package com.bptn.rpg.utils;

import com.bptn.rpg.model.character.Character;

public class Messages {
    public static void attack(Character attacker, Character target, int damage, boolean isDefending) {
        System.out.println("\uD83D\uDDE1️ " + attacker.getName() + " attacks " + target.getName() + " for " + damage + " damage!\n");

        if (isDefending) {
            System.out.println("\uD83D\uDEE1 " + target.getName() + " was defending and reduced the damage to " + (damage / 2) + "!\n");
        }
    }

    public static void defend(Character defender) {
        System.out.println("\uD83D\uDEE1 " + defender.getName() + " takes a defensive stance and will take reduced damage next turn.\n");
    }

    public static void flee(Character hero) {
        if (hero.getIsFleeing()) {
            System.out.println("\uD83D\uDCA8 " + hero.getName() + " flees from battle!");
        } else {
            System.out.println("\uD83E\uDD1A " + hero.getName() + " tried to run but couldn't get away");
        }
    }

    public static void enemyDefeated(Character hero, Character enemy) {
        System.out.println(hero.getName() + " defeated " + enemy.getName() + "!\n");
        System.out.println(hero.getName() + " gained " + enemy.getExperience() + " experience points\n");
        System.out.println(enemy.getGold() + " gold added to your inventory. (Total Gold: " + hero.getGold() + ")\n");
    }

    public static void defeated(Character character) {
        System.out.println("--- 💀 GAME OVER 💀 ---");
        System.out.println(character.getName() + " was defeated!");
    }

    public static void victory() {
        System.out.println("🏆 You are victorious! You've slain the Dragon, Congrats on Beating the game 🏆");
        System.out.println("--- Thanks For Playing! ---");
    }

    public static void turnStart(Character character) {
        System.out.println(character.getName() + "'s turn\n");
    }

    public static void invalidOption() {
        System.out.println("Invalid option. Please choose from the available menu");
    }

    public static void invalidInput() {
        System.out.println("Invalid input. Please enter a number");
    }


}
