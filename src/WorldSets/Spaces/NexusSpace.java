package WorldSets.Spaces;

import Utility.Color;
import Market.Market;

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

    @Override
    protected Color colorForSpace() {
        return Color.GREEN;
    }

    @Override
    public String bgCodeForSpace() {
        return type == NexusType.HERO ? Color.CYAN.getBgAnsiCode() : Color.RED.getBgAnsiCode();
    }

}
