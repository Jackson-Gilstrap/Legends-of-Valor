package WorldSets.Spaces;

import Entities.Hero;
import WorldSets.Space;

public class PlainSpace extends Space {
    private boolean obstacle;

    public PlainSpace(String name, int row, int col) {
        super(name, row, col);
        boolean obstacle = false;

    }

    public boolean getObstacle() {
        return obstacle;
    }

    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }

    @Override
    public boolean canEnter() {
        return !obstacle;
    }

    @Override
    public char getSymbol() {
        return ' ';
    }

    @Override
    public void onEnter(Hero h) {  
        // do nothing
    }

    @Override
    public void onLeave(Hero h) {
        // do nothing
    }
}
