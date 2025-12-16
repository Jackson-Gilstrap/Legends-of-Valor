package Game;

import Interfaces.Positionable;
import Market.MarketVisitor;
// provides an interface for game specific differences
public interface GameContext {
    MarketVisitor getMarketVisitor();
    Positionable getPositionable();
}
