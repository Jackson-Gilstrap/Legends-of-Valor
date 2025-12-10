package World.Tiles;

import World.Tile;


public class BlockingTile extends Tile {

    public BlockingTile(String name, int row, int col) {
        super(name, row, col,'X');
    }

    @Override
    public boolean canEnter() {
        return false;
    }
}
