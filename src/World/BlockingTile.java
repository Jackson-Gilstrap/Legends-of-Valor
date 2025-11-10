package World;

public class BlockingTile extends Tile {

    public BlockingTile(String name, int x, int y) {
        super(name, x, y);
    }

    public char symbolRepresentation() {
        // # to show impassable space
        return '#';
    }
}
