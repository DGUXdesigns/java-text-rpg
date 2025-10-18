package com.bptn.rpg.service;

import com.bptn.rpg.model.Inventory;
import com.bptn.rpg.model.character.Hero;
import com.bptn.rpg.model.item.Consumable;
import com.bptn.rpg.model.item.Item;
import com.bptn.rpg.model.item.ItemType;
import com.bptn.rpg.model.item.Weapon;


import java.util.Scanner;

public class InventoryMenu {
    private final Hero hero;
    private final Inventory inventory;
    private final Scanner scanner = new Scanner(System.in);

    public InventoryMenu(Hero hero) {
        this.hero = hero;
        this.inventory = hero.getInventory();
    }

    public void handleInventory() {
        boolean isOpen = true;

        while (isOpen) {
            System.out.println("Total Items: " + inventory.size());
            System.out.println(inventory.toString());
            System.out.println("0) Exit");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.next();
                continue;
            }

            int selection = scanner.nextInt();

            if (selection == 0) {
                isOpen = false;
            } else if (selection >= 1 || selection <= inventory.size()) {
                Item selectedItem = inventory.getItem(selection - 1);

                switch (selectedItem.getItemType()) {
                    case ItemType.WEAPON -> handleWeapon(selectedItem);
                    case ItemType.CONSUMABLE -> handleConsumable(selectedItem);
                    default -> System.out.println("Invalid choice, try again.");
                }
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void handleWeapon(Item weapon) {
        System.out.println("Do you want to equip " + weapon.getName() + "?");
        System.out.println("1) Yes");
        System.out.println("2) No");

        int choice = getValidatedChoice();

        if (choice == 1) {
            hero.equipWeapon((Weapon) weapon);
            System.out.println(weapon.getName() + " equipped!");
        } else {
            System.out.println("You decided not to equip it.");
        }
    }

    private void handleConsumable(Item consumable) {
        System.out.println("Do you want to use " + consumable.getName() + "?");
        System.out.println("1) Yes");
        System.out.println("2) No");

        int choice = getValidatedChoice();

        if (choice == 1) {
            System.out.println("You used " + consumable.getName() + "!");
            hero.useItem((Consumable) consumable, hero);
            inventory.removeItem(consumable);
        } else {
            System.out.println("You decided not to use it.");
        }
    }

    private int getValidatedChoice() {
        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number (1 or 2).");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            if (choice == 1 || choice == 2) {
                return choice;
            }

            System.out.println("Invalid choice. Please select 1 or 2.");
        }
    }
}
