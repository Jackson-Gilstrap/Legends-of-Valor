# CS611-Assignment 4 
## Monsters vs Heroes
---------------------------------------------------------------------------
- Name: Jackson Gilstrap
- Email: JaxGils@bu.edu
- Student ID: U43908042

## Files
---------------------------------------------------------------------------

This section should be all of the source code files that have a .java extension. You should also include a brief description of what the class does.
Main.java - runs the game

***World***
Tile.java - Abstract class of a tile to allow representation of different spaces
**TileTypes**
BlockingTile/MarketTile/CommonTile.java - Concrete implementation of the different spaces
TileMap.java - represents the map of the game in charge of building the map, special functions for actions on the map like fast travel, moving the party, and rendering
Market.java - implementation of the markets in the game

***Utility**
Wallet.java - A hero's wallet holds gold and provides methods to add and extract gold
Stats.java - 
Level.java - A entities level represenation some useful methods to modify the levels on entities and items
Jacket.java - Where the equipement (weapons, armor, spells, potions) lives once equipped in slots
Inventory.java - The heroes inventory where any item lives not equipped
**slots**
itemSlot.java - abstract implementation of a slot for an item
Armor/Weapon/Spell/PotionSlot.java - concrete implemtations of slots
**seeders**
ItemSeeder - Seeds the items from the text files into lists
EntitySeeder - Seeds the entities from the text files into lists
**parties**
Party - represents the player party
MonsterParty - represents a monster party
**items**
weapons - Concrete implementation of weapons
potions - Concrete implementation of potions
armors - Concrete implementation of armors
spells -Concrete implementation of spells
**armors**
Leggings - type of armor fits in leggings slot
Chestplate - type of armor fits in chestplate slot
Helmet - type of armor fits in helmet slots
**game**
inputhandler -  handle the out of battle inputs
gameUI - scanner wrapper with two methods ask int and ask one word
gameController -  the game loop 
battle -  responsible for a single battle
**filereader**
filereaderutility - reads from a txt file
**Factories**
Factories(Items/Entities) *10  - responsibile for creating the item/
**Entities**
Entity -  Abstract implemtnation of an entity
Monsters(monster(abstract),dragon, exo, spirit) - concreate implementations
Heroes(hero(abstract), warrior, paladin, sorcerer) - concreate implementations
**Builders**
Hero/monster - reponsible for defining the members and setters and a build method
Weapon,Spell,Potion,Armor(helmet, leggings, chestplates) - defining the members and setters and a build method





## Notes
---------------------------------------------------------------------------
Please explain the cool features of your program. Anything that you feel like you did a good job at or were creative about, explain it in bullets here. Additionally, any design decisions should be made here.
Made battles all heroes vs all Monsters
Added fast travel between markets
Heroes have an equipment jacket that will pass on buffs to the hero for all items equipped

I decided to modify some of the stats and combine them to simplfy battles strength is **attack** physical with any type of weapons. 
Dexterity is damage for **spells** , spells can also be used as much as possible (learn a spell once use as much as possible)

You can buy any item from the market (provided you have gold) but you won't be able to equip it.




## How to compile and run
---------------------------------------------------------------------------
Your directions on how to run the code. Make sure to be as thorough as possible!
1. Clone from the github 

2.run in your ide of choice with the run command on your machine
or 
2.javac *.java
3.java Main



## Input/Output Example
---------------------------------------------------------------------------
Please give us a full execution of what we should see on the screen. Label each text with input and output. For example:

