package WorldSets.Spaces;

import Entities.Hero;
import WorldSets.Market;
import WorldSets.Space;

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



}


