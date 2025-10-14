package com.bptn.rpg.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private int gold;
	private List<Item> items;

	public Inventory() {
		this.items = new ArrayList<>();
		this.gold = 50;
	}

	public int getGold() {
		return gold;
	}

	public boolean addGold(int amount) {
		if (amount > 0) {
			gold += amount;
			return true;
		}

		return false;
	}

	public boolean removeGold(int amount) {
		if (amount > 0) {
			gold -= amount;
			return true;
		}

		return false;
	}

	public void addItem(Item item) {
		items.add(item);
		System.out.println(item.getName() + " added to inventory.");
	}

	public boolean removeItem(Item item) {
		boolean removed = items.remove(item);

		if (removed) {
			System.out.println(item.getName() + " removed from inventory");
		}

		return removed;
	}

	public List<Item> getItems() {
		return items;
	}

	public void printInventory() {
		int index = 1;

		if (items.isEmpty()) {
			System.out.println("Inventory is empty.");
		} else {
			System.out.println("--- Inventory ---");
			for (Item item : items) {
				System.out.println(index + ". " + item.getName());
				index++;
			}
		}
	}

}
