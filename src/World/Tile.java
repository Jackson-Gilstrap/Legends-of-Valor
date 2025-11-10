package World;

public abstract class Tile implements Positionable {

    /*
    Every tile is going to have a row and col position on the tilemap.
    a symbol to represent what kind of tile it is
    a name to give name to tile same a as symbol
    ]

    There are three types of tiles
    Common Tile - no action or battles can occur
    Market Tile - player can enter the market and buy/sell items
    Blocked Tile - A tile that blocks movement no action

     */
    private final int xPosition;
    private final int yPosition;
    private String name;

    public Tile(String name, int xPosition, int yPosition) {
        this.name = name;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public String getName() {
        return name;
    }

    public int getXPosition() {
        return xPosition;
    }
    public int getYPosition() {
        return yPosition;
    }

    @Override
    public String toString() {
        return "This is a " + name + " tile and its x position is " + xPosition + " and y position is " + yPosition;
    }

    abstract public char symbolRepresentation();
}
