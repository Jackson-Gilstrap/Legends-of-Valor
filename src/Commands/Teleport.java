/**
 * Command that teleports a hero using the movement controller rules.
 */
package Commands;

import Controllers.LVMovementController;

public class Teleport implements Command {
    private final LVMovementController controller;

    public Teleport(LVMovementController movementController) {
        this.controller = movementController;
    }

    @Override
    public boolean execute() {
        return controller.teleport();
    }

    @Override
    public String name() {
        return "TELEPORT";
    }
}
