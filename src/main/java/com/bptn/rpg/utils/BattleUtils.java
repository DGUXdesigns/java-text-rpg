package com.bptn.rpg.utils;

import com.bptn.rpg.model.character.Enemy;

import java.util.List;
import java.util.Random;

public class BattleUtils {
    private static final Random random = new Random();

    public static Enemy generateRandomMonster(int heroLevel) {
        List<Enemy> baseMonsters = List.of(
                new Enemy("Goblin", 30, 5, 1, 20, 10),
                new Enemy("Skeleton", 25, 7, 2, 25, 15),
                new Enemy("Wolf", 35, 6, 2, 30, 20),
                new Enemy("Orc", 50, 10, 3, 50, 40),
                new Enemy("Dark Mage", 40, 8, 4, 60, 50),
                new Enemy("Troll", 70, 12, 5, 80, 75),
                new Enemy("Giant Spider", 45, 9, 3, 55, 35),
                new Enemy("Slime", 20, 6, 2, 25, 20),
                new Enemy("Bandit", 35, 7, 2, 40, 30),
                new Enemy("Wyvern", 60, 15, 6, 100, 100)
        );

        Enemy base = baseMonsters.get(random.nextInt(baseMonsters.size()));

        int scaledHealth = base.getMaxHealth() + (heroLevel * random.nextInt(5, 11));
        int scaledStrength = base.getStrength() + (heroLevel * random.nextInt(5, 11));
        int scaledLevel = base.getLevel() + heroLevel / 2;
        int scaledXP = base.getExperience() + (heroLevel * random.nextInt(10, 21));
        int scaledGold = base.getGold() + (heroLevel * random.nextInt(15, 30));

        return new Enemy(base.getName(), scaledHealth, scaledStrength, scaledLevel, scaledXP, scaledGold);
    }
}