```
    /Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=56929 -Dfile.encoding=UTF-8 -classpath /Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/ext/jaccess.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre/lib/rt.jar:/Users/jax/projects/grad_oop_611/Heroes and Monsters/out/production/Heroes and Monsters Main
Welcome to the world of Monsters vs Heros

The aim of the game is to take you party of heroes and battle monsters along your journey
On your journey you will encounter various Dragons, ExoSkeletons, and Spirits
The goal... Survive as long as possible



Good luck...

MAX PARTY SIZE = 3

--- HERO SELECTION MENU ---

0. Start Game	1. Add a warrior	2. Add a Paladin	3.Add a Sorcerer 
Select: 1

Choose a hero to add to your party:
1. Clyde Stonebreaker (Lvl 1) | HP:260 MP:120 | ATK:72 DEX:42 | AGI:0.15 DR:0.05 | XP:0/10 | Gold:0
2. Garruk Ironfist (Lvl 1) | HP:290 MP:110 | ATK:82 DEX:36 | AGI:0.11 DR:0.04 | XP:0/10 | Gold:0
3. Rogan Bloodhelm (Lvl 1) | HP:270 MP:115 | ATK:78 DEX:40 | AGI:0.14 DR:0.04 | XP:0/10 | Gold:0
4. Duran Steelsoul (Lvl 1) | HP:260 MP:135 | ATK:68 DEX:46 | AGI:0.16 DR:0.07 | XP:0/10 | Gold:0
5. Thorn Shieldbearer (Lvl 1) | HP:300 MP:100 | ATK:70 DEX:34 | AGI:0.09 DR:0.08 | XP:0/10 | Gold:0
6. Brutus Stormcaller (Lvl 1) | HP:245 MP:145 | ATK:65 DEX:50 | AGI:0.18 DR:0.03 | XP:0/10 | Gold:0

Enter choice (1 - 6): 3

Rogan Bloodhelm has been added to the party


--- HERO SELECTION MENU ---

0. Start Game	1. Add a warrior	2. Add a Paladin	3.Add a Sorcerer 
Select: 2

Choose a hero to add to your party:
1. Alyndra Lightward (Lvl 1) | HP:220 MP:60 | ATK:60 DEX:55 | AGI:0.16 DR:0.06 | XP:0/10 | Gold:0
2. Theron Pureheart (Lvl 1) | HP:240 MP:70 | ATK:70 DEX:48 | AGI:0.14 DR:0.04 | XP:0/10 | Gold:0
3. Mariel Dawnguard (Lvl 1) | HP:210 MP:52 | ATK:52 DEX:62 | AGI:0.13 DR:0.03 | XP:0/10 | Gold:0
4. Lorian Brightshield (Lvl 1) | HP:230 MP:66 | ATK:66 DEX:50 | AGI:0.20 DR:0.04 | XP:0/10 | Gold:0
5. Seraphine Holyedge (Lvl 1) | HP:215 MP:50 | ATK:50 DEX:60 | AGI:0.12 DR:0.07 | XP:0/10 | Gold:0
6. Galen Faithbinder (Lvl 1) | HP:250 MP:68 | ATK:68 DEX:47 | AGI:0.11 DR:0.08 | XP:0/10 | Gold:0

Enter choice (1 - 6): 2

Theron Pureheart has been added to the party


--- HERO SELECTION MENU ---

0. Start Game	1. Add a warrior	2. Add a Paladin	3.Add a Sorcerer 
Select: 0

===== PARTY INFORMATION =====
Party Slot 1:
Rogan Bloodhelm (Lvl 1) | HP:270 MP:115 | ATK:78 DEX:40 | AGI:0.14 DR:0.04 | XP:0/10 | Gold:0
-----------------------------
Party Slot 2:
Theron Pureheart (Lvl 1) | HP:240 MP:70 | ATK:70 DEX:48 | AGI:0.14 DR:0.04 | XP:0/10 | Gold:0
-----------------------------
===== END OF PARTY =====


Are you sure you want to start the game?
yes
Starting game!
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   | P |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
a
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   | P |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
w
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   | P | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
s
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   | P |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
aa
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Invalid choice - Input again
Pick letter
a
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   | P |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
w
Battle started!
Your party walks up to the monsters
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 1) | HP: 270 | MP: 115
[1] Theron Pureheart (Lvl 1) | HP: 240 | MP: 70

=== MONSTERS ===
[0] TheScaleless (Lvl 1) | HP: 290
[1] Taltecuhtli (Lvl 1) | HP: 170

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked TheScaleless and dealt 70 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 1) | HP: 270 | MP: 115
[1] Theron Pureheart (Lvl 1) | HP: 240 | MP: 70

=== MONSTERS ===
[0] TheScaleless (Lvl 1) | HP: 220
[1] Taltecuhtli (Lvl 1) | HP: 170

Theron Pureheart: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Theron Pureheart attacked TheScaleless but missed!
TheScaleless attacked Theron Pureheart and dealt 54 damage!
Taltecuhtli attacked Theron Pureheart and dealt 28 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 1) | HP: 270 | MP: 115
[1] Theron Pureheart (Lvl 1) | HP: 173 | MP: 70

=== MONSTERS ===
[0] TheScaleless (Lvl 1) | HP: 220
[1] Taltecuhtli (Lvl 1) | HP: 170

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked TheScaleless but missed!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 1) | HP: 270 | MP: 115
[1] Theron Pureheart (Lvl 1) | HP: 173 | MP: 70

=== MONSTERS ===
[0] TheScaleless (Lvl 1) | HP: 220
[1] Taltecuhtli (Lvl 1) | HP: 170

Theron Pureheart: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Theron Pureheart attacked TheScaleless and dealt 63 damage!
TheScaleless attacked Rogan Bloodhelm and dealt 54 damage!
Taltecuhtli attacked Rogan Bloodhelm and dealt 28 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 1) | HP: 206 | MP: 115
[1] Theron Pureheart (Lvl 1) | HP: 190 | MP: 70

=== MONSTERS ===
[0] TheScaleless (Lvl 1) | HP: 157
[1] Taltecuhtli (Lvl 1) | HP: 170

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked TheScaleless and dealt 70 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 1) | HP: 206 | MP: 115
[1] Theron Pureheart (Lvl 1) | HP: 190 | MP: 70

=== MONSTERS ===
[0] TheScaleless (Lvl 1) | HP: 87
[1] Taltecuhtli (Lvl 1) | HP: 170

Theron Pureheart: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Theron Pureheart attacked TheScaleless but missed!
TheScaleless attacked Theron Pureheart and dealt 54 damage!
Taltecuhtli attacked Theron Pureheart but missed!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 1) | HP: 226 | MP: 115
[1] Theron Pureheart (Lvl 1) | HP: 149 | MP: 70

=== MONSTERS ===
[0] TheScaleless (Lvl 1) | HP: 87
[1] Taltecuhtli (Lvl 1) | HP: 170

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked TheScaleless and dealt 70 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 1) | HP: 226 | MP: 115
[1] Theron Pureheart (Lvl 1) | HP: 149 | MP: 70

=== MONSTERS ===
[0] TheScaleless (Lvl 1) | HP: 17
[1] Taltecuhtli (Lvl 1) | HP: 170

Theron Pureheart: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Theron Pureheart attacked TheScaleless and dealt 63 damage!
Rogan Bloodhelm earned 10 points
Rogan Bloodhelm has leveled up from 1 to 2

Stats before level up
Health: 226/270
Mana: 115/115
Attack: 78
Dexterity: 40
Agility: 0.14
Damage Reduction: 0.04


Stats after level up
Health: 289/289
Mana: 118/118
Attack: 83
Dexterity: 41
Agility: 0.17
Damage Reduction: 0.05

Theron Pureheart earned 10 points
Theron Pureheart has leveled up from 1 to 2

Stats before level up
Health: 149/240
Mana: 70/70
Attack: 70
Dexterity: 48
Agility: 0.14
Damage Reduction: 0.04


Stats after level up
Health: 252/252
Mana: 74/74
Attack: 75
Dexterity: 50
Agility: 0.16
Damage Reduction: 0.05

Taltecuhtli attacked Rogan Bloodhelm and dealt 28 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 2) | HP: 287 | MP: 118
[1] Theron Pureheart (Lvl 2) | HP: 252 | MP: 74

=== MONSTERS ===
[0] Taltecuhtli (Lvl 1) | HP: 170

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked Taltecuhtli and dealt 64 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 2) | HP: 287 | MP: 118
[1] Theron Pureheart (Lvl 2) | HP: 252 | MP: 74

=== MONSTERS ===
[0] Taltecuhtli (Lvl 1) | HP: 106

Theron Pureheart: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Theron Pureheart attacked Taltecuhtli and dealt 58 damage!
Taltecuhtli attacked Theron Pureheart but missed!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 2) | HP: 289 | MP: 118
[1] Theron Pureheart (Lvl 2) | HP: 252 | MP: 74

=== MONSTERS ===
[0] Taltecuhtli (Lvl 1) | HP: 48

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked Taltecuhtli and dealt 64 damage!
Rogan Bloodhelm earned 10 points
Theron Pureheart earned 10 points
All monsters defeated
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X | P |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
i
Would you like to view your heros...
Yes or No.
yes
===== PARTY INFORMATION =====
Party Slot 1:
Rogan Bloodhelm (Lvl 2) | HP:289 MP:118 | ATK:83 DEX:41 | AGI:0.17 DR:0.05 | XP:10/20 | Gold:20
-----------------------------
Party Slot 2:
Theron Pureheart (Lvl 2) | HP:252 MP:74 | ATK:75 DEX:50 | AGI:0.16 DR:0.05 | XP:10/20 | Gold:20
-----------------------------
===== END OF PARTY =====


PICK A HERO


1
Rogan Bloodhelm (Lvl 2) | HP:289 MP:118 | ATK:83 DEX:41 | AGI:0.17 DR:0.05 | XP:10/20 | Gold:20
What would you like to do?
1. View Inventory
2. View Equipped Items
3. View Hero Stats
4. Unequip an item
5. Equip an item
6. Exit
6
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X | P |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
s
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   | P |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
a
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   | P |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
a
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M | P |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
a
You have reached a market... Press f to enter or t to traval to another market
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | P |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
t
Market fast travel locations
 Copy the desired coordinates to fast travel
Coordinates: 1,1
Coordinates: 2,1
Coordinates: 2,2
Coordinates: 5,1
1 1
fast traveling to next location
You begin to see your body glow bright.
You wake up at a new location.
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | P |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
f
Welcome to the Market
Pick a hero to enter the market
Hero 1: Rogan Bloodhelm
Hero 2: Theron Pureheart
1
Current gold: 20
Choose and action
Choose an action.
1. Buy | 2. Sell | 3. Pick different hero | 4. Exit the market
1
What item would you like to buy?
[0] Bronze Sword: cost 120 gold | Required equip level : 1
[1] Iron Sword: cost 180 gold | Required equip level : 2
[2] Steel Sword: cost 250 gold | Required equip level : 3
[3] Knight’s Sword: cost 330 gold | Required equip level : 4
[4] Mythril Sword: cost 450 gold | Required equip level : 5
[5] Dragonbone Sword: cost 600 gold | Required equip level : 6
[6] Obsidian Sword: cost 800 gold | Required equip level : 7
[7] Eternal Saber: cost 1100 gold | Required equip level : 8
[8] Royal Sunblade: cost 1400 gold | Required equip level : 9
[9] Divine Sword of Dawn: cost 1800 gold | Required equip level : 10
[10] Greatsword: cost 200 gold | Required equip level : 1
[11] Steel Greatsword: cost 300 gold | Required equip level : 2
[12] Knight’s Claymore: cost 420 gold | Required equip level : 3
[13] Warrior’s Zweihander: cost 560 gold | Required equip level : 4
[14] Mythril Greatblade: cost 750 gold | Required equip level : 5
0
Hero Rogan Bloodhelm does not have enough money to buy Bronze Sword
Pick a hero to enter the market
Hero 1: Rogan Bloodhelm
Hero 2: Theron Pureheart
1
Current gold: 20
Choose and action
Choose an action.
1. Buy | 2. Sell | 3. Pick different hero | 4. Exit the market
3
Returning back to hero selection
Pick a hero to enter the market
Hero 1: Rogan Bloodhelm
Hero 2: Theron Pureheart
2
Current gold: 20
Choose and action
Choose an action.
1. Buy | 2. Sell | 3. Pick different hero | 4. Exit the market
1
What item would you like to buy?
[0] Bronze Sword: cost 120 gold | Required equip level : 1
[1] Iron Sword: cost 180 gold | Required equip level : 2
[2] Steel Sword: cost 250 gold | Required equip level : 3
[3] Knight’s Sword: cost 330 gold | Required equip level : 4
[4] Mythril Sword: cost 450 gold | Required equip level : 5
[5] Dragonbone Sword: cost 600 gold | Required equip level : 6
[6] Obsidian Sword: cost 800 gold | Required equip level : 7
[7] Eternal Saber: cost 1100 gold | Required equip level : 8
[8] Royal Sunblade: cost 1400 gold | Required equip level : 9
[9] Divine Sword of Dawn: cost 1800 gold | Required equip level : 10
[10] Greatsword: cost 200 gold | Required equip level : 1
[11] Steel Greatsword: cost 300 gold | Required equip level : 2
[12] Knight’s Claymore: cost 420 gold | Required equip level : 3
[13] Warrior’s Zweihander: cost 560 gold | Required equip level : 4
[14] Mythril Greatblade: cost 750 gold | Required equip level : 5
0
Hero Theron Pureheart does not have enough money to buy Bronze Sword
Pick a hero to enter the market
Hero 1: Rogan Bloodhelm
Hero 2: Theron Pureheart
2
Current gold: 20
Choose and action
Choose an action.
1. Buy | 2. Sell | 3. Pick different hero | 4. Exit the market
4
The market will refresh once you leave...
Leaving market...
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | P |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
d
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M | P |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
w
+---+---+---+---+---+---+---+---+
|   |   | P |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
d
Battle started!
Your party walks up to the monsters
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 2) | HP: 289 | MP: 118
[1] Theron Pureheart (Lvl 2) | HP: 252 | MP: 74

=== MONSTERS ===
[0] Alexstraszan (Lvl 2) | HP: 353
[1] TheScaleless (Lvl 2) | HP: 330

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked Alexstraszan and dealt 73 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 2) | HP: 289 | MP: 118
[1] Theron Pureheart (Lvl 2) | HP: 252 | MP: 74

=== MONSTERS ===
[0] Alexstraszan (Lvl 2) | HP: 280
[1] TheScaleless (Lvl 2) | HP: 330

Theron Pureheart: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Theron Pureheart attacked Alexstraszan and dealt 66 damage!
Alexstraszan attacked Rogan Bloodhelm but missed!
TheScaleless attacked Rogan Bloodhelm and dealt 59 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 2) | HP: 253 | MP: 118
[1] Theron Pureheart (Lvl 2) | HP: 252 | MP: 74

=== MONSTERS ===
[0] Alexstraszan (Lvl 2) | HP: 214
[1] TheScaleless (Lvl 2) | HP: 330

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked Alexstraszan and dealt 73 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 2) | HP: 253 | MP: 118
[1] Theron Pureheart (Lvl 2) | HP: 252 | MP: 74

=== MONSTERS ===
[0] Alexstraszan (Lvl 2) | HP: 141
[1] TheScaleless (Lvl 2) | HP: 330

Theron Pureheart: Pick a monster to attack
1
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Theron Pureheart attacked TheScaleless and dealt 67 damage!
Alexstraszan attacked Theron Pureheart and dealt 64 damage!
TheScaleless attacked Theron Pureheart and dealt 59 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 2) | HP: 278 | MP: 118
[1] Theron Pureheart (Lvl 2) | HP: 141 | MP: 74

=== MONSTERS ===
[0] Alexstraszan (Lvl 2) | HP: 141
[1] TheScaleless (Lvl 2) | HP: 263

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked Alexstraszan and dealt 73 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 2) | HP: 278 | MP: 118
[1] Theron Pureheart (Lvl 2) | HP: 141 | MP: 74

=== MONSTERS ===
[0] Alexstraszan (Lvl 2) | HP: 68
[1] TheScaleless (Lvl 2) | HP: 263

Theron Pureheart: Pick a monster to attack
1
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Theron Pureheart attacked TheScaleless but missed!
Alexstraszan attacked Rogan Bloodhelm and dealt 64 damage!
TheScaleless attacked Rogan Bloodhelm and dealt 59 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 2) | HP: 170 | MP: 118
[1] Theron Pureheart (Lvl 2) | HP: 155 | MP: 74

=== MONSTERS ===
[0] Alexstraszan (Lvl 2) | HP: 68
[1] TheScaleless (Lvl 2) | HP: 263

Rogan Bloodhelm: Pick a monster to attack
1
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked TheScaleless and dealt 74 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 2) | HP: 170 | MP: 118
[1] Theron Pureheart (Lvl 2) | HP: 155 | MP: 74

=== MONSTERS ===
[0] Alexstraszan (Lvl 2) | HP: 68
[1] TheScaleless (Lvl 2) | HP: 189

Theron Pureheart: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Theron Pureheart attacked Alexstraszan but missed!
Alexstraszan attacked Theron Pureheart and dealt 64 damage!
TheScaleless attacked Rogan Bloodhelm and dealt 59 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 2) | HP: 122 | MP: 118
[1] Theron Pureheart (Lvl 2) | HP: 100 | MP: 74

=== MONSTERS ===
[0] Alexstraszan (Lvl 2) | HP: 68
[1] TheScaleless (Lvl 2) | HP: 189

Rogan Bloodhelm: Pick a monster to attack
1
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked TheScaleless but missed!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 2) | HP: 122 | MP: 118
[1] Theron Pureheart (Lvl 2) | HP: 100 | MP: 74

=== MONSTERS ===
[0] Alexstraszan (Lvl 2) | HP: 68
[1] TheScaleless (Lvl 2) | HP: 189

Theron Pureheart: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Theron Pureheart attacked Alexstraszan but missed!
Alexstraszan attacked Theron Pureheart and dealt 64 damage!
TheScaleless attacked Rogan Bloodhelm but missed!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 2) | HP: 134 | MP: 118
[1] Theron Pureheart (Lvl 2) | HP: 39 | MP: 74

=== MONSTERS ===
[0] Alexstraszan (Lvl 2) | HP: 68
[1] TheScaleless (Lvl 2) | HP: 189

Rogan Bloodhelm: Pick a monster to attack
1
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked TheScaleless and dealt 74 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 2) | HP: 134 | MP: 118
[1] Theron Pureheart (Lvl 2) | HP: 39 | MP: 74

=== MONSTERS ===
[0] Alexstraszan (Lvl 2) | HP: 68
[1] TheScaleless (Lvl 2) | HP: 115

Theron Pureheart: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Theron Pureheart attacked Alexstraszan and dealt 66 damage!
Alexstraszan attacked Theron Pureheart but missed!
TheScaleless attacked Theron Pureheart and dealt 59 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 2) | HP: 147 | MP: 118

=== MONSTERS ===
[0] Alexstraszan (Lvl 2) | HP: 2
[1] TheScaleless (Lvl 2) | HP: 115

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked Alexstraszan and dealt 73 damage!
Rogan Bloodhelm earned 40 points
Rogan Bloodhelm has leveled up from 2 to 3

Stats before level up
Health: 147/289
Mana: 118/118
Attack: 83
Dexterity: 41
Agility: 0.17
Damage Reduction: 0.05


Stats after level up
Health: 309/309
Mana: 122/122
Attack: 89
Dexterity: 42
Agility: 0.2
Damage Reduction: 0.060000000000000005

Rogan Bloodhelm has leveled up from 3 to 4

Stats before level up
Health: 309/309
Mana: 122/122
Attack: 89
Dexterity: 42
Agility: 0.2
Damage Reduction: 0.060000000000000005


Stats after level up
Health: 331/331
Mana: 126/126
Attack: 95
Dexterity: 43
Agility: 0.23
Damage Reduction: 0.07

TheScaleless attacked Rogan Bloodhelm and dealt 58 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 4) | HP: 300 | MP: 126

=== MONSTERS ===
[0] TheScaleless (Lvl 2) | HP: 115

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked TheScaleless and dealt 84 damage!
TheScaleless attacked Rogan Bloodhelm and dealt 58 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 4) | HP: 266 | MP: 126

=== MONSTERS ===
[0] TheScaleless (Lvl 2) | HP: 31

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked TheScaleless but missed!
TheScaleless attacked Rogan Bloodhelm but missed!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 4) | HP: 292 | MP: 126

=== MONSTERS ===
[0] TheScaleless (Lvl 2) | HP: 31

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked TheScaleless but missed!
TheScaleless attacked Rogan Bloodhelm and dealt 58 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 4) | HP: 257 | MP: 126

=== MONSTERS ===
[0] TheScaleless (Lvl 2) | HP: 31

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked TheScaleless and dealt 84 damage!
Rogan Bloodhelm earned 40 points
Rogan Bloodhelm has leveled up from 4 to 5

Stats before level up
Health: 257/331
Mana: 126/126
Attack: 95
Dexterity: 43
Agility: 0.23
Damage Reduction: 0.07


Stats after level up
Health: 354/354
Mana: 130/130
Attack: 102
Dexterity: 44
Agility: 0.26
Damage Reduction: 0.08

All monsters defeated
+---+---+---+---+---+---+---+---+
|   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
i
Would you like to view your heros...
Yes or No.
yes
===== PARTY INFORMATION =====
Party Slot 1:
Rogan Bloodhelm (Lvl 5) | HP:354 MP:130 | ATK:102 DEX:44 | AGI:0.26 DR:0.08 | XP:0/50 | Gold:180
-----------------------------
Party Slot 2:
Theron Pureheart (Lvl 2) | HP:-20 MP:74 | ATK:75 DEX:50 | AGI:0.16 DR:0.05 | XP:10/20 | Gold:20
-----------------------------
===== END OF PARTY =====


PICK A HERO

2
Theron Pureheart (Lvl 2) | HP:-20 MP:74 | ATK:75 DEX:50 | AGI:0.16 DR:0.05 | XP:10/20 | Gold:20
What would you like to do?
1. View Inventory
2. View Equipped Items
3. View Hero Stats
4. Unequip an item
5. Equip an item
6. Exit
6
+---+---+---+---+---+---+---+---+
|   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
s
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   | P | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
s
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M | P |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
s
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | P |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
d
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
s
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   | P |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
a
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
s
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
d
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | P |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
w
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   | P |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
a
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
s
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
a
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M | P |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
s
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   | P |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
ds
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Invalid choice - Input again
Pick letter
d
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
w
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
w
Battle started!
Your party walks up to the monsters
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 5) | HP: 354 | MP: 130

=== MONSTERS ===
[0] Rakkshasass (Lvl 3) | HP: 174
[1] Ereshkigall (Lvl 3) | HP: 214

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked Rakkshasass and dealt 79 damage!
Rakkshasass attacked Rogan Bloodhelm and dealt 29 damage!
Ereshkigall attacked Rogan Bloodhelm and dealt 35 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 5) | HP: 319 | MP: 130

=== MONSTERS ===
[0] Rakkshasass (Lvl 3) | HP: 95
[1] Ereshkigall (Lvl 3) | HP: 214

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked Rakkshasass and dealt 79 damage!
Rakkshasass attacked Rogan Bloodhelm and dealt 29 damage!
Ereshkigall attacked Rogan Bloodhelm but missed!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 5) | HP: 319 | MP: 130

=== MONSTERS ===
[0] Rakkshasass (Lvl 3) | HP: 16
[1] Ereshkigall (Lvl 3) | HP: 214

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked Rakkshasass and dealt 79 damage!
Rogan Bloodhelm earned 100 points
Rogan Bloodhelm has leveled up from 5 to 6

Stats before level up
Health: 319/354
Mana: 130/130
Attack: 102
Dexterity: 44
Agility: 0.26
Damage Reduction: 0.08


Stats after level up
Health: 379/379
Mana: 134/134
Attack: 109
Dexterity: 45
Agility: 0.29000000000000004
Damage Reduction: 0.09

Ereshkigall attacked Rogan Bloodhelm and dealt 35 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 6) | HP: 378 | MP: 134

=== MONSTERS ===
[0] Ereshkigall (Lvl 3) | HP: 214

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked Ereshkigall and dealt 81 damage!
Ereshkigall attacked Rogan Bloodhelm but missed!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 6) | HP: 379 | MP: 134

=== MONSTERS ===
[0] Ereshkigall (Lvl 3) | HP: 133

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked Ereshkigall and dealt 81 damage!
Ereshkigall attacked Rogan Bloodhelm and dealt 35 damage!
=== HEROES ===
[0] Rogan Bloodhelm (Lvl 6) | HP: 378 | MP: 134

=== MONSTERS ===
[0] Ereshkigall (Lvl 3) | HP: 52

Rogan Bloodhelm: Pick a monster to attack
0
Battle options
1. View battle status
2. Attack the monster
3. Use spell on monster
4. Use potion on hero
2
Rogan Bloodhelm attacked Ereshkigall and dealt 81 damage!
Rogan Bloodhelm earned 100 points
Rogan Bloodhelm has leveled up from 6 to 7

Stats before level up
Health: 378/379
Mana: 134/134
Attack: 109
Dexterity: 45
Agility: 0.29000000000000004
Damage Reduction: 0.09


Stats after level up
Health: 406/406
Mana: 138/138
Attack: 117
Dexterity: 46
Agility: 0.32000000000000006
Damage Reduction: 0.09999999999999999

Rogan Bloodhelm has leveled up from 7 to 8

Stats before level up
Health: 406/406
Mana: 138/138
Attack: 117
Dexterity: 46
Agility: 0.32000000000000006
Damage Reduction: 0.09999999999999999


Stats after level up
Health: 434/434
Mana: 142/142
Attack: 125
Dexterity: 47
Agility: 0.3500000000000001
Damage Reduction: 0.10999999999999999

All monsters defeated
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| X | M | M |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |
+---+---+---+---+---+---+---+---+
|   | X |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | M |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+

┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
Pick letter
q
Thanks for playing!
Game over

Process finished with exit code 0

```


