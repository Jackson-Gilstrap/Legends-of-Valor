package Controllers;

import Enums.Direction;
import Interfaces.Positionable;
import WorldSets.MapSet;
import WorldSets.Space;
import WorldSets.Spaces.ObstacleSpace;

public abstract class MovementController {

    protected final MapSet mapSet;

    protected MovementController(MapSet mapSet) {
        this.mapSet = mapSet;
    }

    /**
     *
     * @param target - Some party of Heroes or a singular Hero or Monster that is Positionable
     * @param direction - Direction that target is moving in
     * @return - true or false move has been made
     */
    public boolean move(Positionable target, Direction direction) {
        int current_row = target.getRow();
        int current_col = target.getCol();

        int target_row = current_row + direction.dRow();
        int target_col = current_col+ direction.dCol();

        if(!mapSet.inBounds(target_row, target_col)) {
            onOutOfBounds(target,target_row,target_col);
            return false;
        }

        Space destination = mapSet.getSpace(target_row, target_col);
        if(destination instanceof ObstacleSpace) {
            onBlocked(target, mapSet.getSpace(target_row, target_col));
            return false;
        }


        if (!canInteract(target, direction, destination)) {
            return false;
        }

        target.setPosition(target_row, target_col);

        onInteract(target, destination);

        return true;
    }

    /**
     *
     * @param target Some party of Heroes or a singular Hero or Monster that is Positionable
     * @param direction - Direction that target is moving in
     * @param space - The space that the target wants to move to
     * @return - true or false
     */
    protected abstract boolean canInteract(Positionable target, Direction direction, Space space);

    /**
     *
     * @param target Some party of Heroes or a singular Hero or Monster that is Positionable
     * @param row x pos of the target space
     * @param col y pos of the target space
     */
    protected abstract void onOutOfBounds(Positionable target, int row, int col);

    /**
     *
     * @param target Some party of Heroes or a singular Hero or Monster that is Positionable
     * @param space The target space the target is being blocked by
     */
    protected abstract void onBlocked(Positionable target, Space space);

    /**
     *
     * @param target Some party of Heroes or a singular Hero or Monster that is Positionable
     * @param space The target space the target is interacting with
     */
    protected abstract void onInteract(Positionable target, Space space);

}
