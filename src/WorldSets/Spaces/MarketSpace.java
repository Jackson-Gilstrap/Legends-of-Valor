package WorldSets.Spaces;

import Entities.Hero;
import Utility.Color;
import Market.Market;
import WorldSets.Space;
// MarketSpace.java
// Represents a market in MVH and the base for the nexus space in LoV
// Contains a market
public class MarketSpace extends Space {

    private final Market market;
     public MarketSpace(String name, int row, int col, Market market) {
         super(name, row, col);
         this.market = market;
     }

     public Market getMarket() {
         return market;
     }

     @Override
     public boolean canEnter() {
         return true;
     }

     @Override
     public void onEnter(Hero h) {
        // do nothing
     }

     @Override
     public void onLeave(Hero h) {
        // do nothing
     }

    @Override
    protected Color colorForSpace() {
            return Color.MAGENTA;
    }

    @Override
    public String bgCodeForSpace() {
        return Color.MAGENTA.getBgAnsiCode();
    }




}


