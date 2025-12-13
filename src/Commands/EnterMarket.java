package Commands;

import Controllers.LVMovementController;

public class EnterMarket implements Command{
    private LVMovementController controller;

    public EnterMarket(LVMovementController controller){
        this.controller = controller;
    }

    @Override
    public boolean execute() {
        return controller.interactMarket();
    }

    @Override
    public String name() {
        return "MARKET";
    }
    
}
