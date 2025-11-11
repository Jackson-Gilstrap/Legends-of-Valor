package World;

import Interfaces.Positionable;

import java.util.ArrayList;
import java.util.List;

public abstract class Tile implements Positionable {

    /*
    Every tile is going to have a row and col position on the tilemap.
    a symbol to represent what kind of tile it is
    a name to give name to tile same as it's symbol


    There are three types of tiles
    Common Tile - no action or battles can occur
    Market Tile - player can enter the market and buy/sell items
    Blocked Tile - A tile that blocks movement no action

     */
    private final int xPosition;
    private final int yPosition;
    private final String name;
    private String baseSymbol;

    private final List<String> overlays = new ArrayList<>();

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
    public void addOverlay(String symbol) {
        overlays.add(symbol);
    }

    public void removeOverlay(String symbol) {
        overlays.remove(symbol);
    }

    public boolean hasOverlay(String symbol) {
        return overlays.contains(symbol);
    }

    public void clearOverlays() {
        overlays.clear();
    }


    protected abstract char getBaseSymbol();

    public final char symbolRepresentation() {
        if (!overlays.isEmpty()) {
            String top = overlays.get(overlays.size() - 1);
            return top.charAt(0);
        }
        return getBaseSymbol();
    }
    @Override
    public String toString() {
        return "This is an " + name + " tile and its x position is " + xPosition + " and y position is " + yPosition;
    }

}
