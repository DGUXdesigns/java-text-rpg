package com.bptn.rpg.model.character;

import com.bptn.rpg.model.item.Consumable;
import com.bptn.rpg.model.item.Rarity;
import com.bptn.rpg.model.item.Weapon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    private Hero hero;
    private Weapon ironSword;
    private Enemy enemy;

    @BeforeEach
    void setUp() {
        hero = new Hero("Arthur");
        ironSword = new Weapon("Iron Sword", Rarity.UNCOMMON, 80, 12);
        enemy = new Enemy("Goblin", 100, 50, 1, 50, 50);
    }

    @Test
    void starterWeaponTest() {
        assertNotNull(hero.getWeapon(), "Hero should have a starter weapon");
    }

    @Test
    void equipWeapon() {
        hero.equipWeapon(ironSword);

        assertFalse(hero.getInventory().getItems().isEmpty(), "Old weapon should be added to inventory");
        assertEquals(hero.getWeapon(), ironSword, "New Weapon should be equipped");
    }

    @Test
    void levelUp() {
        hero.levelUp();

        assertEquals(2, hero.getLevel(), "Hero should gain 1 level");
        assertEquals(145, hero.getMaxHealth(), "Hero max health should increase by 25");
        assertEquals(15, hero.getStrength(), "Hero strength should increase by 5");
    }

    @Test
    void getIsFleeing() {
        assertFalse(hero.getIsFleeing(), "Hero should not start out fleeing");
    }

    @Test
    void setIsFleeing() {
        hero.setIsFleeing(true);
        assertTrue(hero.getIsFleeing(), "Hero fleeing status should update correctly");
    }

    @Test
    void attack() {
        int enemyOldHealth = enemy.getHealth();
        hero.attack(enemy);

        assertTrue(enemy.getHealth() < enemyOldHealth, "Enemy should take damage from attack");
    }

    @Test
    void useItem() {
        hero.setHealth(100);
        Consumable potion = new Consumable("Health Potion", Rarity.COMMON, 0, 50);

        hero.useItem(potion, hero);

        assertTrue(hero.getHealth() > 50, "Hero's health should increase after using potion");
        assertTrue(hero.getHealth() <= hero.getMaxHealth(), "Hero's health should not exceed max health");
    }

    @Test
    void flee() {
        boolean result = hero.flee();
        
        if (result) {
            assertTrue(hero.getIsFleeing(), "Hero should be fleeing when successful");
        } else {
            assertFalse(hero.getIsFleeing(), "Hero should not be fleeing when failed");
        }
    }

    @AfterEach
    void tearDown() {
        hero = null;
        ironSword = null;
        enemy = null;
    }
}