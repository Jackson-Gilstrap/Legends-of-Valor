/**
 * Command to display hero information and inventory details.
 */
package Commands;

import Controllers.MovementController;

public class Info implements Command{
    private MovementController<?,?> controller;

    public Info(MovementController<?,?> controller){
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
