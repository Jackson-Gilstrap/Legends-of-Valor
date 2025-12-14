package Commands;

import Controllers.LVMovementController;

public class Info implements Command{
    private LVMovementController controller;

    public Info(LVMovementController controller){
        this.controller = controller;
    }
    @Override
    public boolean execute() {
        controller.getHeroInfo();
        return false;
    }

    @Override
    public String name() {
        return "INFO";
    }
    
}
