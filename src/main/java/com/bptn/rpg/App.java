package com.bptn.rpg;

import java.util.Scanner;

import com.bptn.rpg.model.character.Hero;
import com.bptn.rpg.service.ShopService;

public class App {
	public static void main(String[] args) {
		// Get Hero Name
		Scanner scanner = new Scanner(System.in);
		System.out.println("===========================");
		System.out.println("⚔️ What is your name Hero?");
		System.out.println("===========================");

		String heroName = scanner.next();

		// Create Hero
		Hero hero = new Hero(heroName, 100, 50);

		boolean gameRunning = true;

		while (gameRunning) {
			System.out.println("Where to?");

			String[] options = { "Shop", "Training Dungeon", "Slay The Dragon" };
			int index = 1;

			for (String option : options) {
				System.out.println(index++ + ". " + option);
			}

			System.out.println("0. Exit the game");
			int choice = scanner.nextInt();

			if (choice == 0) {
				gameRunning = false;
				System.out.println("Thanks For Playing!");
			} else if (choice == 1) {
				ShopService shop = new ShopService(scanner);
				shop.open(hero);
			} else {
				System.out.println("Feature in the works...");
			}
		}

	}
}
