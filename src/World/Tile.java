package World;

public abstract class Tile extends Space{
    private final char symbol;

    public Tile(String name, int row, int col, char symbol) {
        super(name, row, col);
        this.symbol= symbol;
    }
    public char getSymbol(){return symbol;}



    @Override
    public String toString() {
        return "This is an " + super.getName() + " tile and its x position is " + super.getRow() + " and y position is " + super.getCol();
    }

}
