package World.Spaces;

import World.Space;

public class NexusSpace extends Space {
    // needs to hold state of either a Monster or Hero
    // need to hold state of a global market

    public enum NexusType {
        HERO,
        MONSTER
    }
    private NexusType type;


    /**
     *
     * @param name
     * @param row
     * @param col
     * @param type
     */
    public NexusSpace(String name, int row, int col, NexusType type) {
        super(name, row, col);
        this.type = type;
    }


    public NexusType getType() {
        return type;
    }

    @Override
    public boolean canEnter() {
        return true;
    }






}
