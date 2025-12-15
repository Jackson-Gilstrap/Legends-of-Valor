# LEGENDS
## 🛡️ Legends of Valor
## 🛡️ Heros And Monsters
### Final Project & Presentation

## Team Members
- **Jackson Gilstrap**
- **Ruoxie Cao**
- **Ansh Sindhwani**

---

## 🎮 Overview

### Monsters and heros 
The monsters and heroes live in a fictional world. They do not get along and therefore fight each other.
Every time the heroes win, they gain experience and money. Heroes use the money to buy a variety of
items to aid them in their battles with the monsters. When they accumulate enough experience, they
level up, which improves their skills. The goal of the game is for the heroes to defeat monsters and level
up indefinitely.

#### Legends of Valor.
This time the heroes battle monsters in a contest of strategy and skill. Take advantage of the terrain, coordinate actions between heroes, and use items to outwit and outfight the invading waves of monsters. Can your heroes destroy the monsters’ Nexus and stop the monster invasion? Or will the monsters overrun your own fortress?
From the world of Monsters vs Heroes we land in the battle arena of Valor. 
After the Heroes conquered the monsters a century ago, they tamed the monsters. 
The Valorsseum was built and yearly games began to please the citizens and kings. 
The Monsters pitted against Heroes to the death in a tactical battle arena with three lanes with terrain that changes providing special effects to those who stand on the space.
The Games end when either party Heroes or Monsters reach the opposing sides camp. Thus resulting in the execution of the remaining foes.

### 🎯 Goal of the Game
- Defeat monsters  
- Gain experience and gold  
- Buy items and improve stats  
- Level up 
- Destroy the enemy Nexus before yours is destroyed  

---

## 🧙 Heroes & Monsters Description 

### Hero Types

Each hero belongs to a class with different strengths:

#### **Warriors**
- High damage  
- High agility  
- Front-line fighters  

#### **Paladins**
- High defense  
- Balanced agility  
- Durable protectors  

#### **Sorcerers**
- High dexterity  
- High agility  
- Powerful spell users  

Each hero grows stronger by leveling up and equipping **weapons, armor, spells, and potions**.

---
## Compile & Run 
- From project root: `mkdir -p bin && javac -d bin $(find src -name "*.java")`
- Launch: `java -cp bin Main`

---

## 🗺️ Gameplay (High-Level)

*(Detailed gameplay walkthrough to be added by Jackson)*

At a high level:
- Players select a game mode from the Legends menu  
- Heroes move across the battlefield using directional commands  
- Terrain tiles provide special effects (buffs, restrictions, markets, Nexus)  
- Players take turns issuing commands:
  - Move  
  - Attack  
  - Cast spells  
  - Use items  
  - Enter markets  
- Strategy, positioning, and timing determine victory  

---

## 🧠 Design Philosophy

We designed **Legends of Valor** with three main goals:

- **Clarity** – Easy to understand game flow  
- **Extensibility** – Easy to add new heroes, monsters, items, or commands  
- **Separation of Concerns** – Game logic, input handling, and display are kept separate  

This makes the system easier to **maintain, test, and extend**.

---

## 🏗️ Major Design Choices  

---

### 🧱 Builder + Factory Pattern  
*(Creating Heroes, Monsters, and Items)*

#### Plain-Language Explanation
Heroes, monsters, weapons, spells, and potions all have many properties (stats, levels, prices, abilities).  
Instead of using massive constructors with many parameters, we build objects step-by-step and let factories decide what to build.

This makes object creation:
- Easier to read  
- Less error-prone  
- Easy to extend  

#### Technical Details

**Builders (`src/Builders/*.java`)**
- Provide fluent setters (`setAttack()`, `setDefense()`, etc.)
- End with `build()` or `buildWeapon()` / `buildHero()`
- Examples:
  - `HeroBuilder`
  - `WeaponBuilder`
  - `MonsterBuilder`
  - `StatsBuilder`

**Factories (`src/Factories/*.java`)**
- Decide which concrete type to build
- Use builders internally
- Examples:
  - `WarriorFactory`, `PaladinFactory`, `SorcererFactory`
  - `DragonFactory`, `SpiritFactory`, `ExoskeletonFactory`
  - `WeaponFactory`, `ArmorFactory`, `PotionFactory`, `SpellFactory`

**Seeders**
- Load CSV or predefined data
- Populate objects using factories
- Keep file parsing completely separate from game logic

---

### 🎮 Command Pattern  
*(Handling Player Input Cleanly)*

#### Plain-Language Explanation
Instead of writing one giant `if/else` or `switch` statement for player input, each action is its own object.

This means:
- Adding a new command does **not** require modifying the game loop  
- Commands are easy to test  
- Input handling stays clean and readable  

#### Technical Details

**Command Interface**
- Defines:
  - `execute()`
  - `name()`

