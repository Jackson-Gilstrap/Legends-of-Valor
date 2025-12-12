package WorldSets;

import Entities.Hero;
import Entities.Monster;

import java.util.ArrayList;
import java.util.List;


public abstract class Space {
    private final String name;
    private final char symbol;
    private int row;
    private int col;
    private Hero hero;
    private Monster monster;

    protected static final int SPACE_ROWS = 3;
    protected static final int SPACE_COLS = 3;

    public Space(String name, int row, int col) {
        this.name = name;
        this.row = row;
        this.col = col;
        this.symbol = name.toUpperCase().charAt(0);

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
    public char getSymbol() {return symbol;}

    public boolean hasHero() {return hero != null;}
    public boolean hasMonster() {return monster != null;}

    public abstract boolean canEnter();

    /**
     * The effect the space will do on the hero 
     * (heroes only for now, maybe in the future we will do some effect on monsters)
     * @param h The hero on the space
     */
    public abstract void onEnter(Hero h);

    /**
     * Same as above. The effet whe leaving.
     * @param h
     */
    public abstract void onLeave(Hero h);

    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }

    public void  setHero(Hero hero) {
        this.hero = hero;
    }
    public void setMonster(Monster monster) {
        this.monster = monster;
    }




    @Override
    public String toString() {
        return String.join("\n",renderLines());
    }

    // helpful render methods

    protected String buildHorizontal() {
        StringBuilder sb = new StringBuilder();

        for (int c = 0; c < SPACE_COLS; c++) {
            sb.append(symbol);
            if (c < SPACE_COLS - 1) {sb.append(" - ");}
        }

        return sb.toString();
    }
    protected String buildMiddle() {

        StringBuilder sb = new StringBuilder();

        String leftSpace = hasHero() ? "H" : " ";
        String rightSpace = hasMonster() ? "M" : " ";

        int insideWidth = (SPACE_COLS - 1)* 4 -1;
        int gap = insideWidth - 2;

        sb.append("|");
        sb.append(leftSpace);
        for (int i = 0; i < gap; i++) {sb.append(" ");}
        sb.append(rightSpace);
        sb.append("|");

        return sb.toString();
    }




    public List<String> renderLines() {
        List<String> lines = new ArrayList<>();
        lines.add(buildHorizontal());
        lines.add(buildMiddle());
        lines.add(buildHorizontal());
        return lines;
    }


}
