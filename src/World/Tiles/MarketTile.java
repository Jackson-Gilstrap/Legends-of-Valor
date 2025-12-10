package World.Tiles;

import World.Market;
import World.Tile;

public class MarketTile extends Tile {
    private Market market;
    public MarketTile(String name,int row, int col, Market market) {
        super(name, row, col, 'M');
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
