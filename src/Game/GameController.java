package Game;

import Entities.Hero;
import Factories.*;
import Items.*;
import Seeders.EntitySeeder;
import World.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameController {
    private InputHandler inputHandler;
    private TileMap map;
    private EntitySeeder entitySeeder;
    private GameUI ui = new GameUI();
    private Battle battle;

    private List<Hero> warriors = new ArrayList<>();
    private List<Hero> paladins = new ArrayList<>();
    private List<Hero> sorcerers = new ArrayList<>();



    public GameController(InputHandler inputHandler, TileMap map) {
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
        for(Hero warrior : warrior_data){
            warriors.add(warrior);
        }

        List<Hero> paladin_data = entitySeeder.seedPaladins("src/TextFiles/Paladins.txt");
        for (Hero paladin : paladin_data){
            paladins.add(paladin);
        }
        List<Hero> sorcerer_data = entitySeeder.seedSorcerers("src/TextFiles/Sorcerers.txt");
        for (Hero sorcerer : sorcerer_data){
            sorcerers.add(sorcerer);

        }


    }

    public void startGame() {
        loadGameData(); // loads heros into game
        showHeroMenu(); // players choose their party
        gameLoop(); // run the game
    }

    private void showHeroMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- HERO SELECTION MENU ---");
            System.out.println("1. Show Warriors");
            System.out.println("2. Show Paladins");
            System.out.println("3. Show Sorcerers");
            System.out.println("0. Exit - Exiting this menu will start the game and you can't select anymore characters");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    addHeroToParty(warriors);
                    break;
                case "2":
                    addHeroToParty(paladins);
                    break;

                case "3":
                    addHeroToParty(sorcerers);
                    break;
                case "0" :
                {
                    System.out.println("Are you sure you want to start the game?");
                    String choice = scanner.nextLine().trim();
                    if (choice.equalsIgnoreCase("yes")) {
                    System.out.println("Starting game!");
                    return;
                    }
                    break;

                }
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
                    Item item = hero.getInventory().getItem(inventory_choice);
                    if(item instanceof Weapon) {
                        hero.getJacket().equipWeapon((Weapon) item);
                        hero.getInventory().removeItem(item);
                        System.out.println("Equipping "+ item.getName());
                        break;
                    }else if (item instanceof Armor) {
                        hero.getJacket().equipArmor((Armor) item);
                        hero.getInventory().removeItem(item);
                        System.out.println("Equipping "+ item.getName());
                        break;
                    }else if (item instanceof Spell) {
                        hero.getJacket().equipSpell((Spell) item);
                        hero.getInventory().removeItem(item);
                        System.out.println("Equipping "+ item.getName());
                        break;
                    } else if (item instanceof Potion) {
                        hero.getJacket().equipPotion((Potion) item);
                        hero.getInventory().removeItem(item);
                        System.out.println("Equipping "+ item.getName());
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
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nChoose a hero to add to your party:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1));
        }

        System.out.print("Enter choice (1 - " + list.size() + "): ");
        int input = scanner.nextInt();

        int index = input - 1;

        if (index < 0 || index >= list.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        map.getPlayerParty().addHeroToParty(list.get(index));


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
                        showHeroDetails((Hero) map.getPlayerParty().getHeroFromParty(hero_choice - 1));
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
                if (map.getTile(map.getParty_row(), map.getParty_col()) instanceof MarketTile) {
                    System.out.println("Market fast travel locations\n Copy the desired coordinates to fast travel");
                    for(Tile market: map.getMarkets()) {
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
        if(map.getTile(map.getParty_row() + delta[0], map.getParty_col() + delta[1]) instanceof BlockingTile) {
            return false; // tile should be a blocking tile
        }
        map.moveParty(delta[0], delta[1]);
        if(map.getTile(map.getParty_row() +  delta[0], map.getParty_col() + delta[1] ) instanceof CommonTile) {
            if(rollDie()) {
                Battle battle = new Battle(map.getPlayerParty());
                return battle.battle();
            }
        }
        return false;
    }

    private void interactMarket() {
        if(map.getTile(map.getParty_row(), map.getParty_col()) instanceof MarketTile) {
            Market market = ((MarketTile) map.getTile(map.getParty_row(), map.getParty_col())).getMarket();
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

    private boolean rollDie() {
        Random random = new Random();
        int die1 = random.nextInt(6);
        int die2 = random.nextInt(6);
        return die1 == die2;
    }


}
