package World;

public class MarketTile extends Tile {

    public MarketTile(String name, int x, int y) {
        super(name, x, y);
    }

    public char symbolRepresentation() {
        String name = getName();
        return name.toUpperCase().charAt(0);
    }
}
