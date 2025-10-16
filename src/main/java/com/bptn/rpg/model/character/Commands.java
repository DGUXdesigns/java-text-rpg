package com.bptn.rpg.model.character;

import com.bptn.rpg.model.item.Consumable;

public interface Commands {
	public void attack(Character target);

	public void defend();

	public void useItem(Consumable item, Character target);

	public boolean flee();
}
