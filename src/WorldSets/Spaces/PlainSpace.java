package WorldSets.Spaces;

import Entities.Hero;
import Utility.Color;
import WorldSets.Space;
// PlainSpace.java
// Represents the basic space seen on a map provides no bonuses
public class PlainSpace extends Space {

    public PlainSpace(String name, int row, int col) {
        super(name, row, col);

    }

    @Override
    public boolean canEnter() {
        return true;
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

    @Override
    protected Color colorForSpace() {
        return Color.GREEN;
    }

    @Override
    public String bgCodeForSpace() {
        return Color.WHITE.getBgAnsiCode();
    }

}
