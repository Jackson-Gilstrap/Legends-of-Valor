package Game;

import Interfaces.Positionable;
import Market.MarketVisitor;

public interface GameContext {
    MarketVisitor getMarketVisitor();
    Positionable getPositionable();
}
