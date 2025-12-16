/**
 * Command that recalls a hero to their nexus.
 */
package Commands;

import Controllers.LVMovementController;

public class Recall implements Command{
    private LVMovementController controller;

    public Recall(LVMovementController movementController) {
        this.controller = movementController;
    }

    @Override
    public boolean execute() {
        return controller.recallToNexus();
    }
    
    @Override
    public String name(){
        return "RECALL";
    }
}
