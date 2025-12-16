package WorldSets.Spaces;

import Utility.Color;
import Market.Market;
//NexusSpace.java
// Represents the spawn location in LoV of heroes and monsters extends the market space
// Type of hero allows interaction with the market
public class NexusSpace extends MarketSpace {

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
