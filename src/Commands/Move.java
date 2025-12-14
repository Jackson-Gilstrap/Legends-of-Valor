package Commands;

import Controllers.MovementController;
import Enums.Direction;

public class Move implements Command {
    private final MovementController<?,?> movementController;
    private final Direction direction;

    public Move(MovementController<?,?> controller, Direction dir) {
        this.movementController = controller;
        this.direction = dir;
    }

    @Override
    public boolean execute() {
        return movementController.move(direction);
    }

    @Override
    public String name(){
        return "MOVE "+ direction.toString().toUpperCase();
    }
}

