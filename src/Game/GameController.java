package Game;

import Entities.Hero;
import Factories.*;
import Items.*;
import Seeders.EntitySeeder;
import WorldSets.*;
import WorldSets.Maps.World;
import WorldSets.Spaces.MarketSpace;
import WorldSets.Spaces.ObstacleSpace;
import WorldSets.Spaces.PlainSpace;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameController {
    private InputHandler inputHandler;
    private World map;
    private EntitySeeder entitySeeder;
    private GameUI ui = new GameUI();

    private List<Hero> warriors = new ArrayList<>();
    private List<Hero> paladins = new ArrayList<>();
    private List<Hero> sorcerers = new ArrayList<>();



    public GameController(InputHandler inputHandler, World map) {
        this.inputHandler = inputHandler;
        this.map = map;
        this.entitySeeder = new EntitySeeder(
                new WarriorFactory(),
                new PaladinFactory(),
                new SorcererFactory(),
                new DragonFactory(),
                new ExoskeletonFactory(),
                new SpiritFactory(),
                new WeaponFactory(),
                new ArmorFactory()
        );

    }

    public void loadGameData() {
        List<Hero> warrior_data = entitySeeder.seedWarriors("src/TextFiles/warriors.txt");
        warriors.addAll(warrior_data);

        List<Hero> paladin_data = entitySeeder.seedPaladins("src/TextFiles/Paladins.txt");
        paladins.addAll(paladin_data);
        List<Hero> sorcerer_data = entitySeeder.seedSorcerers("src/TextFiles/Sorcerers.txt");
        sorcerers.addAll(sorcerer_data);

    }

    public void startGame() {
        loadGameData(); // loads heros into game
        introduceGame();
        showHeroMenu(); // players choose their party
        gameLoop(); // run the game
    }

    private void introduceGame() {
        try {
            System.out.println("Welcome to the world of Monsters vs Heros");
            Thread.sleep(2000);
            System.out.println();
            System.out.println("The aim of the game is to take you party of heroes and battle monsters along your journey");
            Thread.sleep(2000);
            System.out.println("On your journey you will encounter various Dragons, ExoSkeletons, and Spirits");
            Thread.sleep(2000);
            System.out.println("The goal... Survive as long as possible");
            Thread.sleep(3000);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("Good luck...");
            System.out.println();

        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted " + e.getMessage());

            Thread.currentThread().interrupt();
        }

    }



    private void showHeroMenu() {


        final int PARTY_CAPACITY = 3;
        System.out.println("MAX PARTY SIZE = "+ PARTY_CAPACITY);


        while (true) {

            if(map.getPlayerParty().getPartySize() >= PARTY_CAPACITY) {
                System.out.println("Party is full no more heroes can be added to the party\n");
                System.out.println("Starting game!...");
                System.out.println();
                return;
            }

            System.out.println("\n--- HERO SELECTION MENU ---");
            System.out.println();
            System.out.print("0. Start Game\t1. Add a warrior\t2. Add a Paladin\t3.Add a Sorcerer \nSelect: ");
            int input = ui.askInt();

            switch (input) {
                case 1:
                    addHeroToParty(warriors);
                    break;
                case 2:
                    addHeroToParty(paladins);
                    break;

                case 3:
                    addHeroToParty(sorcerers);
                    break;
                case 0 :
                    if (map.getPlayerParty().getPartySize() == 0) {
                        System.out.println("You need at least one party size");
                        break;

                    }
                    System.out.println();
                    map.getPlayerParty().getPartyInfo();
                    String choice = ui.askOneWord("Are you sure you want to start the game?");
                    if (choice.equalsIgnoreCase("yes")) {
                    System.out.println("Starting game!");
                    return;
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 0–3.");
            }

        }

    }

    private void showHeroDetails(Hero hero) {
        System.out.println(hero.toString());
        boolean exit = false;
        while (!exit) {
            System.out.println("What would you like to do?\n1. View Inventory\n2. View Equipped Items\n3. View Hero Stats\n4. Unequip an item\n5. Equip an item\n6. Exit");
            int choice = ui.askInt();
            switch (choice) {
                case 1: // view inventory
                    hero.getInventory().viewInventory();
                    break;
                case 2: // view jacket
                    hero.getJacket().viewJacket();
                    break;
                case 3:
                    hero.viewStats();
                    break;
                case 4: // unequip
                    hero.getJacket().viewJacket();
                    System.out.println("What Item would you like to unequip");
                    String slot_choice = ui.askOneWord("Choose a slot: Main / Offhand / Helmet / Chestplate / Legging / Spell / Potion ").toLowerCase();
                    switch (slot_choice) {
                        case "main":
                            if(hero.getJacket().isOccupied(hero.getJacket().getMain())) {
                                Item item = hero.getJacket().getMain().unequipFromSlot();
                                hero.getInventory().addItem(item);
                                System.out.println("Unequipping " + item.getName() + "from " + slot_choice);
                                break;
                            }
                            System.out.println("slot is unoccupied");
                            break;
                        case "offhand":
                            if(hero.getJacket().isOccupied(hero.getJacket().getOffhand())) {
                                Item item = hero.getJacket().getOffhand().unequipFromSlot();
                                hero.getInventory().addItem(item);
                                System.out.println("Unequipping " + item.getName() + "from " + slot_choice);
                                break;
                            }
                            System.out.println("slot is unoccupied");
                            break;
                        case "helmet":
                            if(hero.getJacket().isOccupied(hero.getJacket().getHelmet())) {
                                Item item = hero.getJacket().getHelmet().unequipFromSlot();
                                hero.getInventory().addItem(item);
                                System.out.println("Unequipping " + item.getName() + "from " + slot_choice);
                                break;
                            }
                            System.out.println("slot is unoccupied");
                            break;
                        case "chestplate":
                            if(hero.getJacket().isOccupied(hero.getJacket().getChestplate())) {
                                Item item = hero.getJacket().getChestplate().unequipFromSlot();
                                hero.getInventory().addItem(item);
                                System.out.println("Unequipping " + item.getName() + "from " + slot_choice);
                                break;
                            }
                            System.out.println("slot is unoccupied");
                            break;
                        case "legging":
                            if(hero.getJacket().isOccupied(hero.getJacket().getLeggings())) {
                                Item item = hero.getJacket().getLeggings().unequipFromSlot();
                                hero.getInventory().addItem(item);
                                System.out.println("Unequipping " + item.getName() + "from " + slot_choice);
                                break;
                            }
                            System.out.println("slot is unoccupied");
                            break;
                        case "spell":
                            if(hero.getJacket().isOccupied(hero.getJacket().getSpells())) {
                                Item item = hero.getJacket().getSpells().unequipFromSlot();
                                hero.getInventory().addItem(item);
                                System.out.println("Unequipping " + item.getName() + "from " + slot_choice);
                                break;

                            }
                            System.out.println("slot is unoccupied");
                            break;
                        case "potion":
                            if(hero.getJacket().isOccupied(hero.getJacket().getPotions())) {
                                Item item = hero.getJacket().getPotions().unequipFromSlot();
                                hero.getInventory().addItem(item);
                                System.out.println("Unequipping " + item.getName() + "from " + slot_choice);
                                break;
                            }
                            System.out.println("slot is unoccupied");
                            break;
                        default:
                            System.out.println("Not a valid item slot");
                            break;
                    }
                    break;
                case 5: // equip
                    hero.getInventory().viewInventory();
                    if(hero.getInventory().getInventorySize() == 0) {
                        System.out.println("No items in your inventory to equip");
                        break;
                    }
                    System.out.println("Choose an item from your inventory");
                    int inventory_choice = ui.askInt();

                    if(inventory_choice < 0 || inventory_choice >= hero.getInventory().getInventorySize()) {
                        System.out.println("Invalid choice");
                        break;
                    }
                    Item item = hero.getInventory().getItem(inventory_choice);
                    if(item instanceof Weapon) {
                        if(hero.getLevelObj().getCurrentLevel() < item.getLevel().getCurrentLevel()){
                            System.out.println("Item is not equippable yet please level up");
                            break;
                        }
                        if(hero.getJacket().equipWeapon((Weapon) item)) {
                            hero.getInventory().removeItem(item);
                            System.out.println("Equipping "+ item.getName());
                        }  else {
                            System.out.println("Failed to equip " + item.getName());
                        }
                        break;

                    }else if (item instanceof Armor) {
                        if(hero.getLevelObj().getCurrentLevel() < item.getLevel().getCurrentLevel()){
                            System.out.println("Item is not equippable yet please level up");
                            break;
                        }
                        if(hero.getJacket().equipArmor((Armor) item)) {
                            hero.getInventory().removeItem(item);
                            System.out.println("Equipping "+ item.getName());
                        } else {
                            System.out.println("Failed to equip " + item.getName());
                        }
                        break;

                    }else if (item instanceof Spell) {
                        if(hero.getLevelObj().getCurrentLevel() < item.getLevel().getCurrentLevel()){
                            System.out.println("Item is not equippable yet please level up");
                            break;
                        }
                        if(hero.getJacket().equipSpell((Spell) item)) {
                            hero.getInventory().removeItem(item);
                            System.out.println("Equipping "+ item.getName());
                        } else {
                            System.out.println("Failed to equip " + item.getName());
                        }
                        break;

                    } else if (item instanceof Potion) {
                        if(hero.getLevelObj().getCurrentLevel() < item.getLevel().getCurrentLevel()) {
                            System.out.println("Item is not equippable yet please level up");
                            break;
                        }
                        if(hero.getJacket().equipPotion((Potion) item)) {
                            hero.getInventory().removeItem(item);
                            System.out.println("Equipping "+ item.getName());
                        } else {
                            System.out.println("Failed to equip " + item.getName());
                        }
                        break;

                    }else {
                        System.out.println("Not a valid item to equip");
                        continue;
                    }
                case 6:
                   exit = true;
                   break;
               default:
                   System.out.println("Invalid choice");
                   break;
            }
        }

    }

    private void addHeroToParty(List<Hero> list)  {

        System.out.println("\nChoose a hero to add to your party:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + ". " + list.get(i));
        }
        System.out.println();

        System.out.print("Enter choice (1 - " + list.size() + "): ");
        int input = ui.askInt();
        System.out.println();

        int index = input - 1;

        if (index < 0 || index >= list.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        map.getPlayerParty().addHeroToParty(list.get(index));
        System.out.println(list.get(index).getName() + " has been added to the party");
        System.out.println();
        list.remove(index);
    }



    private void gameLoop() {
        boolean gameOver = false;

       // add method that checks entire party health if 0 end game
        while (!gameOver) {
            System.out.println(map.render());

            String command = inputHandler.getInput();

            gameOver = handleCommand(command);
        }

        System.out.println("Game over");
    }

    private boolean handleCommand(String command) {
        switch (command) {
            case "W":
            case "S":
            case "A":
            case "D":
                return handleMovement(command);

            case "F":
                interactMarket();
                return false;

            case "I":
                String choice = ui.askOneWord("Would you like to view your heros...\nYes or No.");
                switch (choice) {
                    case "Yes":
                    case "yes":
                        map.getPlayerParty().getPartyInfo();

                        System.out.println("PICK A HERO\n");
                        int hero_choice = ui.askInt();
                        if(hero_choice > map.getPlayerParty().getPartySize() || hero_choice <= 0) {
                            System.out.println("Invalid choice.");
                            break;
                        }
                        showHeroDetails(map.getPlayerParty().getHeroFromParty(hero_choice - 1));
                        break;
                    case "No":
                    case "no":
                        break;
                    default:
                        System.out.println("Not a valid choice.");
                        break;

                }

                return false;

            case "T":
                if (map.getSpace(map.getParty_row(), map.getParty_col()) instanceof MarketSpace) {
                    System.out.println("Market fast travel locations\n Copy the desired coordinates to fast travel");
                    for(Space market: map.getMarkets()) {
                        System.out.println("Coordinates: " + market.getRow() + "," + market.getCol());
                    }
                    Scanner scanner = new Scanner(System.in);
                    int row = scanner.nextInt();
                    int col = scanner.nextInt();

                    System.out.println("fast traveling to next location");
                    map.fastTravel(row, col);
                }
                else {
                    System.out.println("Not on a market square no fast travel available");
                }

                return false;

            case "Q":
                System.out.println("Thanks for playing!");
                return true;

            default:
                System.out.println("Invalid command");
                return false;
        }
    }


    private boolean handleMovement(String cmd) {
        int[] delta = mapInputToVector(cmd);

        int next_row = map.getParty_row() + delta[0];
        int next_col = map.getParty_col() + delta[1];

        if(!map.inBounds(next_row, next_col)) {
            return false;
        }

        if(map.getSpace(next_row, next_col) instanceof ObstacleSpace) {
            return false;
        }

        if(map.getSpace(next_row, next_col) instanceof PlainSpace) {
            if(rollDie(7)) {
                Battle battle = new Battle(map.getPlayerParty());
                boolean player_survived = battle.battle();
                if(!player_survived) return true; // game over

            }

        }

        map.moveParty(delta[0], delta[1]);
        return false;
    }

    private void interactMarket() {
        if(map.getSpace(map.getParty_row(), map.getParty_col()) instanceof MarketSpace) {
            Market market = ((MarketSpace) map.getSpace(map.getParty_row(), map.getParty_col())).getMarket();
            market.enterMarket(ui, map.getPlayerParty());
        }
    }

    private int[] mapInputToVector(String cmd) {
        switch (cmd) {
            case "W":
                return new int[]{-1, 0};
            case "S":
                return new int[]{1, 0};
            case "A":
                return new int[]{0, -1};
            case "D":
                return new int[]{0, 1};
            default:
                return new int[]{0, 0};
        }
    }

    private boolean rollDie(int sides) {
        Random random = new Random();
        int die1 = random.nextInt(sides);
        int die2 = random.nextInt(sides);
        return die1 == die2;
    }


}
