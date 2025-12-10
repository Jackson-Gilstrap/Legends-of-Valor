package World.Tiles;

import World.Tile;

public class CommonTile extends Tile {

    public CommonTile(String name, int row, int col) {
        super(name, row, col, ' ');

    }

    @Override
    public boolean canEnter() {
        return true;
    }
}
