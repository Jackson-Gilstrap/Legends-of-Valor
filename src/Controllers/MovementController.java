/**
 * Base movement controller that coordinates map navigation for positionable targets.
 */
package Controllers;

import Enums.Direction;
import Interfaces.Positionable;
import WorldSets.MapSet;
import WorldSets.Space;

public abstract class MovementController<M extends MapSet, P extends Positionable> {

    protected final M mapSet;
    protected P target;

    protected MovementController(M mapSet, P target) {
        this.mapSet = mapSet;
        this.target = target;
    }

    public Space getCurrentSpace() {

        return mapSet.getSpace(target.getRow(),  target.getCol());
    }



    /**
     *
     * @param direction - Direction that target is moving in
     * @return - true or false move has been successful
     */
    public abstract boolean move(Direction direction);

    
    public abstract void getHeroInfo();


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
