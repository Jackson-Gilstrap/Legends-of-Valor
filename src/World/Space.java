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
        this.hero = null;
        this.monster = null;

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
    public boolean hasHero() {
        return hero != null;
    }
    public boolean hasMonster() {
        return monster != null;
    }
    public Hero getHero() {
        return hero;
    }

    public Monster getMonster() {
        return monster;
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
                sb.append(buildMiddle(false, true, cols));
                sb.append("\n");
            }

        }
        return sb.toString();
    }

    // helpful render methods
    private String buildMiddle(boolean has_hero, boolean has_monster, int cols) {
        StringBuilder sb = new StringBuilder();

        sb.append("|");
//        String leftSpace = has_hero ? String.valueOf(hero.getSymbol()) : " ";
//        String rightSpace = has_monster ? String.valueOf(monster.getSymbol()) : " ";

        //testing
        String leftSpace = has_hero ? "H" : " ";
        String rightSpace = has_monster ? "M" : " ";



        int insideWidth = (cols - 1)* 4 -1;
        int gap =insideWidth -2;

        sb.append(leftSpace);
        for (int i = 0; i < gap; i++) {sb.append(" ");}

        sb.append(rightSpace);
        sb.append("|");

        return sb.toString();
    }



}
