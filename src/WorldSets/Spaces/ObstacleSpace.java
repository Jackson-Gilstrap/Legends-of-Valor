package WorldSets.Spaces;

import Utility.Color;
// ObstacleSpace.java
// Represents an obstacle in LOV and removed by using a turn on hero's turn
public class ObstacleSpace extends PlainSpace{

    public ObstacleSpace(String name, int row, int col) {
        super(name, row, col);

    }

    @Override
    public boolean canEnter() {
        return false;
    }

    @Override
    public char getSymbol() {
        return 'X';
    }

    @Override
    protected Color colorForSpace() {
            return Color.RED;

    }

    @Override
    public String bgCodeForSpace() {
        return Color.LIGHTRED.getBgAnsiCode();
    }

}
