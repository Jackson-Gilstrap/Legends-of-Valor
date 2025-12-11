package WorldSets.Spaces;

import Entities.Hero;
import WorldSets.Market;
import WorldSets.Space;

public class NexusSpace extends MarketSpace {
    // needs to hold state of either a Monster or Hero
    // need to hold state of a global market

    public enum NexusType {
        HERO,
        MONSTER
    }
    private final NexusType type;


    /**
     *
     * @param name
     * @param row
     * @param col
     * @param type
     */
    public NexusSpace(String name, int row, int col, Market market, NexusType type) {
        super(name, row, col, market);
        this.type = type;
    }


    public NexusType getType() {
        return type;
    }

}
