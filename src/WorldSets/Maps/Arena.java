package World.Maps;

import World.Map;
import World.Space;
import World.Spaces.NexusSpace;
import World.Spaces.PlainSpace;
import World.Spaces.WallSpace;

import java.util.ArrayList;
import java.util.List;

public class Arena extends Map {
    private final Space[][] arena;

    public Arena(int rows, int cols) {
        super(rows, cols);
        this.arena = new Space[cols][rows];
        build();
    }

    protected void build() {
        for (int r = 0; r < getRows() ; r++) {
            for (int c = 0; c < getCols(); c++) {

                //print the Monster Nexus
                if(c == 2 || c == 5) {
                    arena[c][r] = new WallSpace("Wall", r, c);
                    continue;
                }
                if(r == 0) {
                    arena[c][r] = new NexusSpace("Nexus", r, c, NexusSpace.NexusType.MONSTER);
                    continue;
                }

                if(r == getRows() - 1) {
                    arena[c][r] = new NexusSpace("Nexus",r,c, NexusSpace.NexusType.HERO);
                    continue;
                }


                arena[c][r] = generateRandomLaneSpace(r,c);
            }

        }

    }

    private Space generateRandomLaneSpace(int r,int c) {
        //return random but test with plain
        return new PlainSpace("Plain",r,c);
    }

    public String render() {
        StringBuilder sb = new StringBuilder();
        int spaceheight = 3;

        for (int r = 0; r < getRows(); r++) {

            List<StringBuilder> rowBuilders = new ArrayList<>();
            for (int i = 0; i < spaceheight; i++) {
                rowBuilders.add(new StringBuilder());
            }

            for(int c =0; c < getCols(); c++) {
                Space space = arena[c][r];
                List<String> lines = space.renderLines();

                for (int i = 0; i < spaceheight; i++) {
                    rowBuilders.get(i).append(lines.get(i)).append(" ");

                }

            }

            for (StringBuilder line : rowBuilders) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }


    public Space getSpaceAt(int row, int col) {return arena[col][row];}
}
