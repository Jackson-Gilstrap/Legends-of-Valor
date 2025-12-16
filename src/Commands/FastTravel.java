/**
 * Command enabling fast travel via the movement controller.
 */
package Commands;

import Controllers.MVHMovementController;

public class FastTravel implements Command{
    private MVHMovementController controller;

    public FastTravel(MVHMovementController controller){
        this.controller = controller;
    }

    @Override
    public boolean execute() {
        return controller.handleFastTravel();
    }

    @Override
    public String name() {
        return "FAST TRAVEL";
    }
    
}
