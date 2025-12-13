package Controllers;

import Enums.Direction;
import Interfaces.Positionable;
import WorldSets.MapSet;
import WorldSets.Space;
import WorldSets.Spaces.ObstacleSpace;

public abstract class MovementController {

    protected final MapSet mapSet;
    protected final Positionable target;

    protected MovementController(MapSet mapSet, Positionable target) {
        this.mapSet = mapSet;
        this.target = target;
    }

    /**
     *
     * @param direction - Direction that target is moving in
     * @return - true or false move has been successful
     */
    public abstract void move(Direction direction);

    /**
     *
     * @param row x pos of the target space
     * @param col y pos of the target space
     */
    protected abstract void onOutOfBounds(int row, int col);

    /**
     *
     * @param space The target space the target is being blocked by
     */
    protected abstract void onBlocked(Space space);


}
