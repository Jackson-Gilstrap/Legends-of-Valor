package Market;

import Entities.Hero;
import Game.GameUI;
import Parties.Party;
// MarketController.java
// provides methods to choose the hero and display strategy for how a market displays itself on a map
public class MarketController {
    private final GameUI gameUI;
    private  MarketDisplayStrategy marketDisplayStrategy;

    public MarketController(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    public void setMarketDisplayStrategy(MarketDisplayStrategy marketDisplayStrategy) {
        this.marketDisplayStrategy = marketDisplayStrategy;
    }

    public boolean  displayMarket(Market market, Hero hero) {
        return marketDisplayStrategy.display(market, hero,  gameUI);
    }

//    public void enterMarket(Market market, MarketVisitor marketVisitor) {
//        System.out.println("Entering Market");
//        marketVisitor.visit(market, this);
//    }

    public Hero pickHero(Party party) {

        System.out.println("Picking a hero");
        for (int i = 0; i < party.size(); i++) {
            System.out.println((i + 1) + ". " + party.get(i).getName());
        }
        while (true) {
            int choice = gameUI.askInt();
            if(choice < 0 || choice > party.size()) {
                System.out.println( "Invalid choice");
                continue;
            }
            return party.get(choice - 1);

        }
    }


}
