package Market;


import WorldSets.Space;
import WorldSets.Spaces.MarketSpace;

public class MarketInteractionController {

    private final MarketController marketController;

    public MarketInteractionController(MarketController marketController) {
        this.marketController = marketController;
    }

    public boolean interactMarket(Space space, MarketVisitor mv) {
        if(space instanceof MarketSpace) {
            Market market = ((MarketSpace) space).getMarket();
            mv.visit(market,marketController);
            return true;

        }
        System.out.println("You are not on a market square");
        return false;
    }
}
