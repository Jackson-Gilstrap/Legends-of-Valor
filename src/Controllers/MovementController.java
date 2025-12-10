package Controllers;


import Enums.Direction;
import Interfaces.Positionable;
import WorldSets.MapSet;
import WorldSets.Space;
import WorldSets.Spaces.ObstacleSpace;

public abstract class MovementController {

    protected final MapSet map;

    /**
     *
     * @param map - The Map that is being used per game
     */
    protected MovementController(MapSet map) {
        this.map = map;
    }

    /**
     *
     * @param target - Some party of Heroes or a singular Hero or Monster that is Positionable
     * @param direction - Direction that target is moving in
     * @return - true or false move has been made
     */
    public final boolean move (Positionable target, Direction direction) {
        int current_row = target.getRow();
        int current_col = target.getCol();

        int target_row = current_row + direction.dRow();
        int target_col = current_col+ direction.dCol();

        if(!map.inBounds(target_row, target_col)) {
            onOutOfBounds(target,target_row,target_col);
            return false;
        }

        Space destination = map.getSpace(target_row, target_col);
        if(destination instanceof ObstacleSpace) {
            onOutOfBounds(target,target_row,target_col);
            return false;
        }


        if (!canEnter(target, direction, destination, target_row, target_col)) {
            return false;
        }

        target.setPosition(target_row, target_col);

        onEnter(target, destination, target_row, target_col);

        return true;
    }

    /**
     *
     * @param target Some party of Heroes or a singular Hero or Monster that is Positionable
     * @param direction - Direction that target is moving in
     * @param space - The space that the target wants to move to
     * @param row - x pos of the row desired
     * @param col -  y pos of the col desired
     * @return - true or false
     */
    protected boolean canEnter(Positionable target, Direction direction, Space space, int row, int col) {
        return true;
    }

    /**
     *
     * @param target Some party of Heroes or a singular Hero or Monster that is Positionable
     * @param row x pos of the target space
     * @param col y pos of the target space
     */
    protected void onOutOfBounds(Positionable target, int row, int col) {
        System.out.println("Out of Bounds");
    }

    /**
     *
     * @param target Some party of Heroes or a singular Hero or Monster that is Positionable
     * @param space The target space the target is moving to
     * @param row x pos of the target space
     * @param col y pos of the target space
     */
    protected void onBlocked(Positionable target, Space space, int row, int col) {
        System.out.println("Blocked");
    }

    /**
     *
     * @param target Some party of Heroes or a singular Hero or Monster that is Positionable
     * @param space The target space the target is moving to
     * @param row x pos of the target space
     * @param col y pos of the target space
     */
    protected void onEnter(Positionable target, Space space, int row, int col) {}



}
