package World.TileTypes;

import World.Market;
import World.Tile;

public class MarketTile extends Tile {
    // need to add a market object here
    private Market market;

    public MarketTile(String name,int row, int col, Market market) {
        super(name, row, col, 'M');
        this.market = market;

    }

    public Market getMarket() {
        return market;
    }


}
