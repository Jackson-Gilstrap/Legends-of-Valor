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

import Entities.Entity;
import Interfaces.*;

// A map unit, can be Hero or Monster
// Stores current location, spawn location, and entity reference
public class UnitToken {
    private final Entity occupant;
    private final int spawnRow;    // Original spawn row
    private final int spawnCol;    // Original spawn column

    // Constructor for a Hero token
    public UnitToken(Entity occupant, int row, int col) {
        this.occupant = occupant;
        setPosition(row, col);
        this.spawnRow = row;
        this.spawnCol = col;
    }

    // Interface implementations
    public Positionable getOccupant() { return occupant; }
    public int getSpawnRow() { return spawnRow; }
    public int getSpawnCol() { return spawnCol; }
    public int getRow() { return occupant.getRow(); }
    public int getCol() { return occupant.getCol(); }

    // Update current position
    public void setPosition(int row, int col) {
        occupant.setPosition(row, col);
    }
}
