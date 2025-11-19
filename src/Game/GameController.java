package Game;

import Entities.Hero;
import Entities.Warrior;
import Factories.*;
import Player.Party;
import World.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    private InputHandler inputHandler;
    private TileMap map;
    private Seeder seeder;

    private List<Hero> warriors = new ArrayList<>();
    private List<Hero> paladins = new ArrayList<>();
    private List<Hero> sorcerers = new ArrayList<>();


    public GameController(InputHandler inputHandler, TileMap map) {
        this.inputHandler = inputHandler;
        this.map = map;
        this.seeder = new Seeder(
                new WarriorFactory(),
                new PaladinFactory(),
                new SorcererFactory(),
                new DragonFactory(),
                new ExoskeletonFactory(),
                new SpiritFactory()
        );

    }

    public void loadGameData() {
        List<Hero> warrior_data = seeder.seedWarriors("src/TextFiles/warriors.txt");
        for(Hero warrior : warrior_data){
            warriors.add(warrior);
        }

        List<Hero> paladin_data = seeder.seedPaladins("src/TextFiles/Paladins.txt");
        for (Hero paladin : paladin_data){
            paladins.add(paladin);
        }
        List<Hero> sorcerer_data = seeder.seedSorcerers("src/TextFiles/Sorcerers.txt");
        for (Hero sorcerer : sorcerer_data){
            sorcerers.add(sorcerer);

        }


    }

    public void startGame() {
        loadGameData();
        // populate player party here
        showHeroMenu();
        gameLoop();
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

                handleMovement(command);
                return false;

            case "F":
//                interactMarket();
                // input later
                return false;

            case "I":
                map.getPlayerParty().getPartyInfo();;
                /*
                give user more choice here to interact with their party
                access specific hero inventory and jacket to equip and unequip


                 */
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

    private boolean handleBattleCommand(String command) {
        return true;
    }

    private void handleMovement(String cmd) {
        int[] delta = mapInputToVector(cmd);
        if(map.getTile(map.getParty_row() + delta[0], map.getParty_col() + delta[1]) instanceof BlockingTile) {
            return; // tile should be a blocking tile
        }
        map.moveParty(delta[0], delta[1]);

    }

    private void interactMarket() {
        System.out.println("Welcome to the market");
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


}
