package Enums;


public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    private final int dRow;
    private final int dCol;

    Direction(int dRow, int dCol) {
        this.dRow = dRow;
        this.dCol = dCol;
    }

    public int dRow() { return dRow; }
    public int dCol() { return dCol; }

    @Override
    public String toString() {
        switch (this) {
            case UP: return "Up";
            case DOWN: return "Down";
            case LEFT: return "Left";
            case RIGHT: return "Right";
            default: throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}