package Commands;

import Controllers.LVMovementController;

public class ClearObstacle implements Command{
    private LVMovementController controller;

    public ClearObstacle(LVMovementController movementController) {
        this.controller = movementController;
    }

    @Override
    public boolean execute() {
        return controller.clearObstacle();
    }

    @Override
    public String name() {
        return "CLEAR OBSTACLE";
    }
    
}
