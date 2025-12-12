package Controllers;

import Enums.Direction;
import Game.GameUI;
import Interfaces.Positionable;

import java.util.List;

public class LVinputController {

    private final GameUI ui;
    private final LVMovementController movementController;
    private final List<? extends Positionable> heroes;

    public LVinputController(GameUI ui,
                             LVMovementController movementController,
                             List<? extends Positionable> heroes) {
        this.ui = ui;
        this.movementController = movementController;
        this.heroes = heroes;
    }

    public String getInput() {
        return ui.askOneWord("Enter Command: ").toUpperCase();
    }

    public boolean handleCommand(String command) {
        switch (command) {
            case "W":
                movementController.move(Direction.UP);
                return false;
            case "S":
                movementController.move(Direction.DOWN);
                return false;
            case "A":
                movementController.move(Direction.LEFT);
                return false;
            case "D":
                movementController.move(Direction.RIGHT);
                return false;
            case "TP":
                handleTeleport();
                return false;
            case "R":
                movementController.recallToNexus();
                return false;
            case "Q":
                System.out.println("Thanks for playing Legends of Valor!");
                return true;
            default:
                System.out.println("Invalid command.");
                return false;
        }
    }

    private void handleTeleport() {
        Positionable actingHero = movementController.getTarget();
        if (heroes == null || heroes.size() < 2) {
            System.out.println("No other heroes available to teleport to.");
            return;
        }

        System.out.println("Select a hero to teleport next to:");
        for (int i = 0; i < heroes.size(); i++) {
            Positionable hero = heroes.get(i);
            String label = (hero == actingHero) ? " (you)" : "";
            System.out.printf("%d) Hero at (%d,%d)%s%n", i + 1, hero.getRow(), hero.getCol(), label);
        }

        int selection = ui.askInt() - 1;
        if (selection < 0 || selection >= heroes.size()) {
            System.out.println("Invalid hero selection.");
            return;
        }

        Positionable targetHero = heroes.get(selection);
        if (targetHero == actingHero) {
            System.out.println("You cannot teleport to yourself.");
            return;
        }

        movementController.teleportToHero(targetHero);
    }

    public void printValidCommands() {
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃ W/A/S/D Move | TP Teleport |R Recall | Q Quit┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }
}
