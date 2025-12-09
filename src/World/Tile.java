package World;

public abstract class Tile{


    private int row;
    private int col;
    private final String name;
    private final char symbol;


    public Tile(String name, int row, int col, char symbol) {
        this.name = name;
        this.row = row;
        this.col = col;
        this.symbol= symbol;
    }
    public String getName(){return name;}
    public int getRow(){return row;}
    public int getCol(){return col;}
    public char getSymbol(){return symbol;}

    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }


    @Override
    public String toString() {
        return "This is an " + name + " tile and its x position is " + row + " and y position is " + col;
    }

}
