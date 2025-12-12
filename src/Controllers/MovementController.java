package Controllers;

import Enums.Direction;
import Interfaces.Positionable;
import WorldSets.MapSet;
import WorldSets.Space;

/**
 * MovementController is responsible for controlling a movable object on a map.
 * It specify the rules for movement, allowing some action and denying other.
 */
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
     * Reponse to the movement that is refused.
     * @param space The target space the target is being blocked by
     */
    protected abstract void onBlocked(Space space);


}
