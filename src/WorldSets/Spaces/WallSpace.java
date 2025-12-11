package WorldSets.Spaces;

import Entities.Hero;
import WorldSets.Space;

public class WallSpace extends Space {
    /**
     *
     * @param name
     * @param row
     * @param col
     */
    public WallSpace(String name, int row, int col) {
        super(name, row, col);

    }

    @Override
    public boolean canEnter() {
        return false;
    }

    @Override
    protected String buildMiddle() {
        StringBuilder sb = new StringBuilder();

        sb.append("|");
        int insideWidth = (SPACE_COLS -1) * 4 -1;
        for (int i = 0; i < insideWidth; i++) {sb.append("X");}
        sb.append("|");
        return sb.toString();
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
