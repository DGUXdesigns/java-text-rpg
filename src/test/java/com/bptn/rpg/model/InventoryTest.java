package com.bptn.rpg.model;

import com.bptn.rpg.model.item.Item;
import com.bptn.rpg.model.item.ItemType;
import com.bptn.rpg.model.item.Rarity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private Inventory inventory;
    private Item sword;
    private Item potion;
    private Item claymore;
    ;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();

        sword = new Item("Sword", ItemType.WEAPON, Rarity.COMMON, 100) {
            @Override
            public String toString() {
                return "Sword (Weapon)";
            }
        };

        claymore = new Item("Claymore", ItemType.WEAPON, Rarity.EPIC, 200);

        potion = new Item("Potion", ItemType.CONSUMABLE, Rarity.COMMON, 50) {
            @Override
            public String toString() {
                return "Potion (Consumable)";
            }
        };

        inventory.addItem(sword);
        inventory.addItem(potion);
    }

    @Test
    void testGetItemsByType() {
        List<Item> weapons = inventory.getItemsByType(ItemType.WEAPON);

        assertEquals(1, weapons.size(), "Should return one weapon item");
        assertEquals(sword, weapons.getFirst());

        List<Item> consumables = inventory.getItemsByType(ItemType.CONSUMABLE);

        assertEquals(1, consumables.size(), "Should return one consumable item");
        assertEquals(potion, consumables.getFirst());
    }

    @Test
    void testSize() {
        assertEquals(2, inventory.size(), "Inventory should contain 2 items");
    }

    @Test
    void testGetItem() {
        assertEquals(sword, inventory.getItem(0), "First item should be Sword");
        assertEquals(potion, inventory.getItem(1), "Second item should be Potion");
        assertNull(inventory.getItem(-1), "Negative index should return null");
        assertNull(inventory.getItem(10), "Out-of-range index should return null");
    }

    @Test
    void testAddItem() {
        Item shield = new Item("Claymore", ItemType.WEAPON, Rarity.EPIC, 200);
        inventory.addItem(claymore);

        assertTrue(inventory.getItemsByType(ItemType.WEAPON).contains(claymore),
                "Newly added claymore should be in inventory");
        assertEquals(3, inventory.size(), "Size should increase after adding item");
    }

    @Test
    void testRemoveItem() {
        inventory.removeItem(potion);

        assertEquals(1, inventory.size(), "Size should decrease after removing item");
        assertFalse(inventory.getItemsByType(ItemType.CONSUMABLE).contains(potion),
                "Potion should no longer exist in consumables list");

        // Try removing something not in inventory
        Item fake = new Item("Fake", ItemType.CONSUMABLE, Rarity.UNCOMMON, 100);
        inventory.removeItem(fake);
        assertEquals(1, inventory.size(), "Removing non-existent item shouldn't change size");
    }

    @Test
    void testToString() {
        String result = inventory.toString();

        assertTrue(result.contains("Inventory"), "toString should include 'Inventory'");
        assertTrue(result.contains("Sword"), "toString should list Sword");
        assertTrue(result.contains("Potion"), "toString should list Potion");

        // Empty inventory test
        Inventory emptyInventory = new Inventory();
        String emptyResult = emptyInventory.toString();
        assertTrue(emptyResult.contains("Inventory is empty"), "Empty inventory should display 'Inventory is Empty'");
    }

    @AfterEach
    void tearDown() {
        inventory = null;
        sword = null;
        potion = null;
        claymore = null;
    }
}