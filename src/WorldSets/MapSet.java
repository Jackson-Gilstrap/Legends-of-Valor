package WorldSets;

public abstract class MapSet {
    private final int rows, cols;

    public MapSet(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {return rows;}
    public int getCols() {return cols;}

    protected abstract void build();
    public abstract String render();
    public abstract Space getSpace(int row, int col);
    public abstract void setSpace(int row, int col, Space space);

    public boolean inBounds(int row, int col){
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

}
