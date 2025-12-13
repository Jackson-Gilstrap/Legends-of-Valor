/*
 * UnitToken.java
 *
 * Represents a unit on the map, which can be a Hero or a Monster.
 * Keeps track of current position, spawn position, and the associated entity.
 * Used by the Map class to manage unit placement and movement.
 *
 * Author: Ansh
 * Date: 2025-12-13
 */

package WorldSets.Maps;

import Entities.Hero;
import Entities.Monster;
import Interfaces.*;

// A map unit, can be Hero or Monster
// Stores current location, spawn location, and entity reference
public class UnitToken implements Positionable, HasSpawnPosition {
    private final Positionable occupant;
    private final int spawnRow;    // Original spawn row
    private final int spawnCol;    // Original spawn column
    private int row;               // Current row
    private int col;               // Current column

    // Constructor for a Hero token
    public UnitToken(Positionable occupant, int row, int col) {
        this.occupant = occupant;
        this.row = row;
        this.col = col;
        this.spawnRow = row;
        this.spawnCol = col;
    }

    // Interface implementations
    public Positionable getOccupant() { return occupant; }
    @Override
    public int getSpawnRow() { return spawnRow; }
    @Override
    public int getSpawnCol() { return spawnCol; }
    @Override
    public int getRow() { return row; }
    @Override
    public int getCol() { return col; }

    // Update current position
    @Override
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
