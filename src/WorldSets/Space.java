package WorldSets;

import Entities.Hero;
import Entities.Monster;
import Utility.Color;

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
    private static final int INNER_WIDTH = 9;

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
    public abstract void onEnter(Hero h);
    public abstract void onLeave(Hero h);

    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }

    public void setHero(Hero hero) {
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
        String line = "+" + repeat("-", INNER_WIDTH) + "+";
        return applyColor(line);
    }
    protected String buildMiddle(boolean heroPresent, boolean monsterPresent) {
        Color heroColor = Color.PURPLE;
        Color monsterColor = Color.GREEN;
        String bg = bgCodeForSpace();

        StringBuilder sb = new StringBuilder();
        sb.append(bg);
        sb.append("| ");

        if (heroPresent) {
            sb.append(heroColor.getAnsiCode()).append("H").append(bg);
        } else {
            sb.append(" ");
        }

        sb.append("  ").append(symbol).append("  ");

        if (monsterPresent) {
            sb.append(monsterColor.getAnsiCode()).append("M").append(bg);
        } else {
            sb.append(" ");
        }

        sb.append(" |").append(Color.reset());
        return sb.toString();
    }

    public List<String> renderLines() {
        return renderLinesWithOccupants(hasHero(), hasMonster());
    }

    public List<String> renderLinesWithOccupants(boolean heroPresent, boolean monsterPresent) {
        List<String> lines = new ArrayList<>();
        lines.add(buildHorizontal());
        lines.add(buildMiddle(heroPresent || hasHero(), monsterPresent || hasMonster()));
        lines.add(buildHorizontal());
        return lines;
    }

    private String repeat(String s, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    protected String applyColor(String text) {
        return bgCodeForSpace() + text + Color.reset();
    }

    protected abstract Color colorForSpace();

    protected abstract String bgCodeForSpace();
}
