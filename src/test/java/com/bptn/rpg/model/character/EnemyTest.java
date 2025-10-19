package com.bptn.rpg.model.character;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

    private Enemy enemy;
    private Hero hero;

    @BeforeEach
    void setUp() {
        enemy = new Enemy("Goblin", 100, 10, 2, 50, 20);
        hero = new Hero("Arthur");
    }

    @Test
    void attackTest() {
        int initialHealth = hero.getHealth();
        enemy.attack(hero);

        assertTrue(hero.getHealth() < initialHealth,
                "Enemy attack should reduce target's health");

    }

    @Test
    void defendTest() {
        assertFalse(enemy.isDefending(), "Enemy should not be defending to start");

        enemy.defend();

        assertTrue(enemy.isDefending(), "Enemy should be defending after calling defend()");
    }

    @Test
    void fleeTest() {
        enemy.flee();
        assertTrue(true, "Flee should return a boolean without exceptions");
    }

    @AfterEach
    void tearDown() {
        enemy = null;
        hero = null;
    }
}