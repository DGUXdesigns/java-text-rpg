package com.bptn.rpg.model.character;

import com.bptn.rpg.model.item.Consumable;

public class Enemy extends Character {
	public Enemy(String name, int health, int strength, int level) {
		super(name, health, strength);
	}

	@Override
	public void attack(Character target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void defend() {
		// TODO Auto-generated method stub

	}

	@Override
	public void useItem(Consumable item, Character target) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean flee() {
		// TODO Auto-generated method stub
		return false;
	}

}
