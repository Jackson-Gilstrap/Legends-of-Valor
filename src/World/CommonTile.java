package World;

public class CommonTile extends Tile {

    public CommonTile(String name, int x, int y) {
        super(name, x, y);
    }
    @Override
    protected char getBaseSymbol() {
        return ' ';
    }
}
