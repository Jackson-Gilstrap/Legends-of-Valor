package Controllers;

import Enums.Direction;
import Game.GameUI;
import Interfaces.Positionable;

import java.util.List;

public class LVinputController {
    public enum TurnStatus {
        QUIT,
        CONSUMED,
        RETRY
    }

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

    public TurnStatus handleCommand(String command) {
        switch (command) {
            case "W":
                return movementController.attemptMove(Direction.UP) ? TurnStatus.CONSUMED : TurnStatus.RETRY;
            case "S":
                return movementController.attemptMove(Direction.DOWN) ? TurnStatus.CONSUMED : TurnStatus.RETRY;
            case "A":
                return movementController.attemptMove(Direction.LEFT) ? TurnStatus.CONSUMED : TurnStatus.RETRY;
            case "D":
                return movementController.attemptMove(Direction.RIGHT) ? TurnStatus.CONSUMED : TurnStatus.RETRY;
            case "TP":
                return handleTeleport() ? TurnStatus.CONSUMED : TurnStatus.RETRY;
            case "R":
                return movementController.recallToNexus() ? TurnStatus.CONSUMED : TurnStatus.RETRY;
            case "C":
                return handleClearObstacle() ? TurnStatus.CONSUMED : TurnStatus.RETRY;
            case "B":
                return handleBattle() ? TurnStatus.CONSUMED : TurnStatus.RETRY;
            case "Q":
                System.out.println("Thanks for playing Legends of Valor!");
                return TurnStatus.QUIT;
            default:
                System.out.println("Invalid command.");
                return TurnStatus.RETRY;
        }
    }

    private boolean handleTeleport() {
        Positionable actingHero = movementController.getTarget();
        if (heroes == null || heroes.size() < 2) {
            System.out.println("No other heroes available to teleport to.");
            return false;
        }

        System.out.println("Teleport mode: (A) Adjacent | (B) Behind");
        String mode = ui.askOneWord("Choose mode: ").toUpperCase();

        System.out.println("Select a hero to teleport next to:");
        for (int i = 0; i < heroes.size(); i++) {
            Positionable hero = heroes.get(i);
            String label = (hero == actingHero) ? " (you)" : "";
            System.out.printf("%d) Hero at (%d,%d)%s%n", i + 1, hero.getRow(), hero.getCol(), label);
        }

        int selection = ui.askInt() - 1;
        if (selection < 0 || selection >= heroes.size()) {
            System.out.println("Invalid hero selection.");
            return false;
        }

        Positionable targetHero = heroes.get(selection);
        if (targetHero == actingHero) {
            System.out.println("You cannot teleport to yourself.");
            return false;
        }

        boolean success = "B".equals(mode)
                ? movementController.teleportBehindHero(targetHero)
                : movementController.teleportToHero(targetHero);

        if (!success) {
            System.out.println("Teleport failed. Try a different mode or target.");
        }
        return success;
    }

    public void printValidCommands() {
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃ W/A/S/D Move | TP Teleport | R Recall | C Clear Obstacle | B Battle | Q Quit┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    private boolean handleClearObstacle() {
        System.out.println("Choose direction to clear obstacle (W/A/S/D):");
        String dirInput = ui.askOneWord("Direction: ").toUpperCase();
        Direction dir;
        switch (dirInput) {
            case "W":
                dir = Direction.UP;
                break;
            case "S":
                dir = Direction.DOWN;
                break;
            case "A":
                dir = Direction.LEFT;
                break;
            case "D":
                dir = Direction.RIGHT;
                break;
            default:
                System.out.println("Invalid direction.");
                return false;
        }
        return movementController.clearObstacle(dir);
    }

    private boolean handleBattle() {
        List<Positionable> candidates = movementController.monstersInRange();
        if (candidates.isEmpty()) {
            System.out.println("No monsters in battle range.");
            return false;
        }

        System.out.println("Choose a monster to battle:");
        for (int i = 0; i < candidates.size(); i++) {
            Positionable m = candidates.get(i);
            System.out.printf("%d) Monster at (%d,%d)%n", i + 1, m.getRow(), m.getCol());
        }
        int choice = ui.askInt() - 1;
        if (choice < 0 || choice >= candidates.size()) {
            System.out.println("Invalid monster selection.");
            return false;
        }

        return movementController.battleMonster(candidates.get(choice));
    }
}
