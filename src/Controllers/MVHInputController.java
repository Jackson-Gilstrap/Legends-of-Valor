package Controllers;

import Enums.Direction;
import Game.GameUI;
import Game.MonstersVsHeroes;
import WorldSets.Maps.World;
import WorldSets.Market;
import WorldSets.Space;
import WorldSets.Spaces.MarketSpace;

import java.util.Scanner;

public class MVHInputController {
    private final MVHMovementController movement;
    private final GameUI ui;
    private final World worldMap;
    private final MonstersVsHeroes controller;

    public MVHInputController(GameUI ui, MonstersVsHeroes controller, World worldMap, MVHMovementController movement) {
        this.ui = ui;
        this.worldMap = worldMap;
        this.controller = controller;
        this.movement = movement;
    }

    public String getInput() {
        return ui.askOneWord("Enter Command: ").toUpperCase();
    }

    public boolean handleCommand(String command) {
        switch (command) {

            // Movement
            case "W":
                movement.move(Direction.UP);
                controller.checkForBattle();
                return false;
            case "S":
                movement.move(Direction.DOWN);
                controller.checkForBattle();
                return false;
            case "A":
                movement.move(Direction.LEFT);
                controller.checkForBattle();
                return false;
            case "D":
                movement.move(Direction.RIGHT);
                controller.checkForBattle();
                return false;

            // Market
            case "F":
                interactMarket();
                return false;

            case "T":
                handleFastTravel();
                return false;

            // Inspect
            case "I":
                controller.getHeroInfo(); //need to modify
                return false;

            // Quit
            case "Q":
                System.out.println("Thanks for playing!");
                return true;

            default:
                System.out.println("Invalid command");
                return false;
        }

    }


    private void interactMarket() {
        if(worldMap.getSpace(worldMap.getParty_row(), worldMap.getParty_col()) instanceof MarketSpace) {
            Market market = ((MarketSpace) worldMap.getSpace(worldMap.getParty_row(), worldMap.getParty_col())).getMarket();
            market.enterMarket(ui, worldMap.getPlayerParty());
        }
    }

    private void handleFastTravel() {
        if (worldMap.getSpace(worldMap.getParty_row(), worldMap.getParty_col()) instanceof MarketSpace) {
            System.out.println("Market fast travel locations\n Copy the desired coordinates to fast travel");
            for(Space market: worldMap.getMarkets()) {
                System.out.println("Coordinates: " + market.getRow() + "," + market.getCol());
            }
            Scanner scanner = new Scanner(System.in);
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            System.out.println("fast traveling to next location");
            worldMap.fastTravel(row, col);
        }
        else {
            System.out.println("Not on a market square no fast travel available");
        }
    }

    public void printValidCommands() {

        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

    }


}
