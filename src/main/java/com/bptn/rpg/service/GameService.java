package com.bptn.rpg.service;

import com.bptn.rpg.model.character.Hero;

import java.util.Scanner;

public class GameService {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create Hero
        Hero hero = new Hero(getHeroName(scanner), 100);
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
}
