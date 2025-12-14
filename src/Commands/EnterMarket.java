package Commands;

import Controllers.MovementController;

public class EnterMarket implements Command{
    private MovementController<?,?> controller;

    public EnterMarket(MovementController<?,?> controller){
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
