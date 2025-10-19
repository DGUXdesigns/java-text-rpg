package com.bptn.rpg.model.character;

import com.bptn.rpg.model.item.Consumable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCharacter extends Character {
    public TestCharacter(String name, int level, int experience, int gold, int maxHealth, int strength) {
        super(name, level, experience, gold, maxHealth, strength);
    }

    @Override
    public int attack(Character target) {
        return 0;
    }

    @Override
    public void defend() {

    }

    @Override
    public void useItem(Consumable item, Character target) {

    }

    @Override
    public void flee() {

    }
}

class CharacterTest {

    private TestCharacter character;

    @BeforeEach
    void setUp() {
        character = new TestCharacter("Hero", 1, 0, 100, 100, 20);
    }

    @Test
    void testTakeDamage() {
        character.takeDamage(40);

        assertEquals(60, character.getHealth(), "Character should take 40 damage and have 60 health remaining");
        assertTrue(character.isAlive(), "Character should still be alive when health is more than 0");

        character.takeDamage(70);

        assertEquals(0, character.getHealth(), "Health should not drop below 0");
        assertFalse(character.isAlive(), "Character should be dead when health is 0");
    }

    @Test
    void testIsDead() {
        character.takeDamage(120);

        assertEquals(0, character.getHealth(), "Health should not drop below 0");
        assertFalse(character.isAlive(), "Character should be dead when health is 0");
    }

    @Test
    void testTakeDamageWhileDefending() {
        character.setDefending(true);
        character.takeDamage(40);

        assertEquals(80, character.getHealth(), "Damage should be reduced by 50%");
    }

    @Test
    void testAddStrength() {
        character.addStrength(10);

        assertEquals(30, character.getStrength(), "Should add 10 to max health");
    }

    @Test
    void testAddMaxHealth() {
        character.addMaxHealth(50);

        assertEquals(150, character.getMaxHealth(), "Should add 50 to strength");

    }

    @Test
    void testAddGold() {
        character.addGold(50);
        assertEquals(150, character.getGold(), "Should add 50 gold");
    }

    @Test
    void testRemoveGoldSuccess() {
        boolean result = character.removeGold(50);

        assertTrue(result);
        assertEquals(50, character.getGold(), "Should remove 50 gold");
    }

    @Test
    void testRemoveGoldFail() {
        boolean result = character.removeGold(200);

        assertFalse(result, "Should fail to get gold");
        assertEquals(100, character.getGold(), "amount should remain unchanged"); // unchanged
    }

    @Test
    void testGainExperienceAndLevelUp() {
        character.gainExperience(100);

        assertEquals(100, character.getExperience(), "Should gain 100 experience");
        assertEquals(2, character.getLevel(), "Should level Up once every 100 experience");
    }

    @Test
    void testMultipleLevelUps() {
        character.gainExperience(700);

        assertEquals(8, character.getLevel(), "Should level up multiple times");
    }

    @AfterEach
    void tearDown() {
        character = null;
    }
}