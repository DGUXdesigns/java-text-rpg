package com.bptn.rpg.model.character;

import com.bptn.rpg.model.item.Consumable;

public interface Commands {
    int attack(Character target);

    void defend();

    void useItem(Consumable item, Character target);

    void flee();
}
