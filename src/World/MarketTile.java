package World;

public class MarketTile extends Tile {
    /*

    Since the markets need to be unique in the items they sell I'm thinking that we have 4 markets spawn
    one for weapons, armor, spells, potions - This causes some complexity if
    for example a player tries to get to certain markets and keeps running into battle they will struggle
     */
    public MarketTile(String name,int x, int y) {
        super(name, x, y);
    }
    @Override
    protected char getBaseSymbol() {
        return 'M';
    }
}
