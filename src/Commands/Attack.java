package Commands;

import Controllers.LVMovementController;

public class Attack implements Command{
    private LVMovementController controller;

    public Attack(LVMovementController controller){
        this.controller = controller;
    }
    
    @Override
    public boolean execute() {
        return controller.attackMonster();
    }

    @Override
    public String name() {
        return "ATTACK";
    }
    
}
