package World;

public class BlockingTile extends Tile {

    public BlockingTile(String name, int x, int y) {
        super(name, x, y);
    }
    @Override
    protected char getBaseSymbol() {
        return 'X';
    }

}
