package com.bptn.rpg.model.shop;

import com.bptn.rpg.model.character.Hero;
import com.bptn.rpg.model.item.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    private Shop shop;
    private Hero hero;

    @BeforeEach
    void setUp() {
        shop = new Shop("Armory");
        hero = new Hero("Arthur", 500);
    }

    @Test
    void initializeStockTest() {
        assertNotNull(shop.getStock(), "Should initialize with items in stock");
    }

    @Test
    void buyItemTest() {
        Item item = shop.getStock().get(1); // Iron Sword
        Item item2 = shop.getStock().get(4); // Dragon Slayer
        int initialGold = hero.getGold();

        shop.buyItem(hero, item);

        assertTrue(hero.getInventory().getItems().contains(item), "Item should be added to hero's inventory when successfully purchased");
        assertEquals(initialGold - item.getPrice(), hero.getGold(), "Gold should be deducted correctly when successfully purchased");
        assertFalse(shop.getStock().contains(item), "Items Should be removed when purchased");

        shop.buyItem(hero, item2);

        assertFalse(hero.getInventory().getItems().contains(item2), "Item should NOT be added when there's not enough gold");
        assertEquals(initialGold - item.getPrice(), hero.getGold(), "Gold should remain unchanged if item not purchased");
        assertTrue(shop.getStock().contains(item2), "Items Should NOT be removed if not purchased");
    }

    @AfterEach
    void tearDown() {
        shop = null;
        hero = null;
    }
}