package Market;

import Entities.Hero;
import Game.GameUI;

public interface MarketDisplayStrategy {

    boolean display(Market market, Hero hero, GameUI ui);

}
