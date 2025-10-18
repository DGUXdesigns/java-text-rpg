package com.bptn.rpg.model;

import java.util.ArrayList;
import java.util.List;

import com.bptn.rpg.model.item.Item;
import com.bptn.rpg.model.item.ItemType;

public class Inventory {
    private final List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Item> getItemsByType(ItemType type) {
        return items.stream()
                .filter(item -> item.getItemType() == type)
                .toList();
    }

    public void addItem(Item item) {
        items.add(item);
        System.out.println(item.getName() + " added to inventory.");
    }

    public boolean removeItem(Item item) {
        //TODO: implement into useItem method
        boolean removed = items.remove(item);

        if (removed) {
            System.out.println(item.getName() + " removed from inventory");
        }

        return removed;
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
                sb.append(index).append(". ").append(item.toString()).append("\n");
                index++;
            }
        }

        return sb.toString();
    }

}
