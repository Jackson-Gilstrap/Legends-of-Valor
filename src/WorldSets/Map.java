package World;

public abstract class Map {
    private final int rows, cols;

    public Map(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {return rows;}
    public int getCols() {return cols;}

    protected abstract void build();
    public abstract String render();

    public boolean inBounds(int row, int col){
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

}
