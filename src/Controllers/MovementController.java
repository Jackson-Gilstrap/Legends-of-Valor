package Controllers;

import Enums.Direction;
import Interfaces.Positionable;
import WorldSets.MapSet;
import WorldSets.Space;

public abstract class MovementController<M extends MapSet> {

    protected final M mapSet;
    protected Positionable target;

    protected MovementController(M mapSet, Positionable target) {
        this.mapSet = mapSet;
        this.target = target;
    }

    /**
     *
     * @param direction - Direction that target is moving in
     * @return - true or false move has been successful
     */
    public abstract boolean move(Direction direction);

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
