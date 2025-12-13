/*
 * MapSet.java
 *
 * Abstract base class for all map structures in the game.
 * A MapSet defines a 2D grid (rows × cols) and provides basic
 * operations for map boundaries, rendering, and placing spaces.
 *
 * Subclasses should implement specific map layouts and rendering logic.
 *
 * Author: YourName
 * Date: 2025-12-13
 */

package WorldSets;

public abstract class MapSet {

    // Basic map dimensions
    private final int rows;
    private final int cols;
    protected Space[][] grids;

    // Constructor: set up the grid size
    public MapSet(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grids = new Space[rows][cols];
    }

    // -------------------------------
    // Public methods
    // -------------------------------

    // Check if the given position is inside the map
    public boolean inBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    // Getters for dimensions
    public int getRows() { return rows; }
    public int getCols() { return cols; }

    // -------------------------------
    // Abstract methods (to be implemented by subclasses)
    // -------------------------------

    // Build the map layout (like initializing lanes, nexuses, etc.)
    protected abstract void build();

    // Render the map into a string for display
    public abstract String render();

    // Place a Space object at a specific position
    public abstract void setSpace(int row, int col, Space space);

    // Retrieve a Space object from a specific position
    public abstract Space getSpace(int row, int col);
}
