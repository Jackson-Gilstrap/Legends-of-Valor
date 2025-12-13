package WorldSets.Spaces;

import Entities.Hero;
import Utility.Color;
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
    protected String buildMiddle(boolean heroPresent, boolean monsterPresent) {
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        for (int i = 0; i < 9; i++) {
            sb.append("X");
        }
        sb.append("|");
        return applyColor(sb.toString());
    }

    @Override
    public void onEnter(Hero h) {
        // do nothing
    }

    @Override
    public void onLeave(Hero h) {  
        // do nothing
    }


    @Override
    protected Color colorForSpace() {
        return Color.WHITE;
    }

    @Override
    public String bgCodeForSpace() {
        return Color.WHITE.getBgAnsiCode();
    }

}
