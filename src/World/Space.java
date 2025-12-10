package World;

import Entities.Hero;
import Entities.Monster;


public abstract class Space {
    private final String name;
    private int row;
    private int col;
    private Hero hero;
    private Monster monster;

    public Space(String name, int row, int col) {
        this.name = name;
        this.row = row;
        this.col = col;

    }

    public String getName() {
        return name;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }


    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }

    public abstract boolean canEnter();

    @Override
    public String toString() {
        int rows = 2;
        int cols = 3;
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {
                sb.append(name.toUpperCase().charAt(0));
                if (c< cols - 1) {sb.append(" - ");}
            }
           sb.append("\n");
            if (r < rows -1 ) {
                for (int c = 0; c < cols; c++) {
                    sb.append("|");
                    if (c< cols - 1) {sb.append("   ");}
                }

            }
            sb.append("\n");

        }
        return sb.toString();
    }



}
