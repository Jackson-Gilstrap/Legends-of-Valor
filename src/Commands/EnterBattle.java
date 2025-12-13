package Commands;

import Controllers.LVMovementController;

public class EnterBattle implements Command{
    private LVMovementController controller;

    public EnterBattle(LVMovementController controller){
        this.controller = controller;
    }
    
    @Override
    public boolean execute() {
        return controller.battleMonster();
    }

    @Override
    public String name() {
        return "BATTLE";
    }
    
}
