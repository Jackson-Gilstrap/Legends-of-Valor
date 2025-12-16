package Market;


import WorldSets.Space;
import WorldSets.Spaces.MarketSpace;
// MarketInteractionController.java
// Provides method to interact with the market using the mktController
public class MarketInteractionController {

    private final MarketController marketController;

    public MarketInteractionController(MarketController marketController) {
        this.marketController = marketController;
    }

    public void interactMarket(Space space, MarketVisitor mv) {
        if(space instanceof MarketSpace) {
            Market market = ((MarketSpace) space).getMarket();
            mv.visit(market,marketController);
            return;

        }
        System.out.println("You are not on a market square");
    }
}
