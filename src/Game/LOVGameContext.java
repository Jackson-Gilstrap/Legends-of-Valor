package Game;

import Entities.Hero;
import Interfaces.Positionable;
import Market.MarketVisitor;

public class LOVGameContext implements GameContext {

    private Hero activeHero;

    public void setActiveHero(Hero hero) {
        this.activeHero = hero;
    }

    @Override
    public MarketVisitor getMarketVisitor() {
        return activeHero; // Hero implements MarketVisitor
    }

    @Override
    public Positionable getPositionable() {
        return activeHero;
    }
}
