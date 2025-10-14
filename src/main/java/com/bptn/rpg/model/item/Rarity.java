package com.bptn.rpg.model.item;

public enum Rarity {
	COMMON("Common"), UNCOMMON("Uncommon"), RARE("Rare"), EPIC("Epic"), LEGENDARY("Legendary");

	private final String displayName;

	private Rarity(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return displayName;
	}
}
