package Utility;

import java.util.Objects;

/**
 * The Dot class is a basic class that represents a dot in a 2-D space.
 * It has x-coordinate and y-coordinate, which is compatible with the board.
 * In this project it can represent a dot on the board(e.g. Quoridor) or a position on the board.
 */
public class Position {
    private final int row;
    private final int col;
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public int getRow() {
        return this.row;
    }
    public int getCol() {
        return this.col;
    }

    public Position move(int dRow, int dCol) {
        return new Position(this.row + dRow, this.col + dCol);
    }
    
    /**
     * Check if two dots are the same
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Position)) return false;
        Position other = (Position) obj;
        return this.row == other.row && this.col == other.col;
    }

    /**
     * Override the function for set operations.
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString(){
        return "•";
    }
}
