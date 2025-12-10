package WorldSets.Spaces;

import WorldSets.Market;
import WorldSets.Space;

public class MarketSpace extends Space {

    private Market market;
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



}


