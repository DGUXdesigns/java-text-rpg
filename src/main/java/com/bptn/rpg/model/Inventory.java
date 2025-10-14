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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		if (items.isEmpty()) {
			sb.append("Inventory is empty.");
		} else {
			sb.append("--- Inventory ---\n");
			int index = 1;
			for (Item item : items) {
				sb.append(index).append(". ").append(item.getName()).append("\n");
				index++;
			}
		}

		return sb.toString();
	}

}
