# Java Text RPG

A text-based RPG adventure game built in Java where you embark on a quest to defeat a mighty dragon! Train your hero, collect weapons, manage your inventory, and engage in strategic turn-based battles.

## 🎮 Features

- **Character Progression**: 
  - Start as a novice hero and level up through combat, gaining improved stats and abilities


- **Combat System**: 
  - Turn-based battle system with multiple action options:
    - Attack enemies with your equipped weapon
    - Defend to reduce incoming damage
    - Use items from your inventory
    - Strategic option to flee from tough battles


- **Inventory Management**: 
  - Collect and manage various items:
    - Weapons of different rarities
    - Consumable items for healing
    - Equipment system for weapons


- **Shopping System**: 
  - Visit the shop to:
    - Purchase new weapons
    - Buy healing potions
    - Spend gold earned from battles


- **Multiple Game Modes**:
  - Training Dungeon: Fight random monsters to gain experience
  - Final Boss Battle: Challenge the dragon when you're ready
  - Shop & Inventory management between battles

## 🚀 Getting Started

### Prerequisites

- Java JDK 8 or higher
- Maven

### Installation

1. Clone the repository:
```bash
git clone https://github.com/DGUXdesigns/java-text-rpg.git
```

2. Navigate to the project directory:
```bash
cd java-text-rpg
```

3. Build the project with Maven:
```bash
mvn clean install
```

4. Run the game:
```bash
mvn exec:java -Dexec.mainClass="com.bptn.rpg.App"
```

## 🎯 How to Play

1. **Start the Game**
   - Enter your hero's name when prompted
   - Your hero starts with basic stats and a wooden sword


2. **Main Menu Options**
   - Shop: Purchase items and equipment
   - Inventory: Manage your items and equipment
   - Training Dungeon: Fight monsters to gain experience
   - Slay The Dragon: Challenge the final boss


3. **Combat System**
   - Choose your actions wisely in turn-based combat
   - Actions include: Attack, Defend, Use Item, or Flee
   - Manage your HP and use items strategically


4. **Character Development**
   - Gain experience points by defeating enemies
   - Level up to increase your HP and Strength
   - Purchase better weapons to increase your damage output

## 🎨 Game Features

### Combat
- Dynamic damage calculation based on weapon stats and character strength
- Random damage variation for unpredictable battles
- Defense system to reduce incoming damage
- Level-based enemy scaling

### Items and Equipment
- Multiple weapon types with varying damage values
- Different item rarities (Common, Uncommon, Rare, Epic)
- Consumable items for healing during battle

### Progression
- Experience-based leveling system
- Gold economy for purchasing items
- Increasingly challenging enemies as you level up

## 🛠️ Technical Details

- Built in Java
- Implements object-oriented programming principles
- Uses design patterns for game systems
- Includes unit tests for game components

## 🧪 Testing

Run the unit tests with:
```bash
mvn test
```