package com.bptn.rpg.model.character;

import com.bptn.rpg.model.item.Item;

public interface Commands {
	public void attack(Character target);

	public void defend();

	public void useItem(Item item, Character target);

	public boolean flee();
}
