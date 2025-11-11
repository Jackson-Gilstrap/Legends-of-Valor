package World;

public class MarketTile extends Tile {

    public MarketTile(String name,int x, int y) {
        super(name, x, y);
    }
    @Override
    protected char getBaseSymbol() {
        return 'M';
    }
}
