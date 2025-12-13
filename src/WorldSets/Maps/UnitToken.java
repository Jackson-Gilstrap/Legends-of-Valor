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
public class UnitToken implements Positionable, HasHero, HasMonster, HasSpawnPosition {
    private final Hero hero;       // If this token is a hero, store here
    private final Monster monster; // If this token is a monster, store here
    private final int spawnRow;    // Original spawn row
    private final int spawnCol;    // Original spawn column
    private int row;               // Current row
    private int col;               // Current column

    // Constructor for a Hero token
    public UnitToken(Hero hero, int row, int col) {
        this.hero = hero;
        this.monster = null;
        this.row = row;
        this.col = col;
        this.spawnRow = row;
        this.spawnCol = col;
    }

    // Constructor for a Monster token
    public UnitToken(Monster monster, int row, int col) {
        this.hero = null;
        this.monster = monster;
        this.row = row;
        this.col = col;
        this.spawnRow = row;
        this.spawnCol = col;
    }

    // Check if this token represents a hero
    public boolean isHero() {
        return hero != null;
    }

    // Get the Hero or Monster entity
    public Hero getHero() { return hero; }
    public Monster getMonster() { return monster; }

    // Interface implementations
    @Override
    public Hero getHeroEntity() { return hero; }
    @Override
    public Monster getMonsterEntity() { return monster; }
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
