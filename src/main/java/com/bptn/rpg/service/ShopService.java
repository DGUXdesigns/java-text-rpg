package com.bptn.rpg.service;

import java.util.List;
import java.util.Scanner;

import com.bptn.rpg.model.Inventory;
import com.bptn.rpg.model.character.Hero;
import com.bptn.rpg.model.item.Item;
import com.bptn.rpg.model.shop.Shop;

public class ShopService {
	private Shop shop;
	private Scanner scanner;

	public ShopService(Scanner scanner) {
		this.shop = new Shop("Hero Shop");
		this.scanner = scanner;
	}

	public void open(Hero hero) {
		boolean running = true;
		Inventory bag = hero.getInventory();
		StringBuilder sb = new StringBuilder();

		sb.append("=================\n");
		sb.append("--- ").append(shop.getName()).append(" ---\n");
		sb.append("=================\n");

		while (running) {
			System.out.println(sb.toString());
			System.out.println("Gold: " + bag.getGold() + "\n");
			System.out.println(shop.toString());
			System.out.println("0. Leave the Shop");

			int choice = scanner.nextInt();

			if (choice == 0) {
				running = false;
			} else {
				List<Item> stock = shop.getStock();

				shop.buyItem(bag, stock.get(choice - 1));
			}

		}
	}
}
