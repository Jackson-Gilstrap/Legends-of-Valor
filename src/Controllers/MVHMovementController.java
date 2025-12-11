package Controllers;


import Enums.Direction;
import Game.GameUI;
import Interfaces.Positionable;
import WorldSets.MapSet;
import WorldSets.Space;
import WorldSets.Spaces.ObstacleSpace;

public class MVHMovementController extends MovementController {

    private final GameUI ui;
    public MVHMovementController(MapSet world_map, Positionable movable, GameUI ui) {
        super(world_map, movable);
        this.ui = ui;

    }
    @Override
    public void move(Direction direction) {
        int current_row = target.getRow();
        int current_col = target.getCol();

        int target_row = current_row + direction.dRow();
        int target_col = current_col + direction.dCol();

        if(!mapSet.inBounds(target_row, target_col)) {
            onOutOfBounds(target_row,target_col);
            return;
        }

        Space destination = mapSet.getSpace(target_row, target_col);
        if(destination instanceof ObstacleSpace) {
            onBlocked(mapSet.getSpace(target_row, target_col));
            return;
        }

        target.setPosition(target_row, target_col);

    }

    @Override
    protected void onBlocked(Space space) {
        System.out.println("There appears to be a massive rock formation blocking your path...");
        System.out.println("You imagine there is some way around it.");
    }

    @Override
    protected void onOutOfBounds(int row, int col) {
        System.out.println("You stop in your tracks and look over at the hazy fog with no ground in sight...");
        System.out.println("You decide that it's best not to proceed.");
    }




}
