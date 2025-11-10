package World;

public class CommonTile extends Tile {

    public CommonTile(String name, int x, int y) {
        super(name, x, y);
    }



    public char symbolRepresentation() {
        //empty string to show regular tile
        return ' ';
    }
}
