package Market;

public interface MarketVisitor {

    void visit(Market market, MarketController marketController);
}