**Concrete Commands**
- `MoveCommand`
- `AttackCommand`
- `TeleportCommand`
- `RecallCommand`
- `EnterMarketCommand`
- `InfoCommand`
- `QuitCommand`

**InputHandler**
- Registers commands once
- Maps keys like `W/A/S/D`, `ATK`, `TP`, `R`, `M`, `I`, `Q`
- Executes the correct command without knowing its logic

Battle actions such as:
- `AttackWithWeaponAction`
- `CastSpellAction`
- `UsePotionAction`  
also follow the same command philosophy.

---

### 🧭 Movement Controller Abstraction  
*(Separating Movement from Game Logic)*

#### Plain-Language Explanation
Movement rules should not be mixed with combat logic or world logic.  
We created dedicated movement controllers to handle how characters move on the map.

This allows:
- Different games to reuse movement logic  
- Cleaner battle and game controllers  
- Easy changes to movement rules  

#### Technical Details

**MovementController (abstract class)**
- Holds reference to the map and moving entity
- Defines `move()` logic skeleton
- Uses hooks like:
  - `onOutOfBounds()`
  - `onBlocked()`

**Concrete Implementations**
- `LVMovementController`
- `MVHMovementController`

---

### 🧩 Template Method Pattern for Map Spaces  
*(Terrain Effects & Map Behavior)*

#### Plain-Language Explanation
All map tiles behave similarly (enter, stand, leave), but each tile has special effects.

Instead of rewriting the same logic, we define a general structure once and let each tile customize its behavior.

#### Technical Details

**Base Class**
- `WorldSets.Space`
  - Defines shared algorithm for entering, leaving, and rendering
  - Provides overridable hooks:
    - `canEnter()`
    - `onEnter()`
    - `onLeave()`
    - `colorForSpace()`

**Concrete Spaces**
- `BushSpace`
- `CaveSpace`
- `KoulouSpace`
- `MarketSpace`
- `NexusSpace`

This is a classic **Template Method** pattern.

---

### 🔌 Adapter Pattern  
*(PartyPositionAdapter)*

#### Plain-Language Explanation
Sometimes two parts of the system expect different interfaces.  
Rather than rewriting everything, we add a small adapter that translates between them.

#### Technical Details
- `PartyPositionAdapter`
  - Makes the party appear as a `Positionable`
  - Allows reuse of `MovementController`
  - Avoids forcing `World` or `Party` to implement extra interfaces

---

### 🛒 Market System (MVC-Inspired)

#### Plain-Language Explanation
Markets are a major feature and deserve clean separation:
- **Data** (items)
- **Logic** (buy/sell rules)
- **Display** (how items are shown)

This makes markets easy to expand and customize.

#### Technical Details

**Model**
- `Market`
- Inventory of weapons, armor, spells, and potions

**Controllers**
- `MarketController` – handles buying/selling rules
- `MarketInteractionController` – manages entering/leaving markets

**View Strategy**
- `MarketDisplayStrategy`
- Example: `FilteredMarketDisplay`
- Allows swapping display logic without changing controllers

**World Integration**
- `MarketSpace` and `NexusSpace` embed markets
- Triggered automatically when heroes enter
* * *

### ⚔️ Decoupling Battle Logic (Experimental Branch)

In an experimental branch, we explored a more modular and extensible battle architecture by fully decoupling combat rules, execution, and presentation. The goal was to ensure that changes to combat mechanics—such as adding new actions, tweaking damage formulas, or introducing new side effects—would not require modifications to the core battle loop or controllers.

Battle behavior is split into **three independent layers**. First, **Actions** follow the Command pattern: each hero action (for example, `AttackWithWeaponAction`, `CastSpellAction`, or `UsePotionAction`) implements a `BattleAction` interface and encapsulates its own preconditions and execution logic. The battle controller simply executes an action without knowing how it works internally, making new actions easy to add without touching existing flow control.

Second, **Strategies** isolate all combat calculations. Damage and hit logic are delegated to pluggable strategies such as `HeroDamageStrategy`, `WeaponDamageStrategy`, `SpellDamageStrategy`, `HeroHitChanceStrategy`, and their monster counterparts. This keeps combat math out of controllers and models, allows rules to be swapped or rebalanced easily, and makes the system more testable.

Third, **Services** handle cross-cutting battle responsibilities that do not belong to individual actions. Components such as `RewardDistributor` and `RegenerationService` manage experience gain, gold rewards, and post-turn regeneration. This prevents controllers from mixing orchestration logic with bookkeeping and side effects.

These layers are wired together through `Battle`, `BattleActionPresenter`, and a `BattleView` (CLI). The controller manages turn flow, the presenter gathers player input, the view handles output, actions execute independently, strategies compute outcomes, and services apply side effects. Each concern is isolated and replaceable, illustrating a clean combination of **Command, Strategy, and service-based layering** for scalable and maintainable battle systems.
