package Controllers;


import Enums.Direction;
import Interfaces.Positionable;
import WorldSets.MapSet;
import WorldSets.Space;
import WorldSets.Spaces.MarketSpace;

public class MVHMVMTController extends MovementController {

    public MVHMVMTController(MapSet map) {
        super(map);
    }

    @Override
    protected void onBlocked(Positionable target, Space space) {
        System.out.println("There appears to be a massive rock formation blocking your path...");
        System.out.println("You imagine there is some way around it.");
    }

    @Override
    protected void onOutOfBounds(Positionable target, int row, int col) {
        System.out.println("You stop in your tracks and look over at the hazy fog with no ground in sight...");
        System.out.println("You decide that it's best not to proceed.");
    }

    @Override
    protected void onInteract(Positionable target, Space space) {
        if (space instanceof MarketSpace) {
            System.out.println("You have reached a market... Press f to enter or t to travel to another market");
        }
    }

    @Override
    protected boolean canInteract(Positionable target, Direction direction, Space space) {
        return space instanceof MarketSpace;
    }
}
